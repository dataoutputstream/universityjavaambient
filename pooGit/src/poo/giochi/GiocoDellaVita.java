package poo.giochi;

public class GiocoDellaVita {
	private char[][] mappa;
	private char[][] nuovaMappa;
	private int n, m;
	public GiocoDellaVita(int n, int m) {
		if (n < 1 || m < 1) throw new IllegalArgumentException();
		this.n = n; this.m = m;
		mappa = new char[n][m];
		nuovaMappa = new char[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				mappa[i][j] = '.';
	} // Costruttore
	public void aggiungiOrganismo(int i, int j){
		if (i < 0 || i >= n || j < 0 || j >= m) throw new IllegalArgumentException();
		mappa[i][j] = '*';
	} // aggiungiOrganismo
	private int vicini(int i, int j) {
		if (i < 0 || i >= n || j < 0 || j >= m) throw new IllegalArgumentException();
		int cont = 0;
		for (int r = (i == 0 ? i : i - 1); r <= (i == n - 1 ? i : i + 1); r++)
			for (int c = (j == 0 ? j : j - 1); c <= (j == m - 1 ? j : j + 1); c++)
				if ((r != i || c != j) && mappa[r][c] == '*') cont++;
		return cont;
	} // vicini
	public void prossimaGenerazione() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				int v = vicini(i, j);
				if (mappa[i][j] == '*')
					nuovaMappa[i][j] = (v == 2 || v == 3) ? '*' : '.';
				else
					nuovaMappa[i][j] = (v == 3) ? '*' : '.';
			}
		char[][] tmp = mappa;
		mappa = nuovaMappa;
		nuovaMappa = tmp;
	} // prossimaGenerazione
	public String toString() {
		StringBuilder sb = new StringBuilder(400);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				sb.append(mappa[i][j]);
			sb.append('\n');
		}
		return sb.toString();
	} // toString
	public static void main(String[]args) {
		GiocoDellaVita gol = new GiocoDellaVita(5,7);
		System.out.println(gol);
		gol.aggiungiOrganismo(0,2);
		gol.aggiungiOrganismo(0,5);
		gol.aggiungiOrganismo(0,6);
		gol.aggiungiOrganismo(1,0);
		gol.aggiungiOrganismo(2,3);
		gol.aggiungiOrganismo(2,5);
		gol.aggiungiOrganismo(3,0);
		gol.aggiungiOrganismo(3,4);
		System.out.println(gol);
		gol.prossimaGenerazione();
		System.out.println(gol);
		gol.prossimaGenerazione();
		System.out.println(gol);
	} // main
} // GiocoDellaVita
