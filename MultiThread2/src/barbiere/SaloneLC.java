package barbiere;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import it.unical.sisop.appelli.barbiere.Barbiere.STATI_BARBIERE;

public class SaloneLC extends Salone {

	private Lock l = new ReentrantLock(true);

	// condition cliente
	private long idClientePoltrona = -1;
	private long idUltimoCliente = -1;
	
	private Condition poltronaOccupataC = l.newCondition();
	private Condition attendiFineTaglio = l.newCondition();

	// condition barbiere
	// come condizione usiamo il metodo vuoto()
	private Condition salaVuotaC = l.newCondition();
	private Condition attendiCliente = l.newCondition();

	public SaloneLC(int postiSalaAttesa) {
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
			idUltimoCliente = idClientePoltrona;
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
				postiDisponibili--;
				System.out.println(this);
				while (!pronto()) {
					poltronaOccupataC.await();
				}
				idClientePoltrona = Thread.currentThread().getId();
				attendiCliente.signal();
				
				//while(!(statoBarbiere == STATI_BARBIERE.PRONTO))
				while(idUltimoCliente!=idClientePoltrona)
					attendiFineTaglio.await();

				idClientePoltrona = -1;
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
		return idClientePoltrona == -1 && !(statoBarbiere == STATI_BARBIERE.ADDORMENTATO);
	}

	public static void main(String[] args) {
		Salone salone = new SaloneLC(4);
		salone.test(20);
	}
}
