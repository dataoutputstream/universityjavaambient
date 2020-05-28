package Esercizi;


public class Esercizio24 {

	public static void main(String[] args) {
		//Ipotesi di questa soluzione: il massimo di una riga (o il minimo di una colonna) 
		//è un unico elemento della riga (o della colonna). 
		/**
		 * 2 2 2 5 2
		 * 2 2 2 7 2
		 * 1 1 1 4 1
		 * 2 2 2 9 2
		 */
		int[][] matrice = { { 2, 2, 2, 5, 2 }, { 2, 2, 2, 7, 2 }, { 1, 1, 1, 4, 1 },
				{ 2, 2, 2, 9, 2 } };
		int numRighe = matrice.length;
		int numColonne = matrice[0].length;
		System.out.format("La matrice è composta da %d righe e %d colonne.%n", matrice.length, matrice[0].length);
		
		TrovaMassimo[] maxThread = new TrovaMassimo[numRighe];
		for (int i = 0; i < numRighe; i++) {
			maxThread[i] = new TrovaMassimo(matrice, i);
			maxThread[i].start();
		}
		TrovaMinimo[] minThread = new TrovaMinimo[numColonne];
		for (int j = 0; j < numColonne; j++) {
			minThread[j] = new TrovaMinimo(matrice, j);
			minThread[j].start();
		}
		
		int iMin = -1;
		int jMax = -1;
		
		if (numRighe <= numColonne) {
			for (int i = 0; i < numRighe; i++) {
				jMax = maxThread[i].getjMax();
				if (minThread[jMax].getiMin() == i) {
					iMin = i;
					break;
				}
			}
		} else {
			for (int j = 0; j < numColonne; j++) {
				iMin = minThread[j].getiMin();
				if (maxThread[iMin].getjMax() == j) {
					jMax = j;
					break;
				}
			}
		}
		if (jMax < 0 || iMin < 0) {
			System.out.println("L'elemento cercato non esiste");
		} else {
			System.out.println(String.format("L'elemento cercato si trova in posizione (%d,%d) con valore %d",iMin, jMax, matrice[iMin][jMax]));
		}
		
	}
}
