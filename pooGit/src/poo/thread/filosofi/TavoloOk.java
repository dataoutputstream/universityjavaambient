package poo.thread.filosofi;

public class TavoloOk implements Tavolo {
	private boolean forchetta[];
	public TavoloOk (int n) {
		if (n <= 1) throw new IllegalArgumentException();
		forchetta = new boolean[n];
		for (int i = 0; i < forchetta.length; i++)
			forchetta[i] = true;
	}
	public synchronized void ottieniForchette(int id) {
		if (id < 0 || id >= forchetta.length)
			throw new IllegalArgumentException();
		while (!forchetta[id] || !forchetta[(id + 1) % forchetta.length])
			try { wait(); } catch (InterruptedException ie) {}
		forchetta[id] = false;
		forchetta[(id + 1) % forchetta.length] = false;
	} // ottieniForchette
	public synchronized void rilasciaForchette(int id) {
		if (id < 0 || id >= forchetta.length)
			throw new IllegalArgumentException();
		forchetta[id] = true;
		forchetta[(id + 1) % forchetta.length] = true;
		notifyAll();
	} // rilasciaForchette
} // Tavolo
