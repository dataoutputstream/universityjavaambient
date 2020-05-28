package poo.thread.buffer;

import poo.util.*;

public class Produttore extends Thread {
	private int id;
	private BufferLimitato<String> b;
	private int delayMax, delayMin, i = 0;
	private String msg;
	public Produttore(int id, BufferLimitato<String> b, int delayMax, int delayMin) {
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
			delay();
			msg = "P#" + id + "_" + i;
			System.out.println("Produttore#" + id + " genera messaggio " + msg);
			i++;
			b.put(msg);
		}
	} // run
} // Produttore
