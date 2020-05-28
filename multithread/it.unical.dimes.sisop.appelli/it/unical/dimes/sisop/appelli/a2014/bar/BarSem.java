package it.unical.dimes.sisop.appelli.a2014.bar;

import java.util.concurrent.Semaphore;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0, May 6, 2014
 */
public class BarSem extends Bar {
	private Semaphore mutex = new Semaphore(1);
	private Semaphore[] fila = new Semaphore[MAX_PERSONE.length];
	private int[] numPersoneInFila = new int[MAX_PERSONE.length];

	public BarSem() {
		super();
		for (int i = 0; i < fila.length; i++) {
			fila[i] = new Semaphore(0, true);
			numPersoneInFila[i] = 0;
		}
	}

	@Override
	public int scegliEInizia() throws InterruptedException {
		int i = 0;
		mutex.acquire();
		if (numPostiLiberi[i] == 0) {
			if (numPostiLiberi[1 - i] > 0) {
				i = 1 - i;
			} else {
				if (numPersoneInFila[1] < numPersoneInFila[0]) {
					i = 1;
				}
				attendiInFila(i);
			}
		}
		numPostiLiberi[i]--;
		mutex.release();
		return i;
	}

	private void attendiInFila(int i) throws InterruptedException {
		numPersoneInFila[i]++;
		mutex.release();
		fila[i].acquire();
		numPersoneInFila[i]--;
	}

	@Override
	public void inizia(int i) throws InterruptedException {
		mutex.acquire();
		if (numPostiLiberi[i] == 0) {
			attendiInFila(i);
		}
		numPostiLiberi[i]--;
		mutex.release();
	}

	@Override
	public void finisci(int i) throws InterruptedException {
		mutex.acquire();
		numPostiLiberi[i]++;
		if (numPersoneInFila[i] > 0) {
			fila[i].release();
		} else {
			mutex.release();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int numPersone = 100;
		new BarSem().test(numPersone);
	}
}
