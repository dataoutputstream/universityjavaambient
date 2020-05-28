package poo.eratostene;

import java.util.*;

public class CrivelloTreeSet extends CrivelloAstratto {
	private Set<Integer> crivello = new TreeSet<Integer>();
	private Set<Integer> primi = new TreeSet<Integer>();
	private final int N; // Attende inizializzazione
	public CrivelloTreeSet(int N) {
		if (N < 2) throw new RuntimeException("N minore di 2!");
		this.N = N;
		for (int i = 2; i <= N; i++) crivello.add(i);
	} // Costruttore
	public int size() { return primi.size(); }
	public void filtra() {
		int minimo, multiplo;
		while (!crivello.isEmpty()) {
			minimo = crivello.iterator().next();
			primi.add(minimo);
			multiplo = minimo;
			while (multiplo <= N) {
				crivello.remove(multiplo);
				multiplo += minimo;
			}
		}
	} // filtra
	public Iterator<Integer> iterator() {
		return primi.iterator();
	} // iterator
} // CrivelloTreeSet
