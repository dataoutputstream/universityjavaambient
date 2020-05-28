package poo.geometria;

public class Cilindro extends Disco implements FiguraSolida {
	private double altezza;
	public Cilindro(double raggio, double altezza) {
		super(raggio);
		if (altezza <= 0) throw new IllegalArgumentException();
		this.altezza = altezza;
	} // Costruttore base
	public Cilindro(double x, double y, double raggio, double altezza) {
		super(x, y, raggio);
		if (altezza <= 0) throw new IllegalArgumentException();
		this.altezza = altezza;
	} // Costruttore 1
	public Cilindro(Punto p, double raggio, double altezza) {
		this(p.getX(), p.getY(), raggio, altezza);
	} // Costruttore 2
	public Cilindro(Disco d, double altezza) {
		this(d.getX(), d.getY(), d.getRaggio(), altezza);
	} // Costruttore 3
	public Cilindro(Cilindro c) {
		this(c.getX(), c.getY(), c.getRaggio(), c.altezza);
	} // Costruttore di copia
	public double getAltezza() { return altezza; }
	public double area() {
		return 2 * areaDiBase() + areaLaterale();
	} // area
	public double areaDiBase() { return super.area(); }
	public double areaLaterale() { return super.perimetro() * altezza; }
	public double volume() { return super.area() * altezza; }
	public double perimetro() {
		throw new UnsupportedOperationException();
	} // perimetro
	public String toString(){
		return "Cilindro di raggio = " + String.format("%1.2f altezza = %1.2f e punto base <%1.2f, %1.2f>", getRaggio(), altezza, getX(), getY());
	}//toString
	public static void main(String[]args) {
		Cilindro c = new Cilindro( new Punto(), 3.5, 6.72 );
		System.out.println(c);
		System.out.println("Area totale cilindro = " + String.format("%1.2f", c.area()));
		System.out.println("Area laterale cilindro = " + String.format("%1.2f", c.areaLaterale()));
		System.out.println("Area base cilindro = " + String.format("%1.2f", c.areaDiBase()));
		System.out.println("Volume cilindro = " + String.format("%1.2f", c.volume()));
		//demo
		System.out.println("Perimetro cilindro = " + String.format("%1.2f", c.perimetro()));
	} // main
} // Cilindro
