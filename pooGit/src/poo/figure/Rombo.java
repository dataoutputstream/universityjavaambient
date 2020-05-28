package poo.figure;

import poo.util.Mat;

public class Rombo extends Figura {
	private double d1, d2;
	public Rombo(double d1, double d2) {
		super(Math.sqrt(d1 * d1 + d2 * d2) / 2); // Considero 'dimensione' come lato del rombo
		if (d1 <= 0 || d2 <= 0) throw new IllegalArgumentException();
		this.d1 = d1;
		this.d2 = d2;
	} // Costruttore normale
	public Rombo(Rombo r) {
		super(r.getDimensione());
		d1 = r.d1; d2 = r.d2;
	} // Costruttore di copia
	public double getLato() { return getDimensione(); }
	public double getDiagonalePrincipale() { return d1; }
	public double getDiagonaleSecondaria() { return d2; }
	public double perimetro() {
		return 4 * getDimensione();
	} // perimetro
	public double area() {
		return d1 * d2 / 2;
	} // area
	public String toString() {
		return "Rombo di lato = " + String.format("%1.2f e diagonali = %1.2f e %1.2f", getDimensione(), d1, d2);
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Rombo)) return false;
		if (o == this) return true;
		Rombo r = (Rombo)o;
		return Mat.circaUguali(d1, r.d1) && Mat.circaUguali(d2, r.d2);
	} // equals
	public int hashCode() {
		final int PRIMO = 59;
		return (new Double(d1).hashCode()) * PRIMO + new Double(d2).hashCode();
	} // hashCode
} // Rombo
