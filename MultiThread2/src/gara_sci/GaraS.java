package gara_sci;

import java.util.Collections;
import java.util.concurrent.Semaphore;

public class GaraS extends Gara{
	
	public GaraS(int n) {
		super(n);
		for(int i=0;i<n;i++) {
			partenza[i]= new Semaphore(0);
		}
		
	}
	
	Semaphore mutex=new Semaphore(1);
	
	Semaphore addetto=new Semaphore(1);

	Semaphore [] partenza=new Semaphore[n];
	

	@Override
	public void partenza(Sciatore s) throws InterruptedException {
		partenza[s.nMaglia].acquire();
		System.out.println("Lo sciatore "+ s.nMaglia+ " è partito.");
		
		
	}

	@Override
	public int arrivo(Sciatore s) throws InterruptedException {
		mutex.acquire();
		set.add(s);
		mutex.release();
		int posizione=posizione(s);
		addetto.release();
		return posizione;
	}

	private int posizione(Sciatore s) {
		Collections.sort(set);
		int index=0;
		for(Sciatore sx:set) {
		if(sx.nMaglia==s.nMaglia)break;
		index++;
		}
		return index+1;
	}

	@Override
	public boolean prossimo() throws InterruptedException {
		addetto.acquire();
		if(numSciatore<n-1) {
			numSciatore++;
			partenza[numSciatore].release();
			return true;
		}
		stampa();
		return false;
	}

	private void stampa() {
		Collections.sort(set);
		int pos=1;
		for(Sciatore s:set) {
			System.out.println("Lo sciatore: "+s.nMaglia+ "ha completato il percorso in "+ s.getTempo()+ " secondi aggiudicandosi la posizione: "+ pos);
			pos++;
		}
		
	}
	
	
	public static void main(String...Args) {
		Gara g= new GaraS(100);
		for(int i=0;i<100;i++) {
			Sciatore x= new Sciatore(i,g);
			x.start();
		}
		Addetto a= new Addetto(g);
		a.start();
	}

}
