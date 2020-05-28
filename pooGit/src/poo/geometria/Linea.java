package poo.geometria;

public class Linea {
	private Punto p1, p2; // Classe definita per COMPOSIZIONE di due oggetti Punto
	public Linea(Punto p1, Punto p2) {
		this.p1 = new Punto(p1);
		this.p2 = new Punto(p2);
	} // Costruttore normale
	public Linea(Linea l) {
		p1 = new Punto(l.p1);
		p2 = new Punto(l.p2);
	} // Costruttore di copia
	public Punto[] getEstremi() {
		Punto[] e = { new Punto(p1), new Punto(p2) };
		return e;
	} // getEstremi
	public double lunghezza() {
		return p1.distanza(p2);
	} // lunghezza
	public String toString() {
		return "Segmento di estremi: " + p1 + " " + p2;
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Linea)) return false;
		if (o == this) return true;
		Linea l = (Linea)o;
		return p1.equals(l.p1) && p2.equals(l.p2);
	} // equals
	public int hashCode() {
		final int PRIMO = 43;
		return p1.hashCode() * PRIMO + p2.hashCode();
	} // hashCode
} // Linea
