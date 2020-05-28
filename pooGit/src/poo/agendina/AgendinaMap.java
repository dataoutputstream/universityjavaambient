package poo.agendina;

import java.util.*;

public class AgendinaMap extends AgendinaAstratta {
	private Map<Nominativo, Nominativo> tabella = new TreeMap<Nominativo, Nominativo>();
	public int size() {
		return tabella.size();
	}
	public void svuota() {
		tabella.clear();
	}
	public void rimuovi(Nominativo n) {
		tabella.remove(n);
	}
	public Nominativo cerca(Nominativo n) {
		return tabella.get(n);
	}
	public void aggiungi(Nominativo n) {
		tabella.put(n, n);
	}
	public Iterator<Nominativo> iterator() {
		return tabella.keySet().iterator();
	}
} // AgendinaMap
