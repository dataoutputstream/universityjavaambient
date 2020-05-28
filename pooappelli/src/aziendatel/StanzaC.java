package aziendatel;

import java.util.Iterator;

public class StanzaC extends Stanza{
	
	private class Nodo<T>{
		T info;
		Nodo<T>next;
	}
	
	Nodo<Persona>testa;

	public StanzaC(int numero) {
		super(numero);
	}

	@Override
	public void aggiungiPersona(Persona p) {
		Nodo<Persona>nuovo=new Nodo<>();
		nuovo.info=p;
		if(testa==null) {
			testa=nuovo;
			return;
		}
		Nodo<Persona>pre=new Nodo<>(),cor;
		cor=testa;
		while(cor!=null) {
			pre=cor;
			cor=cor.next;
		}
		pre.next=nuovo;
	}

	@Override
	public Iterator<Persona> impiegati() {
		return new Iteratore();
	}
	
	private class Iteratore implements Iterator<Persona>{
		
		Nodo<Persona>pre,cor;
		boolean rimuovibile=false;

		@Override
		public boolean hasNext() {
			if(cor==null) return testa!=null;
			return cor.next!=null;
		}

		@Override
		public Persona next() {
			if(!hasNext())throw new IllegalStateException();
			boolean rimuovibile=true;
			if(cor==null) {
				cor=testa;
				return cor.info;
			}
			pre=cor;
			cor=cor.next;
			return cor.info;
		}
		@Override
		public void remove() {
			if(!rimuovibile)throw new IllegalStateException();
			boolean rimuovibile=false;
			if(cor==testa) {
				testa=testa.next;
				return;
			}
			pre.next=cor.next;
		}
	}

}
