package poo.thread.stazione;

public class StazioneUnSafe implements Stazione{
	private int veicoli=0;
	public void segnaleVeicolo(){
		veicoli++;
	}//campionamento
	public int rilevazione(){
		int dato=veicoli;
		veicoli=0;
		return dato;
	}//rilevazione
}//StazioneUnSafe
