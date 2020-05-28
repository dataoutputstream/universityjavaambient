package muratore;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasaL extends Casa{
	Lock l=new ReentrantLock();
	Condition[] lavoratore=new Condition[2];
	LinkedList<Thread>[]lista=new LinkedList[2];

	
	public CasaL(int n) {
		lavoratore[0]=l.newCondition();
		lavoratore[1]=l.newCondition();
		lista[0]=new LinkedList<Thread>();
		lista[1]=new LinkedList<Thread>();
		this.n=n;
		nMod=n;
	}
	
	@Override
	public boolean inizia(int t) throws InterruptedException {
		l.lock();
		try {
		if(pareti==0) {
			return false;
		}
		lista[t].add(Thread.currentThread());
		
		while(!mioTurno(Thread.currentThread(),t) || t!=turno) {
			lavoratore[t].await();
		}
		if(t==0)System.out.println("Un muratore ha messo del gimento");
		else System.out.println("Un muratore ha messo un mattone");
		
		nMod--;
		
		if(nMod==0 && t==0) {
			turno=1;nMod=n;
		}else if(nMod==0 && t==1) {
			file++;
			if(file==n) {pareti--;System.out.println("Parete finita");file=0;}
			turno=0;nMod=n;
			
		}
		
		return true;
		}finally {
			l.unlock();
		}
	}
	

	private boolean mioTurno(Thread t,int t1) {
		
		return lista[t1].peek()==t;
	}

	@Override
	public void termina() throws InterruptedException {
		lavoratore[turno].signal();
		
	}

}
