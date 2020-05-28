package snowboarder;


import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Semaphore;



public class GaraS extends Gara{
	Semaphore partito=new Semaphore(1,true);
	Semaphore mutex=new Semaphore(1);
	
	
	public GaraS(int n) {
		super();
		this.n=n;
	}

	@Override
	protected void partenza(SnowBoarder s) {
		try {
			
			partito.acquire();
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected int arrivo(SnowBoarder s) throws InterruptedException {
		mutex.acquire();
		s.start();
		s.join();
		classifica.add(s);
		Collections.sort(classifica);
		return classifica.indexOf(s);
		
	}

	@Override
	protected boolean StampaeProssimo() {
		
		partito.release();
		if(classifica.size()!=n) {
		mutex.release();
		return true;
		}return false;
	}

	@Override
	protected void classificaFinale() {
		Collections.sort(classifica);
		System.out.println("Classifica della gara odierna: ");
		for(int i=0;i<n;i++) {
			System.out.println("Snowboarder: " +classifica.get(i).id+ " tempo: "+classifica.get(i).tempo+" Posto: "+(i+1));
		}
		
	}

public static void main(String[]args) {
	GaraS g=new GaraS(20);
	AddettoDiGara ag=new AddettoDiGara(20,g);
	ag.start();
}
}
