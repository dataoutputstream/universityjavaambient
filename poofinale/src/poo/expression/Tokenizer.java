package poo.expression;
import java.util.*;
public class Tokenizer {
	public static void main( String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.print("linea> ");
		String linea=sc.nextLine();
		if( linea!=null && linea.length()!=0 ){
			int j=0, i=0;
			do{
				//skip terminatori
				while( j<linea.length() && linea.charAt(j)==' ' ) ++j;
				//individua fine della parola, ossia la posizione del primo terminatore successivo
				i=linea.indexOf(' ',j);
				if( i<0 ) i=linea.length();
				String tk=linea.substring(j,i);
				System.out.println(tk);
				j=i;
			}while( i<linea.length());
		}
		sc.close();
		System.out.println("Bye.");
	}
}
