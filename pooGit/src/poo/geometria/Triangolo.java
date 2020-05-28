package poo.geometria;

import poo.util.Mat;

public class Triangolo implements FiguraPiana {
	private Punto p1, p2, p3;
	private double a, b, c;
	public enum Tipo { Equilatero, Isoscele, Scaleno }
	public Triangolo(Punto p1, Punto p2, Punto p3) {
		a = p1.distanza(p2); b = p2.distanza(p3); c = p3.distanza(p1); // Lati
		if ( Mat.circaUguali(a + b, c) || Mat.circaUguali(b + c, a) || Mat.circaUguali(c + a, b) ) { // Se i punti sono allineati:
			System.out.println("Triangolo inesistente!");
			System.exit(-1);
		}
		this.p1 = new Punto(p1); this.p2 = new Punto(p2); this.p3 = new Punto(p3);
	} // Costruttore normale
	public Triangolo(Triangolo t) {
		p1 = new Punto(t.p1);
		p2 = new Punto(t.p2);
		p3 = new Punto(t.p3);
		a = t.a; b = t.b; c = t.c;
	} // Costruttore copia
	public double getA() { return a; } //
	public double getB() { return b; } // Accesso ai lati
	public double getC() { return c; } //
	public Punto[] getVertici() {
		return new Punto[] { new Punto(p1), new Punto(p2), new Punto(p3) };
	} // vertici
	public double perimetro() {
		return a + b + c;
	} // perimetro
	public double area() {
		double s = (a + b + c) / 2;
		return Math.sqrt(s * (s - a) * (s - b) * (s - c)); // Erone
	} // area
	public Tipo tipo() {
		if (Mat.circaUguali(a, b) && Mat.circaUguali(b, c)) return Tipo.Equilatero;
		else if (Mat.circaUguali(a, b) || Mat.circaUguali(b, c) || Mat.circaUguali(c, a)) return Tipo.Isoscele;
		return Tipo.Scaleno;
	} // tipo
	public String toString() {
		return "Triangolo " + tipo() + " di vertici: " + p1 + " " + p2 + " " + p3;
	} // toString
	public static void main(String[]args) {
		Triangolo t = new Triangolo(new Punto(), new Punto(2, 0), new Punto(2, 2));
		System.out.println(t);
		System.out.printf("Perimetro: %1.2f \n", t.perimetro());
		System.out.printf("Area: %1.2f \n", t.area());
		System.out.println("Primo vertice: " + t.getVertici()[0]);
	} // main
} // Triangolo
