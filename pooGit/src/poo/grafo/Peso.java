package poo.grafo;

import poo.util.*;

public class Peso implements Comparable<Peso> {
	public static final double INFINITO = Double.POSITIVE_INFINITY;
	private double val;
	public Peso() { this(0D); }
	public Peso(double val) { this.val = val; }
	public double val() { return val; }
	public void setVal(double val) { this.val = val; }
	public Peso piu(Peso p) {
		Peso somma = new Peso();
		if (val == INFINITO || p.val == INFINITO)
			somma.setVal(INFINITO);
		else somma.setVal(val + p.val);
		return somma;
	} // piu
	public String toString() {
		if (val == INFINITO) return "oo";
		else return "" + val;
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Peso)) return false;
		if (o == this) return true;
		Peso p = (Peso)o;
		return Mat.circaUguali(val, p.val);
	} // equals
	public int hashCode() { return new Double(val).hashCode(); }
	public int compareTo(Peso p) {
		if (val == p.val) return 0;
		if (val != INFINITO && p.val == INFINITO) return -1;
		if (val == INFINITO && p.val != INFINITO) return 1;
		if (Mat.circaUguali(val, p.val)) return 0;
		if (val < p.val) return -1;
		return 1;
	} // compareTo
} // Peso
