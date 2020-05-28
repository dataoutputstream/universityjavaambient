package poo.recursion;

public class ListaRec<T extends Comparable<? super T>> implements CollezioneOrdinata<T>{
	private static class Lista<E>{
		E info;
		Lista<E> next;
	}//Lista

	private Lista<T> lista=null;

	//metodi pubblici o di interfaccia
	public int size(){
		return size( lista );
	}//size
	public boolean contains( T elem ){
		return contains( lista, elem );
	}//contains
	@SuppressWarnings("unchecked")
	public boolean equals( Object x ){
		if( !(x instanceof ListaRec<?>) ) return false;
		if( this==x ) return true;
		return equals( lista, ((ListaRec<T>)x).lista );
	}//equals
	public String toString(){
		StringBuilder sb=new StringBuilder(200);
		sb.append('[');
		toString( lista, sb );
		sb.append(']');
		return sb.toString();
	}//toString
	public int hashCode(){
		return hashCode( lista );
	}//hashCode
	public T get( T elem ){
		return get( lista, elem );
	}//get
	public boolean isEmpty(){
		return lista==null;
	}//isEmpty
	public boolean isFull(){
		return false;
	}//isFull
	public void clear(){
		lista=null;
	}//clear
	public void add( T elem ){
		lista=add( lista, elem );
	}//add
	public void remove( T elem ){
		lista=remove( lista, elem );
	}//remove

	//metodi ricorsivi privati ausiliari
	private int size( Lista<T> lista ){
		if( lista==null ) return 0;
		return 1+size( lista.next );
	}//size
	private boolean contains( Lista<T> lista, T elem ){
		if( lista==null ) return false;
		if( lista.info.equals(elem) ) return true;
		if( lista.info.compareTo(elem)>0 ) return false;
		return contains( lista.next, elem );
	}//contains
	private void toString( Lista<T> lista, StringBuilder sb ){
		if( lista==null ) return;
		sb.append( lista.info );
		if( lista.next!=null ) sb.append(',');
		toString(lista.next, sb);
	}//toString
	private boolean equals( Lista<T> l1, Lista<T> l2 ){
		if( l1==null && l2==null ) return true;
		if( l1==null || l2==null ) return false;
		if( !l1.info.equals(l2.info) ) return false;
		return equals( l1.next, l2.next );
	}//equals
	private int hashCode( Lista<T> lista ){
		final int primo=997;
		if( lista==null ) return 0;
		return lista.info.hashCode()*primo+hashCode(lista.next);
	}//hashCode
	private T get( Lista<T> lista, T elem ){
		if( lista==null || lista.info.compareTo(elem)>0 ) return null;
		if( lista.info.equals(elem) ) return lista.info;
		return get( lista.next, elem );
	}//get
	private Lista<T> add( Lista<T> lista, T elem ){
		if( lista==null ){
			lista=new Lista<T>();
			lista.info=elem; lista.next=null;
			return lista;
		}
		if( lista.info.compareTo(elem)>=0 ){
			Lista<T> nuovo=new Lista<T>();
			nuovo.info=elem; nuovo.next=lista;
			return nuovo;
		}
		lista.next=add( lista.next, elem );
		return lista;
	}//add
	private Lista<T> remove( Lista<T> lista, T elem ){
		if( lista==null || lista.info.compareTo(elem)>0 ) return lista;
		if( lista.info.equals(elem) ) return lista.next;
		lista.next=remove( lista.next, elem );
		return lista;
	}//remove

	public static void main( String[] args ){
		ListaRec<Integer> lr=new ListaRec<Integer>();
		int[] a={10,3,6,5,4,9,1};
		for( int x: a )
			lr.add(x);
		System.out.println(lr);
		lr.remove( 9 ); lr.remove( 1 );
		System.out.println(lr);
	}//main
}//ListaRec
