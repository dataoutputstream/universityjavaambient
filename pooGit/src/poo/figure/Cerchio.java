package poo.figure;

import poo.util.Mat;

public class Cerchio extends Figura {
	public Cerchio(double r) {
		super(r);
	} // Costruttore normale
	public Cerchio(Cerchio c) {
		super(c.getDimensione());
	} // Costruttore di copia
	public double getRaggio() {
		return getDimensione();
	} // getRaggio
	public double perimetro() {
		return 2 * Math.PI * getDimensione();
	} // perimetro
	public double area() {
		double r = getDimensione();
		return Math.PI * r * r;
	} // area
	public String toString() {
		return "Cerchio di raggio = " + getDimensione();
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Cerchio)) return false;
		if (o == this) return true;
		Cerchio c = (Cerchio)o;
		return Mat.circaUguali(getDimensione(), c.getDimensione());
	} // equals
	public int hashCode() {
		return new Double(getDimensione()).hashCode();
	} // hashCode
} // Cerchio
