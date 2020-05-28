package poo.esempi;

import java.util.*;

public class TestSet {
	static Scanner sc = null, sl = null;
	static String l = null;
	public static void main(String[]args) {
		sc = new Scanner(System.in);
		System.out.println("Inserisci gli elementi del primo insieme (numeri interi, solo INVIO per terminare):");
		Set<Integer> s1 = new HashSet<Integer>();
		riempi(s1);
		System.out.println("Inserisci gli elementi del secondo insieme (numeri interi, solo INVIO per terminare):");
		Set<Integer> s2 = new HashSet<Integer>();
		riempi(s2);
		System.out.println("Insieme s1: " + s1);
		System.out.println("Insieme s2: " + s2);
		Set<Integer> s3 = new HashSet<Integer>(s1);
		Set<Integer> s4 = new HashSet<Integer>(s1);
		Set<Integer> s5 = new HashSet<Integer>(s1);
		Set<Integer> s6 = new HashSet<Integer>(s1);
		s3.retainAll(s2);
		System.out.println("Insieme intersezione: " + s3);
		s4.removeAll(s2);
		System.out.println("Insieme differenza s1 - s2: " + s4);
		s5.addAll(s2);
		System.out.println("Insieme unione: " + s5);
		s6.addAll(s2); s6.removeAll(s3);
		System.out.println("Insieme differenza simmetrica (unione meno intersezione): " + s6);
		Set<Integer> t = new TreeSet<Integer>(s1);
		System.out.println("Insieme s1 ordinato: " + t);
	} // main
	public static void riempi(Set<Integer> s) {
		for (;;) {
			l = sc.nextLine();
			if (l.equals(""))
				break;
			try {
				sl = new Scanner(l);
				while (sl.hasNext())
					s.add(Integer.parseInt(sl.next()));
			} catch (NumberFormatException e) {
				System.out.println("Gli elementi devono essere numeri interi!");
			}
		}
	}
} // TestSet
