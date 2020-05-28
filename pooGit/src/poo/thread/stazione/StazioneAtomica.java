package poo.thread.stazione;

import java.util.concurrent.atomic.AtomicInteger;

public class StazioneAtomica implements Stazione {
	private AtomicInteger veicoli = new AtomicInteger();
	public void segnaleVeicolo() {
		veicoli.incrementAndGet();
	} // campionamento
	public int rilevazione() {
		return veicoli.getAndSet(0);
	} // rilevazione
}
