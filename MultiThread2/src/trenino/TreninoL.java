package trenino;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TreninoL extends Trenino{
	
	Lock l=new ReentrantLock();
	Condition entro=l.newCondition();
	int contatoreEntrati=0;
	boolean possoEntrare;
	Condition[] uscita=new Condition[numCabine];
	Condition waitImp=l.newCondition();
	Condition aspettoUscita=l.newCondition();
	int turistiDisponibili;
	
	public TreninoL() {
		super();
		for(int i=0;i<uscita.length;i++) {
			uscita[i]=l.newCondition();
		}
	}
	@Override
	public void turSali() throws InterruptedException {
		l.lock();
		try {
			Turista x=(Turista)Thread.currentThread();
			turistiDisponibili++;
			while(!PossoEntrare()) {
				entro.await();
			}
			contatoreEntrati--;
			System.out.println("Il turista"+ Thread.currentThread().getId()+" è salito sul trenino: "+indice);
			x.setIndice(indice);
			turistiDisponibili--;
			Cabine[indice]--;
			waitImp.signal();
			
		}finally {
			l.unlock();
		}
		
		
	}

	private boolean PossoEntrare() {
		
		return contatoreEntrati>0 && possoEntrare;
	}
	@Override
	public void turScendi() throws InterruptedException {
		l.lock();
		try {
			
			Turista x=(Turista)Thread.currentThread();
			while(!(x.indice==indice)||possoEntrare) {
				uscita[x.indice].await();
			}
			System.out.println("Il turista"+ Thread.currentThread().getId()+" è sceso dal trenino: "+indice);
			Cabine[indice]++;
			aspettoUscita.signal();
		}finally {
			l.unlock();
		}
		
	}

	@Override
	public void impFaiScendere() throws InterruptedException {
		l.lock();
		try {
			possoEntrare=false;
			uscita[indice].signalAll();
			while(Cabine[indice]!=10)
			aspettoUscita.await();
		}finally {
			l.unlock();
		}
		
	}

	@Override
	public void impFaiSalire() throws InterruptedException {
		l.lock();
		try {
			possoEntrare=true;
			if(turistiDisponibili>=10) {
				entro.signalAll();
				contatoreEntrati=10;
				while(contatoreEntrati!=0)
				waitImp.await();
			}else {
				entro.signalAll();
				contatoreEntrati=turistiDisponibili;
				while(contatoreEntrati!=0)
				waitImp.await();
				}
			
		}finally {
			l.unlock();
		}
		
	}

	@Override
	public void impMuovi() throws InterruptedException {
		l.lock();
		try {
			muovi();
		}finally {
			l.unlock();
		}
	}
	
	public static void main(String...Args) {
		TreninoL tre=new TreninoL();
		for(int i=0;i<175;i++) {
			Turista x=new Turista(tre);
			x.start();
		}
		Impiegato i=new Impiegato(tre);
		i.start();
	}

}
