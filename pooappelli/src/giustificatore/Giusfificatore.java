package giustificatore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Giusfificatore {
	
	
	
	
	
	
	public static void main(String...Args) throws IOException {
		File f = null;
		String nomeFile=null;
		Scanner sc=new Scanner(System.in);
		List<String>lista = new ArrayList<String>();
		int lunghezzaMax=0;
		
		do {
			System.out.println("Dammi il percorso del file");
			nomeFile=sc.nextLine();
			f=new File(nomeFile);
			
		}while(!f.exists());
		
		
		
		BufferedReader br=new BufferedReader(new FileReader(nomeFile));
		
		for(;;) {
			String linea=br.readLine();
			if(linea==null)break;
			if(lunghezzaMax<linea.length())lunghezzaMax=linea.length();
			lista.add(linea);
			
		}
		
		lista=applicazione(lista,lunghezzaMax);
		System.out.println(lista);
		
		PrintWriter pw=new PrintWriter(new FileWriter(nomeFile));
		for(String s:lista) {
			pw.println(s);
		}
		
		
		
	}
//C://giustificato.txt
	 private static List<String> applicazione(List<String> lista, int lunghezzaMax) {
		 List<String>ret=new ArrayList<String>();
		 for(String linea :lista) {
			 if(linea.length()==lunghezzaMax || linea.charAt(linea.length()-1)=='.') {ret.add(linea);continue;}
			 StringBuilder sb=new StringBuilder();
			 int nSpazi=0;
			 StringTokenizer st=new StringTokenizer(linea ," ",true);
			 
			 int spaziOccupati=0;
			 while(st.hasMoreTokens()) {
				 String Token=st.nextToken();
				 if(Token.charAt(0)==' ')nSpazi++;
				 spaziOccupati+=Token.length();
			 }
			 int distanza =(lunghezzaMax-spaziOccupati)/nSpazi;
			 int resto=(lunghezzaMax-spaziOccupati)%nSpazi;
			 char[]c=linea.toCharArray();
			ArrayList<Character>linked=new ArrayList<>();
			for(int i=0;i<c.length;i++) {
			linked.add(c[i]);
			}
			List<Integer>indici=new ArrayList<>();
			int indice=-1;;
			for(Character ch:linked) {
				indice++;
				if(ch==' ') {
				indici.add(indice);
				}	
			}
			boolean check = false;
			int aggiunto = 0;
			for(Integer ind :indici) {
				if(!check) {
				indice=ind;
				}else {
					aggiunto++;
					indice=ind+distanza*aggiunto;
				}
				for(int i=0;i<distanza;i++) {
					linked.listIterator(indice).add(' ');
					check=true;
					
				}
			}
				for(Integer i1:indici) {
					if(resto!=0) {
					linked.listIterator(i1).add(' ');
					resto--;
					}else break;
				}
			
			for(Character c1:linked) {
				sb.append(c1);
			}
			
			ret.add(sb.toString());
			 
			 
		 }
		
		 
		 
		 System.out.println(ret);
		 return ret;
		
		
	}
	
	

}
