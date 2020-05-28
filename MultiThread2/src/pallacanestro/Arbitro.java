package pallacanestro;

import java.util.concurrent.TimeUnit;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0, Sep 6, 2013
 */
public class Arbitro implements Runnable {

	private static final int DURATA = 40;

	private final Partita partita;

	public Arbitro(Partita p) {
		partita = p;
	}

	@Override
	public void run() {
		try {
			System.out.println("La partita è iniziata.");
			/*
			 * L'ordine di grandezza dei tempi è stato ridotto rispetto a quello
			 * richiesto dalla traccia per permettere l'esame dell'output al
			 * calcolatore in tempi ragionevoli.
			 */
			TimeUnit.SECONDS.sleep(DURATA);
			int risultato[] = partita.termina();
			if (risultato[0] == risultato[1]) {
				System.out.println("La partita è terminata in parità, "
						+ risultato[0] + " a " + risultato[1] + ".");
			} else {
				int idSquadraVincente = risultato[0] > risultato[1] ? 0 : 1;
				System.out.println("La partita è terminata, la squadra "
						+ idSquadraVincente + " ha vinto per "
						+ risultato[idSquadraVincente] + " a "
						+ risultato[1 - idSquadraVincente] + ".");
			}
		} catch (InterruptedException e) {
		}
	}
}
