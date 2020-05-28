package muratori;

public abstract class Casa {
	public int pareti_rimanenti;
	public int n;
	public int nMesse;
	public int nMesseF;
	public int turno=0;
	
	public Casa(int n) {
		pareti_rimanenti=4;
		this.n=n;
		nMesse=0;
		nMesseF=n;
	}
	
	 public abstract boolean inizia(int t) throws InterruptedException;
	 
	 public abstract void termina() throws InterruptedException;
	

}
