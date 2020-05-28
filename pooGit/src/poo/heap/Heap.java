package poo.heap;

public interface Heap<T extends Comparable<? super T>> extends Iterable<T> {
	int size();
	void clear();
	boolean contains(T elem);
	void add(T elem);
	T remove();
} // Heap