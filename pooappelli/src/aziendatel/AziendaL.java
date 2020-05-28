package aziendatel;

import java.util.Iterator;
import java.util.LinkedList;

public class AziendaL extends Azienda{
	
	LinkedList<Stanza>stanze=new LinkedList<Stanza>();

	@Override
	public void aggiungiImpiegato(Persona p, int numero) {
		for(Stanza s:stanze) {
			if(s.getTelefono()==numero) {
				s.aggiungiPersona(p);
				return;
			}
		}
		Stanza n=new StanzaL(numero);
		n.aggiungiPersona(p);
		stanze.add(n);
		
	}

	@Override
	public Iterator<Stanza> uffici() {
		
		return stanze.iterator();
	}

}
