package funivia;

import java.util.concurrent.TimeUnit;

public class Pilota implements Runnable{
	
	private Funivia funivia;

	private int numViaggiDaFare;
	
	public Pilota(Funivia f) {
		funivia = f;
		
	}
	
	
	public void run() {
		try {
			while(true) {
				funivia.pilotaStart();
				attendi(2);
				funivia.pilotaEnd();
				attendi(2);
			}
		}catch(InterruptedException e) {}
		
	}
	
	private void attendi(int waiting_time) throws InterruptedException{
		TimeUnit.SECONDS.sleep(waiting_time);
	}

}
