package it.unical.dimes.sisop.appelli.a2014.bar;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0, May 6, 2014
 */
public class Persona implements Runnable {

	private static final int[] MIN_ATTESA = { 5, 20 };
	private static final int[] MAX_ATTESA = { 10, 40 };

	private Bar bar;
	private Random r = new Random();

	public Persona(Bar bar) {
		this.bar = bar;
	}

	@Override
	public void run() {
		try {
			int operazione = bar.scegliEInizia();
			attendi(MIN_ATTESA[operazione], MAX_ATTESA[operazione]);
			bar.finisci(operazione);
			operazione = 1 - operazione;

			bar.inizia(operazione);
			attendi(MIN_ATTESA[operazione], MAX_ATTESA[operazione]);
			bar.finisci(operazione);
		} catch (InterruptedException e) {
		}

	}

	public void attendi(int min, int max) throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt(max - min + 1) + min);
	}
}
