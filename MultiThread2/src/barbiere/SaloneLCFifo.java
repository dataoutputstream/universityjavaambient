package barbiere;

import it.unical.sisop.barbiere.Barbiere.STATI_BARBIERE;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaloneLCFifo extends Salone {

	private Lock l = new ReentrantLock(true);
	private LinkedList<Thread> lista = new LinkedList<Thread>();

	// condition cliente
	private Condition poltronaOccupataC = l.newCondition();
	private Condition attendiFineTaglio = l.newCondition();

	// condition barbiere
	// come condizione usiamo il metodo vuoto()
	private Condition salaVuotaC = l.newCondition();
	private Condition attendiCliente = l.newCondition();

	public SaloneLCFifo(int postiSalaAttesa) {
		super(postiSalaAttesa);
	}

	protected void serviCliente() throws InterruptedException {
		l.lock();
		try {
			if (vuoto())
				addormentati();
			while (idClientePoltrona==-1)
					attendiCliente.await();
			taglio();			
		} finally {
			l.unlock();
		}
	}

	private void taglio() {
		statoBarbiere = STATI_BARBIERE.TAGLIO;
		System.out.print(this);
		System.out.println(idClientePoltrona);
		
	}

	private void addormentati() throws InterruptedException {
		statoBarbiere = STATI_BARBIERE.ADDORMENTATO;
		System.out.println(this);
		while (vuoto()) {
			salaVuotaC.await();
		}
		statoBarbiere = STATI_BARBIERE.PRONTO;
	}

	protected void congedaCliente() throws InterruptedException {
		l.lock();
		try {
			statoBarbiere = STATI_BARBIERE.PRONTO;
			System.out.println(this);
			attendiFineTaglio.signal();
		} finally {
			l.unlock();
		}

	}

	protected boolean entra() throws InterruptedException {
		boolean servito = false;
		l.lock();
		try {
			if (pieno()) {
				servito = false;
			} else {
				if (vuoto())
					salaVuotaC.signal();
				lista.add(Thread.currentThread());
				postiDisponibili--;
				System.out.println(this);
				while (!pronto()) {
					poltronaOccupataC.await();
				}
				attendiCliente.signal();
				
				// attendiFineTaglio();
				attendiFineTaglio.await();

				lista.remove();
				poltronaOccupataC.signal();

				postiDisponibili++;
				System.out.println(this);
				servito = true;
			}
		} finally {
			l.unlock();
		}
		return servito;
	}

	private boolean pronto() {
		return lista.getFirst() == Thread.currentThread() && !(statoBarbiere == STATI_BARBIERE.ADDORMENTATO);
	}

	public static void main(String[] args) {
		Salone salone = new SaloneLCFifo(4);
		salone.test(20);
	}
}
