package piscinannmiaclasse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PiscinaLC extends Piscina {

	private Lock l = new ReentrantLock();
	private Condition[] corsiaOccupata = {l.newCondition(),l.newCondition(),l.newCondition(),l.newCondition(),l.newCondition()};
	private boolean istruttorePresente = false;
	private Condition istruttore = l.newCondition();
	private HashSet<Thread> personeInCorsia = new HashSet<>();


	@Override
	public void nuota() throws InterruptedException {
		Thread myself = Thread.currentThread();
		int corsia;
		l.lock();
		try{
			while(!istruttorePresente){
				istruttore.await();
			}
			corsia = scegliCorsia();
			System.out.printf("P%d ha scelto la corsia %d" + Arrays.toString(numPermessi) +"%n",myself.getId(),corsia);
			while(numPermessi[corsia] == 0){
				corsiaOccupata[corsia].await();
			}
			System.out.printf("P%d e' in corsia %d%n",myself.getId(),corsia);
			personeInCorsia.add(myself);
			numPermessi[corsia]--;
		}
		finally {
			l.unlock();
		}
		try{
			TimeUnit.SECONDS.sleep(random.nextInt(Persona.MAX_NUOTO - Persona.MIN_NUOTO + 1)
					+ Persona.MIN_NUOTO);
		}catch (InterruptedException e){
			l.lock();
			numPermessi[corsia]++;
			l.unlock();
			throw e;
		}
		l.lock();
		try {
			numPermessi[corsia]++;
			personeInCorsia.remove(myself);
			corsiaOccupata[corsia].signalAll();
			System.out.printf("P%d e' uscito dalla corsia %d%n",myself.getId(),corsia);
		}
		finally {
			l.unlock();
		}

	}

	@Override
	public void iniziaTurno(Istruttore.Turno turno) throws InterruptedException {
		l.lock();
		try {
			istruttorePresente = true;
			istruttore.signalAll();
			System.out.println("L'istruttore e' entrato");
		}
		finally {
			l.unlock();
		}
		TimeUnit.MINUTES.sleep(turno.getDurata()/4);
		l.lock();
		try {
			istruttorePresente = false;
			System.out.println("L'istruttore e' uscito");
			for (Thread t : personeInCorsia) {
				t.interrupt();
			}
			personeInCorsia.clear();
		}
		finally {
			l.unlock();
		}
	}

	public static void main(String[] args) {
		new PiscinaLC().test(100);
	}
}
