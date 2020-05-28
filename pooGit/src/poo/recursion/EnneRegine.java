package poo.recursion;

public class EnneRegine {
	private boolean[][] board;
	private  int n, numSol = 0;
	public EnneRegine(int n) {
		if (n < 4) throw new IllegalArgumentException();
		this.n = n; board = new boolean[n][n];
	} // Costruttore
	public void risolvi() {
		collocaRegina(0);
	} // risolvi
	private void collocaRegina(int row) {
		for (int col = 0; col < n; col++) {
			if (assegnabile(row, col)) {
				assegna(row, col);
				if (row == n - 1) scriviSoluzione();
				else collocaRegina(row + 1);
				deassegna(row, col);
			}
		}
	} // collocaRegina
	private boolean assegnabile(int row, int col) {
		int i;
		for (i = row - 1; i >= 0; i--)
			if (board[i][col]) return false;
		for (i = 1; row - i >= 0 && col - i >= 0; i++)
			if (board[row - i][col - i]) return false;
		for (i = 1; row - i >= 0 && col + i < n; i++)
			if (board[row - i][col + i]) return false;
		return true;
	} // assegnabile
	private void assegna(int row, int col) {
		board[row][col] = true;
	} // assegna
	private void deassegna(int row, int col) {
		board[row][col] = false;
	} // deassegna
	private void scriviSoluzione() {
		numSol++;
		StringBuilder sb = new StringBuilder(300);
		sb.append("Sol. #" + numSol + '\n');
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {	
				if (board[i][j]) sb.append('o');
				else sb.append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	} // scriviSoluzione
	public static void main(String[]args) {
		new EnneRegine(10).risolvi();
	} // main
} // EnneRegine
