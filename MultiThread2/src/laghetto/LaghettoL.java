package laghetto;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LaghettoL extends Laghetto{
	
	Lock l=new ReentrantLock();
	Condition pesco=l.newCondition();
	Condition addetti=l.newCondition();
	
	boolean ciSonoAddetti=false;
	//similcontatore permessi semaforo
	int turniPesci=maxPesci-minPesci;
	//turni degli addetti similPermesso
	int turni;
	
	public LaghettoL(int minPesci, int maxPesci) {
		super(minPesci, maxPesci);
		
	}
	
	@Override
	public void inizia(int t) throws InterruptedException {
		l.lock();
		try {
			if(t==0) {
				if(pesci==minPesci && !rilasciatiA) {rilasciatiA=true;rilasciati=false;turni=(maxPesci-minPesci)/10;addetti.signalAll();}
				while(ciSonoAddetti || turniPesci<=0) {
				pesco.await();
				}
				turniPesci--;
				System.out.println("Il pescatore "+Thread.currentThread().getId()+" sta pescando");
				}
				if(t==1) {
					while(!ciSonoAddetti || turni<=0) {
					addetti.await();
					}
					turni--;
					System.out.println("L'addetto "+Thread.currentThread().getId()+" sta caricando i pesci");
				}
		}finally {
			l.unlock();
		}
		
	}
	@Override
	public void finisci(int t) throws InterruptedException {
		l.lock();
		try {
			if(t==0) {
				
				pesci--;
				System.out.println("Il Pescatore "+ Thread.currentThread().getId()+" ha pescato un pesce i pesci restanti sono: "+pesci);
				
			}
			if(t==1) {
				
				pesci+=10;
				System.out.println("L'addetto "+ Thread.currentThread().getId()+" ha rilasciato 10 pesci");
				if(pesci==maxPesci && !rilasciati) {rilasciatiA=false;rilasciati=true;turniPesci=maxPesci-minPesci;ciSonoAddetti=false;pesco.signalAll();}
				rilasciati=false;
				
			}
		}finally {
			l.unlock();
		}
		
		
	}
	public static void main(String...args) {
		Laghetto l=new LaghettoL(50,200);
		for(int i=0;i<5;i++) {
			Addetto a=new Addetto();
			a.setLaghetto(l);
			a.start();
		}
		for(int i=0;i<50;i++) {
			Pescatore x=new Pescatore();
			x.setLaghetto(l);
			x.start();
		}
	}

}
