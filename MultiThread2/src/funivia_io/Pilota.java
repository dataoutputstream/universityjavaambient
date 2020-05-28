package funivia_io;

public class Pilota extends Thread{

	Funivia f;
	
	
	public Pilota(Funivia f) {
		this.f=f;
	}


	public void run() {
		while(true) {
			try {
			f.pilotaStart();
			
			f.pilotaEnd();
			
			}catch(InterruptedException e) {
				
			}
		}
	}
}
