package poo.figure;
import poo.util.*;
public class Cerchio extends Figura{
	public Cerchio( double raggio ){
		super( raggio );
	}
	public Cerchio( Cerchio c ){
		super( c.getDimensione() );
	}
	public double getRaggio(){ return getDimensione(); }
	@Override
	public double perimetro(){
		return 2*Math.PI*getDimensione();
	}//perimetro
	@Override
	public double area(){
		double r=getDimensione();
		return r*r*Math.PI;
	}//area
	@Override
	public boolean equals( Object o ){
		if( !(o instanceof Cerchio) ) return false;
		if( o==this ) return true;
		Cerchio c=(Cerchio)o;
		return Mat.sufficientementeProssimi( getDimensione(), c.getDimensione() );
	}//equals
	public int hashCode(){
		return new Double( getDimensione() ).hashCode();
	}//hashCode
	public String toString(){
		return String.format("Cerchio: raggio=%1.2f",getDimensione());
	}//toString
}//Cerchio
