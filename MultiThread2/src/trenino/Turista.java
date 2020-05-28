package trenino;

public class Turista extends Thread{
	
	Trenino t;
	int indice;
	
	
	public Turista(Trenino t) {
		this.t=t;
	}
	
	public void run() {
		try {
		t.turSali();
		t.turScendi();
		}catch(InterruptedException e) {
			
		}
	}
	
	public void setIndice(int indice) {
		this.indice=indice;
	}

}
