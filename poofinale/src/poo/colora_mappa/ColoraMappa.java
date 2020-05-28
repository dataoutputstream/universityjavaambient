package poo.colora_mappa;
import poo.grafo.*;
import java.util.*;
public class ColoraMappa {
	private enum Colore{ ROSSO, GIALLO, VERDE, NERO };
	private Grafo<Integer> mappa=new GrafoNonOrientatoImpl<>();
	private Map<Colore,Set<Integer>> stessoColore=new HashMap<>();
	private int numSol=0;
	public ColoraMappa(){
		Colore col[]=Colore.values();
		for( int i=0; i<col.length; ++i ){
			stessoColore.put( col[i], new HashSet<>() );
		}
	}
	public void confinanti( int n1, int n2 ){
		assert n1>=0 && n2>=0 : "Indice nazione negativo";
		if( !mappa.esisteNodo(n1) ) mappa.insNodo(n1);
		if( !mappa.esisteNodo(n2) ) mappa.insNodo(n2);
		mappa.insArco(n1,n2);
	}//confinanti
	public void risolvi(){ 
		if( mappa.numNodi()==0 ) return;
		System.out.println("Topologia");
		System.out.println(mappa);
		coloraNazione( mappa.iterator().next() );
	}//risolvi
	private boolean ultimaNazione( int nazione ){
		Iterator<Integer> it=mappa.iterator();
		while( it.hasNext() ){
			int n=it.next();
			if( n==nazione ) break;
		}
		return !it.hasNext();
	}//ultimaNazione
	private int prossimaNazione( int nazione ){
		Iterator<Integer> it=mappa.iterator();
		while( it.hasNext() ){
			int n=it.next();
			if( n==nazione ) break;
		}
		return it.next();
	}//prossimaNazione
	private void coloraNazione( int nazione ){
		Colore col[]=Colore.values();
		for( int i=0; i<col.length; ++i ){
			if( assegnabile( col[i], nazione ) ){
				assegna( col[i], nazione );
				if( ultimaNazione(nazione) ) scriviSoluzione();
				else coloraNazione( prossimaNazione(nazione) );
				deassegna( col[i], nazione );
			}
		}
	}//coloraNazione
	private boolean assegnabile( Colore c, int naz ){
		Iterator<? extends Arco<Integer>> confinanti=mappa.adiacenti( naz );
		while( confinanti.hasNext() ){
			Arco<Integer> a=(Arco<Integer>)confinanti.next();
			if( stessoColore.get(c).contains( a.getDestinazione() ) ) return false;
		}
		return true;
	}//assegnabile
	private void assegna( Colore c, int naz ){
		stessoColore.get(c).add( naz );
	}//assegna
	private void deassegna( Colore c, int naz ){
		stessoColore.get(c).remove( naz );
	}//deassegna
	private void scriviSoluzione(){
		numSol++;
		System.out.print( numSol+": " );
		Iterator<Integer> it=mappa.iterator();
		Colore col[]=Colore.values();
		while( it.hasNext() ){
			int naz=it.next();
			for( int i=0; i<col.length; ++i )
				if( stessoColore.get( col[i] ).contains(naz) ){
					System.out.print("<"+naz+","+col[i]+">"); break;
				}
		}
		System.out.println();
	}//scriviSoluzione
}//ColoraMappa
