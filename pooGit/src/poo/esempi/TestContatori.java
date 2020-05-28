package poo.esempi;

import poo.contatori.*;

public class TestContatori {
	public static void main(String[]args) {
		Contatore c = new Contatore();
		for (int i = 0; i < 100; i++)
			c.incr();
		for (int i = 0; i < 10; i++)
			c.decr();
		System.out.println(c);
		ContatoreModulare cm = new ContatoreModulare(25);
		for (int i = 0; i < 100; i++)
			cm.incr();
		for (int i = 0; i < 10; i++)
			cm.decr();
		System.out.println(cm);
	} // main
} // TestContatori
