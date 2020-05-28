package poo.thread.generatore;

public class Generatore extends Thread{
	private int seed, id;
	public Generatore( int id, int seed ){
		this.id=id; this.seed=seed;
	}
	public void run(){
		while( true ){
			System.out.println("Generatore#"+id+" produce "+seed);
			seed++;
		}
	}//run
}//Generatore
