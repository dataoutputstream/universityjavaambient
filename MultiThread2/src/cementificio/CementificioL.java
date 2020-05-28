package cementificio;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CementificioL extends Cementificio{
	
	Lock l=new ReentrantLock();
	Condition sacchi=l.newCondition();
	LinkedList<Thread>FilaCemento=new LinkedList<Thread>();
	Condition entro=l.newCondition();
	LinkedList<Thread>FilaEntrata=new LinkedList<Thread>();
	Condition ricarica=l.newCondition();
	int sacchif;
	int nthread=0;
	
	
	
	
	
	
	

	public CementificioL(int n, int p) {
		super(n, p);
		sacchif=p;
		
	}

	@Override
	public void entra() throws InterruptedException {
		l.lock();
		try {
		FilaEntrata.add(Thread.currentThread());
		while(!mioturno(Thread.currentThread())) {
			entro.await();
			
		}
		FilaEntrata.remove(Thread.currentThread());
		nthread++;
		System.out.println(Thread.currentThread().getId() + " è ENTRATO NEL CEMENTIFICIO");
		}finally{
			l.unlock();
		}
	}

	private boolean mioturno(Thread t) {
		return (t.getId()==FilaEntrata.getFirst().getId())&& nthread<=n;
	}

	@Override
	public void esci() throws InterruptedException {
		l.lock();
		try {
			System.out.println("Il cliente "+Thread.currentThread().getId() +" ha finito");	
			nthread--;
			entro.signal();
			
		}finally {
			l.unlock();
		}
		
	}

	@Override
	public void preleva() throws InterruptedException {
		l.lock();
		try {
			while(!possoPrelevare()) {
				sacchi.await();
			}
			p--;
			System.out.println("Il cliente "+Thread.currentThread().getId() +" ha prelevato");
			if(p==0)ricarica.signal();
			
		}finally {
			l.unlock();
		}
		
	}

	private boolean possoPrelevare() {
		return p>0;
	}

	@Override
	public void iniziaRifornimento() throws InterruptedException {
		l.lock();
		try {
		while(!ricaricare()) {
			ricarica.await();
		}
		System.out.println("Rifornimento in corso");
		
		}finally {
			l.unlock();
		}
	}

	private boolean ricaricare() {
		return p==0;
	}

	@Override
	public void terminaRifornimento() throws InterruptedException {
		l.lock();
		try {
		p=sacchif;
		sacchi.signalAll();
		System.out.println("Rifornimento finito");
		}finally {
			l.unlock();
		}
	}
	
public static void main(String...args) {
		
		Cementificio c=new CementificioL(100,100);
		Addetto a=new Addetto(c);
		a.start();
		for(int i=0;i<200;i++) {
			Cliente x= new Cliente(c);
			x.start();
		}

}
}
