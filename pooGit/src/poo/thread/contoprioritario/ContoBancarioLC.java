package poo.thread.contoprioritario;

import java.util.*;
import java.util.concurrent.locks.*;

public class ContoBancarioLC implements ContoBancario {
	private class Elemento implements Comparable<Elemento> {
		private double quanto;
		private int id;
		public Elemento(int id, double quanto) {
			this.id = id; this.quanto = quanto;
		}
		public int getId() { return id; }
		public double getQuanto() { return quanto; }
		public int compareTo(Elemento e) {
			if (this.id < e.id) return 1;
			if (this.id == e.id) return 0;
			return -1;
		}
	} // Elemento
	private double bilancio = 0;
	private PriorityQueue<Elemento> attesa = new PriorityQueue<Elemento>();
	private Lock lock = new ReentrantLock();
	private Condition prelevanti = lock.newCondition();
	public ContoBancarioLC(double bilancio) { this.bilancio=bilancio; }
	private boolean prelevanteDeveAttendere(int id, double quanto) {
		if (quanto > bilancio || attesa.peek().getId() != id)
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
			if (attesa.size() > 0 && bilancio >= attesa.peek().getQuanto())
				prelevanti.signalAll();
		} finally { lock.unlock(); }
	} // deposito
	public void prelievo(int id, double quanto) {
		lock.lock();
		try {
			if (quanto <= 0) throw new IllegalArgumentException();
			attesa.add(new Elemento(id, quanto));
			while (prelevanteDeveAttendere(id, quanto)) {
				try {
					prelevanti.await();
				} catch (InterruptedException e) {}
			}
			attesa.poll();
			bilancio = bilancio - quanto;
			System.out.println("Dopo prelievo di " + String.format("%1.2f", quanto) +
				" bilancio = " + String.format("%1.2f", bilancio));
			if (attesa.size() > 0 && bilancio >= attesa.peek().getQuanto())
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
