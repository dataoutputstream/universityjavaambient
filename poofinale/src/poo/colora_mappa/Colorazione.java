package poo.colora_mappa;
import java.util.*;
public class Colorazione {
	public static void main( String []args ){
		System.out.println("Problema Colorazione Mappa di Nazioni");
		Scanner sc=new Scanner( System.in );
		System.out.print("Numero nazioni=");
		int n=sc.nextInt(); sc.nextLine();
		assert n>0 : n+" deve essere >0";
		ColoraMappa cm=new ColoraMappa();
		for( int naz=0; naz<n; ++naz ){
			System.out.print("Nazioni confinanti con "+naz+": ");
			String linea=sc.nextLine();
			StringTokenizer st=new StringTokenizer(linea," ");
			while( st.hasMoreTokens() ){
				int nc=Integer.parseInt( st.nextToken() );
				assert nc>=0 && nc<n && nc!=naz: nc+" confinanza errata";
				cm.confinanti( naz, nc );
			}
		}
		cm.risolvi();
		sc.close();
	}
}//Colorazione
