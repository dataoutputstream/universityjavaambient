package SnowBoard;

public class Addetto extends Thread{
	
	Gara g;
	
	public Addetto(Gara g) {
		this.g=g;
	}
	
	public void run() {
		try {
		while(g.stampaEprossimo()) {
		}
		g.classificaFinale();
		}catch(InterruptedException e) {
			
		}
	}

}
