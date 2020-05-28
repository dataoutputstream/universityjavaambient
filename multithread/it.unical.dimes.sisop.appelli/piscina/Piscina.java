package piscina;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Piscina {
	protected ArrayList<LinkedList<Persona>> p;
	protected boolean istruttore;
	
	public Piscina() {
		p=new ArrayList<LinkedList<Persona>>();
		for(int i=0;i<5;i++) {
		LinkedList<Persona>l=new LinkedList<Persona>();
		p.add(l);
		}
	}
	
	
	public abstract void scegli() throws InterruptedException;
	public abstract void nuota() throws InterruptedException;
	public abstract void apri() throws InterruptedException;
	public abstract void chiudi() throws InterruptedException;

}
