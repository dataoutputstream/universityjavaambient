package poo.polinomi;

import java.util.*;

public class PolinomioMap extends PolinomioAstratto {
	private TreeMap<Integer, Monomio> elenco = new TreeMap<Integer, Monomio>();
	protected PolinomioMap crea() {
		return new PolinomioMap();
	} // crea
	public int size() {
		return elenco.size();
	} // size
	public void add(Monomio m) {
		if (m.getCoeff() != 0) {
			Monomio m1 = elenco.get(m.getGrado());
			if (m1 == null) elenco.put(m.getGrado(), m);
			else {
				Monomio somma = m1.add(m);
				if (somma.getCoeff() != 0)
					elenco.put(m.getGrado(), m1.add(m));
				else
					elenco.remove(m.getGrado());
			}
		}
	} // add
	public Iterator<Monomio> iterator() {
		return elenco.descendingMap().values().iterator();
	} // iterator
} // PolinomioMap
