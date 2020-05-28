package poo.util;
import java.util.*;

public class ArrayVector<T> implements Vector<T>{
	private int size;
	private T []array;
	@SuppressWarnings("unchecked")  
	public ArrayVector( int capacita ){
		if( capacita<=0 ) throw new IllegalArgumentException();
		array=(T[]) new Object[capacita];
		size=0;
	}
	public ArrayVector(){ this(20); }

	public int size(){ return size; }
	public int indexOf( T elem ){
		for( int i=0; i<size; ++i )
			if( array[i].equals( elem ) ) return i;
		return -1;
	}//indexOf
	public boolean contains( T elem ){
		return indexOf( elem ) != -1;
	}//contains
	public T get( int indice ){
		if( indice<0 || indice>=size ) throw new IndexOutOfBoundsException();
		return array[indice];
	}//get
	public T set( int indice, T elem ){
		if( indice<0 || indice>=size ) throw new IndexOutOfBoundsException();
		T x = array[indice];
		array[indice] = elem;
		return x;
	}//set
	public void add( T elem ){
		if( size==array.length )// espandi
			array=Arrays.copyOf( array, array.length*2 );
		array[size] = elem;
		size++;
	}//add
	public void add( int indice, T elem ){
		if( indice<0 || indice>size ) 
			throw new IndexOutOfBoundsException();
		if( size==array.length )//espandi
			array=Arrays.copyOf( array, array.length*2 );
		for( int i = size-1; i>=indice; --i )
			array[i+1] = array[i];
		array[indice] = elem;
		size++;
	}//add
	public void remove( T elem ){
		int i = indexOf( elem );
		if( i == -1 ) return;
		remove( i );
	}//remove
	public T remove( int indice ){
		if( indice<0 || indice>=size ) 
			throw new IndexOutOfBoundsException();
		T old = array[indice];
		for( int i = indice+1; i<size; ++i )
			array[i-1] = array[i];
		size--; array[size]=null;
		if( size<array.length/2 )// contrai
			array=Arrays.copyOf( array, array.length/2 );
		return old;
	}//remove
	public void clear(){
		for( int i=0; i<size; ++i ) array[i]=null;
		size=0;
	}//clear
	public boolean isEmpty(){
		return size==0;
	}//isEmpty
	public Vector<T> subVector( int da, int a ){
		if( da<0 || da>=size || a<0 || a>size || da>a )
			throw new RuntimeException();
		Vector<T> v = new ArrayVector<T>( a - da );
		for( int j=da; j<a; ++j )
			v.add( array[j] );
	    return v;
	}//subVector
	@Override
	public boolean equals( Object x ){
		if( !(x instanceof Vector<?>) ) return false;
		if( x==this ) return true;
		@SuppressWarnings("unchecked")
		Vector v = (Vector)x;
		if( this.size!=v.size() ) return false;
		for( int i=0; i<this.size; ++i )
			if( !array[i].equals(v.get(i)) ) return false;
		return true;
	}//equals
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder(200);
		sb.append('[');
		for( int i=0; i<size; ++i ){
			sb.append(array[i]);
			if( i<size-1 ) sb.append(", ");
		}
		sb.append(']');
		return sb.toString();
	}//toString
	public Iterator<T> iterator(){ return new Iteratore(); }
	
	private class Iteratore implements Iterator<T>{
		private int corrente=-1;
		private boolean rimuovibile=false;
		public boolean hasNext(){
			if( corrente==-1 ) return size>0;
			return corrente<size-1;
		}//hasNext
		public T next(){
			if( !hasNext() ) throw new NoSuchElementException();
			corrente++;
			rimuovibile=true;
			return array[corrente]; 
		}//next
		public void remove(){
			if( !rimuovibile ) throw new IllegalStateException();
			rimuovibile=false;
			ArrayVector.this.remove( corrente );
			corrente--;
		}//remove
	}//Iterator

	public static void main( String[] args ){
		Vector<Integer> v = new ArrayVector<Integer>();
		for( int i=10; i>0; --i )
			v.add(i);
		System.out.println(v);
		v.clear();
		for( int i=10; i>0; --i )
			v.add(0,i);
		System.out.println(v);
		Vector<Integer> sv=v.subVector(4,10);
		System.out.println(sv);

		Vector<String> w = new ArrayVector<String>();
		Scanner sc=new Scanner( System.in );
		for(;;){
			System.out.print("String( solo INVIO per terminare) : ");
			String s=sc.nextLine();
			if( s.length()==0 ) break;
			boolean flag=false;
			int indice=0;
			while( indice<w.size() && !flag ){
				String str = w.get(indice);
				if( str.compareTo(s)>=0 ) flag=true;
				else indice++;
			}
			w.add( indice, s );
		}
		System.out.println(w);
		System.out.println("Stampa tramite iteratore");
		for( String st: w ) System.out.print(st+" ");
		System.out.println();
		System.out.println("Rimozione tramite iteratore");
		Iterator<String> it=w.iterator();
		while( it.hasNext() ){
			String t=it.next();
			System.out.println("Rimuovo "+t);
			it.remove();
			System.out.println(w);
		}
		System.out.println(w);
	}//main

}//ArrayVector