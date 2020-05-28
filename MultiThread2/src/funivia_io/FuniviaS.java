package funivia_io;

import java.util.concurrent.Semaphore;

public class FuniviaS extends Funivia{
	
	
	Semaphore mutex=new Semaphore(1);
	Semaphore[] saliti=new Semaphore[2];
	Semaphore aPiedi=new Semaphore(0);
	Semaphore conBici=new Semaphore(0);
	Semaphore[] scesi=new Semaphore[2];
	Semaphore saPiedi=new Semaphore(0);
	Semaphore sconBici=new Semaphore(0);

	public FuniviaS(int tipo) {
		super(tipo);
		saliti[tipo]=new Semaphore(0);
		saliti[1-tipo]=new Semaphore(0);
		scesi[tipo]=new Semaphore(0);
		scesi[1-tipo]=new Semaphore(0);

	}

	@Override
	public void pilotaStart() throws InterruptedException {
		if(tipo==0)aPiedi.release(6);
		else conBici.release(3);
		saliti[tipo].acquire(maxTipo[tipo]);
		
		
	}

	@Override
	public void pilotaEnd() throws InterruptedException {
		for(Thread t:lista) {
			System.out.println("Il turista di tipo "+tipo+" con id : " +t.getId()+" è arrivato in cima");
		}
		if(tipo==0) {
			saPiedi.release(6);
		}else {
			sconBici.release(3);
		}
		
		scesi[tipo].acquire(maxTipo[tipo]);
		aggiornaTipo();
		
	}

	@Override
	public void turistaSali(int t) throws InterruptedException {
		if(t==0) {
			aPiedi.acquire();
			mutex.acquire();
			lista.add(Thread.currentThread());
			mutex.release();
			System.out.println("Il turista "+Thread.currentThread().getId()+" è nella funivia");
			saliti[t].release();
			return;
		}
		conBici.acquire();
		mutex.acquire();
		lista.add(Thread.currentThread());
		mutex.release();
		System.out.println("Il turista "+Thread.currentThread().getId()+" è nella funivia");
		saliti[t].release();
		return;
		
		
	}

	@Override
	public void turistaScendi(int t) throws InterruptedException {
		if(t==0) {
			saPiedi.acquire();
			mutex.acquire();
			lista.remove(Thread.currentThread());
			mutex.release();
			scesi[t].release();
			return;
		}
		sconBici.acquire();
		mutex.acquire();
		lista.remove(Thread.currentThread());
		mutex.release();
		scesi[t].release();
		return;
	
		
	}
	
	public void aggiornaTipo() {
		tipo=1-tipo;
	}
	
	public static void main(String...Args) {
		Funivia f = new FuniviaS(0);
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
