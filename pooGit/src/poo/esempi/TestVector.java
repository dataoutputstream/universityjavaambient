package poo.esempi;

import poo.util.*;
import java.util.Arrays;

public class TestVector {
	public static void main(String[]args) {
		System.out.println("Vector di Integer:");
		Vector<Integer> v1 = new ArrayVector<Integer>();
		v1.add(new Integer(5));
		v1.add(-3); // boxing automatico
		v1.add(7);
		System.out.println(v1);
		int i = v1.indexOf(-3);
		v1.remove(i);
		System.out.println(v1);
		int x = v1.get(0); // unboxing automatico
		System.out.println("Elemento in prima posizione: " + x);
		System.out.println("\nVector di String:");
		Vector<String> vs = new ArrayVector<String>(50);
		String[] as = {"casa", "abaco", "lupo", "zaino", "dado"};
		System.out.println("Vettore di partenza: " + Arrays.toString(as));
		for (i = 0; i < as.length; i++) {
			String s = as[i];
			int pos = 0;
			while (pos < vs.size() && vs.get(pos).compareTo(s) < 0) pos++;
			vs.add(pos, s);
		}
		System.out.println("Vector ordinato: " + vs);
	} // main
} // TestVector
