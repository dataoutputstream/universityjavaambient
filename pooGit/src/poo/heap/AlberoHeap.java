package poo.heap;

import java.util.*;
import poo.util.*;

public class AlberoHeap<T extends Comparable<? super T>> extends HeapAstratto<T> {
	private static class Nodo<E> {
		E info;
		Nodo<E> figlioS, figlioD, padre;
	} // Nodo
	private Nodo<T> radice = null;
	private Nodo<T> ultimo = null;
	public void add(T e) {
		Nodo<T> nuovo = new Nodo<T>();
		nuovo.info = e; ultimo = nuovo;
		add(nuovo);
		Nodo<T> cor = ultimo;
		while (cor.padre != null && cor.info.compareTo(cor.padre.info) < 0) {
			T tmp = cor.info;
			cor.info = cor.padre.info;
			cor.padre.info = tmp;
			cor = cor.padre;
		}
	} // add
	private void add(Nodo<T> nuovo) {
		if (radice == null) {
			nuovo.padre = null;
			nuovo.figlioS = null;
			nuovo.figlioD = null;
			radice = nuovo;
		} else { // Visita per livelli
			Coda<Nodo<T>> coda = new CodaConcatenata<Nodo<T>>();
			Nodo<T> cor = radice;
			while (cor.figlioS != null && cor.figlioD != null) {
				coda.put(cor.figlioS); coda.put(cor.figlioD);
				cor = coda.get();
			}
			nuovo.padre = cor;
			if (cor.figlioS == null) cor.figlioS = nuovo;
			else cor.figlioD = nuovo;
		}
	} // add
	public Iterator<T> iterator() { return new HeapIterator(); }
	private class HeapIterator implements Iterator<T> {
		private Coda<Nodo<T>> prossimi = new CodaConcatenata<Nodo<T>>();
		private Nodo<T> cor = null, pre = null;
		public HeapIterator() {
			if (radice != null) prossimi.put(radice);
		} // Costruttore
		public boolean hasNext() {
			return !prossimi.isEmpty();
		} // hasNext
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			pre = cor; cor = prossimi.get();
			if (cor.figlioS != null) {
				prossimi.put(cor.figlioS);
				if (cor.figlioD != null) prossimi.put(cor.figlioD);
			}
			return cor.info;
		} // next
		public void remove() {
			if (cor == pre) throw new IllegalStateException();
			Coda<T> daInserire = new CodaConcatenata<T>();
			// Elimino cor
			if (cor.padre != null) {
				if (cor == cor.padre.figlioS) cor.padre.figlioS = null;
				else cor.padre.figlioD = null;
			} else radice = null;
			// Elimino i successivi, per poi rigenerare l'Heap
			while (!prossimi.isEmpty()) {
				Nodo<T> tmp = prossimi.get();
				if (tmp == tmp.padre.figlioS) tmp.padre.figlioS = null;
				else tmp.padre.figlioS = null;
				daInserire.put(tmp.info);
				if (tmp.figlioS != null) {
					prossimi.put(tmp.figlioS);
					if (tmp.figlioD != null) prossimi.put(tmp.figlioD);
				}
			}
			// Rigenero l'Heap aggiungendo nuovamente i successivi
			while (!daInserire.isEmpty()) add(daInserire.get());
			// Riporto l'iteratore al suo posto (dopo 'pre')
			if (radice != null) prossimi.put(radice);
			if (pre != null) {
				Nodo<T> tmp;
				while (!prossimi.isEmpty() && pre != (tmp = prossimi.get())) {
					if (tmp.figlioS != null) {
						prossimi.put(tmp.figlioS);
						if (tmp.figlioD != null) prossimi.put(tmp.figlioD);
					}
				}
			}
			cor = pre;
		} // remove
	} // HeapIterator
} // AlberoHeap
