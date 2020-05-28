//file CrossIndex.java

package poo.indice;
import java.io.*;
import java.util.*;
import poo.testo.*;

public class CrossIndex{
	public static void main( String []args ) throws IOException{
		System.out.println("Indice dei riferimenti incrociati");
		Scanner s=new Scanner( System.in );
		String nomeFile=null;
		File f=null;
		do{
			System.out.print("Nome file testo = ");
		    nomeFile = s.nextLine();
		    f = new File(nomeFile);
		    if( !f.exists() ) System.out.println("File inesistente. Ridarlo!");
		}while( !f.exists() );

		GestoreTesto gt = new GestoreTesto( f );
		Indice indice = new IndiceMappato();
		String word = null;
		int numLinea = 0;
		GestoreTesto.Simbolo simbolo = null;

		for(;;){
			simbolo = gt.prossimoSimbolo();
			if( simbolo==GestoreTesto.Simbolo.EOF ) break;
			word = gt.getString().toUpperCase();
			numLinea = gt.getNumeroLinea();
			indice.add( word, numLinea );
		}

		System.out.println();
		System.out.println("Contenuto dell'indice");
		System.out.println( indice );
	}//main
}//CrossIndex
