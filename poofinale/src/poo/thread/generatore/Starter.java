package poo.thread.generatore;

public class Starter {
	public static void main( String[] args ){
		Generatore g1=new Generatore( 1, 0 );
		Generatore g2=new Generatore( 2, 1000000 );
		g1.start();
		g2.start();
	}//main
}//Starter
