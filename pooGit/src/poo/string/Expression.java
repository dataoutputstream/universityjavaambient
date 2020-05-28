package poo.string;

import java.util.*;

public class Expression{
	/*
	 * Valutatore interattivo di espressioni aritmetiche intere.
	 * Gli operatori ammessi sono + - * / e sono assunti equiprioritari.
	 * Per recuperare la priorita' degli operatori moltiplicativi (* e /)
	 * rispetto a quelli additivi (+ e -) si possono introdurre sotto espressioni
	 * tra parentesi tonde. Una parentesi è sempre valutata prima.
	 * Si ignorano i problemi delle espressioni malformate.
	 */
	static StringTokenizer st;
	static int valuta() {
		String tk = st.nextToken();
		int esito;
		if (tk.equals("(")) // sotto espressione nel primo operando 
			esito = valuta();  //richiama ricorsivamente il processo 
		else
			esito = Integer.parseInt(tk);
		while (st.hasMoreTokens()) {
			char op = st.nextToken().charAt(0);
			if (op == ')') return esito;
			tk = st.nextToken();
			int opnd;
			if (tk.equals("(")) // sotto espressione nel secondo operando
				opnd = valuta(); // richiama ricorsivamente il processo
			else
				opnd = Integer.parseInt(tk);
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
					"Utilizzare le tonde () per la precedenza degli operatori.\n" +
					"Digitare '.' (punto) su una riga vuota per uscire."		);
		String linea = null;
		Scanner sc = new Scanner(System.in);
		for (;;) {
			System.out.print("> ");
			linea = sc.nextLine();
			if (linea.equals(".")) break;
			st = new StringTokenizer(linea, "+-*/()", true);
			int ris = valuta();
			System.out.println(linea + " = " + ris);
		}
		System.out.println("Bye.");
	}
} // Expression
