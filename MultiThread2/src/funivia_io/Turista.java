package funivia_io;

public class Turista extends Thread{
	
	Funivia f;
	int tipo;
	
	public Turista(int tipo,Funivia f) {
		this.tipo=tipo;
		this.f=f;
	}
	
	
	public void run() {
		
		try {
		
		f.turistaSali(tipo);
		
		f.turistaScendi(tipo);
		}catch(InterruptedException e) {
			
		}
		
	}

}
