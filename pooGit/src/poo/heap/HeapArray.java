package poo.heap;

import java.util.*;

public class HeapArray<T extends Comparable<? super T>> extends HeapAstratto<T> {
	private T[] heap;
	private int n, size;
	private Comparator<T> c;
	public HeapArray(int n) {
		this(n, new Comparator<T>() { // Default: natural sorting
			public int compare(T a, T b) { return a.compareTo(b); }
		});
	} // Costruttore
	@SuppressWarnings("unchecked")
	public HeapArray(int n, Comparator<T> c) {
		if (n <= 0) throw new IllegalArgumentException();
		this.n = n; size = 0;
		heap = (T[])new Comparable[n + 1];
		this.c = c;
	} // Costruttore 2
	public int size() { return size; }
	public void add(T elem) {
		if (size == n) {
			heap = java.util.Arrays.copyOf(heap, 2 * n + 1);
			n = 2 * n;
		}
		heap[++size] = elem;
		int i = size;
		while (i > 1) {
			if (c.compare(heap[i], heap[i / 2]) < 0) {
				T tmp = heap[i];
				heap[i] = heap[i / 2];
				heap[i / 2] = tmp;
				i = i / 2;
			} else break;
		}
	} // add
	public T remove() {
		if (size == 0) throw new RuntimeException("Empty Heap!");
		T min = heap[1]; heap[1] = heap[size];
		heap[size--] = null;
		int i = 1;
		while (i <= size / 2) {
			int j = 2 * i, k = j;
			if (k + 1 <= size && c.compare(heap[k + 1], heap[k]) < 0) k++; // minimo
			if (c.compare(heap[i], heap[k]) > 0) {
				T tmp = heap[i]; heap[i] = heap[k];
				heap[k] = tmp; i = k;
			} else break;
		}
		return min;
	} // remove
	public Iterator<T> iterator() { return new HeapIterator(); }

	private class HeapIterator implements Iterator<T> {
		int corrente = 0; public boolean rimovibile = false;
		public boolean hasNext() {
			return corrente < size;
		} // hasNext
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			rimovibile = true;
			return heap[++corrente];
		} // next
		@SuppressWarnings("unchecked")
		public void remove() {
			if (!rimovibile) throw new IllegalStateException();
			rimovibile = false;
			heap[corrente] = null;
			int tmp = size; size -=corrente;
			for (int i = corrente + 2; i <= tmp; i++) add(heap[i]);
			heap[tmp] = null;
		} // remove
	} // HeapIterator
} // Heap
