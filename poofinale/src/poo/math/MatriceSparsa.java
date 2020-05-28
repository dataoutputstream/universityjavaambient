package poo.math;

import java.util.Iterator;

public interface MatriceSparsa{
	
	int getN();
	
	default void clear() {
		//per righe o per colonne
		for( int i=0; i<getN(); i++ ) {
			Iterator<Elemento> it=this.riga(i).iterator();
			while( it.hasNext() ) {
				it.next(); it.remove();
			}
		}
	}//clear
	
	default double grado() {
		int nz=0;
		for( int i=0; i<getN(); i++ ) {
			Iterator<Elemento> it=this.riga(i).iterator();
			while( it.hasNext() ) {
				it.next(); nz++;
			}
		}		
		return ((double)nz)/(getN()*getN());
	}//grado
	
	default int get( int i, int j ) {
		if( i<0||i>=getN() ) throw new IndexOutOfBoundsException();
		if( rigaVuota(i) ) return 0;
		for( Elemento e: riga(i) )
			if( e.getI()==i && e.getJ()==j ) return e.getV();
		return 0;
	}//get
	
	default int get( Elemento e ) {
		return get( e.getI(), e.getJ() );
	}//get
	
	void set( int i, int j, int v );
	
	default void set( Elemento e ) {
		set( e.getI(), e.getJ(), e.getV() );
	}//set
	
	default boolean rigaVuota(int i) {
		if(i<0||i>=getN() ) throw new IndexOutOfBoundsException();
		return !riga(i).iterator().hasNext();
	}//rigaVuota
	
	default boolean colonnaVuota(int j) {
		if(j<0||j>=getN() ) throw new IndexOutOfBoundsException();
		return !colonna(j).iterator().hasNext();
	}//colonnaVuota
	
	default int sizeRiga(int i) {
		if( i<0||i>=getN() ) throw new IndexOutOfBoundsException();
		int c=0;
		for( Elemento e: this.riga(i) ) c++;
		return c;
	}//sizeRiga
	
	default int sizeColonna(int j) {
		if( j<0||j>=getN() ) throw new IndexOutOfBoundsException();
		int c=0;
		for( Elemento e: this.colonna(j) ) c++;
		return c;
	}//sizeColonna
	
	Iterable<Elemento> riga(int i);
	Iterable<Elemento> colonna(int j);
	MatriceSparsa crea(); //factory
	
	default MatriceSparsa add( MatriceSparsa m ) {
		MatriceSparsa somma=crea();
		for( int i=0; i<getN(); i++ ) {
			for( Elemento e1: this.riga(i) ) {
				Elemento x=null;
				for( Elemento e2: m.riga(i) ) {
					if( e1.equals(e2) ) { x=e2; break; }
					if( e1.compareTo(e2)<0 ) break;
				}
				if( x!=null ) 
					somma.set( e1.getI(), e1.getJ(), e1.getV()+x.getV() );
				else 
					somma.set( e1.getI(), e1.getJ(), e1.getV() );
			}
			for( Elemento e2: m.riga(i) ) {
				boolean flag=false;
				for( Elemento e1: this.riga(i) ) {
					if( e2.equals(e1) ) { flag=true; break; }
					if( e1.compareTo(e2)>0 ) break;
				}
				if( !flag ) somma.set( e2.getI(),e2.getJ(),e2.getV() );
			}
		}
		return somma;
	}//add
	
	default MatriceSparsa mul( MatriceSparsa m ) {
		MatriceSparsa p=crea();
		for( int i=0; i<getN(); i++ ) {
			//se una riga è vuota il prodotto sarà 0 inutile per 
			//ADT matrice sparse andare a calcolare il prodotto 
			//visto che in una matrice sparsa l'elemento 0 non è presente.
			if( this.rigaVuota(i) ) continue;
			
			//per definizione di prodotto fra matrici una riga viene 
			//moltiplicato per getN() volte, con le colonna poichè le
			//colonne sono proprio getN() da ipotesi "Quadrate"
			
			//[1,2] [1][2] LA RIGA VIENE MOLTIPLICATA PER 2 VOLTE 
			//[2,3] [2][2] PRIMA DEL PASSAGGIO ALLA SUCCESSIVA.
			
			for( int j=0; j<getN(); j++ ) {
				if( m.colonnaVuota(j) ) continue;		
				int ps=0;
				for( Elemento e1: this.riga(i) ) {
					for( Elemento e2: m.colonna(j) ) {
						//e1 su riga varia la J
						//e2 su colonna varia la I
						if( e1.getJ()==e2.getI() ) { 
							//se ho trovato un elemento e1 ed un elemento e2 
							//aggiungo il loro prodotto a ps
							ps=ps+e1.getV()*e2.getV(); break; 
						}
						//é inutile cercare se j<i sicuramente non esiste un'elemento 
						//sulla stessa posizione vista per colonna
						if( e1.getJ()<e2.getI() ) break;
					}
				}
				//alla fine dell'elaborazione di una riga e di tutte le colonne verra settato
				p.set(i,j,ps);
			}
		}
		return p;
	}//mul
	
	default boolean simmetrica() {
		boolean answer=true;
		for( int i=0; i<getN(); i++ ) {
			Iterator<Elemento> it=this.riga(i).iterator();
			while( it.hasNext() ) {
				Elemento e=it.next();
				int r=e.getI(), c=e.getJ();
				if( c<i && e.getV()!=this.get(c,r) ) { 
					answer=false; break; 
				}
			}
		}
		return answer;
	}//simmetrica
	
}//MatriceSparsa

