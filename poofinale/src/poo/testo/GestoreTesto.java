package poo.testo;
import java.io.*;
import java.util.*;

public class GestoreTesto {
	public enum Simbolo{ WORD, EOF }
	private boolean EOF=false;
	private String linea=null;
	private Scanner input, scan;
	private int numeroLineaCorrente=0;
	private String word;
	
	public GestoreTesto( File f ) throws IOException{
		input=new Scanner( f );
	}//costruttore
	
	private void avanza(){
		try{
			if( linea==null || !scan.hasNext() ){
				linea=input.nextLine();
				numeroLineaCorrente++;	
				//echo su output della linea
				System.out.println(numeroLineaCorrente+": "+linea); 
				scan=new Scanner( linea );
				scan.useDelimiter("[^a-zA-Z_]+");
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
	public static void main(String[]args) throws IOException {
		File f=null;
		do {
			Scanner sc=new Scanner(System.in);
			f=new File(sc.nextLine());
			
		}while(f.exists());
		PrintWriter p=new PrintWriter(new FileWriter(f));
		for(int i=0;i<9;i++) {p.append(Integer.toString(i).charAt(0));p.println();}
		p.flush();
		GestoreTesto g=new GestoreTesto(f);
		try {
		while(g.prossimoSimbolo()!=Simbolo.EOF) {
		g.avanza();
		String s=g.getString();
		System.out.println(s);
		
		}
		}catch(Exception e) {
			
		}
		
		
		BufferedReader br=new BufferedReader(new FileReader(f));
		for(;;) {
			String s2=br.readLine();
			if(s2==null)break;
			System.out.println(s2);
		}
		
		
		
		}
	}
//GestoreTesto
