package poo.eratostene;
import java.util.*;   

public class CrivelloSet extends CrivelloAstratto{   
	private Set<Integer> crivello=new TreeSet<Integer>();
	private Set<Integer> primi=new TreeSet<Integer>();
	private final int N;
	public CrivelloSet( int N ){
		if( N<2 ) 
			throw new RuntimeException("N minore di 2");
		this.N=N;
		for( int i=2; i<=N; i++ ) 
			crivello.add( i );
	}
	public int size(){ return primi.size();}
	public void filtra(){
		while( !crivello.isEmpty() ){
			//1 fase: estrazione del minimo
			int minimo=crivello.iterator().next(); //certamente primo
			primi.add( minimo );
			//2 fase: eliminazione del minimo e dei suoi multipli
			int multiplo=minimo;
			while( multiplo<=N ){
				crivello.remove( multiplo );
				multiplo+=minimo;
			}
		}
	}//filtra
	public Iterator<Integer> iterator(){
		return primi.iterator();
	}//iterator
	
	public static void main( String []args ){
		Crivello cE=new CrivelloSet(1000);	
		cE.filtra();
		System.out.println(cE);
	}//main
}//CrivelloSet
