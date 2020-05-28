package it.unical.sisop.appelli.funivia;

import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class FuniviaSemVer1 extends Funivia{
	
	private int numViaggio = - 1;
	private int postiOccupati = 0;
	
	//regola l'entrata dei turisti nella funivia
	private Semaphore permettiEntrataInBici = new Semaphore(0);
	
	private Semaphore permettiEntrataAPiedi = new Semaphore(0);
	
	//permette la mutua esclusione
	private Semaphore mutex = new Semaphore(1);
	
	//regola la partenza della funivia
	private Semaphore permettiPartenza = new Semaphore(0);
	
	//regola l'uscita dei turisti dalla funivia
	private Semaphore permettiUscitaAPiedi = new Semaphore(0);
	
	private Semaphore permettiUscitaInBici = new Semaphore(0);
	
	//permette l'avvio del metodo pilotaEnd() (e quindi della stampa).
	private Semaphore permettiStampa = new Semaphore(0);
	
	//permette di completare il metodo pilotaEnd()
	private Semaphore permettiFine = new Semaphore(0);
	
	private Semaphore permettiInizio = new Semaphore(1);
	
	private ArrayList<Long> iDTuristi = new ArrayList<>();
	
	
	
	public void pilotaStart() throws InterruptedException {
		permettiInizio.acquire();
		
		numViaggio ++;
		System.out.println("permessi di PermettiPartenza (dovrebbero essere 0), sono: "+ permettiPartenza.availablePermits() +"\n");
		if(numViaggio % 2 == 0) {
			permettiEntrataAPiedi.release(6);
		}else {
			permettiEntrataInBici.release(3);
		}
		
		//il pilota parte solo quando l'ultimo turista è salito a bordo.
		permettiPartenza.acquire();
		
		permettiStampa.release();
	}
	
	public void turistaSali(int tipo) throws InterruptedException {
		
		if(tipo == 0) {
			permettiEntrataAPiedi.acquire();
			mutex.acquire();
			postiOccupati++;
			iDTuristi.add( Thread.currentThread().getId() );
			mutex.release();
			
		}else {
			permettiEntrataInBici.acquire();
			mutex.acquire();
			postiOccupati += 2;
			iDTuristi.add( Thread.currentThread().getId() );
			mutex.release();
		}
		//Genera dei problemi acquisire e rilasciare il mutex
		mutex.acquire();
		if( postiOccupati == 6) {
			permettiPartenza.release();
		}
		mutex.release();
		
	}
	
	public void turistaScendi(int tipo) throws InterruptedException {
		
		if(tipo == 0) {
			permettiUscitaAPiedi.acquire();
			mutex.acquire();
			postiOccupati --;
			mutex.release();
		}else {
			permettiUscitaInBici.acquire();
			mutex.acquire();
			postiOccupati -= 2;
			mutex.release();
		}
		//Genera dei problemi acquisire e rilasciare il mutex
		mutex.acquire();
		if(postiOccupati == 0) {
			permettiFine.release();
		}
		mutex.release();
		
	}
	
	
	
	public void pilotaEnd() throws InterruptedException {
		
		permettiStampa.acquire();
		System.out.println("permessi di PermettiFine (dovrebbero essere 0), sono: "+ permettiFine.availablePermits());
		System.out.println("Viaggio numero: "+ (numViaggio+1) );
		System.out.print("ID turisti presenti: ");
		
		for(int i = 0; i < iDTuristi.size(); i++) {
			System.out.print(iDTuristi.get(i) + " ");
		}
		
		System.out.println("\n");
		
		if(numViaggio % 2 == 0) {
			permettiUscitaAPiedi.release(6);
		}else {
			permettiUscitaInBici.release(3);
		}
		
		permettiFine.acquire();
		iDTuristi.clear();
		permettiInizio.release();
		
	}
	

	public static void main(String[] args) {
		Funivia fun = new FuniviaSemVer1();
		fun.test();
		
	}
	
	

}
