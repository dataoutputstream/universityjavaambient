package poo.figure;

import poo.geometria.FiguraPiana;

public abstract class Figura implements FiguraPiana {
	private double dimensione;
	public Figura(double dimensione) {
		if (dimensione <= 0) throw new IllegalArgumentException();
		this.dimensione = dimensione;
	} // Costruttore base
	protected double getDimensione() { return dimensione; }
	public abstract double perimetro();
	public abstract double area();
	public String toString() {
		return String.format("%1.2f\n", dimensione);
	} // toString
} // Figura
