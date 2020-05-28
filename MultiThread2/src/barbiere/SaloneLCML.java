package it.unical.sisop.appelli.barbiere;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.10, Jul 25, 2014
 */
public class SaloneLC extends Salone2 {

	private Lock l = new ReentrantLock();
	private Condition barbiere = l.newCondition();
	private Condition salaDAttesa = l.newCondition();
	private Condition taglioCapelli = l.newCondition();

	private boolean barbiereAddormentato = false;
	private boolean clienteServito = false;
	private boolean prossimoCliente = false;

	public SaloneLC(int numPostiSalaDAttesa) {
		super(numPostiSalaDAttesa);
	}

	@Override
	public boolean entra() throws InterruptedException {
		boolean servito = true;
		stampaStato("@:) ...", "");
		l.lock();
		try {
			if (numPostiDisponibili > 0) {
				numPostiDisponibili--;
				if (!barbiereAddormentato || prossimoCliente) {
					attendiInSalaDAttesa();
				}
				siediInPoltrona();
				stampaStato(EMPTY, "... :)");
				numPostiDisponibili++;
			} else {
				servito = false;
				stampaStato(EMPTY, "... @:(");
			}
		} finally {
			l.unlock();
		}
		return servito;
	}

	private void attendiInSalaDAttesa() throws InterruptedException {
		while (!prossimoCliente) {
			salaDAttesa.await();
		}
		prossimoCliente = false;
	}

	private void siediInPoltrona() throws InterruptedException {
		barbiereAddormentato = false;
		barbiere.signal();
		while (!clienteServito) {
			taglioCapelli.await();
		}
		clienteServito = false;
		barbiere.signal();
	}

	@Override
	public void serviCliente() throws InterruptedException {
		l.lock();
		try {
			if (numPostiDisponibili - 1 < numPostiSalaDAttesa) {
				prossimoCliente = true;
				salaDAttesa.signal();
			}
			stampaStato();
			barbiereAddormentato = true;
			while (barbiereAddormentato) {
				barbiere.await();
			}
			stampaStato();
		} finally {
			l.unlock();
		}
	}

	@Override
	public void congedaCliente() throws InterruptedException {
		l.lock();
		try {
			clienteServito = true;
			taglioCapelli.signal();
			while (clienteServito) {
				barbiere.await();
			}
		} finally {
			l.unlock();
		}
	}

	public static void main(String[] args) {
		int capienza = 4;
		int numClienti = 12;
		new SaloneLC(capienza).test(numClienti);
	}
}