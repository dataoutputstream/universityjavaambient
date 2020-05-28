package poo.util;

public interface CollezioneOrdinata<T extends Comparable<? super T>> extends Iterable<T> {
	int size();
	boolean contains(T e);
	T get(T e);
	boolean isEmpty();
	boolean isFull();
	void clear();
	void add(T e);
	void remove(T e);
} // CollezioneOrdinata
