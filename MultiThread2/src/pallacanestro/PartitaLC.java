package pallacanestro;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0, Sep 6, 2013
 */
public class PartitaLC extends Partita {

	private final Lock lock = new ReentrantLock();
	private final Condition[] squadra = new Condition[2];
	private final Random random = new Random();
	private int squadraInPossessoDellaPalla = -1;

	public PartitaLC() {
		for (int i = 0; i < squadra.length; i++) {
			squadra[i] = lock.newCondition();
		}
	}

	@Override
	public boolean riceviPalla(int s) throws InterruptedException {
		lock.lock();
		try {
			if (squadraInPossessoDellaPalla >= 0) {
				while (partitaInCorso && squadraInPossessoDellaPalla != s) {
					squadra[s].await();
				}
			}
			// Mi premunisco dallo spurious wakeup di uno dei miei compagni
			squadraInPossessoDellaPalla = 3;
		} finally {
			lock.unlock();
		}
		return partitaInCorso;
	}

	@Override
	public boolean lanciaPalla(int s, int prob) throws InterruptedException {
		lock.lock();
		try {
			if (partitaInCorso) {
				if (random.nextInt(100) < prob) {
					if (numPassaggi == 3) {
						numPassaggi = 0;
						punteggio[s]++;
					} else {
						numPassaggi++;
					}
					squadraInPossessoDellaPalla = s;
				} else {
					numPassaggi = 0;
					squadraInPossessoDellaPalla = 1 - s;
				}
				squadra[squadraInPossessoDellaPalla].signal();
			}
		} finally {
			lock.unlock();
		}
		return partitaInCorso;
	}

	@Override
	public int[] termina() {
		lock.lock();
		try {
			partitaInCorso = false;
			for (int i = 0; i < squadra.length; i++) {
				squadra[i].signalAll();
			}
		} finally {
			lock.unlock();
		}
		return punteggio;
	}

	public static void main(String[] args) throws InterruptedException {
		new PartitaLC().test();
	}

}
