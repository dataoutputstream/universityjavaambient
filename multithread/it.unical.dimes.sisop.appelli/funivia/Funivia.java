package funivia;

import java.util.LinkedList;

public abstract class Funivia {
	LinkedList<Turista>l;
	int turno=0;
	public Funivia() {
		l=new LinkedList<Turista>();
	}
	public abstract void pilotaStart() throws InterruptedException;
	public abstract void pilotaEnd() throws InterruptedException;
	public abstract void turistaSali(int t) throws InterruptedException;
	public abstract void turistacendi(int t) throws InterruptedException; 

}
