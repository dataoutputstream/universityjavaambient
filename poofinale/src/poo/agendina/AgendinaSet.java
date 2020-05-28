package poo.agendina;
import java.util.*;
/**
 * AgendinaSet utilizza un HashSet per velocizzare
 * le operazioni di ricerca. Tuttavia, quando e' richiesto
 * l'ordine, ossia a tempo di un'iterazione, si passa
 * ad un TreeSet. Per garantire che un'eventuale rimozione
 * fatta sull'iteratore si ripercuota sull'hash set, si
 * implementa un proprio iteratore interno che provvede, al
 * tempo di una remove, a mantenere allineati il tree set
 * utilizzato per l'iterazione, e l'hash set utilizzato
 * come base dell'implementazione. La costruzione fa uso
 * di una semplice inner class.
 * @author Libero Nigro
 *
 */
public class AgendinaSet extends AgendinaAstratta{
	private Set<Nominativo> tabella=new HashSet<Nominativo>();
	
	private class Iteratore implements Iterator<Nominativo>{
		private Iterator<Nominativo> it;
		private Nominativo corrente=null;
		public Iteratore(){
			TreeSet<Nominativo> ts=new TreeSet<Nominativo>(tabella);
			it=ts.iterator();
		}
		public boolean hasNext(){ return it.hasNext(); }
		public Nominativo next(){
			corrente=it.next();
			return corrente;
		}//next
		public void remove(){
			it.remove();
			tabella.remove( corrente );
			corrente=null;
		}//remove	
	}//Iteratore
	
	@Override
	public int size(){ return tabella.size(); }
	
	@Override
	public void svuota(){
		tabella.clear();
	}//clear
	
	@Override
    public void aggiungi( Nominativo n ){
		tabella.remove(n);
		tabella.add(n);
	}//aggiungi
	
	@Override
	public void rimuovi( Nominativo n ){
		tabella.remove( n );
	}//rimuovi
	
	@Override
	public Nominativo cerca( Nominativo n ){
		if( tabella.contains(n) ){
			Iterator<Nominativo> i=tabella.iterator();
			while( i.hasNext() ){
				Nominativo q=i.next();
				if( q.equals(n) ) return q;
			}
		}
		return null;
	}//cerca
	
	@Override
	public Iterator<Nominativo> iterator(){
		return new Iteratore();
	}//iterator
	
}//AgendinaSet