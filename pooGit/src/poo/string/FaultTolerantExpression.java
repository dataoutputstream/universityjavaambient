package poo.string;

import java.util.*;

public class FaultTolerantExpression {
	/*
	 * Valutatore interattivo di espressioni aritmetiche intere.
	 * Gli operatori ammessi sono + - * / e sono assunti equiprioritari.
	 * Per recuperare la priorita' degli operatori moltiplicativi (* e /)
	 * rispetto a quelli additivi (+ e -) si possono introdurre sotto espressioni
	 * tra parentesi tonde. Una parentesi e' sempre valutata prima.
	 * Espressioni malformate vengono identificate e segnalate.
	 */
	static StringTokenizer st;
	static int pA = 0; // contatore parentesi aperte non ancora accoppiate
	static int valuta() {
		String tk = st.nextToken();
		int esito, opnd; char op;
		if (tk.equals(")")) // il primo operando non puo' iniziare con )
			throw new BadSyntaxException();
		if (tk.equals("(")) {
			pA++;
			esito = valuta();
		} else
			esito = Integer.parseInt(tk);
		while (st.hasMoreTokens()) {
			op = st.nextToken().charAt(0);
			if (op == ')') {
				if (pA == 0) throw new BadSyntaxException();
				pA--; return esito;
			}
			tk = st.nextToken();
			if (tk.equals("(")){
				pA++;
				opnd = valuta();
			} else
				opnd = Integer.parseInt(tk);
			switch (op) {
				case '+': esito += opnd; break;
				case '-': esito -= opnd; break;
				case '*': esito *= opnd; break;
				default : esito /= opnd;
			}
		}
		if (pA > 0) throw new BadSyntaxException();
		return esito;
	} // valuta
	public static void main(String[]args) {
		System.out.println(	"Valutatore interattivo di espressioni aritmetiche intere.\n" +
					"Supporta gli operatori '+', '-', '*' e '/'. \n" +
					"Utilizzare le tonde () per la precedenza degli operatori.\n" +
					"Digitare '.' (punto) su una riga vuota per uscire."		);
		String linea = null;
		Scanner sc = new Scanner(System.in);
		for (;;) {
			System.out.print("> ");
			linea = sc.nextLine();
			if (linea.equals(".")) break;
			st = new StringTokenizer(linea, "+-*/()", true);
			try {
				System.out.println(linea + " = " + valuta());
			} catch (NumberFormatException e) {
				System.out.println("L'espressione inserita contiene caratteri non validi!");
			} catch (NoSuchElementException e) {
				System.out.println("Operando mancante!");
			} catch (BadSyntaxException e) {
				System.out.println("Sintassi parentesi errata!");
			} finally {
				pA = 0;
			}
		}
		System.out.println("Bye.");
	}
} // Expression
