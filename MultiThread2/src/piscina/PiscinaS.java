package piscina;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PiscinaS extends Piscina {
	Random r=new Random();
	Semaphore mutex= new Semaphore(1);
	Semaphore[] corsia=new Semaphore[5];
	int[] ContatorePermessi=new int[5];
	LinkedList<Persona>[]dentro=new LinkedList [5];
	
	public PiscinaS() {
		super();
		for(int i=0;i<5;i++) {
			corsia[i]=new Semaphore(4,true);
			dentro[i]=new LinkedList<Persona>();
		}
	}
	

	@Override
	public int scegli() throws InterruptedException{
		if(istruttore) {
			mutex.acquire();
			int i=-1;
			int nPersone=Integer.MAX_VALUE;
			for(int j=0;j<5;j++) {
				if(l[j].size()<nPersone) {i=j;nPersone=l[j].size();}
			}
			boolean uguali=true;
			for (int j=0; j<5;j++) {
				if(uguali=false)break;
				int size=l[j].size();
				for(int z=0;z<5;z++) {
					if(size!=l[z].size()) {uguali=false;break;}	
				}
				
			}
			mutex.release();
			if(!uguali) {
			mutex.acquire();
			l[i].add((Persona)Thread.currentThread());
			mutex.release();
			return i;
			}else {
				int c=r.nextInt(5);
				mutex.acquire();
				l[c].add((Persona)Thread.currentThread());
				mutex.release();
				return c;
			}
			
		}
		return 0;
	}

	@Override
	public void entra() throws InterruptedException{
		
		if(istruttore) {
			Persona p=(Persona)Thread.currentThread();
			corsia[p.i].acquire();
			mutex.acquire();
			dentro[p.i].add(p);
			mutex.release();
		}
		
	}

	@Override
	public void esci() throws InterruptedException{
		Persona x=(Persona)Thread.currentThread();
		mutex.acquire();
		l[x.i].remove(x);
		dentro[x.i].remove(x);
		corsia[x.i].release();
		mutex.release();
		System.out.println("La persona "+x.getId()+"sta facendo la doccia");	
	}
	
	public void escif() throws InterruptedException{
		Persona p=(Persona)Thread.currentThread();
		mutex.acquire();
		l[p.i].remove(p);
		dentro[p.i].remove(p);
		ContatorePermessi[p.i]++;
		p.set(true);
		mutex.release();
		System.out.println("La persona "+p.getId()+"sta facendo la doccia");	
	}

	@Override
	public void apri() throws InterruptedException{
		
		this.istruttore=true;
		for(int i=0;i<5;i++) {
			if(ContatorePermessi[i]>0)corsia[i].release(ContatorePermessi[i]);
		}
		
	}

	@Override
	public void chiudi() throws InterruptedException{
		for(int i=0;i<dentro.length;i++) {
			LinkedList<Persona>x=dentro[i];
			for(Persona p:x) {
				
			}
			
			
		}
		this.istruttore=false;
		
	}
	public static void main(String[]args) {
		PiscinaS p=new PiscinaS();
		Istruttore i=new Istruttore(p);
		i.start();
		for(int j=0;j<100;j++) {
			Persona x=new Persona(p);
			x.start();
		}
		
	}
}
