package poo.thread.scambiatore;

public class ExchangerMJ<T> implements Exchanger<T> {
	private T primo, secondo;
	private boolean partner = false, rilascio = false;
	private Object lock = new Object();
	public T exchange(T msg) {
		synchronized(lock) {
			while (rilascio)
				try { lock.wait(); }
				catch(InterruptedException e) {}
			if (partner) {
				secondo = msg;
				T t = primo;
				partner = false;
				rilascio = true;
				lock.notify();
				return t;
			}
			primo = msg;
			partner = true;
			while (partner)
				try { lock.wait(); } catch(InterruptedException e) {}
			T t = secondo;
			rilascio = false;
			lock.notify();
			return t;
		}
	}
} // ExchangerMJ
