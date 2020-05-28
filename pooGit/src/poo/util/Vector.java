package poo.util;

public interface Vector<T> extends Iterable<T> {
	public int size();
	public int indexOf(T o);
	public boolean contains(T o);
	public T get(int indice);
	public T set(int indice, T o);
	public void add(T o);
	public void add(int indice, T o);
	public void remove(T o);
	public T remove(int indice);
	public void clear();
	public boolean isEmpty();
	public Vector<T> subVector(int da, int a);
} // Vector
