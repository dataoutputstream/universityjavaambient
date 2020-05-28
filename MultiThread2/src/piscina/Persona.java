package piscina;

import java.util.concurrent.TimeUnit;

public class Persona extends Thread {
	private Piscina p;
	private boolean finito=false;
	int i;
	public Persona(Piscina p) {
		this.p=p;
	}
	public void set(boolean finito) {
		this.finito=finito;
	}
	public void run() {
		try {
			while(true) {
			i = p.scegli();
			System.out.println("La persona "+this.getId()+ "ha scelto la corsia "+i);
			p.entra();
			System.out.println("La persona "+this.getId()+"sta nuotando");
			TimeUnit.MILLISECONDS.sleep(1000);
			if(!finito) {
			p.esci();
			}p.escif();
			break;
			}
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
		
	}
	
	

}
