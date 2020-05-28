package poo.polinomi;

import java.util.*;
import javax.swing.*;

public class InputPolinomio {
	public static void main(String[]args) {
		PolinomioLL p = new PolinomioLL();
		char segno; int coeff = 0, grado = 0; boolean first = true;
		for (;;) {
			String pol = JOptionPane.showInputDialog("Immettere un polinomio nel formato 3x^2 - 2x + 3");
			try {
				StringTokenizer st = new StringTokenizer(pol, "+-", true);
				while (st.hasMoreTokens()) {
					String tk = st.nextToken().trim();
					if (first) {
						first = false;
						if (tk.charAt(0) == '-') {
							segno = '-'; tk = st.nextToken();
						} else segno = '+';
					} else if (tk.charAt(0) != '-' && tk.charAt(0) != '+')
						throw new RuntimeException();
					else {
						segno = tk.charAt(0);
						tk = st.nextToken();
					}
					if (tk.indexOf('x') < 0) { // Termine noto
						coeff = Integer.parseInt(tk.trim()) * (segno == '+' ? 1 : -1); grado = 0;
					} else {
						coeff = Integer.parseInt(tk.substring(0, tk.indexOf('x')).trim()) * (segno == '+' ? 1 : -1);
						if (tk.indexOf('^') < 0) // Termine di grado 1 implicito
							grado = 1;
						else
							grado = Integer.parseInt(tk.substring(tk.indexOf('^') + 1).trim());
					}
					p.add(new Monomio(coeff, grado));
				}
				JOptionPane.showMessageDialog(null, "Hai inserito il polinomio: " + p +
									"\nLa sua derivata è " + p.derivata());
				break;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Inserire un polinomio!");
			} catch (RuntimeException e) {
				JOptionPane.showMessageDialog(null, "Formato non valido!");
			}
		}
		JOptionPane.showMessageDialog(null, "Bye!");
	} // main
} // InputPolinomio
