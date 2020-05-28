package poo.figure;

import poo.util.Mat;

public class Quadrato extends Figura {
	public Quadrato(double lato) {
		super(lato);
	} // Costruttore normale
	public Quadrato(Quadrato q) {
		super(q.getDimensione());
	} // Costruttore di copia
	public double getLato() { return getDimensione(); }
	public double perimetro() {
		return getDimensione() * 4;
	} // perimetro
	public double area() {
		double l = getDimensione();
		return l * l;
	} // area
	public String toString() {
		return "Quadrato di lato = " + String.format("%1.2f", getDimensione());
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Quadrato)) return false;
		if (o == this) return true;
		Quadrato q = (Quadrato)o;
		return Mat.circaUguali(getDimensione(), q.getDimensione());
	} // equals
	public int hashCode() {
		return new Double(getDimensione()).hashCode();
	} // hashCode
}
