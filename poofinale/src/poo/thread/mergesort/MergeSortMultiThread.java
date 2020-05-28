package poo.thread.mergesort;
import java.util.*;

public class MergeSortMultiThread <T extends Comparable<? super T>>{
	private T[] a;
	private int inf, sup;
	public MergeSortMultiThread( T[] a, int inf, int sup ) 
	throws InterruptedException{
		this.a=a; this.inf=inf; this.sup=sup;
	}//MergeSortMultiThread
	
	public void start() throws InterruptedException{
		Sorter primo=new Sorter( a, inf, sup );
		primo.start();
		try{
			primo.join();
		}catch( InterruptedException e ){
			throw e;
		}		
	}//start
	
	private class Sorter extends Thread{
		private T[]a, aux;
		private int inf, sup;
		public Sorter( T[]a, int inf, int sup ){
			this.a=a; this.inf=inf; this.sup=sup;
		}//Sorter
		public void run(){
			if( inf<sup ){
				int med=(inf+sup)/2;
				Sorter s1=new Sorter(a,inf,med);
				Sorter s2=new Sorter(a,med+1,sup);
				s1.start(); s2.start();
				try{
					s1.join();
					s2.join();
				}catch( InterruptedException e ){
					//si fa terminare il thread
					return;
				}
				merge(a,inf,med,sup); 
			}
		}//run
		@SuppressWarnings("unchecked")
		private void merge( T[]v, int inf, int med, int sup ){
			//si alloca aux di dimensione strettamente indispensabile
			aux=(T[]) new Comparable[sup-inf+1];
			int i=inf, j=med+1, k=0;
			while( i<=med && j<=sup ){
				if( v[i].compareTo(v[j])<0 ){ aux[k]=v[i]; i++; k++; }
				else{ aux[k]=v[j]; j++; k++; }
			}
			while( i<=med ){
				aux[k]=v[i]; i++; k++;
			}
			while( j<=sup ){
				aux[k]=v[j]; j++; k++;
			}
			for( k=0; k<aux.length; k++ ) 
				v[k+inf]=aux[k];		
		}//merge
	}//Sorter

	
	public static void main( String []args ) throws InterruptedException{
		Integer a[]=new Integer[50];
		for( int i=0; i<a.length; ++i ) a[i]=a.length-i;
		System.out.println("Vettore iniziale: "+Arrays.toString(a));
		MergeSortMultiThread<Integer> msmt=
			new MergeSortMultiThread<Integer>( a, 0, a.length-1 );
		msmt.start();
		System.out.println("Vettore ordinato: "+Arrays.toString(a));
	}//main
}//MergeSortMultiThread
