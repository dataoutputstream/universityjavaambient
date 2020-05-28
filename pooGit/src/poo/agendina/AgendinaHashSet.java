package poo.agendina;

import java.util.*;

public class AgendinaHashSet extends AgendinaAstratta {
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
	public void rimuovi(Nominativo n) {
		tabella.remove(n);
	} // rimuovi
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
		return new Iteratore();
	} // iterator
	private class Iteratore implements Iterator<Nominativo> {
		private Set<Nominativo> ts = new TreeSet<Nominativo>(tabella);
		private Iterator<Nominativo> it = ts.iterator();
		private Nominativo corrente;
		public boolean hasNext() {
			return it.hasNext();
		} // hasNext
		public Nominativo next() {
			corrente = it.next();
			return corrente;
		} // next
		public void remove() {
			it.remove();
			tabella.remove(corrente);
		} // remove
	} // Iteratore
} // AgendinaSet
