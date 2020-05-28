package Esercizi;



public class Esercizio23 {
	public static void main(String[] args) {
		int a[] = { 1, 5, 3, 6, 1, 3, 4, 2, 56, 7, 7, 2 };
		int b[] = { 2, 3, 5, 1, 32, 4, 3, 4, 3, 4, 3, 21 };
		int m = 3;
		int n = a.length;
		ProdottoScalare p[] = new ProdottoScalare[m];
		int porzione = n / m;
		for (int i = 0; i < p.length; i++) {
			int inizio = i * porzione;
			int fine = inizio + porzione - 1;
			p[i] = new ProdottoScalare(a, b, inizio, fine);
			p[i].start();
		}
		int ps = 0;
		for (int i = 0; i < p.length; i++) {
			ps += p[i].getProdottoScalare();
			System.out.println("Prodotto scalare del thread " + i + "="
					+ p[i].getProdottoScalare());
		}
		System.out.println("Prodotto scalare = " + ps);
	}
}
