package poo.util;

import java.util.*;

public class ListaOrdinataConcatenata<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {
	private static class Nodo<E> {
		E info;
		Nodo<E> next;
	} // Nodo
	private Nodo<T> testa = null;
	private int size = 0;
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

	public Iterator<T> iterator() {
		return new Iteratore();
	} // iterator
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
} // ListaOrdinataConcatenata
