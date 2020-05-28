package poo.thread.scambiatore;

public class Scambio {
	public static void main( String[] args ){
		Exchanger<String> exc = new ExchangerMJ<String>();
		Produttore p = new Produttore( 1, exc, 1000, 500 );
		Consumatore c = new Consumatore( 2, exc, 1000, 500 );
		p.start();
		c.start();
	}
}//Scambio
