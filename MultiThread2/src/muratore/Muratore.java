package muratore;

import java.util.concurrent.TimeUnit;

public class Muratore extends Thread{
	
	Casa c;
	int t;
	
	public Muratore(Casa c,int t) {
		this.c=c;
		this.t=t;
	}
	
	
	public void run() {
		try {
			boolean lavoro = true;
		while(lavoro) {
			//TimeUnit.MILLISECONDS.sleep(100);
			lavoro=c.inizia(t);	
			//TimeUnit.MILLISECONDS.sleep(100);
			c.termina();
		}
			
		}catch(Exception e){
			
		}
		
	}

}
