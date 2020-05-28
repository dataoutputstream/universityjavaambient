package poo.heap;

public class HeapSort{
	private HeapSort(){}
	public static <T extends Comparable<? super T>> void heapSort( T[] v ){
		Heap<T> h=new Heap<T>(v.length);
		//prima fase: riempimento heap
		for( T e: v )
			h.add(e);
		//seconda fase: svuotamento heap
		for( int i=0; i<v.length; i++ )
			v[i]=h.remove();
	}//heapSort
	public static void main( String[] args ){
		String[] w={"zaino","cane","pioggia","baita","orso","aia"};
		System.out.println("Array iniziale:");
		for( String s: w )
			System.out.print(s+" ");
		System.out.println();
		HeapSort.heapSort(w);
		System.out.println("Array dopo heap sort:");
		for( String s: w )
			System.out.print(s+" ");
		System.out.println();	
	}//main
}//HeapSort
