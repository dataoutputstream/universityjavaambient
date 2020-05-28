package muratori;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasaL extends Casa{
	Lock l;
	Condition muratoriC;
	Condition muratoriM;
	LinkedList<Muratore>lC;
	LinkedList<Muratore>lM;
	
	public CasaL(int n){
		super(n);
		l=new ReentrantLock();
		muratoriC=l.newCondition();
		muratoriM=l.newCondition();
		lC=new LinkedList<>();
		lM=new LinkedList<>();
	}

	@Override
	public boolean inizia(int t) throws InterruptedException {
		l.lock();
		if(t==0)lC.add((Muratore)Thread.currentThread());
		else if(t==1)lM.add((Muratore)Thread.currentThread());
		try{
			Muratore x=((Muratore) Thread.currentThread());
		while(!mioTurno(t,x))	{
			if(t==0) {muratoriC.await();}
			else muratoriM.await();
		}
		if(pareti_rimanenti>0) {
		nMesse++;
		if(turno==0)lC.pollFirst();
		else lM.pollFirst();
		if(nMesse==nMesseF && turno==1) {n--;if(n==0) {pareti_rimanenti--;n=nMesseF;System.out.println("Parete Nfravicata");}}
		TimeUnit.SECONDS.sleep(1);
		Thread.sleep(1000);
		}if(t==0)lC.pollFirst();
		else lM.pollFirst();
		}catch(InterruptedException e) {
			
		}finally {
			l.unlock();
		}
		if(pareti_rimanenti>0)return true;
		return false;
	}

	private boolean mioTurno(int t, Muratore x) {
		if(t==0)return lC.getFirst()==x;
		if(t==1)return lM.getFirst()==x;
		return false;
	}



	@Override
	public void termina() throws InterruptedException {
		l.lock();
		try {
			if(pareti_rimanenti<=0) {System.out.println("Il Thread: "+Thread.currentThread().getId()+" ha finito");muratoriC.signalAll();muratoriM.signalAll();}
			
			else if(nMesse==nMesseF && turno==0) {turno=1;nMesse=0;muratoriM.signalAll();}
			else if(nMesse==nMesseF && turno==1){turno=0;nMesse=0;muratoriC.signalAll();}
			else if(turno==0) {
				muratoriC.signalAll();
			}
			else if(turno==1) {
				muratoriM.signalAll();
			}
		}finally {
			l.unlock();
		}
		
	}
	
	public static void main(String...args) {
		CasaL c=new CasaL(2);
		for(int i=0;i<5;i++) {
			Muratore m=new Muratore(0,c);
			m.start();
		}
		for(int j=0;j<7;j++) {
			Muratore x= new Muratore(1,c);
			x.start();
		}
	}

}
