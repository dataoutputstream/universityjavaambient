package poo.agendina;

import java.util.*;

public class AgendinaLL extends AgendinaAstratta {
	private List<Nominativo> tabella = new LinkedList<Nominativo>();
	public int size() {
		return tabella.size();
	} // size
	public void svuota() {
		tabella.clear();
	} // svuota
	public void aggiungi(Nominativo n) {
		ListIterator<Nominativo> li = tabella.listIterator();
		while (li.hasNext()) {
			Nominativo x = li.next();
			if (x.equals(n)) { li.set(n); return; }
			else if (x.compareTo(n) > 0) {
				li.previous(); li.add(n); return;
			}
		}
		li.add(n);
	} // aggiungi
	public Iterator<Nominativo> iterator() {
		return tabella.iterator();
	} // iterator
} // AgendinaLL
