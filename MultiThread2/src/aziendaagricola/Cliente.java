package aziendaagricola;

import java.util.Random;

public class Cliente extends Thread{
	private Random random = new Random();
	private AziendaAgricola aziendaAgricola;
	private int numeroSacchiDaPrelevare = 0;
	private int id;
	
	public Cliente(AziendaAgricola aziendaAgricola,int id){
		this.aziendaAgricola = aziendaAgricola;
		this.id = id;
	}
	@Override
	public void run(){
		numeroSacchiDaPrelevare = decidi(10);
		try{
			aziendaAgricola.paga(numeroSacchiDaPrelevare);
			//cambiare con un for
			while(numeroSacchiDaPrelevare>0){
				aziendaAgricola.ritira();
				System.out.println(this);
				sleep(1000);
				numeroSacchiDaPrelevare--;
			}
		}catch(InterruptedException e){
		}
		
	}
	public int decidi(int max){
		return random.nextInt(max)+1;
	}
	
	public String toString(){
		return "Cliente ["+id+"] ha prelevato 1 sacco dal magazzino. Deve ancora prelevare "+numeroSacchiDaPrelevare+" sacchi.";
	}
}
