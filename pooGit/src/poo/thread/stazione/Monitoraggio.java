package poo.thread.stazione;

import java.io.*;

public class Monitoraggio {
	public static void main(String[]args) throws IOException {
		Stazione s = new StazioneAtomica(); // new StazioneUnSafe();
		Trasmettitore t = new Trasmettitore(s, 5000, "stazione_log");
		Thread tt = new Thread(t);
		Sensore s1 = new Sensore(1, 500, s);
		Sensore s2 = new Sensore(2, 500, s);
		tt.start();
		s1.start();
		s2.start();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {}
		s1.interrupt();
		s2.interrupt();
		tt.interrupt();
	} // main
} // Monitoraggio
