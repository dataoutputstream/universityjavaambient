package muratore;

import java.util.concurrent.Semaphore;

public class CasaS extends Casa{
	
	Semaphore[]lavoratore=new Semaphore[2];
	
	public CasaS(int n) {
		lavoratore[0]=new Semaphore(1,true);
		lavoratore[1]=new Semaphore(0,true);
		this.n=n;
		nMod=n;
	}
	

	@Override
	public boolean inizia(int t) throws InterruptedException {
		
		if(pareti==0) {
			return false;
		}
		
		lavoratore[t].acquire();
		
		if(t==0)System.out.println("Un muratore ha messo del gimento");
		else System.out.println("Un muratore ha messo un mattone");
		
		nMod--;
		
		if(nMod==0 && t==0) {
			turno=1;nMod=n;
		}else if(nMod==0 && t==1) {
			file++;
			if(file==n) {pareti--;System.out.println("Parete finita");file=0;}
			turno=0;nMod=n;
			
		}
		
		return true;
		
	}

	@Override
	public void termina() throws InterruptedException{
		lavoratore[turno].release();
		
	}
	
	public static void main(String...args) {
		Casa c=new CasaS(20);	
		
		for(int i=0;i<7;i++) {
			Muratore x=new Muratore(c,0);
			x.start();
		}
		for(int i=0;i<5;i++) {
			Muratore x=new Muratore(c,1);
			x.start();
		}
		
	
	}

}
