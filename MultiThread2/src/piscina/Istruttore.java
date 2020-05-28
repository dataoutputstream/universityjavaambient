package piscina;

import java.util.concurrent.TimeUnit;

public class Istruttore extends Thread {
	private Piscina p;
	
	public Istruttore(Piscina p) {
		this.p=p;
	}

	public void run() {
		while(true) {
			try {
				p.apri();
				System.out.println("Piscina aperta");
				TimeUnit.SECONDS.sleep(4);
				p.chiudi();
				System.out.println("Piscina chiusa");
				TimeUnit.SECONDS.sleep(1);
				p.apri();
				System.out.println("Piscina aperta");
				TimeUnit.SECONDS.sleep(5);
				p.chiudi();
				System.out.println("Piscina chiusa");
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}

}
