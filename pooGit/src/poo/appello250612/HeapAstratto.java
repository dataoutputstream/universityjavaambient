package poo.appello250612;

import java.util.Iterator;

public abstract class HeapAstratto<T extends Comparable<? super T>> implements Heap<T> {
	public int size() {
		int c = 0;
		for (T e: this) c++;
		return c;
	} // size
	public void offer(T elem) { add(elem); }
	public T remove() {
		T e = null;
		Iterator<T> it = iterator();
		if (it.hasNext()) { e = it.next(); it.remove(); }
		return e;
	} // remove
	public T poll() { return remove(); }
	public T peek() {
		Iterator<T> it = iterator();
		if (it.hasNext()) return it.next();
		return null;
	} // peek
	public void remove(T elem) {
		Iterator<T> it = iterator();
		while (it.hasNext())
			if (it.next().equals(elem)) it.remove();
	} // remove
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append('[');
		for (T e: this) { sb.append(e); sb.append(", "); }
		if (sb.length() > 1) sb.setLength(sb.length() - 2);
		sb.append(']');
		return sb.toString();
	} // toString
	public int hashCode() {
		final int MOLT = 43; int h = 0;
		for (T e: this) h = h * MOLT + e.hashCode();
		return h;
	} // hashCode
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

	public abstract void add(T elem);
	public abstract Iterator<T> iterator();
} // HeapAstratto
