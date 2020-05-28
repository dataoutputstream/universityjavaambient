package poo.polinomi;

public interface Polinomio extends Iterable<Monomio> {
	int size();
	void add(Monomio m);
	Polinomio add(Polinomio p);
	Polinomio mul(Monomio m);
	Polinomio mul(Polinomio p);
	double valore(double x);
	Polinomio derivata();
} // Polinomio
