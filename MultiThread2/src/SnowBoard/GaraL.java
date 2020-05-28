package SnowBoard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GaraL extends Gara{
	
	Lock l=new ReentrantLock();
	Condition turno=l.newCondition();
	LinkedList<Thread>fila=new LinkedList<>();
	Condition arrivato=l.newCondition();
	boolean tagliato=true;

	public GaraL(int N) {
		super(N);
		
	}

	@Override
	void partenza(SnowBoarder s) throws InterruptedException {
		l.lock();
		fila.add(s);
		try {
		while(!mioTurno(Thread.currentThread())) {
			turno.await();
		}
		catu.add(s);
		}finally {
			l.unlock();
		}
		
	}

	private boolean mioTurno(Thread t) {
	return t==fila.peekFirst();
	}

	@Override
	int arrivo(SnowBoarder s) throws InterruptedException {
		l.lock();
		
		try{
			ultimo=s;
			int pos;
			Collections.sort(catu);
			pos=catu.indexOf(s)+1;
			tagliato=true;
			arrivato.signal();
			fila.remove(s);
			return pos;	
		}finally {
			l.unlock();
		}
	}

	@Override
	boolean stampaEprossimo() throws InterruptedException {
		l.lock();
		try {
			while(!tagliato) {
				arrivato.await();
			}
			tagliato=false;
			if(ultimo!=null) {
				System.out.println("Lo SnowBoarder "+ ultimo.getMaglia()+" è arrivato");
				System.out.println("Tempo: "+ultimo.tempo+" Posizione= "+ultimo.getPosizione());
				}
				turno.signal();
				if(catu.size()==N)return false;
				return true;
		}finally {
			l.unlock();
		}
	}

	@Override
	void classificaFinale() throws InterruptedException {
		System.out.println("La Classifica Finale è:");
		Collections.sort(catu);
		for(SnowBoarder s:catu) {
			System.out.println("Lo SnowBoarder "+s.getMaglia()+" si è posizionato "+(catu.indexOf(s)+1)+"° con un tempo di "+ s.tempo+ (s.tempo > 1 ? " minuti":" minuto"));
		}
		
	}
	public static void main(String...args) {
		Gara g=new GaraL(50);
		Addetto a=new Addetto(g);
		a.start();
		for(int i=0;i<50;i++) {
			SnowBoarder x=new SnowBoarder(i,g);
			x.start();
		}
	}

}
