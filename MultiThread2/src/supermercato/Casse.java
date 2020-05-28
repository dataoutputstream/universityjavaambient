package supermercato;

import java.util.LinkedList;

public abstract class Casse {
	LinkedList<Cassiere>Monitor=new LinkedList<>();
	int[]nProdotti;
	int ncasse;
	
	public Casse(int ncasse) {
		this.ncasse=ncasse;
		nProdotti=new int[ncasse];
	}
	
	
	public abstract Integer gedIdCassa() throws InterruptedException;
	public abstract void consegnaProdotti(int d,int p)throws InterruptedException;
	public abstract int segnalaCassaLibera(int id)throws InterruptedException;
	public abstract void congedaCliente(int id)throws InterruptedException;
	

}
