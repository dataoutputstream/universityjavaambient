package poo.polinomi;

import java.util.*;

public class PolinomioSet extends PolinomioAstratto {
	private TreeSet<Monomio> elenco = new TreeSet<Monomio>();
	protected PolinomioSet crea() {
		return new PolinomioSet();
	} // crea
	public int size() {
		return elenco.size();
	} // size
	public void add(Monomio m) {
		if (m.getCoeff() != 0) {
			Monomio m1 = elenco.ceiling(m);
			if (m1 != null && m1.equals(m)) {
				elenco.remove(m1);
				Monomio somma = m1.add(m);
				if (somma.getCoeff() != 0)
					elenco.add(somma);
			} else elenco.add(m);
		}
	} // add
	public Iterator<Monomio> iterator() {
		return elenco.iterator();
	} // iterator
} // PolinomioSet
