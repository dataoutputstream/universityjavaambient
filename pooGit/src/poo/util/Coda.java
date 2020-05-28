package poo.util;

public interface Coda<T> extends Iterable<T> {
	int size();
	void clear();
	boolean isEmpty();
	boolean isFull();
	void put(T e);
	T get();
	T peek();
} // Coda
