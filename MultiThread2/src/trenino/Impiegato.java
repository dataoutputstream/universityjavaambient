package trenino;

public class Impiegato extends Thread{
	
	Trenino t;
	
	public Impiegato(Trenino t) {
		this.t=t;
	}
	
	public void run() {
		try {
		while(true) {
			t.impFaiScendere();
			t.impFaiSalire();
			t.impMuovi();
		}
		}catch(InterruptedException e) {
			
		}
	}

}
