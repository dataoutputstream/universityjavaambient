package poo.thread.scambiatore;

public class Produttore extends Thread {
	private Exchanger<String> exch;
	private int id, delayMax, delayMin, i = 0;
	private String msg;
	public Produttore(int id, Exchanger<String> exch, int delayMax, int delayMin) {
		this.id = id;
		this.exch = exch;
		this.delayMax = delayMax;
		this.delayMin = delayMin;
	}
	private void delay() {
		try {
			Thread.sleep((int)(Math.random() * (delayMax - delayMin) + delayMin));
		} catch (InterruptedException e) {}
	} // delay
	public void run() {
		while (true) {
			delay();
			msg="P#" + id + "_" + i;
			System.out.println("Produttore#" + id + " genera messaggio " + msg);
			i++;
			exch.exchange(msg);
		}
	} // run
} // Produttore
