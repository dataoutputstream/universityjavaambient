package poo.razionali;

import poo.util.Mat;

public class Razionale implements Comparable<Razionale> {
	private int num, den;
	public Razionale(int n, int d) {
		if (d == 0) {
			System.out.println("Denominatore nullo!");
			System.exit(-1);
		}
		if (n != 0) {
			int MCD = Mat.mcd(Math.abs(n), Math.abs(d));
			n /= MCD; d /= MCD; // Riduzione ai minimi termini
		}
		if (d < 0) { n *= -1; d *= -1; } // Porto il segno al numeratore
		num = n; den = d;
	} // Costruttore normale
	public Razionale(Razionale r) {
		num = r.num; den = r.den;
	} // Costruttore copia
	public int getNum() { return num; }
	public int getDen() { return den; }
	public Razionale mul(int s) {
		return new Razionale(s * num, den);
	} // Moltiplicazione per uno scalare
	public Razionale mul(Razionale r) {
		return new Razionale(num * r.num, den * r.den);
	} // Moltiplicazione per un altro Razionale
	public Razionale div(Razionale r) {
		return new Razionale(num * r.den, den * r.num);
	} // Divisione per un altro Razionale
	public Razionale add(Razionale r) {
		int mcm = Mat.mcm(den, r.den);
		return new Razionale((mcm / den) * num + (mcm / r.den) * r.num, mcm);
	} // Addizione con un altro Razionale
	public Razionale sub(Razionale r) {
		int mcm = Mat.mcm(den, r.den);
		return new Razionale((mcm / den) * num - (mcm / r.den) * r.num, mcm);
	} // Sottrazione con un altro Razionale
	public String toString() {
		return (num == 0 ? "0" : (num > 0 ? "+" : "") + num + (den != 1 ? "/" + den : "") );
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Razionale)) return false;
		if (o == this) return true;
		Razionale r = (Razionale)o;
		return num == r.num && den == r.den;
	} // equals
	public int hashCode() {
		final int PRIMO = 179;
		return num * PRIMO + den;
	} // hashCode
	public int compareTo(Razionale r) {
		int md = Mat.mcm(den, r.den);
		int n1 = md / den * num; int n2 = md / r.den * r.num;
		return n1 - n2;
	} // compareTo
} // Razionale
