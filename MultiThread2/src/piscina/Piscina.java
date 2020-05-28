package piscina;

import java.util.LinkedList;

public abstract class Piscina {
	LinkedList<Persona>[]l;
	boolean istruttore=true;
	
	
	public Piscina() {
		l=new LinkedList[5];
		for(int i=0;i<5;i++) {
			LinkedList<Persona>x=new LinkedList<Persona>();
			l[i]=x;
		}
	}
	
	public abstract int scegli() throws InterruptedException;
	public abstract void entra()throws InterruptedException;
	public abstract void esci()throws InterruptedException;
	
	
	public abstract void apri()throws InterruptedException;
	public abstract void chiudi()throws InterruptedException;

	public abstract void escif() throws InterruptedException;
		
		
	
}
