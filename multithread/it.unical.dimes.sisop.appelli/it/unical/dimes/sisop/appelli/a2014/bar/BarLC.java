package it.unical.dimes.sisop.appelli.a2014.bar;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0, May 6, 2014
 */
public class BarLC extends Bar {
	private ReentrantLock lock = new ReentrantLock();
	private Condition[] postoLibero = new Condition[2];

	@SuppressWarnings("unchecked")
	private LinkedList<Thread>[] fila = new LinkedList[2];

	public BarLC() {
		for (int i = 0; i < fila.length; i++) {
			fila[i] = new LinkedList<>();
		}
		for (int i = 0; i < postoLibero.length; i++) {
			postoLibero[i] = lock.newCondition();
		}
	}

	@Override
	public int scegliEInizia() throws InterruptedException {
		int i = 0;
		lock.lock();
		try {
			if (numPostiLiberi[i] == 0) {
				if (numPostiLiberi[1 - i] > 0) {
					i = 1 - i;
				} else {
					if (fila[1].size() < fila[0].size()) {
						i = 1;
					}
					attendiInFila(i);
				}
			}
			numPostiLiberi[i]--;
		} finally {
			lock.unlock();
		}
		return i;
	}

	private void attendiInFila(int i) throws InterruptedException {
		Thread t = Thread.currentThread();
		fila[i].add(t);
		while (!mioTurno(t, i)) {
			postoLibero[i].await();
		}
		fila[i].remove(t);
	}

	private boolean mioTurno(Thread t, int i) {
		return fila[i].indexOf(t) < numPostiLiberi[i];
	}

	@Override
	public void inizia(int i) throws InterruptedException {
		lock.lock();
		try {
			if (numPostiLiberi[i] == 0) {
				attendiInFila(i);
			}
			numPostiLiberi[i]--;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void finisci(int i) throws InterruptedException {
		lock.lock();
		numPostiLiberi[i]++;
		try {
			if (fila[i].size() > 0) {
				postoLibero[i].signalAll();
			}
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int numPersone = 100;
		new BarLC().test(numPersone);
	}
}
