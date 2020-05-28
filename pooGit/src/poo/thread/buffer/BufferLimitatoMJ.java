package poo.thread.buffer;

import poo.util.BufferLimitato;
import java.util.*;

public class BufferLimitatoMJ<T> extends BufferLimitato<T> {
	private LinkedList<Thread> listaProd = new LinkedList<Thread>();
	private LinkedList<Thread> listaCons = new LinkedList<Thread>();

	public BufferLimitatoMJ(int n) { super(n); }
	private boolean produttoreDeveDormire() {
		if (super.isFull() || listaProd.getFirst() != Thread.currentThread())
			return true;
		return false;
	} // produttoreDeveDormire
	private boolean consumatoreDeveDormire() {
		if (super.isEmpty() || listaCons.getFirst() != Thread.currentThread())
			return true;
		return false;
	} // consumatoreDeveDormire
	public synchronized void put(T msg) {
		listaProd.add(Thread.currentThread());
		while (produttoreDeveDormire())
			try { wait(); } catch (InterruptedException e) {}
		listaProd.removeFirst();
		super.put(msg);
		notifyAll();
	} // put
	public synchronized T get() {
		listaCons.add(Thread.currentThread());
		while (consumatoreDeveDormire())
			try { wait(); } catch (InterruptedException e) {}
		listaCons.removeFirst();
		T msg = super.get();
		notifyAll();
		return msg;
	} // get
	public synchronized int size() { return super.size(); }
	public synchronized void clear() { super.clear(); }
	public synchronized boolean isEmpty() { return super.isEmpty(); }
	public synchronized boolean isFull() { return super.isFull(); }
} // Mailbox
