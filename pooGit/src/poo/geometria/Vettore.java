package poo.geometria;

import poo.util.Mat;

public class Vettore {
	private double x, y, z; // Componenti del vettore
	public Vettore(double x, double y, double z) {
		this.x = x; this.y = y; this.z = z;
	} // Costruttore normale
	public Vettore(Vettore v) {
		x = v.x; y = v.y; z = v.z;
	} // Costruttore di copia
	public double modulo() {
		return Math.sqrt(x * x + y * y + z * z);
	} // modulo
	public double prodottoScalare(Vettore v) {
		return x * v.x + y * v.y + z * v.z;
	} // prodottoScalare
	public Vettore prodottoVettoriale(Vettore v) {
		return new Vettore(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	} // prodottoVettoriale
	public boolean ortogonale(Vettore v) {
		return (Mat.circaUguali(prodottoScalare(v), 0));
	} // ortogonale
	public boolean parallelo(Vettore v) {
		return (Mat.circaUguali(x / v.x, y / v.y) && Mat.circaUguali(y / v.y, z / v.z));
	} // parallelo
	public boolean nullo() {
		return (Mat.circaUguali(x, 0) && Mat.circaUguali(y, 0) && Mat.circaUguali(z, 0)); 
	} // nullo
	public Vettore add(Vettore v) {
		return new Vettore(x + v.x, y + v.y, z + v.z);
	} // add
	public Vettore sub(Vettore v) {
		return new Vettore(x - v.x, y - v.y, z - v.z);
	} // sub
	public static boolean verificaBase(Vettore v1, Vettore v2, Vettore v3) {
		return ( (v1.x * v2.y * v3.z + v2.x * v3.y * v1.z + v3.x * v1.y * v2.z) -
			 (v2.x * v1.y * v3.z + v1.x * v3.y * v2.z + v3.x * v2.y * v1.z) != 0);
	} // verificaBase
	public static boolean verificaBaseOrtonormale(Vettore v1, Vettore v2, Vettore v3) {
		return (verificaBase(v1, v2, v3)
			&& Mat.circaUguali(v1.modulo(), 1) && Mat.circaUguali(v2.modulo(), 1) && Mat.circaUguali(v3.modulo(), 1) &&
			v1.ortogonale(v2) && v2.ortogonale(v3) && v3.ortogonale(v1)); 
	} // verificaBaseOrtonormale
	public boolean equals(Object o) {
		if (this == o) return true;
		if ( !(o instanceof Vettore) ) return false;
		Vettore v = (Vettore)o;
		return (Mat.circaUguali(x, v.x) && Mat.circaUguali(y, v.y) && Mat.circaUguali(z, v.z));
	} // equals
	public String toString() {
		return String.format("(%1.2f, %1.2f, %1.2f)", x, y, z);
	} // toString
} // Vettore
