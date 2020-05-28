package cementificio;

import java.util.concurrent.TimeUnit;

public class Addetto extends Thread {
	
	Cementificio c;
	
	public Addetto(Cementificio c) {
		this.c=c;
	}
	
	
	public void run()
	{
		try {
			while(true) {
				c.iniziaRifornimento();
				attendi();
				c.terminaRifornimento();
			}
		}catch(InterruptedException e){
			
		}
	}


	private void attendi() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
