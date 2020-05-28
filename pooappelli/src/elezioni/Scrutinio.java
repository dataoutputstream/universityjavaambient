package elezioni;

import java.io.*;
import java.util.*;

public class Scrutinio{
	
	static void caricaCandidati( File f,DizionarioCandidati dc ) throws IOException {
	
	BufferedReader br =new BufferedReader( new FileReader(f) );
	String cand;
	for(;;){
		cand=br.readLine();
		if( cand==null ) break;
		cand=cand.toUpperCase();
		dc.add(cand);
	}
	br.close();
	System.out.println("Elenco Candidati");
	System.out.println(dc);
	}//caricaCandidati

	static void caricaElezione( File f,Elezione elez,DizionarioCandidati dc,Situazione sit ) throws IOException {
		BufferedReader br=new BufferedReader( new FileReader(f) );
		String linea;
		StringTokenizer st;
	for(;;){
		linea=br.readLine();
		if( linea==null ) break;
		if( linea.length()==0 ){
			sit.setSchedeBianche( sit.getSchedeBianche()+1 );
			continue;
		}
		st=new StringTokenizer(linea," ");
		boolean schedaValida=true;
		int pref=0;
		Scheda sch=new Scheda();
		while( st.hasMoreTokens() ){
			String cand=st.nextToken().toUpperCase();
			pref++;
			if( !dc.contieneCandidato(cand) ){
				sit.setSchedeNulle( sit.getSchedeNulle()+1 );
				schedaValida=false; break;
			}
			else sch.add(cand);
		}//while
		if( schedaValida && pref!=dc.getNumeroCandidati() ){
			sit.setSchedeNulle( sit.getSchedeNulle()+1 );
			schedaValida=false;
		}
		if( schedaValida ){
			sit.setSchedeValide( sit.getSchedeValide()+1 );
			elez.addScheda(sch);
		}
	}
	br.close();
	System.out.println("Situazione schede:");
	System.out.println(sit);
	System.out.println("Schede da scrutinare");
	System.out.println(elez);
	}//caricaCandidati
	
	public static void main( String []args ) throws IOException {
		File f1=new File("c:\\poo-file\\candidati.txt");
		File f2=new File("c:\\poo-file\\schede.txt");
		if( !f1.exists() || !f2.exists() ){
		throw new RuntimeException("File elezione inesistenti");
		}
		DizionarioCandidati dc=new DizionarioCandidatiMap();
		Situazione sit=new Situazione();
		Elezione elez=new ElezioneLista(dc);
		caricaCandidati( f1, dc );
		caricaElezione( f2, elez, dc, sit );
		for(;;){
			Conteggio c=elez.conta();
			String cand=c.vincitore( sit.maggioranzaAssoluta() );
			if( cand!=null ){
				System.out.println("Il candidato "+cand+" è vincitore con voti: "+c.voti(cand) );
				break;
			}
			if( c.tuttiMinoritari() ){
				System.out.println("Parita'");
				break;
			}
			List<String> minoritari=c.minoritari();
			for( String cm: minoritari ) dc.elimina(cm);
		}//for
	}//main
}//Scrutinio
