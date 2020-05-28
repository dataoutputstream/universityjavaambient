package poo.figure;

import poo.util.Mat;

public class Rettangolo extends Figura {
	private double altezza;
	public Rettangolo(double base, double altezza) {
		super(base);
		this.altezza = altezza;
	} // Costruttore normale
	public Rettangolo(Rettangolo r) {
		super(r.getDimensione());
		altezza = r.altezza;
	} // Costruttore di copia
	public double getBase() { return getDimensione(); }
	public double getAltezza() { return altezza; }
	public double perimetro() {
		return 2 * (getDimensione() + altezza);
	} // perimetro
	public double area() {
		return getDimensione() * altezza;
	} // area
	public String toString() {
		return "Rettangolo di base = " + String.format("%1.2f e altezza = %1.2f", getDimensione(), altezza);
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Rettangolo)) return false;
		if (o == this) return true;
		Rettangolo r = (Rettangolo)o;
		return Mat.circaUguali(getDimensione(), r.getDimensione()) && Mat.circaUguali(altezza, r.altezza);
	} // equals
	public int hashCode() {
		final int PRIMO = 37;
		return (new Double(getDimensione()).hashCode()) * PRIMO + new Double(altezza).hashCode();
	} // hashCode
} // Figura
