package poo.util;
import java.util.*;
public class AlberoBinarioDiRicerca<T extends Comparable<? super T>>
	implements CollezioneOrdinata<T>{

	private static class Albero<E>{
		E info;
		Albero<E> figlioSinistro, figlioDestro;
	}//Albero

	private Albero<T> radice=null;

	//metodi pubblici di interfaccia
	public int size(){
		return size(radice);
	}//size
	public boolean contains( T elem ){
		return contains(radice,elem);
	}//contains
	public T get( T elem ){
		return get( radice, elem );
	}//get
	public void clear(){ radice=null; }//clear
	public boolean isEmpty(){ return radice==null; }//isEmpty
	public boolean isFull(){ return false; }//isFull
	public void add( T elem ){
		radice=add( radice, elem );
	}//add
	public void remove( T elem ){
		radice=remove( radice, elem );
	}//remove
	@SuppressWarnings("unchecked")
	public boolean equals( Object x ){
		if( !(x instanceof AlberoBinarioDiRicerca) )
			return false;
		if( x==this ) return true;
		return equals( this.radice,
				       ((AlberoBinarioDiRicerca)x).radice );
	}//equals
	public String toString(){
		StringBuilder sb=new StringBuilder(200);
		sb.append('[');
		toString( radice, sb );
		if( sb.length()>1 ) sb.setLength( sb.length()-1 );
		sb.append(']');
		return sb.toString();
	}//toString
	public int hashCode(){
		return hashCode(radice);
	}//hashCode

	public void visitaSimmetrica(){
		visitaSimmetrica(radice);
	}//visitaSimmetrica

	public void visitaSimmetrica( List<T> l ){
		visitaSimmetrica( radice, l );
	}//visitaSimmetrica

	public void visitaAnticipata(){
		visitaAnticipata(radice);
	}//visitaAnticipata

	public void visitaPosticipata(){
		visitaPosticipata(radice);
	}//visitaPosticipata

	//metodi privati ricorsivi
	private int size( Albero<T> radice ){
		if( radice==null ) return 0;
		return 1+size(radice.figlioSinistro)+
		         size(radice.figlioDestro);
	}//size
	private boolean contains( Albero<T> radice, T elem ){
		if( radice==null ) return false;
		if( radice.info.equals(elem) ) return true;
		if( radice.info.compareTo(elem)>0 )
			return contains(radice.figlioSinistro, elem);
		return contains(radice.figlioDestro,elem);
	}//contains

	private T get( Albero<T> radice, T elem ){
		if( radice==null ) return null;
		if( radice.info.equals(elem) ) return radice.info;
		if( radice.info.compareTo(elem)>0 )
			return get(radice.figlioSinistro, elem);
		return get(radice.figlioDestro,elem);
	}//contains

	private Albero<T> add( Albero<T> radice, T elem ){
		if( radice==null ){
			radice=new Albero<T>();
			radice.info=elem;
			return radice;
		}
		if( radice.info.compareTo(elem)>=0 ){
			radice.figlioSinistro=add( radice.figlioSinistro,elem );
			return radice;
		}
		radice.figlioDestro=add( radice.figlioDestro,elem );
		return radice;
	}//add
	private Albero<T> remove( Albero<T> radice, T elem ){
		if( radice==null ) return radice;
		if( radice.info.compareTo(elem)>0 ){
			radice.figlioSinistro=remove( radice.figlioSinistro, elem );
			return radice;
		}
		if( radice.info.compareTo(elem)<0 ){
			radice.figlioDestro=remove( radice.figlioDestro, elem );
			return radice;
		}
		//trovato Albero con elem
		if( radice.figlioSinistro==null && radice.figlioDestro==null ){
			//Albero foglia
			return null;
		}
		if( radice.figlioSinistro==null ){
			//Albero con il solo figlio destro
			return radice.figlioDestro;
		}
		if( radice.figlioDestro==null ){
			//Albero con il solo figlio sinistro
			return radice.figlioSinistro;
		}
		//Albero radice con entrambi i figli
		//promozione del nodo più a sinistra, diciamolo nps, nel
		//sotto albero destro di radice, ed eliminazione di nps
		if( radice.figlioDestro.figlioSinistro==null ){
			//promozione
			radice.info=radice.figlioDestro.info;
		    //eliminazione di nps
			radice.figlioDestro=radice.figlioDestro.figlioDestro;
			return radice;
		}
		//ricerca nodo più a sinistra nel sotto albero destro
		Albero<T> padre=radice.figlioDestro,
		figlio=radice.figlioDestro.figlioSinistro;
		while( figlio.figlioSinistro!=null ){
			padre=figlio;
			figlio=figlio.figlioSinistro;
		}
		//promozione
		radice.info=figlio.info;
		//eliminazione di nps
		padre.figlioSinistro=figlio.figlioDestro;
		return radice;
	}//remove

	private void toString( Albero<T> radice, StringBuilder sb ){
		if( radice!=null ){
			toString( radice.figlioSinistro, sb );
			sb.append( radice.info ); sb.append(',');
			toString( radice.figlioDestro, sb );
		}
	}//toString

	private boolean equals( Albero<T> a1, Albero<T> a2 ){
		if( a1==null && a2==null )
			return true;
		if( a1==null || a2==null )
			return false;
		if( !a1.info.equals(a2.info ) )
			return false;
		return equals(a1.figlioSinistro,a2.figlioSinistro) &&
		equals(a1.figlioDestro,a2.figlioDestro);
	}//equals

	private int hashCode( Albero<T> radice ){
		int primo=43;
		if( radice==null ) return 0;
		return radice.info.hashCode()*primo+
			   hashCode(radice.figlioSinistro)+
			   hashCode(radice.figlioDestro);
	}//hashCode

	private void visitaSimmetrica( Albero<T> radice ){
		if( radice!=null ){
			visitaSimmetrica( radice.figlioSinistro );
			System.out.print(radice.info+" ");
			visitaSimmetrica( radice.figlioDestro );
		}
	}//visitaSimmetrica

	private void visitaSimmetrica( Albero<T> radice, List<T> l ){
		if( radice!=null ){
			visitaSimmetrica( radice.figlioSinistro, l );
			l.add(radice.info);
			visitaSimmetrica( radice.figlioDestro, l );
		}
	}//visitaSimmetrica

	private void visitaAnticipata( Albero<T> radice ){
		if( radice!=null ){
			System.out.print(radice.info+" ");
			visitaAnticipata( radice.figlioSinistro );
			visitaAnticipata( radice.figlioDestro );
		}
	}//visitaAnticipata

	private void visitaPosticipata( Albero<T> radice ){
		if( radice!=null ){
			visitaPosticipata( radice.figlioSinistro );
			visitaPosticipata( radice.figlioDestro );
			System.out.print(radice.info+" ");
		}
	}//visitaPosticipata

	public Iterator<T> iterator(){ return new ABRIterator(); }

	private class ABRIterator implements Iterator<T>{
		private List<T> contenuto=new LinkedList<>();
		private T cor=null;
		private Iterator<T> it=null;
		public ABRIterator(){
			visitaSimmetrica( contenuto );
			it=contenuto.iterator();
		}
		public boolean hasNext(){
			return it.hasNext();
		}//hasNext
		public T next(){
			cor=it.next();
			return cor;
		}//next
		public void remove(){
			it.remove(); //sulla lista
			AlberoBinarioDiRicerca.this.remove( cor ); //sull'albero
			cor=null;
		}//remove
	}//ABRIterator

	public static void main( String[] args ){
		AlberoBinarioDiRicerca<Integer> abr=
			new AlberoBinarioDiRicerca<Integer>();
		abr.add(5);
		abr.add(15);
		abr.add(8);
		abr.add(-10);
		abr.add(2);
		abr.add(4);
		abr.add(9);
		abr.add(-6);
		abr.add(20);
		abr.add(12);
		System.out.println("size="+abr.size());
		System.out.println(abr);
		System.out.println("Stampa con l'iteratore");
		System.out.print("[");
		Iterator<Integer> it=abr.iterator();
		while( it.hasNext() ){
			System.out.print( it.next() );
			if( it.hasNext() ) System.out.print(",");
		}
		System.out.println("]");
		System.out.println("abr contains 15: "+abr.contains(15));
		int r=15;
		System.out.println("Test rimozione di "+r);
		abr.remove(r);
		System.out.println(abr);
		System.out.println("Test metodi di visita");
		System.out.println("Visita Simmetrica");
		abr.visitaSimmetrica();
		System.out.println();
		System.out.println("Visita Anticipata");
		abr.visitaAnticipata();
		System.out.println();
		System.out.println("Visita Posticipata");
		abr.visitaPosticipata();
		System.out.println();

	}//main
}//AlberoBinarioDiRicerca
