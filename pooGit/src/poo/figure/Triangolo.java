package poo.figure;

import poo.util.Mat;

public class Triangolo extends Figura {
	private double b, c;
	public Triangolo(double a, double b, double c) {
		super(a);
		if (b <= 0 || c <= 0) throw new IllegalArgumentException();
		this.b = b; this.c = c;
	} // Costruttore normale
	public Triangolo(Triangolo t) {
		super(t.getDimensione());
		b = t.b; c = t.c;
	} // Costruttore di copia
	public double getA() { return getDimensione(); }
	public double getB() { return b; }
	public double getC() { return c; }
	public double perimetro() {
		return getDimensione() + b + c;
	} // perimetro
	public double area() {
		double a = getDimensione();
		double s = (a + b + c) / 2;
		return Math.sqrt(s * (s - a) * (s - b) * (s - c));
	} // area
	public String toString() {
		return "Triangolo di lati: " + String.format("%1.2f, %1.2f e %1.2f", getDimensione(), b, c);
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Triangolo)) return false;
		if (o == this) return true;
		Triangolo t = (Triangolo)o;
		return Mat.circaUguali(getDimensione(), t.getDimensione()) && Mat.circaUguali(b, t.b) && Mat.circaUguali(c, t.c);
	} // equals
	public int hashCode() {
		final int PRIMO = 13;
		int hash = (new Double(getDimensione()).hashCode()) * PRIMO;
		hash = (hash + new Double(b).hashCode()) * PRIMO;
		hash = (hash + new Double(c).hashCode());
		return hash;
	} // hashCode
} // Figura
