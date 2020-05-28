package piscina;

import java.util.concurrent.TimeUnit;

public class Istruttore extends Thread {
	Piscina p;
	
	public Istruttore(Piscina p) {
		this.p=p;
	}
	
	
	
	public void run() {
		try {
		p.apri();
		TimeUnit.SECONDS.sleep(10);
		System.out.println("La piscina è chiusa, riapre fra un'ora");
		p.chiudi();
		TimeUnit.SECONDS.sleep(4);
		p.apri();
		System.out.println("La piscina apre per il turno pomediriano");
		TimeUnit.SECONDS.sleep(13);
		p.chiudi();
		System.out.println("La piscina è ASTUTATA");
		
		
		
		
		}catch(InterruptedException e) {
			
		}
		
	}

}
