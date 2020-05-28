package aziendatel;

import java.util.Iterator;

public abstract class Stanza implements Comparable<Stanza>{
	
	private Integer numero;
	
	public Stanza(int numero) {
		this.numero=numero;
	}
	
	
	public abstract void aggiungiPersona(Persona p);
	
	public int getTelefono(){
		return  numero;
	}
	
	public abstract Iterator<Persona>impiegati();
	
	public boolean equals(Object o) {
		if(o==this)return true;
		Stanza s=(Stanza)o;
		return numero==s.numero;
	}
	
	public int hashcode() {
		return numero.hashCode();
	}
	public String toString() {
		return Integer.toString(numero);
	}
	@Override
	public int compareTo(Stanza s) {
		return numero.compareTo(s.numero);
		
	}

}
