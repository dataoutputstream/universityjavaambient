package aziendaagricola;


public class Magazziniere extends Thread{
	private AziendaAgricola aziendaAgricola;
	
	public Magazziniere(AziendaAgricola aziendaAgricola){
		this.aziendaAgricola = aziendaAgricola;
	}
	@Override
	public void run(){
		while(true){
			try{
				aziendaAgricola.carica();
				sleep(2000);
			}catch(InterruptedException e){
			}
			System.out.println(this);
		}
	}
	
	public String toString(){
		return "Magazziniere ha caricato "+aziendaAgricola.sacchettiIniziali+ " sacchi nel magazzino";
	}
}
