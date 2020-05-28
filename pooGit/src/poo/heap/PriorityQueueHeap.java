package poo.heap;

import java.util.*;

public class PriorityQueueHeap<T extends Comparable<? super T>> extends HeapAstratto<T> {
	PriorityQueue<T> heap = new PriorityQueue<T>();
	public void add(T elem) { heap.add(elem); }
	public T remove() { return heap.poll(); }
	public Iterator<T> iterator() { return heap.iterator(); }
} // PriorityQueueHeap
