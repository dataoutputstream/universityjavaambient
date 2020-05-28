package aziendaagricola2;

import java.util.Random;

public class Cliente extends Thread{
	
	AziendaAgricola a;
	int sacchi;
	Random r=new Random();
	
	public Cliente(AziendaAgricola a) {
		this.a=a;
	}
	
	public void run() {
		
		try {
			sacchi=scegli();
			a.paga(sacchi);
			while(sacchi!=0) {
				a.ritira();
				sacchi--;
			}
			
		}catch(InterruptedException e) {
			
		}
		
	}

	private int scegli() {
		
		return r.nextInt(10-1+1)+1;
	}

}
