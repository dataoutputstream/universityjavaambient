package poo.recursion;
import java.util.*;

public class InversioneNumero {
	public static void main( String[] args ){
		Scanner sc=new Scanner(System.in);
		System.out.print("Fornisci un intero positivo: ");
		int n=sc.nextInt();
		if( n<0 ){
			System.out.println("Numero negativo!");
			System.exit(-1);
		}
		scriviInverso(n);
	}
	static void scriviInverso( int n ){
		System.out.print(n%10);
		
		if(n/10!=0) scriviInverso(n/10);
		
	}
}//InversioneNumero
