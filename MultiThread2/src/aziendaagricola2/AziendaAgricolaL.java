package aziendaagricola2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AziendaAgricolaL extends AziendaAgricola{
	
	Lock l=new ReentrantLock();
	Condition magazziniere=l.newCondition();
	Condition sacchiL=l.newCondition();
	
	@Override
	public void paga(int numSacchi) throws InterruptedException {
		l.lock();
		try {
			cassa+=numSacchi*costo;
			System.out.println("Il cliente "+Thread.currentThread().getId()+ "ha pagato sacchi n: "+numSacchi);
		}finally {
			l.unlock();
		}
	}

	@Override
	public void ritira() throws InterruptedException {
		l.lock();
		try {
			sacchi--;
			System.out.println("Il cliente "+Thread.currentThread().getId()+ "ha ritirato un sacco ");
			if(sacchi==0) {magazziniere.signal();}
			while(sacchi==0) {
			sacchiL.await();
			}
		}finally {
			l.unlock();
		}
		
	}

	@Override
	public void carica() throws InterruptedException {
		l.lock();
		try {
			while(sacchi>0) {
				magazziniere.await();
			}
			System.out.println("Il magazziniere sta caricando i sacchi");
			sacchi=sacchiIniziali;
			sacchiL.signalAll();
		}finally {
			l.unlock();
		}
		
	}
	
	public static void main(String...args) {
		AziendaAgricola a=new AziendaAgricolaL();
		Magazzinere m=new Magazzinere(a);
		m.start();
		for(int i=0;i<100;i++) {
			Cliente x=new Cliente(a);
			x.start();
		}
	}

}
