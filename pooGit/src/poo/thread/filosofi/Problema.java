package poo.thread.filosofi;

public class Problema {
	public static void main(String[]args) {
		Tavolo t = new TavoloFatale(5);
		for (int i = 0; i < 5; i++)
			//new Filosofo(i,t,2000,4000).start();
			new Filosofo(i,t,0,0).start();
	} // main
} // Problema
