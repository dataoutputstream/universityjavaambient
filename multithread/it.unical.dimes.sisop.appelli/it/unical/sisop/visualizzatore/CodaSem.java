package it.unical.sisop.visualizzatore;

import java.util.concurrent.Semaphore;

public class CodaSem extends Coda {

	private Semaphore inserimento;
	private Semaphore prelievo;
	private Semaphore mutex;

	public CodaSem(int maxSize) {
		super(maxSize);
		inserimento = new Semaphore(maxSize);
		prelievo = new Semaphore(0); // inizialmente il prelievo è inibito,
										// visto che la lista è vuota
		mutex = new Semaphore(1); // per modificare la lista in mutua esclusione
	}

	public void inserisci(String stringhe[]) { // eseguito dagli Utenti
		try {
			inserimento.acquire(stringhe.length); // acquisisco tanti permessi
													// quante sono le stringhe
													// da inserire
			mutex.acquire();
			for (int i = 0; i < stringhe.length; i++) {
				lista.addLast(stringhe[i]);
			}
			mutex.release();
			prelievo.release(stringhe.length); // rilascio tanti permessi quante
												// sono le stringhe inserite
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	public String preleva() { // eseguito dal Visualizzatore
		String result = null;
		try {
			prelievo.acquire(); // acquisisco un solo permesso perchè prelevo
								// una sola stringa
			mutex.acquire();
			result = lista.removeFirst();
			mutex.release();
			inserimento.release(); // rilascio un solo permesso perchè ho
									// prelevato una sola stringa
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		return result;
	}
	
	public static void main(String[] args) {
		int maxSize = 100;
		CodaSem coda = new CodaSem(maxSize);
		
		coda.test();
	}
}