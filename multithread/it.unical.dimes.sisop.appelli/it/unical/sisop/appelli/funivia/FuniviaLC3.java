package it.unical.sisop.appelli.funivia;

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class FuniviaLC3 extends Funivia{

	private int numViaggio = 0;
	private int postiOccupati = 0;
	
	private boolean permessoEntrata = false;
	private boolean permessoUscita = false;
	
	private ArrayList<Long> iDTuristi = new ArrayList<>();
	
	private Lock l = new ReentrantLock();

	private Condition[] turniEntrataTuristi = {l.newCondition(), l.newCondition()};
	private Condition[] turniUscitaTuristi = {l.newCondition(), l.newCondition()};
	
	private Condition inizioViaggio = l.newCondition();
	private Condition fineViaggio= l.newCondition();
	
	public void pilotaStart() throws InterruptedException {
		l.lock();
		try {
			
			permessoEntrata = true;
			
			turniEntrataTuristi[numViaggio % 2].signalAll();
			
			while( !possoIniziareIlViaggio()) {
				inizioViaggio.await();
			}
			
		}finally {
			l.unlock();
		}
	}
	
	
	private boolean possoIniziareIlViaggio() {
		return numViaggio%2 == 0? postiOccupati == 6 : postiOccupati == 3;
	}
	
	public void turistaSali(int tipo) throws InterruptedException {
		l.lock();
		try {
							
			while(!possoSalire(tipo)) {
				turniEntrataTuristi[tipo].await();
			
			}
			postiOccupati ++;
				
			iDTuristi.add( Thread.currentThread().getId() );
			
			if((postiOccupati == 6 && numViaggio%2 == 0) ||(postiOccupati == 3 && numViaggio%2 == 1) ) {
				permessoEntrata = false;
				inizioViaggio.signal();
			}
			
		}finally {
			l.unlock();
		}
	}

	private boolean possoSalire(int tipo) {
		
		return tipo == 0? (permessoEntrata && numViaggio % 2 == 0 && postiOccupati < 6)
						: (permessoEntrata && numViaggio % 2 == 1 && postiOccupati < 3);	
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
			
			turniUscitaTuristi[numViaggio % 2].signalAll();
			
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
			
			while( !possoScendere(tipo) ) {
				turniUscitaTuristi[tipo].await();
				
			}
			postiOccupati --;
			
			if(postiOccupati == 0) {
				permessoUscita = false;
				fineViaggio.signal();
			}
			
		}finally {
			l.unlock();
		}
		
	}
	
	private boolean possoScendere(int tipo) {

		return tipo == 0? (permessoUscita && numViaggio%2 == 0 && postiOccupati > 0)
						: (permessoUscita && numViaggio%2 == 1 && postiOccupati > 0);		
	}
	
	public static void main(String[] args) {
		Funivia fun = new FuniviaLC3();
		fun.test();
		
	}

}
