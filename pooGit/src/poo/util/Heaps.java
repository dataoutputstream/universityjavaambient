package poo.util;

import poo.appello250612.*;
import java.util.Iterator;

public class Heaps {
	private Heaps() {}

	public static <T extends Comparable<? super T>> Heap<T> makeHeap() { return new poo.appello250612.PriorityQueue<T>(); }

	public static <T extends Comparable<? super T>> Heap<T> synchronizedHeap(Heap<T> h) { return new SynchronizedHeap<T>(h); }

	private static class SynchronizedHeap<T extends Comparable<? super T>> implements Heap<T> {
		private Heap<T> h;
		public SynchronizedHeap(Heap<T> h) { this.h = h; }
		public synchronized int size() { return h.size(); }
		public synchronized void add(T elem) { h.add(elem); }
		public synchronized void offer(T elem) { h.add(elem); }
		public synchronized T remove() { return h.remove(); }
		public synchronized T poll() { return h.remove(); }
		public synchronized T peek() { return h.peek(); }
		public synchronized void remove(T elem) { h.remove(elem); }
		public synchronized String toString() { return h.toString(); }
		public synchronized int hashCode() { return h.hashCode(); }
		public synchronized boolean equals(Object o) { return h.equals(o); }
		public synchronized Iterator<T> iterator() { return h.iterator(); }
	} // SynchronizedHeap
} // Heaps
