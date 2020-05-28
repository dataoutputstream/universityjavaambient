package aziendatel;

import java.util.Iterator;

public class AziendaC extends Azienda{
	
	private static class Nodo<T>{
		T info;
		Nodo<T> next;
	}
	
	Nodo<Stanza>stanze;
	
	@Override
	public void aggiungiImpiegato(Persona p, int numero) {
		
		Nodo<Stanza>nuovo=new Nodo<>();
		if(stanze==null) {
			Stanza n=new StanzaC(numero);
			n.aggiungiPersona(p);
			nuovo.info=n;
			stanze=nuovo;
			return;
		}
		Nodo<Stanza> pre = null,cor;
		cor=stanze;
		while(cor!=null) {
			if(cor.info.getTelefono()==numero) {
				cor.info.aggiungiPersona(p);
				return;
			}
			pre=cor;
			cor=cor.next;
		}
		Stanza n=new StanzaC(numero);
		n.aggiungiPersona(p);
		nuovo.info=n;
		nuovo.next=cor;
		pre.next=nuovo;	
		}
		
	

	@Override
	public Iterator<Stanza> uffici() {
		return new Iteratore();
	}
	
	private class Iteratore implements Iterator<Stanza>{
		Nodo<Stanza>cor,pre;
		boolean rimuovibile=false;

		@Override
		public boolean hasNext() {
			if(cor==null)return stanze!=null;
			return cor.next!=null;
		}

		@Override
		public Stanza next() {
			if(!hasNext())throw new IllegalStateException();
			if(cor==null) {
				cor=stanze;
				rimuovibile=true;
				return cor.info;
			}
			pre=cor;
			rimuovibile=true;
			cor=cor.next;
			return cor.info;
		}
		@Override
		public void remove() {
			if(!rimuovibile)throw new IllegalStateException();
			rimuovibile=false;
			if(cor==stanze) {
				if(cor.next!=null) {
				stanze=stanze.next;
				return;
				}
			stanze=null;
			}
			pre.next=cor.next;
		}
	}

}
