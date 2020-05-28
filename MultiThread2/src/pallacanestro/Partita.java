package pallacanestro;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0, Sep 6, 2013
 */
public abstract class Partita {

	private static final int NUM_GIOCATORI = 10;

	protected boolean partitaInCorso = true;
	protected int numPassaggi = 0;
	protected int[] punteggio = { 0, 0 };

	public abstract boolean riceviPalla(int squadra)
			throws InterruptedException;

	public abstract boolean lanciaPalla(int squadra, int prob)
			throws InterruptedException;

	public abstract int[] termina() throws InterruptedException;

	protected void test() throws InterruptedException {
		for (int i = 0; i < NUM_GIOCATORI; i++) {
			new Thread(new Giocatore(this, i % 2)).start();
		}
		new Thread(new Arbitro(this)).start();
	}
}
