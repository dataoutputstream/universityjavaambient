package piscina;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PiscinaL extends Piscina {
	Random r=new Random();
	Lock lock;
	ArrayList<LinkedList<Persona>>adt=new ArrayList<>(5);
	Condition[] corsia;

	
	public PiscinaL(){
		
		super();
		lock=new ReentrantLock();
		corsia=new Condition[5];
		
		for(int i=0;i<5;i++) {
			corsia[i]=lock.newCondition();
			LinkedList<Persona>x =new LinkedList<>();
			adt.add(x);
		}
	}
	@Override
	public int scegli() throws InterruptedException {
		lock.lock();
		try {
			if(istruttore) {
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
				if(!uguali) {
					LinkedList<Persona>x=adt.get(i);
					x.add((Persona)Thread.currentThread());
					adt.set(i, x);
					while(!mioturno(i)) {
						corsia[i].await();
					}
						
						return i;	
					
					
					}else {
						int c=r.nextInt(5);
						LinkedList<Persona>x=adt.get(c);
						x.add((Persona)Thread.currentThread());
						adt.set(c, x);
						while(!mioturno(c)) {
							corsia[c].await();
						}
							
							return c;
						
						
					}
			}
				
		}finally {
			lock.unlock();
		}
		return 0;
	}

	private boolean mioturno(int i) {
		if(adt.get(i).peek()==Thread.currentThread())return true;
		return false;
	}

	@Override
	public void entra() throws InterruptedException {
		lock.lock();
		try {
		if(istruttore) {
			Persona p=(Persona)Thread.currentThread();
			l[p.i].add(p);		
		}
		}finally {
			lock.unlock();
		}
		
	}

	@Override
	public void esci() throws InterruptedException {
		lock.lock();
		try {
		Persona x=(Persona)Thread.currentThread();
		
		l[x.i].remove(x);
		LinkedList<Persona>z=adt.get(x.i);
		z.remove(x);
		adt.set(x.i,z);
		corsia[x.i].signalAll();
		
		System.out.println("La persona "+x.getId()+"sta facendo la doccia");
		}finally {
			lock.unlock();
		}
	}

	@Override
	public void apri() throws InterruptedException {
		lock.unlock();
		this.istruttore=true;
		
	}

	@Override
	public void chiudi() throws InterruptedException {
		lock.lock();
		this.istruttore=false;
		
		
		for(int i=0;i<5;i++)l[i].clear();
		
	}
	public static void main(String[]args) {
		PiscinaL p=new PiscinaL();
		Istruttore i=new Istruttore(p);
		i.start();
		for(int j=0;j<100;j++) {
			Persona x=new Persona(p);
			x.start();
		}
	}
	@Override
	public void escif() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}
	
}


