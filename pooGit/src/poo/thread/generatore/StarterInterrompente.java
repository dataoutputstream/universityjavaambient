package poo.thread.generatore;

public class StarterInterrompente {
	public static void main( String[] args ){
		GeneratoreInterrompibile g1 = new GeneratoreInterrompibile(1, 0);
		GeneratoreInterrompibile g2 = new GeneratoreInterrompibile(2, 1000000);
		g1.start();
		g2.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {}
		g1.interrupt();
		g2.interrupt();
	} // main
} // StarterInterrompente
