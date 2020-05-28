package poo.thread.buffer.priorita;
import poo.util.*;

public class Produttore extends Thread{
/**
 * Si ignorano le eccezioni InterruptedException 
 * dal momento che un thread può trovarsi in wait() 
 * non interrompibile.
*/
	private int id;
	private MailboxPrioritaria<String> b;
	private int delayMax, delayMin, i=0;
	private String msg;
	public Produttore( int id, MailboxPrioritaria<String> b, 
			            int delayMax, int delayMin ){
		this.id=id; this.b=b;
		this.delayMax=delayMax; this.delayMin=delayMin;
	}
	private void delay(){
		try{
			Thread.sleep( (int)(Math.random()*(delayMax-delayMin)+delayMin) );
		}catch( InterruptedException e ){}
	}//delay
	public void run(){
		while( true ){
			delay();
			msg="P#"+id+"_"+i; 
			System.out.println("Produttore#"+id+" genera messaggio "+msg);
			i++;
			b.put( id, msg );
		}
	}//run
}//Produttore
