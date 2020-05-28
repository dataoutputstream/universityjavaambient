package cementificio;

import java.util.concurrent.Semaphore;

public class CementificioS extends Cementificio{
	
	Semaphore mutex=new Semaphore(1);
	Semaphore sacchi=new Semaphore(p,true);
	Semaphore entro=new Semaphore(n,true);
	Semaphore ricarica=new Semaphore(0);
	int sacchif;
	
	public CementificioS(int n,int p){
		super(n,p);
		sacchif=p;
		
	}
	
	
	@Override
	public void entra() throws InterruptedException {
		entro.acquire();
		System.out.println(Thread.currentThread().getId() + " è ENTRATO NEL CEMENTIFICIO");
		
	}

	@Override
	public void esci() throws InterruptedException {
		System.out.println("Il cliente "+Thread.currentThread().getId() +" ha finito");	
		entro.release();
	}

	@Override
	public void preleva() throws InterruptedException {
				sacchi.acquire();
				mutex.acquire();
				p--;
				System.out.println("Il cliente "+Thread.currentThread().getId() +" ha prelevato");	
				if(p==0)ricarica.release();
				mutex.release();
			
		
		
		
	}

	@Override
	public void iniziaRifornimento() throws InterruptedException {
		ricarica.acquire();
		System.out.println("Rifornimento in corso");	
		mutex.acquire();
		p=sacchif;
		mutex.release();
		
	}

	@Override
	public void terminaRifornimento() throws InterruptedException {
		sacchi.release(200);
		System.out.println("Rifornimento finito");	
		
	}
	
	public static void main(String...args) {
		
		Cementificio c=new CementificioS(100,1000);
		Addetto a=new Addetto(c);
		a.start();
		for(int i=0;i<200;i++) {
			Cliente x= new Cliente(c);
			x.start();
		}
		
		
	}

}
