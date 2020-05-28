package poo.thread.mergesort;

import java.util.Arrays;
import java.util.concurrent.*;

public class MergeSortThreadPool<T extends Comparable<? super T>> {
	private T[] a;
	private int inf, sup;
	public MergeSortThreadPool(T[] a, int inf, int sup) {
		this.a = a; this.inf = inf; this.sup = sup;
	} // MergeSortThreadPool
	public void start() throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		Future<?> f = pool.submit(new Sorter(a, inf, sup, pool));
		f.get();
		pool.shutdown();
	} // start
	private class Sorter implements Runnable {
		private T[]a;
		private int inf, sup;
		private ExecutorService pool;
		public Sorter(T[]a, int inf, int sup, ExecutorService pool) {
			this.a = a; this.inf = inf; this.sup = sup; this.pool = pool;
		} // Sorter
		public void run() {
			if (inf < sup) {
				int med = (inf + sup) / 2;
				Future<?> f1 = pool.submit(new Sorter(a, inf, med, pool));
				Future<?> f2 = pool.submit(new Sorter(a, med + 1, sup, pool));
				try {
					f1.get(); f2.get();
				} catch (Exception e) { return; }
				merge(a, inf, med, sup);
			}
		} // run
		@SuppressWarnings("unchecked")
		private void merge(T[]v, int inf, int med, int sup) {
			T[] aux = (T[])new Comparable[sup - inf + 1]; 
			int i=inf, j=med+1, k=0;
			while (i <= med && j <= sup) {
				if (v[i].compareTo(v[j]) < 0) { aux[k] = v[i]; i++; }
				else { aux[k] = v[j]; j++; }
				k++;
			}
			while (i <= med) {
				aux[k] = v[i]; i++; k++;
			}
			while (j <= sup) {
				aux[k] = v[j]; j++; k++;
			}
			for (k = 0; k < aux.length; k++)
				v[k + inf] = aux[k];
		} // merge
	} // Sorter
	public static void main(String[]args) throws InterruptedException, ExecutionException {
		Integer[] a = {10,9,8,7,6,5,4,3,2,1};
		System.out.println("Vettore iniziale: " + Arrays.asList(a));
		MergeSortThreadPool<Integer> msmt =
			new MergeSortThreadPool<Integer>(a, 0, a.length - 1);
		msmt.start();
		System.out.println("Vettore ordinato: " + Arrays.asList(a));
	} // main
} // MergeSortThreadPool
