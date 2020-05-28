package poo.esempi;

import poo.complex.Complex;
import poo.razionali.Razionale;

public class TestComplex {
	public static void main(String[]args) {
		Complex z1 = new Complex(-3, 5);
		Complex z2 = new Complex(4, -7);
		System.out.printf("" + z1 + " | Parte reale: %1.2f , Parte immaginaria: %1.2f\n", z1.getRe(), z1.getIm());
		System.out.printf("Modulo di " + z2 + ": %1.2f\n", z2.modulo());
		System.out.println("Coniugato di " + z1 + ": " + z1.coniugato());
		System.out.println("Dati z1 = " + z1 + " e z2 = " + z2 + ":");
		System.out.println("z1 + z2 = " + z1.add(z2));
		System.out.println("z1 - z2 = " + z1.sub(z2));
		System.out.println("z1 * z2 = " + z1.mul(z2));
		System.out.println("z1 / z2 = " + z1.div(z2));
		System.out.println("z1 * 3 = " + z1.mult(3));
		Complex z3 = new Complex(4, -7);
		Razionale r = new Razionale(-3, 5);
		System.out.println(z2.equals(z3)); // True!
		System.out.println(z1.equals(r)); // False: tipo diverso!
	} // main
} // TestComplex
