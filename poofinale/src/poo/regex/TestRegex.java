package poo.regex;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class TestRegex {
	public static void main( String []args ) throws IOException{
		Scanner sc=new Scanner(System.in);
		String nomefile=null;
		boolean okLettura=false;
		do{
			System.out.print("Fornisci il nome di un file testo: ");
			nomefile=sc.nextLine();
			File f=new File(nomefile);
			okLettura=f.exists();
			if( !okLettura ) System.out.println("File inesistente!");
		}while(!okLettura);
		Pattern pattern=Pattern.compile("java", Pattern.CASE_INSENSITIVE);
		String documento="";
		BufferedReader br=
			new BufferedReader( new FileReader(nomefile) );
		for(;;){
			String linea=br.readLine();
			if( linea==null ) break;
			documento+=linea+"\n";
		}
		br.close();
		Matcher matcher=pattern.matcher(documento);
		int cont=0;
		while( matcher.find() ) {
			cont++;
			System.out.println("Trovato match ***"+
				documento.substring( matcher.start(), matcher.end())+"***"+
				" in posizione "+matcher.start() );
		}
		System.out.println("Numero di match: "+cont );
		System.out.println("Documento dopo replaceAll di Java con JAVA");
		documento=matcher.replaceAll("JAVA");
		System.out.println(documento);
	}//main
}//TestRegex
