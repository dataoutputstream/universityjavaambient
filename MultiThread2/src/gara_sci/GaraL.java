package gara_sci;

import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GaraL extends Gara {
	
	Lock l=new ReentrantLock();
	boolean addetto=true;
	Condition [] partenze=new Condition[n];
	Condition addettoP=l.newCondition();
	Condition fine=l.newCondition();

	public GaraL(int n) {
		super(n);
		
		for(int i=0;i<n;i++) {
			partenze[i]=l.newCondition();
		}
		
	}

	@Override
	public void partenza(Sciatore s) throws InterruptedException {
		l.lock();
		
		try {
		while(!mioturno(s)) {
			partenze[s.nMaglia].await();
		}
		System.out.println("Lo sciatore "+ s.nMaglia+ " è partito.");
		}finally {
			l.unlock();
		}
		
	}

	private boolean mioturno(Sciatore s) {
		return s.nMaglia==numSciatore;
		
	}

	@Override
	public int arrivo(Sciatore s) throws InterruptedException {
		l.lock();
		try {
			set.add(s);
			addetto=true;
			addettoP.signalAll();
			
		}finally {
			l.unlock();
			
		}
		return posizione(s);
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
		l.lock();
		try {
		if(numSciatore<n-1) {
			while(!addetto) {
				addettoP.await();
			}
			addetto=false;
			numSciatore++;
			partenze[numSciatore].signalAll();
			return true;
			
		}while(addetto!=true) {
			addettoP.await();
		}
		stampa();
		}finally {
			l.unlock();
		}
		return false;
	}
	
	private void stampa() {
		Collections.sort(set);
		int pos=1;
		for(Sciatore s:set) {
			System.out.println("Lo sciatore: "+s.nMaglia+ " ha completato il percorso in "+ s.getTempo()+ " secondi aggiudicandosi la posizione: "+ pos);
			pos++;
		}
		
	}
	
	public static void main(String...Args) {
		Gara g= new GaraL(100);
		for(int i=0;i<100;i++) {
			Sciatore x= new Sciatore(i,g);
			x.start();
		}
		Addetto a= new Addetto(g);
		a.start();
	}

}
