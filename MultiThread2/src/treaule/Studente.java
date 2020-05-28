package treaule;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Studente extends Thread{
	
	TreAule tre;
	int aulaAcceduta;
	int id;
	Random r=new Random();
	
	public Studente(TreAule tre,int id) {
		this.tre=tre;
		this.id=id;
	}
	
	public void run() {
		try {
			aulaAcceduta=tre.accedi();
			TimeUnit.MILLISECONDS.sleep(r.nextInt(6000-4500+1)+4500);
			tre.esci(aulaAcceduta);
			
		}catch(InterruptedException e) {
			
		}
	}

}
