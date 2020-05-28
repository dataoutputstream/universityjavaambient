package poo.thread.conto;

import java.util.*;

public class ContoBancarioMJ implements ContoBancario {
	private double bilancio = 0;
	private LinkedList<Thread> attesa = new LinkedList<Thread>();
	private Object lock = new Object();
	
	public ContoBancarioMJ(double bilancio) { this.bilancio = bilancio; }
	private boolean prelevanteDeveAttendere(double quanto) {
		if (quanto > bilancio || attesa.getFirst() != Thread.currentThread())
			return true;
		return false;
	} // prelevanteDeveAttendere
	public void deposito(double quanto) {
		synchronized(lock) {
			if (quanto <= 0) throw new IllegalArgumentException();
			bilancio = bilancio + quanto;
			System.out.println("Dopo deposito di " + String.format("%1.2f", quanto) +
				" bilancio = " + String.format("%1.2f", bilancio));
			lock.notifyAll();
		}
	} // deposito
	public void prelievo(double quanto) {
		synchronized(lock) {
			if (quanto <= 0) throw new IllegalArgumentException();
			attesa.add(Thread.currentThread());
			while (prelevanteDeveAttendere(quanto)) {
				try {
					lock.wait();
				} catch (InterruptedException e) {}
			}
			attesa.removeFirst();
			bilancio = bilancio - quanto;
			System.out.println("Dopo prelievo di " + String.format("%1.2f", quanto) +
				" bilancio = " + String.format("%1.2f", bilancio));
			lock.notifyAll();
		}
	} // prelievo
	public double getBilancio() {
		synchronized(lock) {
			return bilancio;
		}
	} // getBilancio
} // ContoBancarioMJ
