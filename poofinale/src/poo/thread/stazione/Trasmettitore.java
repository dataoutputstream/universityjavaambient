package poo.thread.stazione;
import java.io.*;
import java.util.*;

public class Trasmettitore implements Runnable{
	private PrintWriter pw;
	private Stazione s;
	private long periodo;
	public Trasmettitore( Stazione s, long periodo, String fileLog ) throws IOException{
		if( periodo<0 ) throw new IllegalArgumentException();
		this.s=s;
		this.periodo=periodo;
		pw=new PrintWriter( new FileWriter(fileLog) );
	}
	private void pausa() throws InterruptedException{
		try{
			Thread.sleep( periodo );
		}catch(InterruptedException e){ throw e; }
	}//pausa
	public void run(){
		while( !Thread.currentThread().isInterrupted() ){
			try{
				pausa();
			}catch(InterruptedException e ){ break; }
			int dato=s.rilevazione();
			Date ora=new Date();
			pw.println( ora+" veicoli: "+dato );
			pw.flush();
			System.out.println( ora+" veicoli: "+dato );
		}
		pw.close();
	}//run
}//Tasmettitore
