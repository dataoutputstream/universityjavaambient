package Esercizi;

public class Esercizio21 extends Thread {

	private static String nome = "Ciao";

	public static void main(String[] args) {
		try {
			new Esercizio21().prova();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(nome);
	}

	public void prova() throws InterruptedException {
		start();
		join();
		nome += " mondo";
	}

	public void run() {
		nome += " 1 2 3";
	}
}
