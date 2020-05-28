package poo.file;
import java.io.*;
import java.util.*;
/**
 * 
 * Crea un file di interi a partire da una successione
 * letta da tastiera, e terminante col primo 0.
 *
 */
public class Crea{
	public static void main( String []args )throws IOException {
		Scanner s=new Scanner( System.in );
		System.out.print("nome file da creare=");
		String nome=s.nextLine();
		PrintWriter dos=
			new PrintWriter( new FileWriter(nome) );
		System.out.println("Fornisci una serie di interi uno per linea. Solo INVIO termina");
		for(;;){
			System.out.print("int>");
			String input=s.nextLine();
			if( input.length()==0 ) break;
			dos.write( Integer.parseInt(input) );
		}
		dos.close();
		BufferedReader dis=
			new BufferedReader( new FileReader(nome) );
		System.out.println();
		System.out.println("Contenuto del file");
		int x=0;
		for(;;){
			try{
				x=dis.read();
			}catch(Exception e ){ break; }
			System.out.println( x );
		}//for
		dis.close();
	}//main
}//Crea