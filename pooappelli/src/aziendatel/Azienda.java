package aziendatel;

import java.util.Iterator;

public abstract class Azienda {
	
	public abstract void aggiungiImpiegato(Persona p,int numero);
	
	public abstract  Iterator<Stanza> uffici();

}
