package poo.thread.buffer;

import poo.util.*;

public class Consumatore extends Thread {
	private int id;
	private BufferLimitato<String> b;
	private int delayMax, delayMin;
	private String msg;
	public Consumatore(int id, BufferLimitato<String> b, int delayMax, int delayMin) {
		this.id = id; this.b = b;
		this.delayMax = delayMax; this.delayMin = delayMin;
	} // Costruttore
	private void delay() {
		try {
			Thread.sleep((int)(Math.random() * (delayMax - delayMin) + delayMin));
		} catch (InterruptedException e) {}
	} // delay
	public void run() {
		while (true) {
			msg = b.get();
			System.out.println("Consumatore#" + id + " consuma messaggio " + msg);
			delay();
		}
	} // run
} // Consumatore
