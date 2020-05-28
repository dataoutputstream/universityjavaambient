package Esercizi;

public class ContaXY extends Thread {
	private int nX = 0;
	private int nY = 0;
	private int x;
	private int y;
	private int riga;
	private int[][] a;

	public ContaXY(int x, int y, int[][] a, int riga) {
		this.x = x;
		this.y = y;
		this.a = a;
		this.riga = riga;
	}

	public int getnX() {
		try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return nX;
	}

	public int getnY() {
		try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return nY;
	}

	@Override
	public void run() {
		for (int i = 0; i < a[0].length; i++) {
			if (a[riga][i] == x) {
				nX++;
			} else if (a[riga][i] == y) {
				nY++;
			}
		}
	}
}
