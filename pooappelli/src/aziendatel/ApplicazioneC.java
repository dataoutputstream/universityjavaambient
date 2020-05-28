package aziendatel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class ApplicazioneC {
	
	static Azienda a=new AziendaC();
	
	public static void main(String...Args) {
		try {
			gestione();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		elenco();
		
		
		
	}

	private static void elenco() {
		Iterator<Stanza>it=a.uffici();
		Set<Stanza> set=new TreeSet<>();
		while(it.hasNext())set.add(it.next());
		for(Stanza s:set) {
			Iterator<Persona>it2=s.impiegati();
			ArrayList <Persona>set2= new ArrayList<Persona>();
			
			while(it2.hasNext()) {
				
				set2.add(it2.next());
			}
			Collections.sort(set2);
			System.out.println("Nella Stanza con numero: "+s.toString()+" ci sono/c'é:");
			for(Persona p:set2) {
				System.out.println("L'impiegato :"+p.toString());
			}
			set2.clear();
		}
		
	}

	private static void gestione() throws FileNotFoundException{
		
		Scanner sc=new Scanner(System.in);
		File f;
		String nomeFile = null;
		do {
			System.out.println("Dammi il file: ");
			nomeFile=sc.nextLine();
		}while(nomeFile==null);
		f=new File(nomeFile);
		BufferedReader br=new BufferedReader(new FileReader(f));
		String linea="";
		try {
			linea=br.readLine();
			while(linea!=null) {
				StringTokenizer st=new StringTokenizer(linea," ");
				String cognome = null,nome = null,numero = null;
				if(st.countTokens()==3) {
					cognome=st.nextToken();
					nome=st.nextToken();
					numero=st.nextToken();
					
				}
				Persona p=new Persona(cognome,nome);
				int num=Integer.parseInt(numero);
				a.aggiungiImpiegato(p, num);
				linea=br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
