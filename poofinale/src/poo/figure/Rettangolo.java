package poo.figure;
import poo.util.Mat;
public class Rettangolo extends Figura{
	private double altezza;
	public Rettangolo( double base, double altezza ){
		super(base);
		if( altezza<=0 ) throw new IllegalArgumentException();
		this.altezza=altezza;
	}
	public double getBase(){ return super.getDimensione(); }
	public double getAltezza(){ return altezza; }	
	public double perimetro(){ return 2*getDimensione()+2*altezza; }
	public double area(){
		return getDimensione()*altezza;
	}//area
	public String toString(){
		return String.format("Rettangolo: base=%1.2f altezza=%1.2f",
				getDimensione(),altezza);
	}//toString
	public boolean equals( Object o ){
		if( !(o instanceof Rettangolo) ) return false;
		if( o==this ) return true;
		Rettangolo r=(Rettangolo)o;
		return Mat.sufficientementeProssimi( getDimensione(), r.getDimensione() ) &&
			Mat.sufficientementeProssimi( altezza, r.altezza );
	}//equals
	public int hashCode(){
		final int MOLT=41;
		int h=0;
		h=h*MOLT+new Double( getDimensione() ).hashCode();
		h=h*MOLT+new Double( altezza ).hashCode();
		return h;
	}//hashCode	
}//Rettangolo
