package it.unical.sisop.visualizzatore;

import java.util.Random;

class Visualizzatore extends Thread {

	private Coda coda;
	private Random random = new Random();
	  
	private int MAX_ATTESA = 1000;

	public Visualizzatore(Coda coda) {
		this.coda = coda;
	}

	public void run() {
		while (true) {
			long attesaCasuale = random.nextInt(MAX_ATTESA); // numero tra 0 e 99
			try {
				sleep(attesaCasuale); // attesa casuale tra 0 e 99 ms
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			String stringaPrelevata = coda.preleva();
			System.out.println("Visualizzatore ha prelevato: " + stringaPrelevata);
		}
	}

}