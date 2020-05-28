package supermercato;

public class Cassiere extends Thread{
	
	Casse c;
	Integer id;
	int prodottiConsegnati;
	
	public Cassiere(int id,Casse c) {
		this.c=c;
		this.id=id;
	}
	
	public void run() {
		try {
			while(true) {
				System.out.println("La cassa "+id+" è libera");
				c.segnalaCassaLibera(id);
				System.out.println("La cassa "+id+" ha finito");
				c.congedaCliente(id);
			}
		}catch(InterruptedException e){
			
		}
	}
	
	public void setProdotti(int prodotti)
	{
		prodottiConsegnati=prodotti;
	}
}
