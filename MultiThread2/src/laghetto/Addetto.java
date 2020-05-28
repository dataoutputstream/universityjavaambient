package laghetto;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Addetto extends Thread{
	Laghetto l;
	Random r=new Random();
	
	public void setLaghetto(Laghetto l) {
		this.l=l;
	}
	
	
	public void run() {
		while(true) {
		try {
			
			l.inizia(1);
			TimeUnit.MILLISECONDS.sleep(r.nextInt(600-300+1)+300);
			l.finisci(1);
			TimeUnit.MILLISECONDS.sleep(3000);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		}
	}

}
