package poo.appello250612;

import java.util.*;
import poo.util.*;

public class Applicazione {
	public static void main(String[]args) {
		Heap<Paziente> lista = Heaps.synchronizedHeap(new poo.appello250612.PriorityQueue<Paziente>());
		Thread arrivi = new Arrivi(lista);
		Thread uscite = new Uscite(lista);
		arrivi.start(); uscite.start();
		try {
			Thread.currentThread().sleep(25000);
			arrivi.interrupt(); uscite.interrupt();
			arrivi.join(); uscite.join();
		} catch (InterruptedException e) {}
		System.out.println("Lista residua: " + lista);
		System.out.println("Bye!");
	} // main

	private static class Arrivi extends Thread {
		private Heap<Paziente> lista;
		public Arrivi(Heap<Paziente> lista) { this.lista = lista; }
		public void run() {
			while (!isInterrupted()) {
				Paziente p = new Paziente(getRandomPriority());
				System.out.println(p.toString() + " entra in lista.");
				lista.add(p);
				synchronized(lista) { lista.notify(); }
				try { Thread.sleep(1000); } catch (InterruptedException e) { break; }
			}
		} // run
		private Paziente.Priority getRandomPriority() {
			java.util.Random rnd = new java.util.Random();
			int p = rnd.nextInt(3);
			if (p == 0) return Paziente.Priority.HIGH;
			else if (p == 1) return Paziente.Priority.MEDIUM;
			else return Paziente.Priority.LOW;
		}
	} // Arrivi
	public static class Uscite extends Thread {
		private Heap<Paziente> lista;
		public Uscite(Heap<Paziente> lista) { this.lista = lista; }
		public void run() {
			while (!isInterrupted()) {
				try { Thread.sleep(1500); } catch (InterruptedException e) { break; }
				synchronized(lista) {
					while (lista.size() == 0 && !isInterrupted())
						try { lista.wait(); } catch (InterruptedException e) { return; }
				}
				System.out.println(lista.remove().toString() + " esce dalla lista.");
			}
		}
	} // Uscite
} // Applicazione
