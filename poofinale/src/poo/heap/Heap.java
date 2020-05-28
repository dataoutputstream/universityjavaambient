package poo.heap;
import java.util.*;

public class Heap<T extends Comparable<? super T>>{
	private T[] heap;
	private int n, size;
	//size punta all'ultimo occupato: 1<=size<=n
	@SuppressWarnings("unchecked")
	public Heap( int n ){
		if( n<=0 ) throw new IllegalArgumentException();
		this.n=n; size=0;
		heap=(T[]) new Comparable[n+1];
	}//Heap
	public int size(){
		return size;
	}//size
	public boolean contains( T elem ){
		for( int i=1; i<=size; i++ )
			if( heap[i].equals(elem) ) return true;
		return false;
	}//contains

	public void add( T elem ){
		if( size==n ){ //espandi
			heap=Arrays.copyOf( heap, 2*n+1 );
			n=2*n;
		}
		size++;
		heap[size]=elem; //si aggiunge elem in ultima posizione
		//aggiusta heap
		int i=size;
		while( i>1 ){
			if( heap[i].compareTo(heap[i/2])<0 ){
				//scambia heap[i] e heap[i/2]
				T park=heap[i]; heap[i]=heap[i/2];
				heap[i/2]=park;
				i=i/2;
			}
			else break;
		}
	}//add
	public T remove(){
		//rimuove il min e lo restituisce
		if( size==0 )
			throw new RuntimeException("Heap empty!");
		T min=heap[1];
		heap[1]=heap[size]; //promozione ultimo elemento
		heap[size]=null; size--;
		//riaggiusto heap
		int i=1;
		while( i<=size/2 ){
			int j=2*i, k=j+1;
			//trova min tra heap[j] e heap[k], sia z l'indice del min
			int z=j;
			if( k<=size && heap[k].compareTo(heap[z])<0 ) z=k;
			if( heap[i].compareTo(heap[z])>0 ){
				//scambia heap[i] con heap[z]
				T park=heap[i]; heap[i]=heap[z]; heap[z]=park;
				i=z;
			}
			else break;
		}
		return min;
	}//remove
	public void clear(){
		for( int i=1; i<=size; i++ )
			heap[i]=null;
		size=0;
	}//clear
	public String toString(){
		StringBuilder sb=new StringBuilder(200);
		sb.append('[');
		for( int i=1; i<=size; i++ ){
			sb.append( heap[i] );
			if( i<size ) sb.append(',');
		}
		sb.append(']');
		return sb.toString();
	}//toString

	public static void main( String[] args ){
		//Uso di heap come coda a priorita'
		Heap<Integer> h=new Heap<Integer>(10);
		int[] a={-34,12,4,-2,15,20,8,7,14,-5};
		for( int x: a ) h.add(x);
		System.out.println(h);
		//System.out.println("Dopo l'aggiunta di 3 si ha:");
		//h.add(3);
		//System.out.println(h);
		System.out.println("Dopo l'estrazione del minimo:"+h.remove());
		System.out.println(h);
		System.out.println("Estrazione ordinata: ");
		while( h.size()>0 ){
			System.out.print( h.remove()+" ");
		}
		System.out.println();
	}//main
}//Heap
