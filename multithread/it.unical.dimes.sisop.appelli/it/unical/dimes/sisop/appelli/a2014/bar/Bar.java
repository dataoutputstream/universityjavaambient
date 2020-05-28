package it.unical.dimes.sisop.appelli.a2014.bar;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0, May 6, 2014
 */
public abstract class Bar {

	protected static final int[] MAX_PERSONE = { 1, 4 };
	protected int[] numPostiLiberi = new int[MAX_PERSONE.length];

	public Bar() {
		for (int i = 0; i < MAX_PERSONE.length; i++) {
			numPostiLiberi[i] = MAX_PERSONE[i];
		}
	}

	public abstract int scegliEInizia() throws InterruptedException;

	public abstract void inizia(int i) throws InterruptedException;

	public abstract void finisci(int i) throws InterruptedException;

	public void test(int numPersone) throws InterruptedException {
		for (int i = 0; i < numPersone; i++) {
			new Thread(new Persona(this)).start();
		}
	}
}
