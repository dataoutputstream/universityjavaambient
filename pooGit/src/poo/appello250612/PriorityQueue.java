package poo.appello250612;

import java.util.*;

public class PriorityQueue<T extends Comparable<? super T>> extends HeapAstratto<T> {
	private T[] heap;
	private int size = 0, modCount = 0;
	private Comparator<T> c;
	@SuppressWarnings("unchecked")
	public PriorityQueue() {
		heap = (T[])new Comparable[18];
		c = defaultComparator();
	} // Costruttore di default
	@SuppressWarnings("unchecked")
	public PriorityQueue(Comparator<T> c) {
		heap = (T[])new Comparable[18];
		this.c = c;
	} // Costruttore 2
	@SuppressWarnings("unchecked")
	public PriorityQueue(int n) {
		if (n < 0) throw new IllegalArgumentException();
		heap = (T[])new Comparable[n + 1];
		c = defaultComparator();
	} // Costruttore 3
	@SuppressWarnings("unchecked")
	public PriorityQueue(int n, Comparator<T> c) {
		if (n < 0) throw new IllegalArgumentException();
		heap = (T[])new Comparable[n + 1];
		this.c = c;
	} // Costruttore 4
	public PriorityQueue(PriorityQueue<T> p) {
		heap = Arrays.copyOf(p.heap, p.size() + 1);
		size = p.size;
		c = p.c;
	} // Costruttore di copia
	private Comparator<T> defaultComparator() {
		return new Comparator<T>() {
			public int compare(T e1, T e2) {
				return e1.compareTo(e2);
			}
		};
	} // defaultComparator
	public int size() { return size; }
	public void add(T elem) {
		if (size == heap.length - 1) heap = Arrays.copyOf(heap, heap.length * 2);
		heap[++size] = elem; int j;
		for (int i = size; i > 1; i = j) {
			j = i / 2;
			if (c.compare(heap[i], heap[j]) < 0) {
				T tmp = heap[i];
				heap[i] = heap[j];
				heap[j] = tmp;
			} else break;
		}
		modCount++;
	} // add
	public Iterator<T> iterator() { return new HeapIterator(); }

	private class HeapIterator implements Iterator<T> {
		private int cor = 0, iterModCount = modCount;
		private boolean rimovibile = false;
		public boolean hasNext() {
			return cor < size;
		} // hasNext
		public T next() {
			if (iterModCount != modCount) throw new ConcurrentModificationException();
			if (!hasNext()) throw new NoSuchElementException();
			rimovibile = true;
			return heap[++cor];
		} // next
		@SuppressWarnings("unchecked")
		public void remove() {
			if (iterModCount != modCount) throw new ConcurrentModificationException();
			if (!rimovibile) throw new IllegalStateException();
			rimovibile = false;
			heap[cor] = null;
			int tmp = size; size = --cor;
			for (int i = cor + 2; i <= tmp; i++) add(heap[i]);
			heap[tmp] = null;
			iterModCount = ++modCount;
		} // remove
	} // HeapIterator
} // PriorityQueue
