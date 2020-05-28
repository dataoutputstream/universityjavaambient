package poo.thread.stazione;

public class Sensore extends Thread{
	private long periodo;
	private Stazione s;
	private int id;
	public Sensore( int id, long periodo, Stazione s ){
		if( periodo<0 )
			throw new IllegalArgumentException();
		this.id=id;
		this.periodo=periodo;
		this.s=s;
	}
	private void pausa() throws InterruptedException{
		try{
			Thread.sleep( periodo );
		}catch(InterruptedException e){ 
			throw e; 
		}
	}
	public void run(){
		while( !isInterrupted() ){
			boolean passaVeicolo=(Math.random()<0.5)? true : false;
			if( passaVeicolo ){
				System.out.println("Sensore#"+id+" segnala passaggio veicolo");
				s.segnaleVeicolo();
			}
			try{
				pausa();
			}catch( InterruptedException e ){
				break;
			}
		}
	}
}//Sensore
