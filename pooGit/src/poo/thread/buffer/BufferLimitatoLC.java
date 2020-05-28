package poo.thread.buffer;

import poo.util.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class BufferLimitatoLC<T> extends BufferLimitato<T>{
	private LinkedList<Thread> codaProduttori = new LinkedList<Thread>();
	private LinkedList<Thread> codaConsumatori = new LinkedList<Thread>();
	private Lock lucchetto = new ReentrantLock();
	private Condition attesaProd = lucchetto.newCondition();
	private Condition attesaCons = lucchetto.newCondition();

	public BufferLimitatoLC(int n) { super(n); }
	private boolean produttoreDeveDormire() {
		if (isFull() || codaProduttori.getFirst() != Thread.currentThread())
			return true;
		return false;
	} // produttoreDeveDormire
	private boolean consumatoreDeveDormire() {
		if (isEmpty() || codaConsumatori.getFirst() != Thread.currentThread())
			return true;
		return false;
	} // consumatoreDeveDormire
	public void put(T elem) {
		lucchetto.lock();
		try {
			codaProduttori.add(Thread.currentThread());
			while (produttoreDeveDormire()) {
				try {
					attesaProd.await();
				} catch (InterruptedException e) {}
			}
			codaProduttori.removeFirst();
			super.put(elem);
			attesaCons.signalAll();
		} finally {
			lucchetto.unlock();
		}
	} // put
	public T get() {
		lucchetto.lock();
		try {
			codaConsumatori.add(Thread.currentThread());
			while (consumatoreDeveDormire()) {
				try {
					attesaCons.await();
				} catch (InterruptedException e) {}
			}
			codaConsumatori.removeFirst();
			T x = super.get();
			attesaProd.signalAll();
			return x;
		} finally {
			lucchetto.unlock();
		}
	} // get
	public boolean isEmpty() {
		lucchetto.lock();
		try {
			return super.isEmpty();
		} finally { lucchetto.unlock(); }
	} // isEmpty
	public boolean isFull() {
		lucchetto.lock();
		try {
			return super.isFull();
		} finally { lucchetto.unlock(); }
	} // isFull
	public int size() {
		lucchetto.lock();
		try {
			return super.size();
		} finally { lucchetto.unlock(); }
	} // size
	public void clear() {
		lucchetto.lock();
		try {
			super.clear();
		} finally { lucchetto.unlock(); }
	} // clear	
} // BufferLimitatoLC
