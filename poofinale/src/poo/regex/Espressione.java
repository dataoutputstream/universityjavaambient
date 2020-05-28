package poo.regex;
import java.util.*;

public class Espressione {
	public static void main(String []args){
		System.out.println("Fornisci, uno per linea, un numero o un identificatore e termina con STOP");
		Scanner sc=new Scanner(System.in);
		String INTERO="\\-?[0-9]+";
		String ESPRESSIONE=INTERO+"([\\+\\-\\*/]"+INTERO+")*";
		String s=null;
		for(;;){
			System.out.print(">>");
			s=sc.nextLine();
			if( s.equalsIgnoreCase("STOP") ) break;
			if( s.matches(ESPRESSIONE) ){
				System.out.println("expr>>"+s);
			}
			else System.out.println("Nessuna espressione!");
		}
		System.out.println("Bye!");
	}
}//Espressione
