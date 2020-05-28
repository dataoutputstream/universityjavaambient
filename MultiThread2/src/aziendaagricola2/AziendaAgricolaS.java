package aziendaagricola2;

import java.util.concurrent.Semaphore;

import aziendaagricola.Magazziniere;

public class AziendaAgricolaS extends AziendaAgricola{
	
	Semaphore mutex=new Semaphore(1,true);
	Semaphore magazzino=new Semaphore(1,true);
	Semaphore sacchiS=new Semaphore(sacchiIniziali-1);
	Semaphore magazziniere=new Semaphore(0);

	@Override
	public void paga(int numSacchi) throws InterruptedException {
		mutex.acquire();
		cassa+=numSacchi*costo;
		System.out.println("Il cliente "+Thread.currentThread().getId()+ "ha pagato sacchi n: "+numSacchi);
		mutex.release();
		
	}

	@Override
	public void ritira() throws InterruptedException {
		magazzino.acquire();
		sacchi--;
		System.out.println("Il cliente "+Thread.currentThread().getId()+ "ha ritirato un sacco ");
		if(sacchi==0) {magazziniere.release();}
		sacchiS.acquire();
		magazzino.release();
		
		
	}

	@Override
	public void carica() throws InterruptedException {
		magazziniere.acquire();
		System.out.println("Il magazziniere sta caricando i sacchi");
		sacchi=sacchiIniziali;
		sacchiS.release(sacchiIniziali);
		
	}
	
	public static void main(String...args) {
		AziendaAgricola a=new AziendaAgricolaS();
		Magazzinere m=new Magazzinere(a);
		m.start();
		for(int i=0;i<100;i++) {
			Cliente x=new Cliente(a);
			x.start();
		}
	}

}
