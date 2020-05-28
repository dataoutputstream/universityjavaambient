package supermercato;

import java.util.concurrent.Semaphore;

public class CasseS extends Casse{
	
	Semaphore mutex= new Semaphore(1);
	Semaphore coda=new Semaphore(1,true);
	Semaphore[] PossoUscire=new Semaphore[ncasse];
	Semaphore[] ProdottiSegnalati=new Semaphore[ncasse];
	
	
	public CasseS(int nCasse) {
		super(nCasse);
		for(int i=0;i<ncasse;i++) {
			PossoUscire[i]=new Semaphore(0);
			ProdottiSegnalati[i]=new Semaphore(0);
		}
	}

	@Override
	public Integer gedIdCassa() throws InterruptedException {
		if(Monitor.size()==0)
		return null;
		else {
			coda.acquire();
			mutex.acquire();
			Cassiere cassiere=Monitor.pollFirst();
			mutex.release();
			coda.release();
			return cassiere.id;
		}
		
	}

	@Override
	public void consegnaProdotti(int d, int p) throws InterruptedException {
		mutex.acquire();
		nProdotti[d]=p;
		mutex.release();
		ProdottiSegnalati[d].release();
		PossoUscire[d].acquire();
		
	}

	@Override
	public int segnalaCassaLibera(int id) throws InterruptedException {
		mutex.acquire();
		Monitor.add((Cassiere)Thread.currentThread());
		mutex.release();
		ProdottiSegnalati[id].acquire();
		return nProdotti[id];
	}

	@Override
	public void congedaCliente(int id) throws InterruptedException {
		PossoUscire[id].release();
		
	}
	
	public static void main(String...args) {
		Casse c=new CasseS(10);
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
