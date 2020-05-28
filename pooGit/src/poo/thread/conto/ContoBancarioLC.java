package poo.thread.conto;

import java.util.*;
import java.util.concurrent.locks.*;

public class ContoBancarioLC implements ContoBancario {
	private double bilancio = 0;
	private LinkedList<Thread> attesa = new LinkedList<Thread>();
	private Lock lock = new ReentrantLock();
	private Condition prelevanti = lock.newCondition();
	public ContoBancarioLC(double bilancio) { this.bilancio = bilancio; }
	private boolean prelevanteDeveAttendere(double quanto) {
		if (quanto > bilancio || attesa.getFirst() != Thread.currentThread())
			return true;
		return false;
	} // prelevanteDeveAttendere
	public void deposito(double quanto) {
		lock.lock();
		try {
			if (quanto <= 0) throw new IllegalArgumentException();
			bilancio = bilancio + quanto;
			System.out.println("Dopo deposito di " + String.format("%1.2f", quanto) +
				" bilancio = " + String.format("%1.2f", bilancio));
			prelevanti.signalAll();
		} finally { lock.unlock(); }
	} // deposito
	public void prelievo(double quanto) {
		lock.lock();
		try{		
			if (quanto <= 0) throw new IllegalArgumentException();
			attesa.add(Thread.currentThread());
			while (prelevanteDeveAttendere(quanto)) {
				try {
					prelevanti.await();
				} catch (InterruptedException e) {}
			}
			attesa.removeFirst();
			bilancio = bilancio - quanto;
			System.out.println("Dopo prelievo di " + String.format("%1.2f", quanto) +
				" bilancio = " + String.format("%1.2f", bilancio));
			prelevanti.signalAll();
		} finally { lock.unlock(); }
	} // prelievo
	public double getBilancio() {
		lock.lock();
		try {
			return bilancio;
		} finally { lock.unlock(); }
	} // getBilancio
} // ContoBancarioLC
