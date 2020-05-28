package funivia;

public abstract class Funivia { // starts at 19:40. at 20:45 funiviaSem done
	
	public abstract void pilotaStart() throws InterruptedException;
	
	public abstract void pilotaEnd() throws InterruptedException;
	
	public abstract void turistaSali(int t) throws InterruptedException;
	
	public abstract void turistaScendi(int t) throws InterruptedException;
	
	public void test(){
		final int NUM_TURISTI_A_PIEDI = 18;
		final int NUM_TURISTI_IN_BICI = 9;
		
		for(int i = 0; i < NUM_TURISTI_A_PIEDI; i++ ) {
			new Thread( new Turista(this, 0) ).start();
		}
		
		for(int i = 0; i < NUM_TURISTI_IN_BICI; i++ ) {
			
			new Thread( new Turista(this, 1)).start();
		}
		
		Thread t = new Thread( new Pilota(this) );
		t.setDaemon(true);
		t.start();
	}

}
