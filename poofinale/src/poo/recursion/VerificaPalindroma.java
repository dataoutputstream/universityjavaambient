package poo.recursion;
import java.util.*;

public class VerificaPalindroma {
	public static void main( String[] args ){
		Scanner sc=new Scanner(System.in);
		System.out.print("Fornisci una stringa: ");
		String s=sc.nextLine();
		System.out.print("La stringa "+s+" ");
		if( palindroma(s) )
			System.out.println("e' palindroma");
		else
			System.out.println("non e' palindroma");
	}
	static boolean palindroma( String s ){
		if( s.length()<2 ) return true;
		if( s.charAt(0)!=s.charAt(s.length()-1) ) return false;
		return palindroma( s.substring(1,s.length()-1) );
	}
}//InversioneNumero
