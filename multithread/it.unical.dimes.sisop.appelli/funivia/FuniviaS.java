package funivia;

import java.util.concurrent.Semaphore;

public class FuniviaS extends Funivia{
	Semaphore mutex;
	Semaphore persone;
	Semaphore personeb;
	Semaphore parti;
	Semaphore fine;

	
	public FuniviaS() {
		super();
		mutex=new Semaphore(1);
		persone=new Semaphore(0);
		personeb=new Semaphore(0);
		parti=new Semaphore(0);
		fine=new Semaphore(6);
	}
	@Override
	public void pilotaStart() throws InterruptedException {
		fine.acquire(6);
		if(turno==0) {persone.release(6);}else {personeb.release(3);}
		parti.acquire(6);
	}

	@Override
	public void pilotaEnd() throws InterruptedException {
		turno=1-turno;
		for(int i=0;i<l.size();i++)
			System.out.println("Il nero sbarcato è il : "+l.get(i).getID1());
		fine.release(6);
		
	}

	@Override
	public void turistaSali(int t) throws InterruptedException {
		if(t==0) {persone.acquire();}
		else {personeb.acquire();}
		mutex.acquire();
		l.add((Turista)Thread.currentThread());
		mutex.release();
		if(t==0)parti.release(1);
		else parti.release(2);
		
	}

	@Override
	public void turistacendi(int t) throws InterruptedException {
		if(t==0) {
		fine.acquire();
		}else fine.acquire(2);
		mutex.acquire();
		l.remove(Thread.currentThread());
		mutex.release();
		if(t==0) {
		fine.release();
		}else fine.release(2);
		
		
	}

	public static void main(String...args) {
		FuniviaS f=new FuniviaS();
		Pilota p= new Pilota(f);
		p.start();
		for(int i=0;i<24;i++) {
			Turista x=new Turista(0,f,i);
			x.start();
		}
		for(int j=0;j<12;j++) {
			Turista x= new Turista(1,f,j+24);
			x.start();
		}
	}
}
