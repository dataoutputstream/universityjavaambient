package funivia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FuniviaL extends Funivia{
	Lock lock;
	Condition persone;
	Condition personeb;
	Condition parti;
	Condition fine;
	Condition fine2;
	
	
	public FuniviaL(){
		lock=new ReentrantLock();
		persone=lock.newCondition();
		personeb=lock.newCondition();
		parti=lock.newCondition();
		fine=lock.newCondition();
		fine2=lock.newCondition();
	}
	
	
	boolean autorizzazione=false;
	@Override
	public void pilotaStart() throws InterruptedException {
		lock.lock();
		try {
			
			if(turno==0) {
			persone.signalAll();
			}else {
				
			personeb.signalAll();
			}
			while(!partire()) {
				
				parti.await();
			}
		}finally {
			lock.unlock();
		}
		
	}
	private boolean partire() {
		if(turno==0 && l.size()==6) {return true;}if(turno==1 && l.size()==3)return true;
		return false;
	}
	@Override
	public void pilotaEnd() throws InterruptedException {
		lock.lock();
		try {
		System.out.println("Il turno dei neri è il turno: "+turno);
		turno=1-turno;
		for(int i=0;i<l.size();i++)
			System.out.println("Il nero sbarcato è il : "+l.get(i).getID1());
		autorizzazione=true;
		fine.signalAll();
			while(l.size()!=0) {
				fine2.await();
			}autorizzazione=false;
			
			
		}finally {
			lock.unlock();
		}
		
	}
	
	@Override
	public void turistaSali(int t) throws InterruptedException {
		lock.lock();
		try{
			while(turno!=t || dimensionemax(t)) {
				if(t==0) {persone.await();}
				else {personeb.await();
				}
			}
			
			l.add((Turista)Thread.currentThread());
			if((l.size()==6 && turno==0) || (l.size()==3 && turno==1))parti.signalAll();
		}finally {
			lock.unlock();
		}
		
	}
	private int dimturno(int t) throws InterruptedException {
		if(t==0)return 6;
		if(t==1)return 3;
		throw new InterruptedException();
	}
	private boolean dimensionemax(int t) {
		if(t==0 && l.size()==6)return true;
		if(t==1 && l.size()==3)return true;
		return false;
	}
	@Override
	public void turistacendi(int t) throws InterruptedException {
		lock.lock();
		try {
			while(!autorizzazione) {
				fine.await();
			}
			l.remove(Thread.currentThread());
			
			fine2.signalAll();
		}finally {
			lock.unlock();
		}
		
	}
	
	
	public static void main(String...args) {
		FuniviaL f=new FuniviaL();
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
