package Esercizi;

public class TrovaMinimo extends Thread {
	private int[][] matrice;
	private int colonna;
	private int numRighe;
	private int iMin;

	public TrovaMinimo(int[][] matrice, int colonna) {
		this.matrice = matrice;
		this.colonna = colonna;
		numRighe = matrice.length;
	}

	@Override
	public void run() {
		iMin = 0;
		int min = matrice[0][colonna];
		for (int i = 1; i < numRighe; i++) {
			if (matrice[i][colonna] < min) {
				min = matrice[i][colonna];
				iMin = i;
			}
		}
	}

	public int getiMin() {
		try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return iMin;
	}
}
