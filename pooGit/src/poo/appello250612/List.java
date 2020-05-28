package poo.appello250612;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<T extends Comparable<? super T>> implements Iterable<T> {
	private static class Nodo<E> {
		E info;
		Nodo<E> next;
	} // Nodo
	private Nodo<T> testa = null;
	private int size = 0;
	public List() { } // Costruttore di default
	public List(PriorityQueue<T> p) {
		for (T e: p) add(e);
	} // Costruttore
	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	public void clear() { testa = null; size = 0; }
	public void add(T e) {
		Nodo<T> nuovo = new Nodo<T>();
		nuovo.info = e;
		if (testa == null || testa.info.compareTo(e) > 0) {
			nuovo.next = testa; testa = nuovo;
		} else {
			Nodo<T> cor = testa.next, pre = testa;
			while (cor != null && cor.info.compareTo(e) < 0) {
				pre = cor; cor = cor.next;
			}
			nuovo.next = cor; pre.next = nuovo;
		}
		size++;
	} // add
	public void remove(T e) {
		Nodo<T> cor = testa, pre = null;
		while (cor != null && cor.info.compareTo(e) < 0) {
			pre = cor; cor = cor.next;
		}
		if (cor != null && cor.info.equals(e)) {
			if (cor == testa) testa = testa.next;
			else pre.next = cor.next;
			size--;
		}
	} // remove
	public void compatta() {
		Nodo<T> cor = testa;
		while (cor != null) {
			if (cor.next != null && cor.info.equals(cor.next.info)) {
				cor.next = cor.next.next;
				size--;
			} else cor = cor.next;
		}
	} // compatta
	public void inverti() {
		testa = inverti(testa);
		// invertiIte();
	} // inverti
	private void invertiIte() {
		if (testa == null || testa.next == null) return;
		Nodo<T> pre = testa, cor = testa.next; testa.next = null;
		while (cor != null) {
			Nodo<T> next = cor.next;
			cor.next = pre;
			pre = cor; cor = next;
		}
		testa = pre;
	} // invertiIte
	private Nodo<T> inverti(Nodo<T> lista) {
		if (lista == null || lista.next == null) return lista;
		Nodo<T> inv = inverti(lista.next);
		lista.next.next = lista;
		lista.next = null;
		return inv;
	} // inverti
	public int hashCode() {
		final int MOLT = 43; int h = 0;
		Iterator<T> it = iterator();
		while (it.hasNext())
			h = h * MOLT + it.next().hashCode();
		return h;
	} // hashCode
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (!(o instanceof List)) return false;
		if (o == this) return true;
		List<T> l = (List)o;
		if (size() != l.size()) return false;
		Iterator<T> it1 = iterator();
		Iterator<T> it2 = l.iterator();
		while (it1.hasNext())
			if (!it1.next().equals(it2.next())) return false;
		return true;
	} // equals
	public String toString() {
		StringBuilder sb = new StringBuilder(500);
		sb.append('[');
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			sb.append(it.next());
			if (it.hasNext()) sb.append(", ");
		}
		sb.append(']');
		return sb.toString();
	} // toString
	public Iterator<T> iterator() { return new Iteratore(); }
	private class Iteratore implements Iterator<T> {
		Nodo<T> cor = null, pre = null;
		public boolean hasNext() {
			if (cor == null) return testa != null;
			return cor.next != null;
		} // hasNext
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			pre = cor;
			if (cor == null) cor = testa;
			else cor = cor.next;
			return cor.info;
		} // next
		public void remove() {
			if (cor == pre) throw new IllegalStateException();
			if (cor == testa) testa = testa.next;
			else pre.next = cor.next;
			cor = pre; size--;
		} // remove
	} // Iteratore
} // List
