package poo.geometria;

public class Sfera extends Disco implements FiguraSolida {
	public Sfera(double raggio){
		super(raggio);
	} // Costruttore base
	public Sfera(double x, double y, double raggio) {
		super(x, y, raggio);
	} // Costruttore 1
	public Sfera(Punto p, double raggio) {
		super(p, raggio);
	} // Costruttore 2
	public Sfera(Sfera s) {
		super(s.getX(), s.getY(), s.getRaggio());
	} // Costruttore di copia
	public double perimetro() { 
		throw new UnsupportedOperationException();
	} // perimetro
	public double area() { 
		double r = getRaggio();
		return 4 * Math.PI * r * r;
	} // area
	public double areaLaterale() { return area(); }
	public double areaDiBase() { return 0; }
	public double volume() { 
		double r = getRaggio();	
		return (4 * Math.PI * r * r * r)/3; 
	} // volume
	public String toString() {
		return String.format("Sfera di raggio = %1.2f e centro <%1.2f,%1.2f>", getRaggio(), getX(), getY());
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Sfera)) return false;
		if (o == this) return true;
		Sfera s = (Sfera)o;
		return getX() == s.getX() && getY() == s.getY() && getRaggio() == s.getRaggio();
	} // equals
	public int hashCode() {
		final int PRIMO = 43;
		return super.hashCode() * PRIMO;
	} // hashCode
	public static void main(String[]args) {
		Sfera c = new Sfera(new Punto(2,3), 5);
		System.out.println(c);
		System.out.println("Area totale sfera = " + String.format("%1.2f", c.area()));
		System.out.println("Area laterale sfera = " + String.format("%1.2f", c.areaLaterale()));
		System.out.println("Area base sfera = " + String.format("%1.2f", c.areaDiBase()));
		System.out.println("Volume sfera = " + String.format("%1.2f", c.volume()));
		//demo
		System.out.println("Perimetro sfera = " + String.format("%1.2f", c.perimetro()));
	}
} // Sfera

