package poo.util;

public interface Vector<T> extends Iterable<T>{
	public int size();
	public int indexOf( T elem );
	public boolean contains( T elem );
	public T get( int indice );
	public T set( int indice, T elem );
	public void add( T elem );
	public void add( int indice, T elem );
	public void remove( T elem );
	public T remove( int indice );
	public void clear();
	public boolean isEmpty();
	public Vector<T> subVector( int da, int a );
}//Vector