package poo.recursion;

import poo.util.*;
import java.util.*;

public class ListaRec<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {
	private static class Lista<E> {
		E info;
		Lista<E> next;
	} // Lista
	private Lista<T> lista = null;
	public int size() { return size(lista); }
	private int size(Lista<T> lista) {
		if (lista == null) return 0;
		return 1 + size(lista.next);
	} // size
	public boolean contains(T e) { return contains(lista, e); }
	private boolean contains(Lista<T> lista, T e) {
		if (lista == null || lista.info.compareTo(e) > 0) return false;
		if (lista.info.equals(e)) return true;
		return contains(lista.next, e);
	} // contains
	public T get(T e) { return get(lista, e); }
	private T get(Lista<T> lista, T e) {
		if (lista == null || lista.info.compareTo(e) > 0) return null;
		if (lista.info.equals(e)) return lista.info;
		return get(lista.next, e);
	} // get
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (!(o instanceof ListaRec)) return false;
		if (o == this) return true;
		ListaRec<T> l = (ListaRec)o;
		return equals(lista, l.lista);
	} // equals
	private boolean equals(Lista<T> l1, Lista<T> l2) {
		if (l1 == null && l2 == null) return true;
		if (l1 == null || l2 == null) return false;
		return l1.info.equals(l2.info) && equals(l1.next, l2.next);
	} // equals
	public int hashCode() { return hashCode(lista); }
	private int hashCode(Lista<T> lista) {
		final int MOLT = 811;
		if (lista == null) return 0;
		return lista.info.hashCode() * MOLT + hashCode(lista.next);
	} // hashCode
	public String toString() {
		StringBuilder sb = new StringBuilder(500);
		sb.append('[');
		toString(lista, sb);
		sb.append(']');
		return sb.toString();
	} // toString
	private void toString(Lista<T> lista, StringBuilder sb) {
		if (lista == null) return;
		sb.append(lista.info);
		if (lista.next != null) sb.append(", ");
		toString(lista.next, sb);
	} // toString
	public boolean isEmpty() { return lista == null; }
	public void add(T e) { lista = add(lista, e); }
	private Lista<T> add(Lista<T> lista, T e) {
		if (lista == null) {
			lista = new Lista<T>();
			lista.info = e; lista.next = null;
			return lista;
		}
		if (lista.info.compareTo(e) > 0) {
			Lista<T> nuova = new Lista<T>();
			nuova.info = e; nuova.next = lista;
			return nuova;
		}
		lista.next = add(lista.next, e);
		return lista;
	} // add
	public void remove(T e) { lista = remove(lista, e); }
	private Lista<T> remove(Lista<T> lista, T e) {
		if (lista == null) return null;
		if (lista.info.equals(e)) return lista.next;
		if (lista.info.compareTo(e) > 0) return lista;
		lista.next = remove(lista.next, e);
		return lista;
	} // remove
	public void reverse() {
		lista = reverseRec(lista);
	} // reverse
	private Lista<T> reverseRec(Lista<T> lista) {
		if (lista == null || lista.next == null) return lista;
		Lista<T> revres = reverseRec(lista.next);
		lista.next.next = lista;
		lista.next = null;
		return revres;
	} // reverseRic
	public Iterator<T> iterator() {
		return null;
	} // iterator
} // ListaRec
