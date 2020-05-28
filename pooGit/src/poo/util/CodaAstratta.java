package poo.util;

import java.util.Iterator;

public abstract class CodaAstratta<T> implements Coda<T> {
	
	public abstract void put(T e);
	public abstract Iterator<T> iterator();

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
	public boolean isEmpty() { return !iterator().hasNext(); }
	public boolean isFull() { return false; }
	public T get() {
		Iterator<T> it = iterator();
		if (!it.hasNext()) throw new RuntimeException("Empty Queue!");
		T e = it.next(); it.remove();
		return e;
	} // get
	public T peek() {
		Iterator<T> it = iterator();
		if (!it.hasNext()) throw new RuntimeException("Empty Queue!");
		return it.next();
	} // peek
	public int hashCode() {
		final int MOLT = 43; int h = 0;
		Iterator<T> it = iterator();
		while (it.hasNext())
			h = h * MOLT + it.next().hashCode();
		return h;
	} // hashCode
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (!(o instanceof Coda)) return false;
		if (o == this) return true;
		Coda<T> l = (Coda)o;
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
} // CodaAstratta
