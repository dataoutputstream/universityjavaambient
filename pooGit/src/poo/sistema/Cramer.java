package poo.sistema;

import poo.util.*;

public class Cramer extends Sistema {
	private double[][] a; private double[] y;
	public Cramer(double[][] a, double[] y) {
		super(a, y);
		this.a = new double[a.length][a.length];
		this.y = new double[y.length];
		for (int i = 0; i < a.length; i++) {
			System.arraycopy(a[i], 0, this.a[i], 0, a.length);
			this.y[i] = y[i];
		}
	} // Costruttore normale
	public double[] risolvi() {
		double det = Matrix.determinante(a);
		if (Mat.circaUguali(det, 0D)) throw new SistemaSingolare();
		int n = getN();
		double[] x = new double[n]; double tmp;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < a.length; j++) { // Scambio la colonna i-esima con il vettore dei termini noti
				tmp = y[j];
				y[j] = a[j][i];
				a[j][i] = tmp;
			}
			x[i] = Matrix.determinante(a) / det;
			for (int j = 0; j < a.length; j++) { // Rimetto a posto i termini noti
				tmp = y[j];
				y[j] = a[j][i];
				a[j][i] = tmp;
			}
		}
		return x;
	} // risolvi
	public String toString() {
		int n = getN(); final int ELEM_LENGTH = 10;
		StringBuilder sb = new StringBuilder(n * n * ELEM_LENGTH);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				sb.append(String.format("%8.2f ", a[i][j]));
			sb.append(String.format("| %1.2f\n", y[i]));
		}
		return sb.toString();
	} // toString
} // Cramer
