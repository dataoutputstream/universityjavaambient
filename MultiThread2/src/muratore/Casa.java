package muratore;

public abstract class Casa {
	
	protected int pareti=4;
	protected int turno=0;
	protected int n;
	protected int nMod;
	protected int file=0;
	
	public abstract boolean inizia(int t)throws InterruptedException;
	public abstract void termina()throws InterruptedException;


}
