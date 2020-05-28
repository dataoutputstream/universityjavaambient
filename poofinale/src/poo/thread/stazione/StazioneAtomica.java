package poo.thread.stazione;
import java.util.concurrent.atomic.*;

public class StazioneAtomica implements Stazione{
	private AtomicInteger veicoli=new AtomicInteger(0);
	public synchronized void segnaleVeicolo(){
		veicoli.incrementAndGet();
	}//campionamento
	public synchronized int rilevazione(){
		int dato=0;
		while( !veicoli.compareAndSet(dato=veicoli.get(),0) );
		return dato;
	}//rilevazione
}//StazioneAtomica
