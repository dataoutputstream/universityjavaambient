package bar;

import java.util.concurrent.Semaphore;

public class BarS extends Bar{
	
	Semaphore mutex=new Semaphore(1);
	Semaphore[] luogo=new Semaphore[2];


	int[] fila=new int[2];
	
	public BarS() {
		luogo[0]=new Semaphore(1,true);
		luogo[1]=new Semaphore(4,true);
	}
	

	@Override
	public int scegliInizia() throws InterruptedException {
		int i=-1;
		mutex.acquire();
		if(cassa==1) {
			i= 0;
		}
		if(bancone>0) {
			i=1;
		}
		if(fila[0]<=fila[1]) {
			i=0;
		}
		mutex.release();
		return i;
		
	}

	@Override
	public void inizia(int i) throws InterruptedException {
		mutex.acquire();
		fila[i]++;
		mutex.release();
		luogo[i].acquire();
		mutex.acquire();
		if(i==0) {cassa--;System.out.println("Persona"+Thread.currentThread().getId()+" sta pagando");}
		if(i==1) {bancone--;System.out.println("Persona"+Thread.currentThread().getId()+" sta prendendo il caffe");}
		fila[i]--;
		mutex.release();
	}

	@Override
	public void finisci(int i) throws InterruptedException {
		mutex.acquire();
		if(i==0) {cassa++;System.out.println("Persona"+Thread.currentThread().getId()+" ha finito in cassa");}
		if(i==1) {bancone++;System.out.println("Persona"+Thread.currentThread().getId()+" ha finito al bancone");}
		mutex.release();
		luogo[i].release();
	}
	
	public static void main(String...args) {
		Bar b=new BarS();
		for(int i=0;i<100;i++) {
			Persona x=new Persona(b);
			x.start();
		}
	}

}
