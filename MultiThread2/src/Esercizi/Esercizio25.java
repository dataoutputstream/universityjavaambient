
package Esercizi;


public class Esercizio25 {

	public static void main(String[] args) {
		int[][] a = { { 1, 2, 3, 4 }, { 4, 2, 0, -1 }, { 4, 2, 1, 1 },
				{ 3, 3, 2, 2 } };
		int x = 2;
		int y = 1;
		ContaXY[] contaThread = new ContaXY[a.length];
		for (int i = 0; i < a.length; i++) {
			contaThread[i] = new ContaXY(x, y, a, i);
			contaThread[i].start();
		}
		int nX = 0;
		int nY = 0;
		for (int i = 0; i < contaThread.length; i++) {
			nX += contaThread[i].getnX();
			nY += contaThread[i].getnY();
		}
		System.out.format("(nX=%d, nY=%d)%n", nX, nY);
		System.out.println(nX > nY);
	}
}
