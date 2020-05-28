package poo.polinomi;

import java.util.*;

public class PolinomioAL extends PolinomioAstratto {
	private ArrayList<Monomio> elenco;
		public PolinomioAL() {
			this(50);
		} // Costruttore di default
		public PolinomioAL(int n) {
			if (n <= 0) throw new IllegalArgumentException();
			elenco = new ArrayList<Monomio>(n);
		} // Costruttore normale
		protected PolinomioAL crea() {
			return new PolinomioAL();
		} // crea
		public int size() {
			return elenco.size();
		} // size
		public void add(Monomio m) {
			if (m.getCoeff() != 0) {
				int i = Collections.binarySearch(elenco, m);
				if (i >= 0) {
					Monomio somma = elenco.get(i).add(m);
					if (somma.getCoeff() != 0) elenco.set(i, somma);
					else elenco.remove(i);
				} else {
					int pos = 0;
					while (pos < elenco.size() && elenco.get(pos).compareTo(m) < 0) pos++;
					elenco.add(pos, m);
				}
			}
		} // add
		public Iterator<Monomio> iterator() {
			return elenco.iterator();
		} // iterator
} // PolinomioAL
