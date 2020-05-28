package poo.recursion;

public interface CollezioneOrdinata<T extends Comparable<? super T>> {
	public int size();
	public boolean contains( T elem );
	public T get( T elem );
	public boolean isEmpty();
	public boolean isFull();
	public void clear();
	public void add( T elem );
	public void remove( T elem );
}//CollezioneOrdinata
