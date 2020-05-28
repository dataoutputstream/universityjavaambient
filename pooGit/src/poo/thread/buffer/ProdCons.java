package poo.thread.buffer;

import poo.util.*;

public class ProdCons {
	public static void main(String[]args) {
		BufferLimitato<String> b = new BufferLimitatoLC<String>(5);
		Produttore p1 = new Produttore(1, b, 10000, 2000);
		Produttore p2 = new Produttore(2, b, 5000, 1000);
		Produttore p3 = new Produttore(3, b, 20000, 5000);
		Consumatore c1 = new Consumatore(1, b, 10000, 5000);
		Consumatore c2 = new Consumatore(2, b, 8000, 1000);
		p1.start(); p2.start(); p3.start();
		c1.start(); c2.start();
	} // main
} // ProdCons
