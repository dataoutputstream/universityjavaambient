package Esercizi;

public class ProdottoScalare extends Thread {

	private int a[];
	private int b[];
	private int inizio, fine;
	private int prodottoScalare = 0;

	public ProdottoScalare(int[] a, int[] b, int i, int f) {
		this.a = a;
		this.b = b;
		this.inizio = i;
		this.fine = f;
	}

	@Override
	public void run() {
		for (int i = inizio; i <= fine; i++) {
			prodottoScalare += a[i] * b[i];
		}
	}

	public int getProdottoScalare() {
		try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return prodottoScalare;
	}

}
