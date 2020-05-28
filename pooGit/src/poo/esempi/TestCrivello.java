package poo.esempi;

import poo.eratostene.*;

public class TestCrivello {
	public static void main(String[]args) {
		Crivello cl = new CrivelloLinkedSet(100000);
		Crivello ct = new CrivelloTreeSet(100000);
		Crivello cb = new CrivelloBitSet(100000);
		long start1 = System.currentTimeMillis();
		cl.filtra();
		long end1 = System.currentTimeMillis();
		long start2 = System.currentTimeMillis();
		ct.filtra();
		long end2 = System.currentTimeMillis();
		long start3 = System.currentTimeMillis();
		cb.filtra();
		long end3 = System.currentTimeMillis();
		System.out.println(cb);
		System.out.println("Tempo di calcolo con CrivelloLinkedSet: " + (end1 - start1) + " millisecondi.");
		System.out.println("Tempo di calcolo con CrivelloTreeSet: " + (end2 - start2) + " millisecondi.");
		System.out.println("Tempo di calcolo con CrivelloBitSet: " + (end3 - start3) + " millisecondi.");
	} // main
} // TestCrivello
