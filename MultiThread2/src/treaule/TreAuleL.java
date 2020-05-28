package treaule;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TreAuleL extends TreAule{
	
	Lock l=new ReentrantLock();
	Condition nuovoStudente=l.newCondition();
	Condition entro=l.newCondition();
	LinkedList<Thread>lista=new LinkedList<Thread>();

	Condition uscito=l.newCondition();
	boolean possoEntrare=false;
	boolean uscitoT=false;


	@Override
	public int accedi() throws InterruptedException {
		l.lock();
		try {
		lista.add(Thread.currentThread());
		nuovoStudente.signal();
		while(!mioTurno(Thread.currentThread()) || !possoEntrare) {
		entro.await();
		}
		System.out.println("Lo studente"+((Studente)Thread.currentThread()).id+" è entrato nell'aula: "+ ((Studente)Thread.currentThread()).aulaAcceduta);
		possoEntrare=false;
		lista.remove(Thread.currentThread());
		int aula=ultimAula;
		uscitoT=true;
		uscito.signal();
		return aula;
		}finally {
			l.unlock();
		}
	}

	private boolean mioTurno(Thread t) {
		return t.getId()==lista.peek().getId();
	}

	@Override
	public void esci(int aula) throws InterruptedException {
		l.lock();
		try {
			aule[aula]++;
			System.out.println("Lo studente"+((Studente)Thread.currentThread()).id+" è uscito dall'aula: "+ ((Studente)Thread.currentThread()).aulaAcceduta);
		}finally {
			l.unlock();
		}
		
	}

	@Override
	public void chiamaStudente() throws InterruptedException {
		l.lock();
		try {
			while(lista.size()==0) {
				nuovoStudente.await();
			}
			ultimAula=aggiornaAula();
			aule[ultimAula]--;
			possoEntrare=true;
			entro.signal();
			while(!uscitoT) {
			uscito.await();
			}
			uscitoT=true;
			
		}finally {
			l.unlock();
		}
		
	}
	
	public static void main(String...Args) {
		TreAule aule=new TreAuleL();
		Docente d=new Docente(aule);
		d.start();
		for(int i=0;i<200;i++) {
			Studente s=new Studente(aule,i);
			s.start();
		}
	}

}
