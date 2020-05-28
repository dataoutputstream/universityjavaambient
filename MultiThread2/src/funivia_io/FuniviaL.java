package funivia_io;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FuniviaL extends Funivia{
	
	//Mutua Esclusione
	Lock l=new ReentrantLock();
	
	//condition per fermare il pilota fino a quando non sono tutti saliti
	Condition[] saliti=new Condition[2];
	
	//Condition per i due tipi per regolare l'entrata
	Condition aPiedi=l.newCondition();
	Condition conBici=l.newCondition();
	
	//Condition per fermare il pilota fino a quando non sono tutti scesi
	Condition[] scesi=new Condition[2];
	
	//Condition per i due tipi per regolare l'uscita
	Condition saPiedi=l.newCondition();
	Condition sconBici=l.newCondition();
	
	//Per regolare entrata e uscita dei turisti
	boolean possoEntrare= true;
	boolean possoScendere=false;
	
	//Per il while prima delle condition di await di ingresso ed uscita relative al pilota
	int [] pSalite=new int[2];
	int [] pScese=new int[2];
	
	public FuniviaL(int tipo) {
		super(tipo);
		saliti[tipo]=l.newCondition();
		saliti[1-tipo]=l.newCondition();
		scesi[tipo]=l.newCondition();
		scesi[1-tipo]=l.newCondition();
	
	}

	

	@Override
	public void pilotaStart() throws InterruptedException {
		l.lock();
		try {
		possoEntrare=true;
		possoScendere=false;
		if(tipo==0)aPiedi.signalAll();
		else conBici.signalAll();
		//Finchè le persone non sono salite aspetta.
		while(pSalite[tipo]!=maxTipo[tipo]) {
			saliti[tipo].await();
		}
		}finally{
		l.unlock();
		}
	}

	@Override
	public void pilotaEnd() throws InterruptedException {
		l.lock();
		try {
		for(Thread t:lista) {
			System.out.println("Il turista di tipo "+tipo+" con id : " +t.getId()+" è arrivato in cima");
		}
		if(tipo==0) {
			possoScendere=true;
			saPiedi.signalAll();
		}else {
			possoScendere=true;
			sconBici.signalAll();
		}
		while(pScese[tipo]!=maxTipo[tipo]) {
			scesi[tipo].await();
		}
		//riaggiusto tutto per quelli dopo
		aggiornaTipo();
		pSalite[tipo]=0;
		pScese[tipo]=0;
		
		}finally{
		l.unlock();	
		}
		
	}

	private void aggiornaTipo() {
		tipo=1-tipo;
		
	}



	@Override
	public void turistaSali(int t) throws InterruptedException {
		l.lock();
		try {
			if(t==0) {
				while(!mioTurno(t) || !possoEntrare) {
				aPiedi.await();
				}
				lista.add(Thread.currentThread());
				if(lista.size()==maxTipo[tipo])possoEntrare=false;
				//incremento variabile per far cadere il while del Pilotastart
				pSalite[t]++;
				System.out.println("Il turista "+Thread.currentThread().getId()+" è nella funivia");
				saliti[t].signalAll();
				return;
			}
			while(!mioTurno(t) || !possoEntrare) {
			conBici.await();
			}
			lista.add(Thread.currentThread());
			if(lista.size()==maxTipo[tipo])possoEntrare=false;
			pSalite[t]++;
			System.out.println("Il turista "+Thread.currentThread().getId()+" è nella funivia");
			saliti[t].signalAll();
			return;
		}finally {
			l.unlock();
		}
		
	}
	
	// è il mio turno quando tipo==mio e c'è ancora posto per me
	private boolean mioTurno(int t) {
		return t==tipo && lista.size()<maxTipo[t];
	}



	@Override
	public void turistaScendi(int t) throws InterruptedException {
	l.lock();
	try {
		if(t==0) {
			while(!possoScendere) {
			saPiedi.await();
			}
			lista.remove(Thread.currentThread());
			pScese[t]++;
			scesi[t].signalAll();
			return;
		}
		while(!possoScendere) {
			sconBici.await();
		}
		lista.remove(Thread.currentThread());
		pScese[t]++;
		scesi[t].signalAll();
		return;
	
	}finally {
		l.unlock();
	}
		
	}
	public static void main(String...Args) {
		Funivia f = new FuniviaL(0);
		Pilota p=new Pilota(f);
		p.start();
		
		for(int i=0;i<18;i++) {
			Turista x=new Turista(0,f);
			x.start();
		}
		for(int j=0;j<9;j++) {
			Turista x=new Turista(1,f);
			x.start();
		}
	}

}
