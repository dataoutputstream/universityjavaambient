package poo.eratostene;
import java.util.*;

public class CrivelloBitSet extends CrivelloAstratto{
	private BitSet crivello;
	private final int N;
	public CrivelloBitSet( int N ){
		if( N<2 ) 
			throw new RuntimeException("N minore di 2");
		this.N=N;
		crivello=new BitSet( N+1 );
		for( int i=2; i<=N; i++ ) 
			crivello.set( i );
	}
	public void filtra(){
		int i=2, limite=(int)Math.round(Math.sqrt(N));
		while( i<=limite ){
			//1 fase: "estrazione" del minimo
			if( crivello.get(i) ){
				//2 fase: eliminazione dei multipli del minimo
				int multiplo=i+i;
				while( multiplo<=N ){
					crivello.clear( multiplo );
					multiplo+=i;
				}
			}
			i=(i==2)?i+1:i+2;
		}
	}//filtra
	public Iterator<Integer> iterator(){
		return new Iteratore();
	}//iterator
	
	private class Iteratore implements Iterator<Integer>{
		private int cor=1;
		private int next( int i ){
			while( i<crivello.length() && !crivello.get(i) ) i++;
			if( i<crivello.length() ) return i;
			return -1;
		}
		public boolean hasNext(){
			return next(cor+1)!=-1;
		}//hasNext
		public Integer next(){
			if( !hasNext() ) throw new NoSuchElementException();
			cor=next(cor+1);
			return cor;
		}//next
		public void remove(){
			throw new UnsupportedOperationException();
		}//remove
	}//Iteratore
	
	public static void main( String []args ){
		Crivello cE=new CrivelloBitSet(1000);	
		cE.filtra();
		System.out.println(cE);
	}//main
}//CrivelloBitSet
