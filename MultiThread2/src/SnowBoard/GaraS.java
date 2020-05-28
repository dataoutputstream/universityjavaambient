package SnowBoard;

import java.util.Collections;
import java.util.concurrent.Semaphore;

public class GaraS extends Gara{
	
	public GaraS(int N) {
		super(N);
	}

	Semaphore mutex=new Semaphore(1);
	Semaphore turno=new Semaphore(0,true);
	Semaphore arrivato= new Semaphore(1);

	@Override
	void partenza(SnowBoarder s) throws InterruptedException {
		turno.acquire();
		mutex.acquire();
		catu.add(s);
		mutex.release();
	}

	@Override
	int arrivo(SnowBoarder s) throws InterruptedException {
		ultimo=s;
		int pos;
		Collections.sort(catu);
		pos=catu.indexOf(s)+1;
		arrivato.release();
		return pos;
	}

	@Override
	boolean stampaEprossimo() throws InterruptedException {
		arrivato.acquire();
		if(ultimo!=null) {
		System.out.println("Lo SnowBoarder"+ ultimo.getMaglia()+" è arrivato");
		System.out.println("Tempo: "+ultimo.tempo+" Posizione= "+ultimo.getPosizione());
		}
		turno.release();
		if(catu.size()==N)return false;
		return true;
	}

	@Override
	void classificaFinale() throws InterruptedException {
		System.out.println("La Classifica Finale è:");
		Collections.sort(catu);
		for(SnowBoarder s:catu) {
			System.out.println("Lo SnowBoarder "+s.getMaglia()+" si è posizionato "+(catu.indexOf(s)+1)+"° con un tempo di "+ s.tempo+ (s.tempo > 1 ? " minuti":" minuto"));
		}
		
	}
	
	public static void main(String...args) {
		Gara g=new GaraS(50);
		Addetto a=new Addetto(g);
		a.start();
		for(int i=0;i<50;i++) {
			SnowBoarder x=new SnowBoarder(i,g);
			x.start();
		}
	}

}
