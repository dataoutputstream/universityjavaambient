package poo.thread.filosofi;

public class Filosofo extends Thread{
	private final int id;
	private Tavolo t;
	private final long MIN, MAX;
	public Filosofo( final int id, final Tavolo t, final long MIN, final long MAX  ){
		this.id=id; this.t=t; this.MIN=MIN; this.MAX=MAX;
	}
	private void pausa(){
		try{
			Thread.sleep( (long)(Math.random()*(MAX-MIN)+MIN) );
		}catch( InterruptedException ie ){}
	}
	public void run(){
		while( true ){
			System.out.println("Filosofo "+id+" pensa...");
			pausa();
			System.out.println("Filosofo "+id+" richiede forchette...");
			t.ottieniForchette(id);
			System.out.println("Filosofo "+id+" mangia...");
			pausa();
			System.out.println("Filosofo "+id+" rilascia forchette...");
			t.rilasciaForchette(id);
		}
	}//run
}//Filosofo
