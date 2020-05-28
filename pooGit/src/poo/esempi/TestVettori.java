package poo.esempi;

import poo.geometria.Vettore;

public class TestVettori {
	public static void main(String[]args) {
		Vettore v1 = new Vettore(2, 3, 1);
		System.out.printf("Modulo del vettore " + v1 + ": %1.2f\n", v1.modulo());
		Vettore v2 = new Vettore(2, 1, -7);
		System.out.println("I vettori " + v2 + " e " + v1 + (v2.ortogonale(v1) ? " " : " non ") + "sono ortogonali");
		Vettore v3 = new Vettore(4, 6, 2);
		System.out.println("I vettori " + v3 + " e " + v1 + (v3.parallelo(v1) ? " " : " non ") + "sono paralleli");
		System.out.println("I vettori " + v1 + ", " + v2 + " e " + v3 + (Vettore.verificaBase(v1, v2, v3) ? " " : " non ") + "formano una base di R3");
		System.out.println("" + v1 + " + " + v2 + " = " + v1.add(v2));
		Vettore e1 = new Vettore(1, 0, 0); Vettore e2 = new Vettore(0, 1, 0); Vettore e3 = new Vettore(0, 0, 1);
		System.out.println("" + e1 + ", " + e2 + " " + e3 + (Vettore.verificaBaseOrtonormale(e1, e2, e3) ? " " : " non ") + "formano una base ortonormale di R3");
		System.out.println(v1.equals(v1));
		System.out.println(v1.equals(v3));
	} // main
} // TestVettori
