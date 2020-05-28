package barMod;

public abstract class Bar {

	protected static final int CASSA = 0, BANCONE = 1;
	
	protected static final int NUM_FILE = 2;
	protected static final int[] MAX_PERSONE_FILA = { 1, 4 };
	
	protected int[] numPostiLiberi = new int[NUM_FILE];
	

	public Bar() {
		for (int i = 0; i < NUM_FILE; i++) {
			numPostiLiberi[i] = MAX_PERSONE_FILA[i];
		}
	}

	public abstract int scegli() throws InterruptedException;

	public abstract void inizia(int i) throws InterruptedException;

	public abstract void finisci(int i) throws InterruptedException;

	public void test(int numPersone) throws InterruptedException {
		for (int i = 0; i < numPersone; i++) {
			new Thread(new Persona(this),"T"+i).start();
		}
	}
}
