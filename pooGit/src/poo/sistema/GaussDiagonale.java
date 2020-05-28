package poo.sistema;

import poo.util.Mat;

public class GaussDiagonale extends Gauss {
	public GaussDiagonale(double[][] a, double[] y) {
		super(a, y);
	} // Costruttore normale
	@Override protected void triangolarizza() { // diagonalizza
		int n = getN();
		super.triangolarizza();
		for (int j = 0; j < n; j++) {
			double c = a[j][j]; 
			for (int k = j; k <= n; k++) // Normalizzo i pivot
				a[j][k] /= c;
			for (int i = j - 1; i >= 0; i--) {
				if (!Mat.circaUguali(a[i][j], 0D)) {
					c = a[i][j];
					for (int k = j; k <= n; k++)
						a[i][k] -= c * a[j][k];
				}
			}
		}
	} // triangolarizza
	@Override protected double[] calcoloSoluzione() {
		int n = getN();
		double[] x = new double[n];
		for (int i = 0; i < n; i++)
			x[i] = a[i][n];
		return x;
	} // calcoloSoluzione
} // GaussDiagonale
