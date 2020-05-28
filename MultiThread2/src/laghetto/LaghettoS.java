package laghetto;

import java.util.concurrent.Semaphore;

public class LaghettoS extends Laghetto{
	
	Semaphore pesco=new Semaphore(maxPesci-minPesci);
	Semaphore addetti=new Semaphore(0);
	Semaphore mutex=new Semaphore(1);

	public LaghettoS(int minPesci, int maxPesci) {
		super(minPesci, maxPesci);
		
	}

	@Override
	public void inizia(int t) throws InterruptedException {
		if(t==0) {
		mutex.acquire();
		if(pesci==minPesci && !rilasciatiA) {rilasciatiA=true;rilasciati=false;addetti.release((maxPesci-minPesci)/10);}
		mutex.release();
		pesco.acquire();
		System.out.println("Il pescatore "+Thread.currentThread().getId()+" sta pescando");
		}
		if(t==1) {
			addetti.acquire();
			System.out.println("L'addetto "+Thread.currentThread().getId()+" sta caricando i pesci");
		}
		
	}

	@Override
	public void finisci(int t) throws InterruptedException {
		if(t==0) {
			mutex.acquire();
			pesci--;
			System.out.println("Il Pescatore "+ Thread.currentThread().getId()+" ha pescato un pesce");
			mutex.release();
		}
		if(t==1) {
			mutex.acquire();
			pesci+=10;
			System.out.println("L'addetto "+ Thread.currentThread().getId()+" ha rilasciato 10 pesci");
			if(pesci==maxPesci && !rilasciati) {rilasciatiA=false;rilasciati=true;pesco.release(maxPesci-minPesci);}
			mutex.release();
			
		}
		
		
	}
	
	public static void main(String...args) {
		Laghetto l=new LaghettoS(50,200);
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
