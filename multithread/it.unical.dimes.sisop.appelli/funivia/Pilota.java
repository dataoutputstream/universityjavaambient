package funivia;

import java.util.concurrent.TimeUnit;

public class Pilota extends Thread{
	Funivia f;
	
	public Pilota(Funivia f) {
		this.f=f;
	}
	
	public void run() {
		while(true) {
		try {
		f.pilotaStart();
		TimeUnit.SECONDS.sleep(5);
		f.pilotaEnd();
		TimeUnit.SECONDS.sleep(2);
		}catch(InterruptedException e) {
			
		}
		
		}
	}
}
