package poo.appello250612;

public interface Heap<T extends Comparable<? super T>> extends Iterable<T> {
	int size();
	void add(T elem);
	void offer(T elem);
	T remove();
	T poll();
	T peek();
	void remove(T elem);
} // Heap
