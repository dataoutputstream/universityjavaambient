package poo.math;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

public class MatriceSparsaLinked extends MatriceSparsaAstratta{
	
	private Map<Integer,LinkedList<Elemento>> righe=new TreeMap<>();
	private Map<Integer,LinkedList<Elemento>> colonne=new TreeMap<>();
	
	private int n;
	
	public MatriceSparsaLinked( int n ) {
		if( n<=0 ) throw new IllegalArgumentException();
		this.n=n;
		for( int i=0; i<n; i++ ) righe.put(i, new LinkedList<Elemento>() );
		for( int j=0; j<n; j++ ) colonne.put(j, new LinkedList<Elemento>() );
	}
	
	public int getN() { return n; }
	
	public MatriceSparsaLinked crea() {
		return new MatriceSparsaLinked(n);
	}

	public void set( int i, int j, int v ) {
		if( i<0||i>=n||j<0||j>=n ) throw new IndexOutOfBoundsException();
		if( v==0 ) return;
		ListIterator<Elemento> lir=righe.get(i).listIterator();
		boolean flag=false;
		Elemento x=new Elemento(i,j,v);
		while( lir.hasNext() && !flag ) {
			Elemento e=lir.next();
			if( e.compareTo(x)>0 ) {
				lir.previous(); 
				lir.add(x); 
				flag=true;
			}
			else if( e.equals(x) ) {
				e.setV( v );
				flag=true; 
			}
		}
		if( !flag ) lir.add(x);
		
		ListIterator<Elemento> lic=colonne.get(j).listIterator();
		flag=false;
		while( lic.hasNext() && !flag ) {
			Elemento e=lic.next();
			if( e.getI()>i ) {
				lic.previous(); 
				lic.add(x); 
				flag=true;
			}
			else if( e.getI()==i ) {
				e.setV( v );
				flag=true; 
			}
		}
		if( !flag ) lic.add(x);		
	}
	
	public Iterable<Elemento> riga( int i ){
		if( i<0||i>=n ) throw new IndexOutOfBoundsException();
		return new RigaIterable(i);
	}
	
	public Iterable<Elemento> colonna( int j ){
		if( j<0||j>=n ) throw new IndexOutOfBoundsException();
		return new ColonnaIterable(j);
	}	
	
	private class RigaIterable implements Iterable<Elemento>{
		private int i;
		public RigaIterable( int i ) {
			this.i=i;
		}
		public Iterator<Elemento> iterator(){
			return new RigaIterator(i);
		}
		private class RigaIterator implements Iterator<Elemento>{
			private Iterator<Elemento> it;
			private Elemento cor;
			public RigaIterator( int i ) {
				it=righe.get(i).iterator();
			}
			public boolean hasNext() {
				return it.hasNext();
			}
			public Elemento next() {
				cor=it.next();
				return cor;
			}
			public void remove() {
				it.remove(); 
				//toglie cor dalla linked list di riga
				//cor va anche tolto dalla linked list di colonna
				Iterator<Elemento> c=colonne.get(cor.getJ()).iterator();
				while( c.hasNext() ) {
					if( c.next().equals(cor) ) { c.remove(); break; }
				}
			}
		}//RigaIterator
	}//RigaIterable
		
	private class ColonnaIterable implements Iterable<Elemento>{
		private int j;
		public ColonnaIterable( int j ) {
			this.j=j;
		}
		public Iterator<Elemento> iterator(){
			return new ColonnaIterator(j);
		}
		private class ColonnaIterator implements Iterator<Elemento>{
			private Iterator<Elemento> it;
			private Elemento cor;
			public ColonnaIterator( int j ) {
				it=colonne.get(j).iterator();
			}
			public boolean hasNext() {
				return it.hasNext();
			}
			public Elemento next() {
				cor=it.next();
				return cor;
			}
			public void remove() {
				it.remove(); //toglie cor dalla linked list di colonna
				//cor va anche tolto dalla linked list di riga
				Iterator<Elemento> r=righe.get( cor.getI() ).iterator();
				while( r.hasNext() ) {
					if( r.next().equals(cor) ) { r.remove(); break; }
				}
			}
		}//ColonnaIterator
	}//ColonnaIterable		
	
	public static void main( String[] args ) {
		MatriceSparsa a1=new MatriceSparsaLinked(5);
		MatriceSparsa a2=new MatriceSparsaLinked(5);
		a1.set(1,1,1); a1.set(1,4,2); a1.set(3,0,3); a1.set(4,3,4);	
		System.out.println(a1);		
		a2.set(1,1,-1); a2.set(1,3,5); a2.set(2,0,2); a2.set(4,3,-4);
		System.out.println();
		System.out.println(a2);
		System.out.println(a1.equals(a2));
		MatriceSparsa s=a1.add(a2);
		System.out.println("Matrice somma");
		System.out.println(s);
		MatriceSparsa p=a1.mul(a2);
		System.out.println("Matrice prodotto");
		System.out.println(p);
		//test iteratori
		Iterator<Elemento> it=a1.colonna(1).iterator();
		while( it.hasNext() ) {
			if( it.next().equals(new Elemento(1,1)) ) {
				it.remove(); break;
			}
		}
		System.out.println("a1 dopo eliminazione di [1,1]");
		System.out.println(a1);
		System.out.println("a1.get(1,1)="+a1.get(1,1));
		a1.clear();
		System.out.println("dopo clear di a1 grado="+a1.grado());
		System.out.println("nuovo contenuto di a1");
		System.out.println(a1);
		System.out.println("end");
	}
}
