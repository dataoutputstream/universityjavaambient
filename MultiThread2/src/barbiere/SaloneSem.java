package it.unical.sisop.appelli.barbiere;

import java.util.concurrent.Semaphore;

public class SaloneSem extends Salone {
	private Semaphore mutex = new Semaphore(1, true);
	
	public SaloneSem(int numPostiSalaDAttesa) {
		super(numPostiSalaDAttesa);
	}
	
	//chiamato dal barbiere
	protected void serviCliente() throws InterruptedException {
		

	}

	//chiamato dal barbiere
	protected void congedaCliente() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	//chiamato dal cliente
	protected boolean entra() throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}
}

