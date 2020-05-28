package poo.thread.generatore;

public class GeneratoreInterrompibile extends Thread {
	private int seed, id;
	public GeneratoreInterrompibile(int id, int seed) {
		this.id = id; this.seed = seed;
	}
	public void run() {
		while (!isInterrupted()) {
			System.out.println("Generatore#" + id + " produce " + seed);
			seed++;
		}
	} // run
} // Generatore
