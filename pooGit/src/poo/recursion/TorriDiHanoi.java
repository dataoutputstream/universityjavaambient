package poo.recursion;

public class TorriDiHanoi {
	enum Paletto {SX, CL, DX};
	public static void muovi(int n, Paletto sorg, Paletto aus, Paletto dest) {
		if (n == 1) sposta1Disco(sorg, dest);
		else {
			muovi(n - 1, sorg, dest, aus);
			sposta1Disco(sorg, dest);
			muovi(n - 1, aus, sorg, dest);
		}
	} // muovi
	public static void sposta1Disco(Paletto sorg, Paletto dest) {
		System.out.println("Sposta 1 disco da " + sorg + " a " + dest);
	} // sposta1Disco
	public static void main(String[]args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.out.print("Inserisci il numero dei dischi: ");
		int n = sc.nextInt();
		muovi(n, Paletto.SX, Paletto.CL, Paletto.DX);
	} // main
} // TorriDiHanoi
