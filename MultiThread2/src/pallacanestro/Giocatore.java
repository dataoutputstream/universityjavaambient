package pallacanestro;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0, Sep 6, 2013
 */
public class Giocatore implements Runnable {

	private static final int MIN_ATTESA = 1;
	private static final int MAX_ATTESA = 5;
	private static final int MIN_PROB = 30;
	private static final int MAX_PROB = 60;

	private final Partita partita;
	private final int idSquadra;
	private final Random random = new Random();
	private final int probabilita = random.nextInt(MAX_PROB - MIN_PROB + 1)
			+ MIN_PROB;

	public Giocatore(Partita p, int s) {
		partita = p;
		idSquadra = s;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (!partita.riceviPalla(idSquadra)) {
					break;
				}
				palleggia();
				if (!partita.lanciaPalla(idSquadra, probabilita)) {
					break;
				}
			}
		} catch (InterruptedException e) {
		}
		System.out.println("Il giocatore " + Thread.currentThread().getId()
				+ ", della squadra " + idSquadra + ", con probabilità "
				+ probabilita + ", ha terminato la sua esecuzione.");
	}

	private void palleggia() throws InterruptedException {
		/*
		 * L'ordine di grandezza dei tempi è stato ridotto rispetto a quello
		 * richiesto dalla traccia per permettere l'esame dell'output al
		 * calcolatore in tempi ragionevoli.
		 */
		TimeUnit.MILLISECONDS.sleep(random.nextInt(MAX_ATTESA - MIN_ATTESA + 1)
				+ MIN_ATTESA);
	}
}
