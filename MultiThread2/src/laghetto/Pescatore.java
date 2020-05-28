package laghetto;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pescatore extends Thread{
	Laghetto l;
	Random r=new Random();
	
	public void setLaghetto(Laghetto l) {
		this.l=l;
	}
	
	public void run() {
		
		while(true) {
		
			try {
				l.inizia(0);
				TimeUnit.MILLISECONDS.sleep(r.nextInt(800-200+1)+200);
				l.finisci(0);
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
		}
		
	}

}
