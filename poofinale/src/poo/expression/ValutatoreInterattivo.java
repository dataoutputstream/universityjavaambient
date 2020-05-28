package poo.expression;
import java.util.Scanner;
public class ValutatoreInterattivo {
	public static void main( String... args ){
		System.out.println("Valutatore interattivo di espressioni."+
					       " Inserire . per terminare");
		System.out.println("Operandi interi senza segno. Operatori +-*/()");
		String linea=null;
		Scanner sc=new Scanner( System.in );
		for(;;){
			System.out.print("> ");
			linea=sc.nextLine();
			if( linea.equals(".") ) break;
			try{
				int ris=new Espressione( linea ).valuta();
				System.out.println(linea+"="+ris);
			}catch( Exception e ){
				e.printStackTrace();
				System.out.println("Espressione malformata!");
			}

		}
		sc.close();
		System.out.println("Bye.");
	}
}//ValutatoreInterattivo
