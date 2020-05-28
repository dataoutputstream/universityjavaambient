package poo.statistiche_fpfpq;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



public class FpFpq{
		public static void main( String[] args ) throws IOException{
			Scanner sc=new Scanner(System.in);
			String nomeFile=null;
			File f=null;
			do{
					System.out.print("Nome file testo: ");
					nomeFile=sc.nextLine();
					f=new File( nomeFile );
					if( !f.exists() ) System.out.println("File "+nomeFile+" inesistente! Ridarlo");
				}while( !f.exists() );
			GestoreTesto gt=new GestoreTesto( f, "\\W+");
			StatisticaMap stat=new StatisticaMap();
			GestoreTesto.Simbolo sim=gt.prossimoSimbolo();
			String ppre=null; //parola precedente
			while( sim!=GestoreTesto.Simbolo.EOF ){
				String pcor=gt.getString().toUpperCase(); //parola corrente
				stat.arrivoParola( pcor );
				if( ppre!=null ){
					stat.paroleConsecutive( ppre, pcor );
			}
			ppre=pcor;
			sim=gt.prossimoSimbolo();
			}//while
			System.out.print("Parola target=");
			String target=sc.nextLine().toUpperCase();
			sc.close();
			System.out.println("Statistica d'uso delle parole:");
			System.out.println(stat);
			PrintWriter pw=new PrintWriter( new FileWriter("c:\\poo-file\\statistica.txt"));
			pw.println( stat );
			pw.close();
			System.out.println("Parola che più verosimilmente segue "+target+"="+
					stat.parolaCheSeguePiuFrequente(target));
			System.out.println("Parola che meno verosimilmente segue "+target+"="+
					stat.parolaCheSegueMenoFrequente(target));
		}//main
		}//FpFpq
  






class GestoreTesto {
	public enum Simbolo{ WORD, EOF }
	private boolean EOF=false;
	private String linea=null;
	private Scanner input, scan;
	private int numeroLineaCorrente=0;
	private String word, delim;
	
	public GestoreTesto( File f, String delim ) throws IOException{
	input=new Scanner( f );
 	this.delim=delim;
	}//costruttore
	
	private void avanza(){
		try{
			if( linea==null || !scan.hasNext() ){
				linea=input.nextLine();
				numeroLineaCorrente++;
				//echo su output della linea
				System.out.println(numeroLineaCorrente+": "+linea);
				scan=new Scanner( linea );
				scan.useDelimiter( delim );
			}
		}catch( Exception ioe ){
			EOF=true; input.close();
		}
	}//avanza
	
public Simbolo prossimoSimbolo() throws IOException{
	do{
		avanza();
	}while( !EOF && !scan.hasNext() );
	if( EOF ) return Simbolo.EOF;
	word = scan.next();
	return Simbolo.WORD;
}//prossimoSimbolo

public String getString(){
return word;
}//getString

public int getNumeroLinea(){
return numeroLineaCorrente;
}//numeroLinea
}//GestoreTesto