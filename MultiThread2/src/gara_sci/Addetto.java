package gara_sci;

import java.util.concurrent.TimeUnit;

public class Addetto extends Thread{
	
	Gara g;
	private final int WAIT=200;
	
	public Addetto(Gara g) {
		this.g=g;
	}

	public void run() {
		try {
			while(g.prossimo())
				TimeUnit.MILLISECONDS.sleep(WAIT);
			
		}catch(InterruptedException e) {
			
		}
	}

}
