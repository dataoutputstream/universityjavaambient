package poo.geometria;

public class Sfera extends Disco implements FiguraSolida{
	public Sfera( double raggio ){
		super(raggio);
	}
	public Sfera( Punto p, double raggio ){
		super(p,raggio);
	}
	public Sfera( Sfera s ){
		this( new Punto(s.getX(),s.getY()), s.getRaggio() );
	}
	public double perimetro(){ 
		throw new UnsupportedOperationException();
	}//perimetro
	public double area(){ 
		double r=super.getRaggio();
		return 4*Math.PI*r*r; 
	}//area - totale
	public double areaLaterale(){ return area(); }
	public double areaBase(){
		return 0;
	}//areaBase
	public double volume(){ 
		double r=super.getRaggio();	
		return (4*Math.PI*r*r)/3; 
	}//volume
	public String toString(){
		return String.format("Sfera di raggio=%1.2f e centro <%1.2f,%1.2f>",
				getRaggio(),getX(),getY());
	}//toString
	public static void main( String[] args ){
		Sfera c=new Sfera( new Punto(2,3), 5 );
		System.out.println(c);
		System.out.println("Area totale sfera="+String.format("%1.2f",c.area()));
		System.out.println("Area laterale sfera="+String.format("%1.2f",c.areaLaterale()));
		System.out.println("Area base sfera="+String.format("%1.2f",c.areaBase()));
		System.out.println("Volume sfera="+String.format("%1.2f",c.volume()));
		//demo
		System.out.println("Perimetro sfera="+String.format("%1.2f",c.perimetro()));
	}
}//Sfera
