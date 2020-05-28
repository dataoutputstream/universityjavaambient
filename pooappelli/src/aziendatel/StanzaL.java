package aziendatel;

import java.util.Iterator;
import java.util.LinkedList;

public class StanzaL extends Stanza{
	LinkedList<Persona> impiegati;

	public StanzaL(int numero) {
		super(numero);
		impiegati=new LinkedList<Persona>();
	}

	@Override
	public void aggiungiPersona(Persona p) {
		impiegati.add(p);
	}

	@Override
	public Iterator<Persona> impiegati() {
		return impiegati.iterator();
	}

}
