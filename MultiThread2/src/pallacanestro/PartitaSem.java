package pallacanestro;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0, Sep 6, 2013
 */
public class PartitaSem extends Partita {

	private final Semaphore[] squadra = new Semaphore[2];
	private final Semaphore mutex = new Semaphore(1);
	private final Random random = new Random();

	public PartitaSem() {
		int squadraIniziale = random.nextInt(2);
		squadra[0] = new Semaphore(squadraIniziale);
		squadra[1] = new Semaphore(1 - squadraIniziale);
	}

	@Override
	public boolean riceviPalla(int s) throws InterruptedException {
		squadra[s].acquire();
		return partitaInCorso;
	}

	@Override
	public boolean lanciaPalla(int s, int prob) throws InterruptedException {
		mutex.acquire();
		if (partitaInCorso) {
			if (random.nextInt(100) < prob) {
				if (numPassaggi == 3) {
					numPassaggi = 0;
					punteggio[s]++;
				} else {
					numPassaggi++;
				}
				squadra[s].release();
			} else {
				numPassaggi = 0;
				squadra[1 - s].release();
			}
		}
		mutex.release();
		return partitaInCorso;
	}

	@Override
	public int[] termina() throws InterruptedException {
		mutex.acquire();
		partitaInCorso = false;
		mutex.release();
		for (int i = 0; i < squadra.length; i++) {
			squadra[i].release(5);
		}
		return punteggio;
	}

	public static void main(String[] args) throws InterruptedException {
		new PartitaSem().test();
	}

}
