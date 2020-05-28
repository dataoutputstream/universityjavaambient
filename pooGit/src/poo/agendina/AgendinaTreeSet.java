package poo.agendina;

import java.util.*;

public class AgendinaTreeSet extends AgendinaAstratta {
	private Set<Nominativo> tabella = new TreeSet<Nominativo>();
	public int size() {
		return tabella.size();
	} // size
	public void svuota() {
		tabella.clear();
	} // svuota
	public void aggiungi(Nominativo n) {
		tabella.remove(n);
		tabella.add(n);
	} // aggiungi
	public Nominativo cerca(Nominativo n) {
		if (tabella.contains(n)) {
			Iterator<Nominativo> it = tabella.iterator();
			while (it.hasNext()) {
				Nominativo x = it.next();
				if (x.equals(n)) return x;
			}
		}
		return null;
	} // cerca
	public Iterator<Nominativo> iterator() {
		return tabella.iterator();
	} // iterator
} // AgendinaSet
