package barbiere;

import it.unical.sisop.appelli.barbiere.Barbiere.STATI_BARBIERE;

public abstract class Salone {

	protected final int postiSalaAttesa;
	protected int postiDisponibili;

	protected STATI_BARBIERE statoBarbiere;

	public Salone(int postiSalaAttesa) {
		this.postiSalaAttesa = postiSalaAttesa;
		this.postiDisponibili = postiSalaAttesa + 1;
		statoBarbiere = STATI_BARBIERE.ADDORMENTATO;
	}

	// chiamato dal barbiere
	protected abstract void serviCliente() throws InterruptedException;

	// chiamato dal barbiere
	protected abstract void congedaCliente() throws InterruptedException;

	// chiamato dal cliente
	protected abstract boolean entra() throws InterruptedException;

	protected boolean pieno() {
		if (this.postiDisponibili == 0)
			return true;
		else
			return false;
	}

	protected boolean vuoto() {
		if (this.postiDisponibili == postiSalaAttesa + 1)
			return true;
		else
			return false;
	}

	protected void setStatoBarbiere(STATI_BARBIERE stato) {
		this.statoBarbiere = stato;
	}

	public String toString() {
		synchronized (System.out) {
			String ret = "";
			if (statoBarbiere == STATI_BARBIERE.ADDORMENTATO)
				ret += "ADDORMENTATO ";
			else if (statoBarbiere == STATI_BARBIERE.PRONTO)
				ret += "PRONTO       ";
			else if  (statoBarbiere == STATI_BARBIERE.TAGLIO)
				ret += "TAGLIOINCORSO";
			ret += " [";
			for (int i = 0; i < postiSalaAttesa + 1 - postiDisponibili; i++) {
				ret += "X";
			}
			for (int i = 0; i < postiDisponibili; i++) {
				ret += "O";
			}
			ret += "]";
			return ret;
		}
	}

	public void test(int numClienti) {
		Barbiere b = new Barbiere(this);
		Thread t = new Thread(b);
		t.setDaemon(true);
		t.start();

		for (int i = 0; i < numClienti; i++) {
			new Thread(new Cliente(this)).start();
		}
	}
}
