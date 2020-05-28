package poo.thread.buffer;
import poo.util.*;

public class Prod1Cons1 {
	public static void main( String [] args ){
		BufferLimitato<String> b=new BufferLimitatoMJ<String>( 5 );
		Produttore p1=new Produttore( 1, b, 1000, 500 );
		Consumatore c1=new Consumatore( 1, b, 1000, 200 );
		p1.start();
		c1.start();
	}//main
}//Prod1Cons1
