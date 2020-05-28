package poo.esempi;

import poo.razionali.Razionale;

public class TestRazionali {
	public static void main(String[]args) {
		Razionale dueTerzi = new Razionale(2, 3);
		Razionale menoTreQuinti = new Razionale(3, -5);
		Razionale somma = dueTerzi.add(menoTreQuinti);
		System.out.println("" + dueTerzi + menoTreQuinti + " = " + somma);
		System.out.println("(" + dueTerzi + ") * (" + menoTreQuinti + ") = " + dueTerzi.mul(menoTreQuinti));
		System.out.println("" + dueTerzi + " * (-3) = " + dueTerzi.mul(-3));
		System.out.println(new Razionale(0, -3));
		Razionale unMezzo = new Razionale(1, 2);
		Razionale treQuindicesimi = new Razionale(3, 15);
		Razionale quattroDodicesimi = new Razionale(4, 12);
		Razionale treNoni = new Razionale(3, 9);
		Razionale dodiciSesti = new Razionale(12, 6);
		Razionale ris = unMezzo.add(treQuindicesimi.mul(quattroDodicesimi)).sub(treNoni.div(dodiciSesti));
		// L'istruzione di sopra calcola l'espressione: (1/2) + (3/15) * (4/12) - (3/9) / (12/6) = 2/5
		// Verifichiamo il risultato:
		System.out.println("(1/2) + (3/15) * (4/12) - (3/9) / (12/6) = " + ris);
		System.out.println("1/2 = 3/6 ? " + unMezzo.equals(new Razionale(3, 6)));
	} // main
} // TestRazionali
