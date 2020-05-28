package poo.string;

import java.util.*;

public class FaultTolerantSimpleExpression {
	/*
	 * Valutatore interattivo di espressioni aritmetiche intere.
	 * Gli operatori ammessi sono + - * / e sono assunti equiprioritari.
	 * Espressioni malformate vengono segnalate.
	 */
	static StringTokenizer st;
	static int valuta() {
		int esito = Integer.parseInt(st.nextToken());
		while (st.hasMoreTokens()) {
			char op = st.nextToken().charAt(0);
			int opnd = Integer.parseInt(st.nextToken());
			switch (op) {
				case '+': esito += opnd; break;
				case '-': esito -= opnd; break;
				case '*': esito *= opnd; break;
				default : esito /= opnd;
			}
		}
		return esito;
	} // valuta
	public static void main(String[]args) {
		System.out.println(	"Valutatore interattivo di espressioni aritmetiche intere.\n" +
					"Supporta gli operatori '+', '-', '*' e '/'.\n" +
					"Digitare '.' (punto) su una riga vuota per uscire."		);
		String linea = null;
		Scanner sc = new Scanner(System.in);
		for(;;) {
			System.out.print("> ");
			linea = sc.nextLine();
			if (linea.equals(".")) break;
			st = new StringTokenizer(linea, "+-*/", true);
			int ris;
			try {
				ris = valuta();
				System.out.println(linea + " = " + ris);
			} catch (RuntimeException e) {
				System.out.println("Espressione malformata!");
			}
		}
		System.out.println("Bye.");
	}
} // Expression
