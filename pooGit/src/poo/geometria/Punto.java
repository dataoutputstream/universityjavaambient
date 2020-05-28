package poo.geometria;

public class Punto {
	private double x, y;
	public Punto() { x = 0; y = 0; } // Costruttore default
	public Punto(double x, double y) {
		this.x = x; this.y = y;
	} // Costruttore normale
	public Punto(Punto p) {
		x = p.x; y = p.y;
	} // Costruttore copia
	public double getX() { return x; }
	public double getY() { return y; }
	public void sposta(double x, double y) {
		this.x = x; this.y = y;
	} // sposta
	public double distanza(Punto p) {
		return Math.sqrt((p.x - x)*(p.x - x) + (p.y - y)*(p.y - y));
	} // distanza
	public String toString() {
		return String.format("<%1.2f, %1.2f>", x, y);
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Punto)) return false;
		if (o == this) return true;
		Punto p = (Punto)o;
	 	return x == p.x && y == p.y;
	} // equals
	public int hashCode() {
		final int PRIMO = 17;
		return (new Double(x).hashCode()) * PRIMO + new Double(y).hashCode();
	} // hashCode
	public static void main(String[]args) {
		Punto p1 = new Punto(); // origine
		Punto p2 = new Punto(3, -7);
		System.out.println("Origine: x = " + p1.getX() + "  y = " + p1.getY());
		System.out.printf("Distanza tra " + p1 + " e " + p2 + " = %1.2f\n", p1.distanza(p2));
		p1.sposta(3.45782, -5.49872);
		System.out.println(p1);
	} // main
} // Punto
