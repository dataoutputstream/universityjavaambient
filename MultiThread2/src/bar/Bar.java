package bar;

public abstract class Bar {
	
	protected int bancone=4;
	protected int cassa=1;
	
	
	public abstract int scegliInizia() throws InterruptedException;
	public abstract void inizia(int i) throws InterruptedException;
	public abstract void finisci(int i) throws InterruptedException;

}
