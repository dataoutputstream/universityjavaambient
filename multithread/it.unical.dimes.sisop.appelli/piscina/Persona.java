package piscina;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Persona extends Thread{
	Piscina p;
	static int MIN_ATTESA=3;
	static int MAX_ATTESA=6;
	static Random r=new Random();
	
	public Persona(Piscina p) {
		this.p=p;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return false;
	}


	public void run() {
		try {
			while(p.istruttore) {
					p.scegli();
					
					p.nuota();
					break;
					}
					System.out.println("Il thread "+this.getId()+" sta nuotando ");
					
					
				
				this.sleep(20);
				System.out.println("Il thread "+this.getId()+" sta facendo la doccia");

		}catch(InterruptedException e) {
			
		}
	}


	static void attendi(int t1, int t2) throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt(t2 - t1 + 1 )+t1);
		
	}
}
