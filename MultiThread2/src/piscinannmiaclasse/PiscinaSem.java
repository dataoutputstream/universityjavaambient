package piscinannmiaclasse;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PiscinaSem extends Piscina {

	private Random random;
	private Semaphore mutex;
	private Semaphore[] semCorsie;
	private int[] numPermessi;
	private HashSet<Thread> personeInCorsia;

	public PiscinaSem(){
		mutex = new Semaphore(0);
		personeInCorsia = new HashSet<>();
		semCorsie = new Semaphore[NUM_CORSIE];
		numPermessi = new int[NUM_CORSIE];
		for (int i = 0; i < NUM_CORSIE; i++) {
			semCorsie[i] = new Semaphore(MAX_PERSONE,true);
		}
	}

	@Override
	public void nuota() throws InterruptedException {
		Thread myself = Thread.currentThread();
		mutex.acquire();
		int corsia = scegliCorsia();
		System.out.printf("P%d ha scelto la corsia %d" + Arrays.toString(numPermessi) +"%n",myself.getId(),corsia);
		mutex.release();

		semCorsie[corsia].acquire();
		mutex.acquire();
		System.out.printf("P%d e' in corsia %d%n",myself.getId(),corsia);
		personeInCorsia.add(myself);
		numPermessi[corsia]--;
		mutex.release();
		try {
			TimeUnit.SECONDS.sleep(random.nextInt(
					Persona.MAX_NUOTO - Persona.MIN_NUOTO + 1)
					+ Persona.MIN_NUOTO);
			System.out.printf("P%d e' uscito dalla corsia %d%n",myself.getId(),corsia);
			mutex.acquire();
			numPermessi[corsia]++;
			personeInCorsia.remove(myself);
			mutex.release();
			semCorsie[corsia].release();
		} catch (InterruptedException e) {
			semCorsie[corsia].release();
			throw e;
		}
	}

	@Override
	public void iniziaTurno(Istruttore.Turno turno) throws InterruptedException {
		System.out.println("L'istruttore e' entrato");
		mutex.release();
		TimeUnit.MINUTES.sleep(turno.getDurata() / 4);
		mutex.acquire();
		System.out.println("L'istruttore e' uscito");
		for(Thread t : personeInCorsia) {
			t.interrupt();
		}
		personeInCorsia.clear();
		for (int i = 0; i < NUM_CORSIE; i++) {
			numPermessi[i] = MAX_PERSONE;
		}
	}

	public static void main(String[] args) {
		new PiscinaSem().test(100);
	}
}
