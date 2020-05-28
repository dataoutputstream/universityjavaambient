package poo.esempi;

import java.util.*;
import poo.util.*;

public class TestCoda {
	static Scanner sc = null;
	static Coda<String> coda = null;
	static String LINEA_COMANDO = "([Aa][\\s]+[a-zA-Z0-9]+|[PpQq])";
	public static void main(String[]args) {
		System.out.println("TestCoda");
		sc = new Scanner(System.in);
		coda = new CodaConcatenata<String>();
		boolean uscita = false;
		comandi();
		do { uscita = input(); } while (!uscita);
		System.out.println("Bye!");
	} // main
	static void comandi() {
		System.out.println("Comandi accettati:\n" +
					"A string (Arrivo di 'string' in coda)\n" +
					"P (Partenza dalla testa)\n" +
					"Q (Chiusura programma)");
	} // comandi
	static boolean input() {
		System.out.print(">> ");
		String linea = sc.nextLine();
		if (!linea.matches(LINEA_COMANDO)) {
			System.out.println("Comando sconosciuto!");
			comandi(); return false;
		}
		char c = Character.toLowerCase(linea.charAt(0)); String s = null;
		switch(c) {
			case 'a':
				s = linea.substring(linea.lastIndexOf(' ') + 1);
				try {
					coda.put(s); System.out.println("*" + s + "* entra in coda");
					System.out.println("Situazione attuale: " + coda);
				} catch (RuntimeException e) {
					System.out.println("Coda piena!");
				}
				break;
			case 'p':
				try {
					s = coda.get();
					System.out.println("*" + s + "* esce dalla coda");
					System.out.println("Situazione attuale: " + coda);
				} catch (RuntimeException e) {
					System.out.println("Coda vuota!");
				}
				break;
			case 'q':
				System.out.println("Situazione coda residua: " + coda);
				return true;
		}
		return false;
	} // input
} // TestCoda
