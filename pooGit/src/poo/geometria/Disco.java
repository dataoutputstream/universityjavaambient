package poo.geometria;

public class Disco extends Punto implements FiguraPiana {
	private double raggio;
	public Disco(double raggio) {
		if (raggio <= 0) throw new IllegalArgumentException();
		this.raggio = raggio;
	} // Costruttore base
	public Disco(double x, double y, double raggio) {
		super(x, y);
		if (raggio <= 0) throw new IllegalArgumentException();
		this.raggio = raggio;
	} // Costruttore 1
	public Disco(Punto centro, double raggio) {
		super(centro);
		if (raggio <= 0) throw new IllegalArgumentException();
		this.raggio = raggio;
	} // Costruttore 2
	public Disco(Disco d) {
		super(d); raggio = d.raggio;
	} // Costruttore di copia
	public double getRaggio() { return raggio; }
	public double area() {
		return Math.PI * raggio * raggio;
	} // area
	public double perimetro() {
		return 2 * Math.PI * raggio;
	} // perimetro
	public String toString() {
		return "Disco di centro: " + super.toString() + String.format(" e raggio = %1.2f", raggio);
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Disco)) return false;
		if (o == this) return true;
		Disco d = (Disco)o;
		return super.equals(d) && raggio == d.raggio;
	} // equals
	public int hashCode() {
		final int PRIMO = 29;
		return super.hashCode() * PRIMO + new Double(raggio).hashCode();
	} // hashCode
	public static void main(String[]args) {
		Disco d = new Disco(new Punto(3, 4), 6);
		System.out.println(d);
		d.sposta(2, 5);
		Disco d2 = new Disco(4, 2, 8);
		System.out.println("Distanza tra " + d + " e " + d2 + " = " + String.format("%1.2f", d.distanza(d2)));
		System.out.println(d2 + String.format(" | Area = %1.2f | Perimetro = %1.2f", d2.area(), d2.perimetro()));
		System.out.println("hashCode di " + d2 + " = " + d2.hashCode());
	} // main
} // Disco
