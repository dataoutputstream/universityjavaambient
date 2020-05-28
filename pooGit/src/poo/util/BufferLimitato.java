package poo.util;

import java.util.*;

public class BufferLimitato<T> extends CodaAstratta<T> {
	private T[] buffer;
	private int in = 0, out = 0, size = 0;
	@SuppressWarnings("unchecked")
	public BufferLimitato(int capacity) {
		if (capacity <= 0) throw new IllegalArgumentException();
		buffer = (T[])new Object[capacity];
	} // Costruttore
	public int size() { return size; }
	public void clear() {
		for (int i = out; i != in; i = (i + 1) % buffer.length)
			buffer[i] = null;
		in = out = size = 0;
	} // clear
	public boolean isEmpty() { return size == 0; }
	public boolean isFull() { return size == buffer.length; }
	public void put(T e) {
		if (size == buffer.length) throw new RuntimeException("Full Buffer!");
		buffer[in] = e; in = (in + 1) % buffer.length; size++;
	} // put
	public T get() {
		if (size == 0) throw new RuntimeException("Empty Buffer!");
		T e = buffer[out]; buffer[out] = null;
		out = (out + 1) % buffer.length; size--;
		return e;
	} // get
	public T peek() {
		if (size == 0) throw new RuntimeException("Empty Buffer!");
		return buffer[out];
	} // peek
	public Iterator<T> iterator() {
		return new BufferIterator();
	} // iterator
	private class BufferIterator implements Iterator<T> {
		private int cursor = -1; private boolean rimovibile = false;
		public boolean hasNext() {
			if (cursor == -1) return size > 0;
			return (cursor + 1) % buffer.length != in;
		} // hasNext
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			if (cursor == -1) cursor = out;
			else cursor = (cursor + 1) % buffer.length;
			rimovibile = true;
			return buffer[cursor];
		} // next
		public void remove() {
			if (!rimovibile) throw new IllegalStateException();
			rimovibile = false;
			for (int i = cursor; i != in; i = (i + 1) % buffer.length)
				buffer[(i + 1) % buffer.length] = buffer[i];
			size--; in = (in - 1 + buffer.length) % buffer.length;
			buffer[in] = null;
		} // remove
	} // BufferIterator
} // BufferLimitato
