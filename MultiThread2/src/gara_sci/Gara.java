package gara_sci;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public abstract class Gara {
	
	protected int numSciatore;
	public int n;
	ArrayList<Sciatore>set=new ArrayList<>();	
	
	public Gara(int n) {
		this.n=n;
		numSciatore=-1;
	}
	
	
	public abstract void partenza(Sciatore s) throws InterruptedException;
	public abstract int arrivo(Sciatore s) throws InterruptedException;
	public abstract boolean prossimo() throws InterruptedException;
	

}
