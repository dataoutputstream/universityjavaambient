package poo.polinomi;

public class TestPolinomi {
	public static void main(String[]args) {
		Polinomio p1 = new PolinomioMap();
		Polinomio p2 = new PolinomioMap();
		p1.add(new Monomio(-2, 3)); p1.add(new Monomio(3, 0));
		p2.add(new Monomio(5, 4)); p2.add(new Monomio(2, 3));
		p2.add(new Monomio(-3, 1)); p2.add(new Monomio(7, 0));
		System.out.println("p1 = " + p1);
		System.out.println("p2 = " + p2);
		System.out.println("p1 + p2 = " + p1.add(p2));
		System.out.println("p1 * p2 = " + p1.mul(p2));
		System.out.println("p1(2) = " + p1.valore(2));
		System.out.println("D(p2) = " + p2.derivata());
	} // main
} // TestPolinomi
