package poo.geometria;

public class Cilindro extends Disco implements FiguraSolida {
	private double altezza;
	public Cilindro( double raggio, double altezza ){
		super(raggio);
		if( altezza<=0 ) throw new IllegalArgumentException();
		this.altezza=altezza;
	}
	public Cilindro( Punto p, double raggio, double altezza ){
		super(p,raggio);
		if( altezza<=0 ) throw new IllegalArgumentException();
		this.altezza=altezza;
	}
	public Cilindro( Cilindro c ){
		super(new Punto(c.getX(),c.getY()),c.getRaggio());
		if( altezza<=0 ) throw new IllegalArgumentException();
		this.altezza=c.altezza;
	}
	public double perimetro(){ 
		throw new UnsupportedOperationException();
	}//perimetro
	public double area(){ 
		double r=super.getRaggio();
		return areaLaterale()+2*r*r*Math.PI; 
	}//area - totale
	public double areaLaterale(){ return 2*Math.PI*super.getRaggio()*altezza; }
	public double areaBase(){
		double r=super.getRaggio();
		return r*r*Math.PI;
	}//areaBase
	public double volume(){ 
		double r=super.getRaggio();
		return r*r*Math.PI*altezza; 
	}//volume
	public String toString(){
		return String.format("Cilindro di raggio=%1.2f altezza=%1.2f e punto base <%1.2f,%1.2f>",
				             getRaggio(), altezza, getX(), getY());
	}//toString
	public static void main( String[] args ){
		Cilindro c=new Cilindro( new Punto(2,3), 5, 7 );
		System.out.println(c);
		System.out.println("Area totale cilindro="+String.format("%1.2f",c.area()));
		System.out.println("Area laterale cilindro="+String.format("%1.2f",c.areaLaterale()));
		System.out.println("Area base cilindro="+String.format("%1.2f",c.areaBase()));
		System.out.println("Volume cilindro="+String.format("%1.2f",c.volume()));
		//demo
		System.out.println("Perimetro cilindro="+String.format("%1.2f",c.perimetro()));
	}
}//Cilindro
