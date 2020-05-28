package poo.figure;
import poo.util.Mat;
public class Quadrato extends Figura {
	public Quadrato( double lato ){
		super(lato);
	}
	public double getLato(){ return super.getDimensione(); }
	public double perimetro(){ return super.getDimensione()*4; }
	public double area(){
		double l=super.getDimensione();
		return l*l;
	}//area
	public String toString(){
		return String.format("Quadrato di lato=%1.2f",super.getDimensione());
	}//toString
	public boolean equals( Object o ){
		if( !(o instanceof Cerchio) ) return false;
		if( o==this ) return true;
		Quadrato q=(Quadrato)o;
		return Mat.sufficientementeProssimi( getDimensione(), q.getDimensione() );
	}//equals
	public int hashCode(){
		return new Double( getDimensione() ).hashCode();
	}//hashCode	
}//Quadrato
