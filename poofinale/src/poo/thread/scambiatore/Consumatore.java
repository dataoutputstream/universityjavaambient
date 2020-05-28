package poo.thread.scambiatore;

public class Consumatore extends Thread{
	private int id;
	private Exchanger<String> exch;
	private int delayMax, delayMin;
	private String msg;
	public Consumatore( int id, Exchanger<String> exch, 
			            int delayMax, int delayMin ){
		this.id=id; this.exch=exch;
		this.delayMax=delayMax; this.delayMin=delayMin;
	}
	private void delay(){
		try{
			Thread.sleep( (int)(Math.random()*(delayMax-delayMin)+delayMin) );
		}catch( InterruptedException e ){}
	}//delay
	public void run(){
		while( true ){
			msg=exch.exchange(null);
			System.out.println("Consumatore#"+id+" riceve messaggio "+msg);
			delay();
		}
	}//run
}//Consumatore
