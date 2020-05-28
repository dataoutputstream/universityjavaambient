package poo.appello250612;

import java.util.*;

public class JavaPriorityQueue<T extends Comparable<? super T>> extends HeapAstratto<T> {
	private java.util.PriorityQueue<T> heap;
	public JavaPriorityQueue() {
		heap = new java.util.PriorityQueue<T>();
	} // Costruttore di default
	public JavaPriorityQueue(Comparator<? super T> c) {
		heap = new java.util.PriorityQueue<T>(17, c);
	} // Costruttore 2
	public JavaPriorityQueue(int n) {
		heap = new java.util.PriorityQueue<T>(n);
	} // Costruttore 3
	public JavaPriorityQueue(int n, Comparator<? super T> c) {
		heap = new java.util.PriorityQueue<T>(n, c);
	} // Costruttore 4
	public JavaPriorityQueue(JavaPriorityQueue<T> jp) {
		heap = new java.util.PriorityQueue<T>(jp.heap);
	} // Costruttore di copia
	public int size() { return heap.size(); }
	public void add(T elem) { heap.add(elem); }
	public Iterator<T> iterator() { return heap.iterator(); }
} // JavaPriorityQueue
