package muratori;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CasaS extends Casa{
	
	Semaphore inizioC;
	Semaphore inizioM;
	Semaphore mutex;

	public CasaS(int n) {
		super(n);
		mutex=new Semaphore(1);
		inizioC=new Semaphore(1,true);
		inizioM=new Semaphore(0,true);
		
	}

	@Override
	public boolean inizia(int t) throws InterruptedException {
		
		if(t==0)inizioC.acquire();else {inizioM.acquire();}
		if(pareti_rimanenti>0) {
		mutex.acquire();
		nMesse++;
		System.out.println(nMesse);
		mutex.release();
		if(nMesse==nMesseF && turno==1) {n--;if(n==0) {pareti_rimanenti--;n=nMesseF;System.out.println("Parete Nfravicata");}}
		TimeUnit.SECONDS.sleep(1);
		Thread.sleep(1000);
		return true;
		}return false;
	}

	@Override
	public void termina() throws InterruptedException {
		if(pareti_rimanenti<=0) {System.out.println("Il Thread: "+Thread.currentThread().getId()+" ha finito");inizioC.release();inizioM.release();}
		
		else if(nMesse==nMesseF && turno==0) {turno=1;nMesse=0;inizioM.release();}
		else if(nMesse==nMesseF && turno==1){turno=0;nMesse=0;inizioC.release();}
		else if(turno==0) {
			inizioC.release();
		}
		else if(turno==1) {
			inizioM.release();
		}
		
		
	}
	
	public static void main(String...args) {
		CasaS c = new CasaS(2);
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
