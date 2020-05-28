package poo.heap;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HeapSortM <T extends Comparable<? super T>> {
	
	Heap<T> h;
	T[] array;	
	public HeapSortM(T[]array) {
		h=new Heap(array.length);
		this.array=array;
	}
	
	public T[]  HeapSort() {
		for(int i=0;i<array.length;i++){
			h.add(array[i]);
		}
		for(int i=0;i<array.length;i++) {
			array[i]=h.remove();
		}
		
		
		return array;
		
	}
	
	public static void main(String...args) {
		Integer [] daordinare= {2,5,4,5,3};
		HeapSortM hm=new HeapSortM(daordinare);
		daordinare=(Integer[]) hm.HeapSort();
		System.out.println(Arrays.toString(daordinare));
	}

}
