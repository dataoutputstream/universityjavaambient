package funivia_io;

import java.util.LinkedList;

public abstract class Funivia {
	
	int tipo;
	int [] maxTipo;
	LinkedList<Thread>lista=new LinkedList<Thread>();
	
	public Funivia(int tipo) {
		this.tipo=tipo;
		maxTipo=new int[2];
		maxTipo[0]=6;
		maxTipo[1]=3;
	}
	
	
	public abstract void pilotaStart() throws InterruptedException;
	public abstract void pilotaEnd() throws InterruptedException;
	public abstract void turistaSali(int t) throws InterruptedException;
	public abstract void turistaScendi(int t) throws InterruptedException;

}
