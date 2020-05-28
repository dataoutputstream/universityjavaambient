package poo.esempi;

import java.util.*;
import poo.util.*;

public class BitDigits {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Selezionare tipo di numero da visualizzare in binario: ");
		System.out.println("1 - Intero 32 bit in complemento a due");
		System.out.println("2 - Float 32 bit standard IEEE-754");
		try {
			char scelta = sc.nextLine().charAt(0);
			switch (scelta) {
				case '1':
					System.out.println("Inserisci numero intero compreso tra " + Integer.MIN_VALUE + " e " + Integer.MAX_VALUE);
					int[] bits = Mat.int2bit(Integer.parseInt(sc.nextLine()));
					for (int b: bits) System.out.print(b);
					//System.out.println(Integer.toBinaryString(Integer.parseInt(sc.nextLine())));
					break;
				case '2':
					System.out.println("Inserisci numero con la virgola compreso tra " + Float.MIN_VALUE + " e " + Float.MAX_VALUE);
					System.out.println(Integer.toBinaryString(Float.floatToIntBits(Float.parseFloat(sc.nextLine()))));
					break;
				default:
					throw new RuntimeException();
			}
		} catch (RuntimeException e) {
			System.out.println("Errore formato input!");
			System.exit(-1);
		}
		System.out.println("Bye!");
	} // main
} // BitDigits
