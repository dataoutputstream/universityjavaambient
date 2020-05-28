package visualizzatore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CodaLC extends Coda {

	private Lock l;
	private Condition inserimento;
	private Condition prelievo;

	public CodaLC(int maxSize) {
		super(maxSize);
		l = new ReentrantLock();
		inserimento = l.newCondition();
		prelievo = l.newCondition();
	}


	public void inserisci(String stringhe[]) { // eseguito dagli Utenti
		l.lock();
		try {
			while (!possoInserire(stringhe.length)) {
				inserimento.await();
			}
			for (int i = 0; i < stringhe.length; i++) {
				lista.addLast(stringhe[i]);
			}
			prelievo.signal();
		} catch (InterruptedException e) {
			System.err.println(e);
		} finally {
			l.unlock();
		}
	}

	private boolean possoInserire(int n) {
		return lista.size() + n <= maxSize;
	}


	public String preleva() { // eseguito dal Visualizzatore
		String result = null;
		l.lock();
		try {
			while (!possoPrelevare()) {
				prelievo.await();
			}
			result = lista.removeFirst();
			inserimento.signal();
		} catch (InterruptedException e) {
			System.err.println(e);
		} finally {
			l.unlock();
		}
		return result;
	}

	private boolean possoPrelevare() {
		return lista.size() > 0;
	}
	
	public static void main(String[] args) {
		int maxSize = 100;
		Coda coda = new CodaLC(maxSize);
		
		coda.test();
	}
}
