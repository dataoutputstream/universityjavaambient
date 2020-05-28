package supermercato;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasseL extends Casse{
	
	Lock l=new ReentrantLock();
	Condition Coda=l.newCondition();
	Condition [] Uscita=new Condition[ncasse];
	Boolean[]UscitaConsentita=new Boolean[ncasse];
	Condition [] Prodotti=new Condition[ncasse];
	
	Boolean[] ProdottiSegnalati= new Boolean[ncasse];
	LinkedList<Long>CodaADT=new LinkedList<>();

	public CasseL(int ncasse) {
		super(ncasse);
		for(int i=0;i<ncasse;i++) {
			Uscita[i]=l.newCondition();
			Prodotti[i]=l.newCondition();
			UscitaConsentita[i]=false;
			ProdottiSegnalati[i]=false;
		}
	}

	@Override
	public Integer gedIdCassa() throws InterruptedException {
		
		l.lock();
		if(!CodaADT.contains(Thread.currentThread().getId()))CodaADT.add( Thread.currentThread().getId());
		try {
			if(Monitor.size()==0) {
				return null;
			}else {
					while(!mioturno(Thread.currentThread())) {
					Coda.await();
					}
					
					CodaADT.remove(Thread.currentThread().getId());
					Cassiere cassiere=Monitor.pollFirst();
					return cassiere.id;
				}
		}finally{
			l.unlock();
		}
	}

	private boolean mioturno(Thread t) {
		return CodaADT.indexOf(t)<Monitor.size();
	}

	@Override
	public void consegnaProdotti(int d, int p) throws InterruptedException {
		l.lock();
		try {
			nProdotti[d]=p;
			ProdottiSegnalati[d]=true;
			Prodotti[d].signal();
			while(!UscitaConsentita[d]) {
			Uscita[d].await();
			}
		}finally {
			l.unlock();
		}

		
	}

	

	@Override
	public int segnalaCassaLibera(int id) throws InterruptedException {
		l.lock();
		try {
			Monitor.add((Cassiere)Thread.currentThread());
			Coda.signalAll();
			while(!ProdottiSegnalati[id]) {
			Prodotti[id].await();
			}
			return nProdotti[id];
		}finally {
			l.unlock();
		}
		
	}

	@Override
	public void congedaCliente(int id) throws InterruptedException {
		l.lock();
		try {
		UscitaConsentita[id]=true;
		Uscita[id].signal();
		}finally {
			l.unlock();
		}
	}
	public static void main(String...args) {
		Casse c=new CasseL(10);
		for(int i=0;i<10;i++) {
			Cassiere x=new Cassiere(i,c);
			x.start();
		}
		for(int i=0;i<100;i++) {
			Cliente cl=new Cliente(c);
			cl.start();
		}
	}

}
