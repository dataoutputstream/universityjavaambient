package muratori;

import java.util.concurrent.TimeUnit;

public class Muratore extends Thread{
	Casa c;
	int tipo;
	int [] tempo= {500,700};
	boolean lavoro=true;
	
	public Muratore(int tipo,Casa c) {
		this.tipo=tipo;
		this.c=c;
	}
	
	public void run() {
		try {
		while(lavoro) {
		TimeUnit.MILLISECONDS.sleep(tempo[tipo]);
		boolean nuova=c.inizia(tipo);
		c.termina();
		lavoro=nuova;
		}
		}catch(InterruptedException e) {
			
		}
		
	}
}
