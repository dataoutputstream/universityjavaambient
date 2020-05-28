package bar;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarL extends Bar{
	
	Lock l=new ReentrantLock();
	Condition[] luogo=new Condition[2];
	LinkedList<Thread>[]lista=new LinkedList[2];
	
	public BarL() {
		luogo[0]=l.newCondition();
		luogo[1]=l.newCondition();
		lista[0]=new LinkedList<>();
		lista[1]=new LinkedList<>();
	}

	@Override
	public int scegliInizia() throws InterruptedException {
		l.lock();
		try {
		int i=-1;
		if(cassa==1) {
			i= 0;
		}
		if(bancone>0) {
			i=1;
		}
		if(lista[0].size()<=lista[1].size()) {
			i=0;
		}
		return i;
		}finally {
			l.unlock();
		}
	}

	@Override
	public void inizia(int i) throws InterruptedException {
		l.lock();
		try {
			lista[i].add(Thread.currentThread());
			while(!mioTurno(Thread.currentThread(),i)) {
				luogo[i].await();
			}
			if(i==0) {cassa--;System.out.println("Persona"+Thread.currentThread().getId()+" sta pagando");}
			if(i==1) {bancone--;System.out.println("Persona"+Thread.currentThread().getId()+" sta prendendo il caffe");}
			lista[i].remove(Thread.currentThread());
		}finally {
			l.unlock();
		}
		
	}

	private boolean mioTurno(Thread t,int i) {
		
		return lista[i].peek()==t;
	}

	@Override
	public void finisci(int i) throws InterruptedException {
		l.lock();
		try {
			if(i==0) {cassa++;System.out.println("Persona"+Thread.currentThread().getId()+" ha finito in cassa");}
			if(i==1) {bancone++;System.out.println("Persona"+Thread.currentThread().getId()+" ha finito al bancone");}
			luogo[i].signal();
		}finally {
			l.unlock();
		}
		
	}
	public static void main(String...args) {
		Bar b=new BarL();
		for(int i=0;i<100;i++) {
			Persona x=new Persona(b);
			x.start();
		}

	}
}
