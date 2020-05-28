package piscina;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class PiscinaS extends Piscina {
	Semaphore [] s = new Semaphore[5];
	Semaphore mutex=new Semaphore(1);
	 
	public PiscinaS() {
		super();
		int i=0;
		while(i<s.length) {
			s[i]=new Semaphore(4,true);
			i++;
		}
	}

	@Override
	public void scegli() throws InterruptedException {

		Persona id=(Persona) Thread.currentThread();
		int i=Integer.MAX_VALUE;
		int index=0;
		mutex.acquire();
		for(LinkedList<Persona>l:p) {
			if(l.size()<i) {index=p.indexOf(l);i=l.size();}
		}

		LinkedList<Persona>l=p.get(index);
		l.add(id);
		p.set(index, l);
		mutex.release();
		s[i].acquire();
		System.out.println("La persona "+ id.getId()+" ha scelto la corsia "+index);
		
	}

	@Override
	public void nuota() throws InterruptedException {
		
		Persona.attendi(Persona.MIN_ATTESA,Persona.MAX_ATTESA);
		Persona x=(Persona) Thread.currentThread();
		int i=-1;
		mutex.acquire();
		for(LinkedList<Persona>l:p) {
			if(l.contains(x)) {l.remove(x);i=p.indexOf(l);break;}
			
		}
		mutex.release();
		s[i].release();
		
	}

	@Override
	public void apri() throws InterruptedException {
		istruttore=true;
		
	}

	@Override
	public void chiudi() throws InterruptedException {
		istruttore=false;
		
		
	}
	
	public static void main(String...args) {
		PiscinaS p=new PiscinaS();
		Istruttore i=new Istruttore(p);
		i.start();
		for(int t=0;t<100;t++) {
			Persona x=new Persona(p);
			x.start();
		}
	}
}
