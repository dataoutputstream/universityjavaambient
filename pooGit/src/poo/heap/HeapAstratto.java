package poo.heap;

import java.util.Iterator;

public abstract class HeapAstratto<T extends Comparable<? super T>> implements Heap<T> {
	public int size() {
		int c = 0;
		for (T e: this) c++;
		return c;
	} // size
	public void clear() {
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			it.next(); it.remove();
		}
	} // clear
	public boolean contains(T elem) {
		for (T e: this) if (e.equals(elem)) return true;
		return false;
	} // contains
	public T remove() {
		Iterator<T> it = iterator(); T e = null;
		if (it.hasNext()) { e = it.next(); it.remove(); }
		return e;
	} // remove
	public int hashCode() {
		final int MOLT = 43; int h = 0;
		for (T e: this) h = h * MOLT + e.hashCode();
		return h;
	} // hashCode
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append('[');
		for (T e: this) {
			sb.append(e); sb.append(", ");
		}
		if (sb.length() > 1) sb.setLength(sb.length() - 2);
		sb.append(']');
		return sb.toString();
	} // toString
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (!(o instanceof Heap)) return false;
		if (o == this) return true;
		Heap<T> h = (Heap)o;
		if (size() != h.size()) return false;
		Iterator<T> it1 = iterator();
		Iterator<T> it2 = h.iterator();
		while (it1.hasNext())
			if (!it1.next().equals(it2.next())) return false;
		return true;
	} // equals

	public abstract void add(T e);
	public abstract Iterator<T> iterator();
} // HeapAstratto
