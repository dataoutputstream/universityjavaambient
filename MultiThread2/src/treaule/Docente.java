package treaule;

import java.util.concurrent.TimeUnit;

public class Docente extends Thread{
	TreAule tre;
	
	public Docente(TreAule tre) {
		this.tre=tre;
	}
	
	public void run() {
		try {
			while(true) {
			tre.chiamaStudente();
			TimeUnit.MILLISECONDS.sleep(500);
			}
		}catch(InterruptedException e) {
			
		}
	}

}
