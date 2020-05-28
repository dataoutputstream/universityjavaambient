package poo.geometria;
import poo.util.Mat;
public class Disco extends Punto implements FiguraPiana{
	private double raggio;
	public Disco( double raggio ){
		super();
		if( raggio<=0 ) throw new IllegalArgumentException();
		this.raggio=raggio;
	}
	public Disco( double x, double y, double raggio ){
		super(x,y);
		if( raggio<=0 ) throw new IllegalArgumentException();
		this.raggio=raggio;
	}
	public Disco( Punto p, double raggio ){
		super( p.getX(), p.getY() );
		if( raggio<=0 ) throw new IllegalArgumentException();
	}
	public double getRaggio(){ return raggio; }
	public double perimetro(){ return 2*Math.PI*raggio; }
	public double area(){
		return raggio*raggio*Math.PI;
	}//area
	public String toString(){
		return String.format("Disco di raggio=%1.2f e centro <%1.2f,%1.2f>",
				raggio,getX(),getY());
	}//toString
	public boolean equals( Object o ){
		if( !(o instanceof Disco) ) return false;
		if( o==this ) return true;
		Disco d=(Disco)o;
		return Mat.sufficientementeProssimi( raggio, d.raggio );
	}//equals
	public int hashCode(){
		return new Double(raggio).hashCode();
	}//hashCode	
	public static void main( String[] args ){//DI PROVA
		Punto p=new Punto(2,3);
		Disco c=new Disco( 4 );
		System.out.println(c);
		double d=c.distanza(p);
		System.out.println("Distanza tra "+c+" e punto "+p+" = "+String.format("%1.2f",d));
		c.sposta(3,5);
		System.out.println(c);
		d=p.distanza(c);
		System.out.println("Distanza tra "+c+" e punto "+p+" = "+String.format("%1.2f",d));
	}//main	
}//Disco
