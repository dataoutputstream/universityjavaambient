package poo.agendina;

import poo.util.*;

public class AgendinaVector extends AgendinaAstratta {
	private Vector<Nominativo> tabella;
	public AgendinaVector() {
		this(50);
	} // Costruttore di default
	public AgendinaVector(int capacity) {
		if (capacity <= 0) throw new IllegalArgumentException();
		tabella = new ArrayVector<Nominativo>(capacity);
	} // Costruttore normale
	public int size() {
		return tabella.size();
	} // size
	public void svuota() {
		tabella.clear();
	} // svuota
	public void aggiungi(Nominativo n) {
		int i = Array.ricercaBinaria(tabella, n);
		if (i >= 0) tabella.set(i, n);
		else {
			int pos = 0;
			while (pos < tabella.size() && tabella.get(pos).compareTo(n) < 0) pos++;
			tabella.add(pos, n);
		}
	} // aggiungi
	public void rimuovi(Nominativo n) {
		int i = Array.ricercaBinaria(tabella, n);
		if (i >= 0) tabella.remove(i);
	} // rimuovi
	public Nominativo cerca(Nominativo n) {
		int i = Array.ricercaBinaria(tabella, n);
		if (i >= 0) return tabella.get(i);
		return null;
	} // cerca
	public java.util.Iterator<Nominativo> iterator() {
		return tabella.iterator();
	} // iterator
} // AgendinaVector
