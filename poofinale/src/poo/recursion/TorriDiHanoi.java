package poo.recursion;
import poo.util.*;

public final class TorriDiHanoi {
	
	private TorriDiHanoi(){}
	
	private enum Pin { SX, CL, DX };
	
	private static void sposta1Disco( Pin da, Pin a ){
		System.out.println("Sposta un disco da "+da+" a "+a);
	}//Sposta1Disco
	
	public static void muovi( int n, Pin sorg, Pin aus, Pin dest ){
		if( n==1 ) sposta1Disco( sorg, dest );
		else{
			muovi( n-1, sorg, dest, aus );
			sposta1Disco( sorg, dest );
			muovi( n-1, aus, sorg, dest );
		}
	}//muovi
	
	public static void muovi_ite( int n, Pin sorg, Pin aus, Pin dest ){
		
		class AreaDati{
			private int n;
			private Pin sorg, aus, dest;
			public AreaDati( int n, Pin sorg, Pin aus, Pin dest ){
				this.n=n; this.sorg=sorg; this.aus=aus; this.dest=dest;
			}
			public int getN(){ return n; }
			public Pin getSorg(){ return sorg; }
			public Pin getAus(){ return aus; }
			public Pin getDest(){ return dest; }
		}//AreaDati
		
		Stack<AreaDati> pila=new StackConcatenato<AreaDati>();
		
		pila.push( new AreaDati(n, sorg, aus, dest) );
		AreaDati ad=null;
		while( !pila.isEmpty() ){
			ad=pila.pop(); //area dati corrente
			if( ad.getN()==1 ) sposta1Disco( ad.getSorg(), ad.getDest() );
			else{//simulazione chiamate ricorsive - in ordine inverso
				pila.push( new AreaDati( ad.getN()-1, ad.getAus(), 
						                 ad.getSorg(), ad.getDest() ) );
				pila.push( new AreaDati( 1, ad.getSorg(), ad.getAus(), 
						                    ad.getDest() ) );
				pila.push( new AreaDati( ad.getN()-1, ad.getSorg(), 
						                 ad.getDest(), ad.getAus() ) );
			}
		}//while
		
	}//muovi_ite
	
	public static void main( String[] args ){
		//TorriDiHanoi.muovi(3, Pin.SX, Pin.CL, Pin.DX ); //ricorsione
		TorriDiHanoi.muovi_ite( 3, Pin.SX, Pin.CL, Pin.DX );
	}//main
}//TorriDiHanoi
