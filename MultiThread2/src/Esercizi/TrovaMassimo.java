package Esercizi;

public class TrovaMassimo extends Thread {
	private int[][] matrice;
	private int riga;
	private int numColonne;
	private int jMax;

	public TrovaMassimo(int[][] matrice, int riga) {
		this.matrice = matrice;
		this.riga = riga;
		numColonne = matrice[0].length;
	}

	@Override
	public void run() {
		jMax = 0;
		int max = matrice[riga][0];
		for (int j = 1; j < numColonne; j++) {
			if (matrice[riga][j] > max) {
				max = matrice[riga][j];
				jMax = j;
			}
		}
	}

	public int getjMax() {
		try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return jMax;
	}
}
