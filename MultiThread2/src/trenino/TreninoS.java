package trenino;

import java.util.concurrent.Semaphore;

public class TreninoS extends Trenino{
	
	Semaphore mutex=new Semaphore(1);
	Semaphore entro=new Semaphore(0);
	
	Semaphore[] uscita=new Semaphore[numCabine];
	Semaphore waitImp=new Semaphore(0);
	Semaphore aspettoUscita=new Semaphore(0);
	int turistiDisponibili;
	
	public TreninoS() {
		super();
		for(int i=0;i<uscita.length;i++) {
			
			uscita[i]=new Semaphore(0);
		}
	}

	@Override
	public void turSali() throws InterruptedException {
		mutex.acquire();
		turistiDisponibili++;
		mutex.release();
		entro.acquire();
		System.out.println("Il turista"+ Thread.currentThread().getId()+" è salito sul trenino: "+indice);
		Turista x=(Turista)Thread.currentThread();
		x.setIndice(indice);
		mutex.acquire();
		turistiDisponibili--;
		Cabine[indice]--;
		mutex.release();
		waitImp.release();
		
		
	}

	@Override
	public void turScendi() throws InterruptedException {
		Turista x=(Turista)Thread.currentThread();
		uscita[x.indice].acquire();
		System.out.println("Il turista"+ Thread.currentThread().getId()+" è sceso dal trenino: "+indice);
		mutex.acquire();
		Cabine[indice]++;
		mutex.release();
		aspettoUscita.release();
	}

	@Override
	public void impFaiScendere() throws InterruptedException {
		int uscenti=10-Cabine[indice];
		uscita[indice].release(uscenti);
		aspettoUscita.acquire(uscenti);
		
	}

	@Override
	public void impFaiSalire() throws InterruptedException {
		if(turistiDisponibili>=10) {
		entro.release(10);
		waitImp.acquire(10);
		}else {
		entro.release(turistiDisponibili);
		waitImp.acquire(turistiDisponibili);
		}
		
	}

	@Override
	public void impMuovi() throws InterruptedException {
		muovi();	
	}
	
	
	public static void main(String...Args) {
		TreninoS tre=new TreninoS();
		for(int i=0;i<175;i++) {
			Turista x=new Turista(tre);
			x.start();
		}
		Impiegato i=new Impiegato(tre);
		i.start();
	}
}
