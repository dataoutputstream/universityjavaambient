package aziendaagricola2;

public abstract class AziendaAgricola {
	
	final int costo=3;
	final int sacchiIniziali=200;
	protected int sacchi=200;
	protected int cassa=0;
	
	public abstract void paga(int numSacchi)throws InterruptedException;
	public abstract void ritira()throws InterruptedException;
	public abstract void carica()throws InterruptedException;

}
