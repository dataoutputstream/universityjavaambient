package supermercato;

import java.util.Random;

public class Cliente extends Thread{
	Casse c;
	Random r=new Random();
	private final int MAX=50;
	private final int MIN=1;
	
	public Cliente(Casse c) {
		this.c=c;
	}
	
	public void run() {
		try {
		int p=scegli();
		Integer idcassa = null;
		while(idcassa==null) {
			idcassa=c.gedIdCassa();	
		}
		System.out.println("Il Cliente "+currentThread().getId()+" ha scelto la cassa "+idcassa);
		c.consegnaProdotti(idcassa, p);
		System.out.println("Il Cliente "+currentThread().getId()+" ha consegnato "+p+" prodotti");
		}catch(Exception e) {
			
		}
	}


	private int scegli() {
		return r.nextInt(MAX-MIN+1)+MIN;
	}

}
