package aziendaagricola2;

public class Magazzinere extends Thread{
	
	AziendaAgricola a;
	
	public Magazzinere(AziendaAgricola a) {
		this.a=a;
	}
	
	public void run() {
		try {
			while(true) {
				a.carica();
			}
		}catch(InterruptedException e) {
			
		}
		
		
		
	}

}
