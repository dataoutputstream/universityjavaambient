package funivia;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class FuniviaSemArray extends Funivia{
	
	private int turno = -1;//o 0 o 1
	private int postiOccupati = 0;
	
	//regola l'entrata dei turisti nella funivia
	private Semaphore permettiEntrata[] = {new Semaphore(0), new Semaphore(0)};	
	//permette la mutua esclusione
	private Semaphore mutex = new Semaphore(1);
	
	//regola la partenza della funivia
	private Semaphore permettiPartenza = new Semaphore(0);
	
	//regola l'uscita dei turisti dalla funivia
	private Semaphore permettiUscita[] = {new Semaphore(0), new Semaphore(0)};	

	
	//permette l'avvio del metodo pilotaEnd() (e quindi della stampa).
	private Semaphore permettiStampa = new Semaphore(0);
	
	//permette di completare il metodo pilotaEnd()
	private Semaphore permettiFine = new Semaphore(0);
	
	
	private ArrayList<Turista> turisti = new ArrayList<Turista>();
	
	
	
	public void pilotaStart() throws InterruptedException {		
		turno= (turno+1)%2;
		System.out.println("PILOTASTART Permessi di PermettiPartenza (dovrebbero essere 0), sono: "+ permettiPartenza.availablePermits());
		System.out.println("PILOTASTART Posti occupati: "+ postiOccupati);
		permettiEntrata[turno].release(turno == 0?6:3);
		
		//il pilota parte solo quando l'ultimo turista è salito a bordo.
		permettiPartenza.acquire();
		
		permettiStampa.release();
	}
	
	public void turistaSali(int tipo) throws InterruptedException {		
		permettiEntrata[tipo].acquire();
		
		mutex.acquire();
		postiOccupati+=(tipo == 0?1:2);
		turisti.add((Turista)Thread.currentThread());		
		if( postiOccupati == 6) {
			permettiPartenza.release();
		}
		mutex.release();		
	}
	
public void turistaScendi(int tipo) throws InterruptedException {		
		permettiUscita[tipo].acquire();
		
		mutex.acquire();
		postiOccupati-=(tipo == 0?1:2);		
		if(postiOccupati == 0) {
			permettiFine.release();
		}
		mutex.release();		
	}
	
	
	public void pilotaEnd() throws InterruptedException {
		
		permettiStampa.acquire();
		System.out.println("permessi di PermettiFine (dovrebbero essere 0), sono: "+ permettiFine.availablePermits());
		System.out.println("Fine del turno: "+ turno );
		System.out.print("ID turisti presenti: ");
		
		for(Turista t:turisti) {
			System.out.print(t+ " ");
		}
		
		System.out.println("\n");		
		permettiUscita[turno].release(turno % 2==0?6:3);

		permettiFine.acquire();
		turisti.clear();
		
	}
	
	
	
	public static void main(String[] args) {
		Funivia fun = new FuniviaSemArray();
		fun.test();
		
	}
	
	

}
