package poo.complex;

import poo.util.Mat;

public class Complex {
	private double re, im;
	public Complex(double re, double im) {
		this.re = re; this.im = im;
	} // Costruttore normale
	public Complex(Complex z) {
		re = z.re; im = z.im;
	} // Costruttore di default
	public double getRe() { return re; } // Metodi
	public double getIm() { return im; } // 'getter'
	public double modulo() {
		return Math.sqrt(re * re + im * im);
	} // modulo
	public Complex coniugato() {
		return new Complex(re, -im);
	} // coniugato
	public Complex add(Complex z) {
		return new Complex(re + z.re, im +z.im);
	} // add
	public Complex sub(Complex z) {
		return new Complex(re - z.re, im - z.im);
	} // sub
	public Complex mul(Complex z) {
		return new Complex(re * z.re - im * z.im, re * z.im + im * z.re);
	} // mul
	public Complex div(Complex z) {
		double den = z.mul(z.coniugato()).getRe();
		return new Complex((re * z.re + im * z.im)  / den, (im * z.re - re * z.im) / den);
	} // div
	public Complex mult(double s) {
		return new Complex(s * re, s * im);
	} // mult
	public String toString() {
		return String.format("%1.2f ", re) + ( im > 0 ? "+ j" + String.format("%1.2f", im) : "- j" + String.format("%1.2f", -im) );
	} // toString
	public boolean equals(Object obj) {
		if (this == obj) return true; // Stesso riferimento -> Stesso oggetto
		if ( !(obj instanceof Complex) ) return false; // Non è un oggetto Complex
		Complex z = (Complex)obj; // Converto l'oggetto generico in un tipo Complex
		if (Mat.circaUguali(re, z.re) && Mat.circaUguali(im, z.im)) return true; // I due Complex hanno gli stessi valori
		return false;
	} // equals
} // Complex
