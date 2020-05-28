package funivia;

public class Turista extends Thread {
	private Funivia f;
	private int tipo;
	private int ID;
	
	public int getID1() {
		return ID;
	}

	public Turista(int tipo,Funivia f,int ID) {
		this.f=f;
		this.tipo=tipo;
		this.ID=ID;
	}
	
	public void run() {
		try {
			f.turistaSali(tipo);
			f.turistacendi(tipo);
			
		}catch(InterruptedException e) {
			
		}
	}
}
