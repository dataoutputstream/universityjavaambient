package funivia;

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class FuniviaLC extends Funivia{

	private int numViaggio = 0;
	private int postiOccupati = 0;
	
	private boolean permessoEntrata = false;
	private boolean permessoUscita = false;
	
	private ArrayList<Long> iDTuristi = new ArrayList<>();
	
	private Lock l = new ReentrantLock();
	
	private Condition entrataTuristiAPiedi = l.newCondition();
	private Condition entrataTuristiInBici = l.newCondition();
	
	private Condition uscitaTuristiAPiedi = l.newCondition();
	private Condition uscitaTuristiInBici = l.newCondition();
	
	private Condition inizioViaggio = l.newCondition();
	private Condition fineViaggio= l.newCondition();
	
	
	public void pilotaStart() throws InterruptedException {
		l.lock();
		try {
			
			permessoEntrata = true;
			
			if(numViaggio % 2 == 0) {
				entrataTuristiAPiedi.signalAll();
				
			}else {
				entrataTuristiInBici.signalAll();
			}
			
			while( !possoIniziareIlViaggio()) {
				inizioViaggio.await();
			}
			
		}finally {
			l.unlock();
		}
	}
	
	private boolean possoIniziareIlViaggio() {
		return postiOccupati == 6;
	}
	
	public void turistaSali(int tipo) throws InterruptedException {
		l.lock();
		try {
							
			if(tipo == 0) {
				while(!possoSalire(tipo)) {
					entrataTuristiAPiedi.await();
				
				}
				postiOccupati++;
				
			}else {
				while(!possoSalire(tipo)) {
					entrataTuristiInBici.await();
				
				}
				postiOccupati += 2;
			}
				
			iDTuristi.add( Thread.currentThread().getId() );
			
			if(postiOccupati == 6) {
				permessoEntrata = false;
				inizioViaggio.signal();
			}
			
		}finally {
			l.unlock();
		}
	}
	
	private boolean possoSalire(int tipo) {
		if( tipo == 0) {
			return (permessoEntrata && numViaggio % 2 == 0 && postiOccupati <6);
		}else {
			return (permessoEntrata && numViaggio % 2 == 1 && postiOccupati <6);
		}
		
	}
	
	
	public void pilotaEnd() throws InterruptedException {
		
		l.lock();
		try {
			
			System.out.println("Viaggio numero: "+ (numViaggio+1) );
			System.out.print("ID turisti presenti: ");
			
			for(int i = 0; i < iDTuristi.size(); i++) {
				System.out.print(iDTuristi.get(i) + " ");
				
			}
			System.out.println("\n");
			
			permessoUscita = true;
			
			if(numViaggio%2 == 0) {
				uscitaTuristiAPiedi.signalAll();
			}else {
				uscitaTuristiInBici.signalAll();
			}
			
			while( !possoFinireIlViaggio() ) {
				fineViaggio.await();
			}

			iDTuristi.clear();
			numViaggio++;
			
		}finally {
			l.unlock();
		}
		
	}
	
	private boolean possoFinireIlViaggio() {
		return postiOccupati == 0;
	}
	
	public void turistaScendi(int tipo) throws InterruptedException{
		
		l.lock();
		try {
				
			if(tipo == 0) {
				while( !possoScendere(tipo) ) {
					uscitaTuristiAPiedi.await();
					
				}
				postiOccupati --;
				
			}else {
				
				while( !possoScendere(tipo) ) {
					uscitaTuristiInBici.await();
					
				}
				postiOccupati -= 2;
			}
			
			if(postiOccupati == 0) {
				permessoUscita = false;
				fineViaggio.signal();
			}
			
		}finally {
			l.unlock();
		}
		
	}
	
	
	private boolean possoScendere(int tipo) {
		if(tipo == 0) {
			return (permessoUscita && numViaggio%2 == 0 && postiOccupati > 0);
		}else {
			return (permessoUscita && numViaggio%2 == 1 && postiOccupati > 0);
		}
	}
	
	public static void main(String[] args) {
		Funivia fun = new FuniviaLC();
		fun.test();
		
	}

}
