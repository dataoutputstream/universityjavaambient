
package poo.appellovirtuale;

public class Esercizio{		
	
	static void disponi( int[] b, int i ){
		for( int bit=0; bit<=1; bit++ ){ //possibili scelte, ossia i valori dei bit assegnabili a b[i]
			b[i]=bit; //assegnamento sempre sicuro – non va controllato con assegnabile
			if( i==b.length-1 ){
				for( int j=0; j<b.length; ++j ) System.out.print(b[j]);
				System.out.println();
			}
			else
				disponi( b, i+1 );
			//deassegnamento del valore di b[i] inutile
		}
	}//disponi
	
	public static void main( String[] args ){
		int []b=new int[4];
		disponi( b, 0 );
	}
}//Esercizio
