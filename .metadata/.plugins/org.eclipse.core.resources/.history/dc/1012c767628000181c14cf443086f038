package poo.util;

import java.io.*;

public final class Matrix {
	// Precondizione: matrici quadrate o rettangolari
	private Matrix() {} // Sovrascrivo costruttore di default
	public static boolean simmetrica(double[][]m) {
		if (!quadrata(m)) return false;
		for (int i = 0; i < m.length; i++)
			for (int j = i + 1; j < m[0].length; j++)
				if (!Mat.circaUguali(m[i][j], m[j][i]))
					return false;
		return true;
	} // simmetrica
	public static double[][] trasposta(double[][]m) {
		if (!quadrata(m)) throw new IllegalArgumentException("Matrice non quadrata!");
		double[][] t = new double[m.length][m.length];
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[0].length; j++)
				t[j][i] = m[i][j];
		return t;
	} // trasposta
	public static double[][] add(double[][]m1, double[][]m2) {
		if (!dimensioniUguali(m1, m2)) throw new IllegalArgumentException("Matrici incompatibili!");
		double[][] s = new double[m1.length][m1[0].length];
		for (int i = 0; i < m1.length; i++)
			for (int j = 0; j < m1[0].length; j++)
				s[i][j] = m1[i][j] + m2[i][j];
		return s;
	} // add
	public static double[][] sub(double[][]m1, double[][]m2) {
		if (!dimensioniUguali(m1, m2)) throw new IllegalArgumentException("Matrici incompatibili!");
		double[][] d = new double[m1.length][m1[0].length];
		for (int i = 0; i < m1.length; i++)
			for (int j = 0; j < m1[0].length; j++)
				d[i][j] = m1[i][j] - m2[i][j];
		return d;
	} // sub
	public static double[][] mul(double[][]m1, double[][]m2) {
		for (int i = 0; i < m1.length; i++)
			if (m1[i].length != m2.length)
				throw new IllegalArgumentException("Matrici non moltiplicabili!");
		double[][] p = new double[m1.length][m2[0].length];
		for (int i = 0; i < p.length; i++)
			for (int j = 0; j < p[0].length; j++)
				for (int k = 0; k < m1[0].length; k++)
					p[i][j] += m1[i][k] * m2[k][j];
		return p;
	} // mul
	public static double[][] sottoMatrice(double[][]m, int i, int j, int n) {
		if (n > m.length - i) throw new IllegalArgumentException("Sottomatrice non presente!");
		for (int r = i; r < m.length; r++)
			if (n > m[i].length - j)
				throw new IllegalArgumentException("Sottomatrice non presente!");
		double[][] s = new double[n][n];
		for (int r = 0; r < n; r++)
			for (int c = 0; c < n; c++)
				s[r][c] = m[i+r][j+c];
		return s;
	} // sottoMatrice
	public static double[][] minore(double[][] m, int i, int j) {
		if (!quadrata(m)) throw new IllegalArgumentException("Matrice non quadrata!");
		if (m.length == 1) throw new IllegalArgumentException("Matrice di ordine 1!");
		if (!(i >= 0 && i < m.length) || !(j >= 0 && j < m.length)) throw new IllegalArgumentException("Minore complementare inesistente!");
		double[][] min = new double[m.length - 1][m[0].length - 1];
		int saltoRiga, saltoColonna;
		for (int r = 0; r < min.length; r++) {
			saltoRiga = r >= i ? 1 : 0;
			for (int c = 0; c < min[0].length; c++) {
				saltoColonna = c >= j ? 1 : 0;
				min[r][c] = m[r + saltoRiga][c + saltoColonna];
			}
		}
		return min;
	} // minore
	public static boolean quadratoMagico(double[][]m) {
		if (!quadrata(m)) throw new IllegalArgumentException("Matrice non quadrata!");
		double d1 = 0D, d2 = 0D;
		for (int i = 0; i < m.length; i++)
			d1 += m[i][i]; // Diagonale principale
		for (int i = 0; i < m.length; i++)
			d2 += m[i][m.length-1-i]; // Diagonale secondaria
		if (!Mat.circaUguali(d1, d2)) return false;
		double sommaRiga, sommaColonna;
		for (int i = 0; i < m.length; i++) {
			sommaRiga = 0; sommaColonna = 0;
			for (int j = 0; j < m.length; j++) {
				sommaRiga += m[i][j];
				sommaColonna += m[j][i];
			}
			if (!Mat.circaUguali(d1, sommaRiga) || !Mat.circaUguali(d1, sommaColonna))
				return false; // Somma di ogni riga e colonna
		}
		return true;
	} // quadratoMagico
	public static int[] sella(double[][]m) {
		for (int i = 0; i < m.length; i++) {
			int j = posMinimo(m[i]);
			if (posMassimo(trasposta(m)[j]) == i) return new int[] {i,j};
		}
		return new int[] {-1,-1};
	} // sella
	public static double determinante(double[][] m) {
		if (!quadrata(m)) throw new IllegalArgumentException("Matrice non quadrata!");
		int n = m.length;
		double[][] a = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				a[i][j] = m[i][j];
		int contaScambi = 0;
		// Triangolarizzo la matrice
		for (int j = 0; j < n; j++) {
			if (Mat.circaUguali(a[j][j], 0)) { // pivoting
				int p = j + 1; int pmax = p;
				// Cerco pivot massimo per minimizzare gli errori di approssimazione
				for (; p < n; p++)
					if (Math.abs(a[p][j]) > Math.abs(a[pmax][j])) pmax = p;
				if (pmax == n || Mat.circaUguali(a[pmax][j], 0D)) return 0; // Sistema singolare -> Determinante = 0
				double[] tmp = a[j];
				a[j] = a[pmax];
				a[pmax] = tmp;
				contaScambi++;
			}
			for (int i = j + 1; i < n; i++) {
				if (!Mat.circaUguali(a[i][j], 0D)) {
					double c = a[i][j] / a[j][j];
					for (int k = j; k < n; k++)
						a[i][k] -= c * a[j][k];
				}
			} // for i
		} // for j
		double det = 1;
		// Prodotto dei pivot
		for (int i = 0; i < n; i++)
			det *= a[i][i];
		// Segno del determinante
		det *= (contaScambi % 2 == 0 ? 1 : -1);
		return det;
	} // determinante
	private static boolean quadrata(double[][] m) {
		for (int i = 0; i < m.length; i++)
			if (m.length != m[i].length)
				return false;
		return true;
	} // quadrata
	private static boolean dimensioniUguali(double[][]m1, double[][]m2) {
		if (m1.length != m2.length) return false;
		for (int i = 0; i < m1.length; i++)
			if (m1[i].length != m2[i].length)
				return false;
		return true;
	} // dimensioniUguali
	private static int posMinimo(double[]v) {
		double min = v[0]; int minpos = 0;
		for (int i = 1; i < v.length; i++)
			if (v[i] < min) { min = v[i]; minpos = i; }
		return minpos;
	} // posMinimo
	private static int posMassimo(double[]v) {
		double max = v[0]; int maxpos = 0;
		for (int i = 1; i < v.length; i++)
			if (v[i] > max) { max = v[i]; maxpos = i; }
		return maxpos;
	} // posMinimo

	// Stessi metodi per matrici di interi:
	public static boolean simmetrica(int[][]m) {
		if (!quadrata(m)) return false;
		for (int i = 0; i < m.length; i++)
			for (int j = i + 1; j < m[0].length; j++)
				if (m[i][j] != m[j][i])
					return false;
		return true;
	} // simmetrica
	public static int[][] trasposta(int[][]m) {
		if (!quadrata(m)) throw new IllegalArgumentException("Matrice non quadrata!");
		int[][] t = new int[m.length][m.length];
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[0].length; j++)
				t[j][i] = m[i][j];
		return t;
	} // trasposta
	public static int[][] add(int[][]m1, int[][]m2) {
		if (!dimensioniUguali(m1, m2)) throw new IllegalArgumentException("Matrici incompatibili!");
		int[][] s = new int[m1.length][m1[0].length];
		for (int i = 0; i < m1.length; i++)
			for (int j = 0; j < m1[0].length; j++)
				s[i][j] = m1[i][j] + m2[i][j];
		return s;
	} // add
	public static int[][] sub(int[][]m1, int[][]m2) {
		if (!dimensioniUguali(m1, m2)) throw new IllegalArgumentException("Matrici incompatibili!");
		int[][] d = new int[m1.length][m1[0].length];
		for (int i = 0; i < m1.length; i++)
			for (int j = 0; j < m1[0].length; j++)
				d[i][j] = m1[i][j] - m2[i][j];
		return d;
	} // sub
	public static int[][] mul(int[][]m1, int[][]m2) {
		for (int i = 0; i < m1.length; i++)
			if (m1[i].length != m2.length)
				throw new IllegalArgumentException("Matrici non moltiplicabili!");
		int[][] p = new int[m1.length][m2[0].length];
		for (int i = 0; i < p.length; i++)
			for (int j = 0; j < p[0].length; j++)
				for (int k = 0; k < m1[0].length; k++)
					p[i][j] += m1[i][k] * m2[k][j];
		return p;
	} // mul
	public static int[][] sottoMatrice(int[][]m, int i, int j, int n) {
		if (n > m.length - i) throw new IllegalArgumentException("Sottomatrice non presente!");
		for (int r = i; r < m.length; r++)
			if (n > m[i].length - j)
				throw new IllegalArgumentException("Sottomatrice non presente!");
		int[][] s = new int[n][n];
		for (int r = 0; r < n; r++)
			for (int c = 0; c < n; c++)
				s[r][c] = m[i+r][j+c];
		return s;
	} // sottoMatrice
	public static int[][] minore(int[][] m, int i, int j) {
		if (!quadrata(m)) throw new IllegalArgumentException("Matrice non quadrata!");
		if (m.length == 1) throw new IllegalArgumentException("Matrice di ordine 1!");
		if (!(i >= 0 && i < m.length) || !(j >= 0 && j < m.length)) throw new IllegalArgumentException("Minore complementare inesistente!");
		int[][] min = new int[m.length - 1][m[0].length - 1];
		int saltoRiga, saltoColonna;
		for (int r = 0; r < min.length; r++) {
			saltoRiga = r >= i ? 1 : 0;
			for (int c = 0; c < min[0].length; c++) {
				saltoColonna = c >= j ? 1 : 0;
				min[r][c] = m[r + saltoRiga][c + saltoColonna];
			}
		}
		return min;
	} // minore
	public static boolean quadratoMagico(int[][]m) {
		if (!quadrata(m)) throw new IllegalArgumentException("Matrice non quadrata!");
		int d1 = 0, d2 = 0;
		for (int i = 0; i < m.length; i++)
			d1 += m[i][i]; // Diagonale principale
		for (int i = 0; i < m.length; i++)
			d2 += m[i][m.length-1-i]; // Diagonale secondaria
		if (!Mat.circaUguali(d1, d2)) return false;
		int sommaRiga, sommaColonna;
		for (int i = 0; i < m.length; i++) {
			sommaRiga = 0; sommaColonna = 0;
			for (int j = 0; j < m.length; j++) {
				sommaRiga += m[i][j];
				sommaColonna += m[j][i];
			}
			if (d1 != sommaRiga || d1 != sommaColonna)
				return false; // Somma di ogni riga e colonna
		}
		return true;
	} // quadratoMagico
	public static int[] sella(int[][]m) {
		for (int i = 0; i < m.length; i++) {
			int j = posMinimo(m[i]);
			if (posMassimo(trasposta(m)[j]) == i) return new int[] {i,j};
		}
		return new int[] {-1,-1};
	} // sella
	public static int determinante(int[][] m) {
		if (!quadrata(m)) throw new IllegalArgumentException("Matrice non quadrata!");
		int n = m.length;
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				a[i][j] = m[i][j];
		int contaScambi = 0;
		// Triangolarizzo la matrice
		for (int j = 0; j < n; j++) {
			if (a[j][j] == 0) { // pivoting
				int p = j + 1; int pmax = p;
				// Cerco pivot massimo per minimizzare gli errori di approssimazione
				for (; p < n; p++)
					if (Math.abs(a[p][j]) > Math.abs(a[pmax][j])) pmax = p;
				if (pmax == n || a[pmax][j] == 0) return 0; // Sistema singolare -> Determinante = 0
				int[] tmp = a[j];
				a[j] = a[pmax];
				a[pmax] = tmp;
				contaScambi++;
			}
			for (int i = j + 1; i < n; i++) {
				if (a[i][j] != 0) {
					int c = a[i][j] / a[j][j];
					for (int k = j; k < n; k++)
						a[i][k] -= c * a[j][k];
				}
			} // for i
		} // for j
		int det = 1;
		// Prodotto dei pivot
		for (int i = 0; i < n; i++)
			det *= a[i][i];
		// Segno del determinante
		det *= (contaScambi % 2 == 0 ? 1 : -1);
		return det;
	} // determinante
	private static boolean quadrata(int[][] m) {
		for (int i = 0; i < m.length; i++)
			if (m.length != m[i].length)
				return false;
		return true;
	} // quadrata
	private static boolean dimensioniUguali(int[][]m1, int[][]m2) {
		if (m1.length != m2.length) return false;
		for (int i = 0; i < m1.length; i++)
			if (m1[i].length != m2[i].length)
				return false;
		return true;
	} // dimensioniUguali
	private static int posMinimo(int[]v) {
		int min = v[0]; int minpos = 0;
		for (int i = 1; i < v.length; i++)
			if (v[i] < min) { min = v[i]; minpos = i; }
		return minpos;
	} // posMinimo
	private static int posMassimo(int[]v) {
		int max = v[0]; int maxpos = 0;
		for (int i = 1; i < v.length; i++)
			if (v[i] > max) { max = v[i]; maxpos = i; }
		return maxpos;
	} // posMinimo
	public static void sviluppo(char[][] sis, String nomeFile) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(nomeFile));
		char[] col = new char[13];
		sviluppo(sis, col, pw, 0);
		pw.close();
	} // sviluppo
	private static void sviluppo(char[][] sis, char[] col, PrintWriter pw, int i) throws IOException {
		for (int j = 0; j < sis[i].length; j++) {
			col[i] = sis[i][j];
			if (i == 12) pw.println(java.util.Arrays.toString(col));
			else sviluppo(sis, col, pw, i + 1);
		}
	} // sviluppo
	public static void main(String[]args) {
		System.out.println("Matrice m:");
		double[][] m = new double[][] {{2,3,1},{3,4,6},{1,6,5}};
		for (int i = 0; i < m.length; i++)
			System.out.println(java.util.Arrays.toString(m[i]));
		System.out.println("m è simmetrica: " + simmetrica(m) + "\n");
		System.out.println("Determinante di m = " + String.format("%1.2f", determinante(m)) + "\n");
		System.out.println("Trasposta di m:");
		double[][] t = trasposta(m);
		for (int i = 0; i < t.length; i++)
			System.out.println(java.util.Arrays.toString(t[i]));
		System.out.println("\nMatrice somma:");
		double[][] s = add(m, t);
		for (int i = 0; i < s.length; i++)
			System.out.println(java.util.Arrays.toString(s[i]));
		System.out.println("\nMinore complementare (1,1) di m:");
		double[][] min = Matrix.minore(m, 1, 1);
		for (int i = 0; i < min.length; i++)
			System.out.println(java.util.Arrays.toString(min[i]));
		System.out.println("\nElemento 'sella' di m: " + java.util.Arrays.toString(sella(m)));
		System.out.println("\nMatrice q:");
		double[][] q = new double[][] {	{16,3,2,13},
						{5,10,11,8},
						{9,6,7,12},
						{4,15,14,1} }; // Grazie a Melencolia I
		for (int i = 0; i < q.length; i++)
			System.out.println(java.util.Arrays.toString(q[i]));
		System.out.println("La matrice q è un quadrato magico: " + quadratoMagico(q));
		System.out.println("Prova sistema totocalcio: ");
		char[][] sis = {	{'1'},
					{'1', 'X'},
					{'2'},
					{'2', 'X'},
					{'1', '2'},
					{'1', '2', 'X'},
					{'1'},
					{'X'},
					{'1'},
					{'2'},
					{'1', '2'},
					{'2', 'X'},
					{'2'}		};
		try {
			sviluppo(sis, "totocalcio.txt");
		} catch (IOException e) {
			System.out.println("Errore di scrittura!");
		}
	} // main
} // Matrix
