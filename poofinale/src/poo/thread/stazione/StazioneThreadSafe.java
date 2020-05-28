package poo.thread.stazione;

public class StazioneThreadSafe implements Stazione{
	private int veicoli=0;
	public synchronized void segnaleVeicolo(){
		veicoli++;
	}//campionamento
	public synchronized int rilevazione(){
		int dato=veicoli;
		veicoli=0;
		return dato;
	}//rilevazione
}//StazioneUnSafe
