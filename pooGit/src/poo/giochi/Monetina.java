package poo.giochi;

public class Monetina {
	public enum Faccia {TESTA, CROCE}
	private Faccia faccia;
	public Monetina() { lancia(); }
	public void lancia() {
		faccia = (Math.random() < 0.5 ? Faccia.TESTA : Faccia.CROCE);
	} // lancia
	public Faccia getFaccia() { return faccia; }
	public String toString() {
		return (faccia == Faccia.TESTA ? "Testa" : "Croce");
	} // toString
	public static void main(String[]args) {
		Monetina m = new Monetina();
		int testa = 0, croce = 0;
		for (int i = 0; i < 1000; i++) {
			m.lancia();
			if (m.getFaccia() == Monetina.Faccia.TESTA)
				testa++;
			else
				croce++;
		}
		System.out.printf("Su mille lanci: %d volte testa, %d volte croce!\n", testa, croce);
		Monetina m1 = new Monetina();
		Monetina m2 = new Monetina();
		boolean treTeste = false; int testem1 = 0, testem2 = 0;
		while (!treTeste) {
			System.out.println("Lancio monetina 1: " + m1 + " | Lancio monetina 2: " + m2);
			if (m1.getFaccia() == Monetina.Faccia.TESTA) testem1++; else testem1 = 0;
			if (m2.getFaccia() == Monetina.Faccia.TESTA) testem2++; else testem2 = 0;
			if (testem1 == 3 || testem2 == 3) treTeste = true;
			m1.lancia(); m2.lancia();
		}
		if (testem1 == testem2) System.out.println("Parità!");
		else
			if (testem1 == 3) System.out.println("Vince la monetina 1!");
			else System.out.println("Vince la monetina 2!");
	} // main
} // Monetina
