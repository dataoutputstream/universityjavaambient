package poo.geometria;

public class Poligono implements FiguraPiana {
	private Punto vertici[];
	private double lati[];
	public Poligono(Punto[]v) {
		if (v.length < 3) {
			System.out.println("Poligono inesistente!");
			System.exit(-1);
		}
		// Si assume che l'insieme di punti sia ordinato e formi un poligono convesso
		vertici = new Punto[v.length];
		lati = new double[v.length];
		for (int i = 0; i < v.length; i++) {
			vertici[i] = new Punto(v[i]);
			lati[i] = v[i].distanza(v[(i < v.length - 1 ? i + 1 : 0)]);
		}
	} // Costruttore normale
	public Punto[] getVertici() {
		Punto v[] = new Punto[vertici.length];
		for (int i = 0; i < v.length; i++)
			v[i] = new Punto(vertici[i]);
		return v;
	} // getVertici
	public double[] getLati() {
		double l[] = new double[lati.length];
		for (int i = 0; i < l.length; i++)
			l[i] = lati[i];
		return l;
	} // getLati
	public double perimetro() {
		double p = 0;
		for (int i = 0; i < lati.length; i++)
			p += lati[i];
		return p;
	} // perimetro
	public double area() {
		double a = 0;
		for (int i = 1; i < vertici.length - 1; i++)
			a += (new Triangolo(vertici[0], vertici[i], vertici[i+1])).area(); // Qui si ha accumulo di garbage dovuto alla creazione di oggetti Triangolo
		return a;
	} // area
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Poligono di vertici:");
		for (int i = 0; i < vertici.length; i++)
			s.append(" " + vertici[i]);
		return s.toString();
	}
	public static void main(String[]args) {
		Punto vertici[] = { new Punto(2, 3), new Punto(2, -2), new Punto(-4, -2), new Punto(-4, 3) };
		Poligono p = new Poligono(vertici);
		System.out.println(p + " | Area: " + p.area() + " | Perimetro: " + p.perimetro());
	} // main
} // Poligono
