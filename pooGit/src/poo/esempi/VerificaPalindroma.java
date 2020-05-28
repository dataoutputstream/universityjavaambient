package poo.esempi;

import poo.util.*;

public class VerificaPalindroma {
	public static void main(String[]args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		String FORMATO_INPUT = "[\\w]+\\$[\\w]+";
		System.out.println("Inserire stringa di input (formato: stringa1$stringa2)");
		System.out.print("input> ");
		String input = sc.nextLine();
		if (!input.matches(FORMATO_INPUT)) {
			System.out.println("Input malformato!");
			System.exit(-1);
		}
		Stack<Character> pila = new StackConcatenato<Character>();
		int pos = 0; char c;
		while ((c = input.charAt(pos++)) != '$') pila.push(c);
		boolean palindroma = true;
		while (pos < input.length() && !pila.isEmpty())
			if (pila.pop() != input.charAt(pos++)) {
				palindroma = false; break;
			}
		if (palindroma && pos == input.length() && pila.isEmpty())
			System.out.println(input + " palindromo.");
		else System.out.println(input + " non palindromo.");
		System.out.println("Bye!");
	} // main
} // VerificaPalindroma
