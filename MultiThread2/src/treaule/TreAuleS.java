package treaule;

import java.util.concurrent.Semaphore;

public class TreAuleS extends TreAule{
	Semaphore mutex =new Semaphore(1);
	Semaphore nuovoStudente=new Semaphore(0);
	Semaphore entro=new Semaphore(0,true);
	Semaphore[] Aula=new Semaphore[3];
	Semaphore uscito=new Semaphore(0);
	
	public TreAuleS() {
		Aula[0]=new Semaphore(80);
		Aula[1]=new Semaphore(60);
		Aula[2]=new Semaphore(40);
	}

	@Override
	public int accedi() throws InterruptedException {
		nuovoStudente.release();
		entro.acquire();
		int aula=ultimAula;
		System.out.println("Lo studente"+((Studente)Thread.currentThread()).id+" è entrato nell'aula: "+ aula);
		uscito.release();
		return aula;
	}

	@Override
	public void esci(int aula) throws InterruptedException {
		mutex.acquire();
		aule[aula]++;
		System.out.println("Lo studente"+((Studente)Thread.currentThread()).id+" è uscito dall'aula: "+ ((Studente)Thread.currentThread()).aulaAcceduta);
		mutex.release();
		Aula[aula].release();
		
		
	}

	@Override
	public void chiamaStudente() throws InterruptedException {
		nuovoStudente.acquire();
		ultimAula=aggiornaAula();
		Aula[ultimAula].acquire();
		mutex.acquire();
		aule[ultimAula]--;
		mutex.release();
		entro.release();
		uscito.acquire();
	}
	
	public static void main(String...Args) {
		TreAule aule=new TreAuleS();
		Docente d=new Docente(aule);
		d.start();
		for(int i=0;i<200;i++) {
			Studente s=new Studente(aule,i);
			s.start();
		}
	}

}
