package it.unical.sisop.appelli.aziendaagricola;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AziendaAgricolaLC extends AziendaAgricola{
	private Lock l = new ReentrantLock();
	private Condition paga = l.newCondition();
	private Condition preleva = l.newCondition();
	private Condition carica = l.newCondition();
	private LinkedList<Thread> cassa = new LinkedList<>();
	private LinkedList<Thread> magazzino = new LinkedList<>();
	
	public AziendaAgricolaLC(int sacchetti){
		super(sacchetti);
	}
	public void paga(int numSacchi) throws InterruptedException{
		l.lock();
		try{
			cassa.add(Thread.currentThread());
			while(!possoPagare()){
				paga.await();
			}
			cassa.remove();
			incasso += numSacchi * 3;
		}finally{
			l.unlock();
		}
	}//paga
	public boolean possoPagare(){
		return Thread.currentThread() == cassa.getFirst();
	}
	
	public void ritira()throws InterruptedException{
		l.lock();
		try{
			magazzino.add(Thread.currentThread());
			while(!possoPrelevare()){
				preleva.await();
			}
			magazzino.remove();
			sacchetti--;
			System.out.println("Sacchetti: "+sacchetti);
			if(sacchetti == 0)
				carica.signal();
			
			preleva.signalAll();
		}finally{
			l.unlock();
		}
	}//ritira
	
	public boolean possoPrelevare(){
		return Thread.currentThread() == magazzino.getFirst() && sacchetti > 0;
	}
	
	public void carica()throws InterruptedException{
		l.lock();
		try{
			while(sacchetti > 0){
				carica.await();
			}
			sacchetti+= sacchettiIniziali;
			System.out.println("Sacchetti: "+sacchetti);
			preleva.signal();
		}finally{
			l.unlock();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		AziendaAgricolaLC azienda = new AziendaAgricolaLC(200);
		int numClienti  = 100;
		azienda.test(numClienti);
	}
}
