package poo.util;

import java.util.*;

public class CodaConcatenata<T> extends CodaAstratta<T> {
	private static class Nodo<E> {
		E info;
		Nodo<E> next;
	} // Nodo
	private Nodo<T> inizio = null, fine = null;
	private int size = 0;
	public int size() { return size; }
	public void clear() { inizio = fine = null; }
	public boolean isEmpty() { return inizio == null; }
	public void put(T e) {
		Nodo<T> nuovo = new Nodo<T>();
		nuovo.info = e;
		if (inizio == null) inizio = nuovo;
		else fine.next = nuovo; fine = nuovo;
		size++;
	} // put
	public T get() {
		if (inizio == null) throw new RuntimeException("Empty Queue!");
		T e = inizio.info; inizio = inizio.next;
		if (inizio == null) fine = null; size--;
		return e;
	} // get
	public T peek() {
		if (inizio == null) throw new RuntimeException("Empty Queue!");
		return inizio.info;
	} // peek
	public Iterator<T> iterator() {
		return new QueueIterator();
	} // iterator
	private class QueueIterator implements Iterator<T> {
		Nodo<T> pre = null, cor = null;
		public boolean hasNext() {
			if (cor == null) return inizio != null;
			return cor != fine;
		} // hasNext
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			if (cor == null) cor = inizio;
			else { pre = cor; cor = cor.next; }
			return cor.info;
		} // next
		public void remove() {
			if (pre == cor) throw new IllegalStateException();
			if (cor == inizio) {
				inizio = cor.next;
				if (inizio == null) fine = null;
			} else if (cor == fine) {
				fine = pre; pre.next = null;
			} else pre.next = cor.next; size--; cor = pre;
		} // remove
	} // QueueIterator
} // CodaConcatenata
