package poo.polinomi;

public class Monomio implements Comparable<Monomio> {
	private int coeff, grado;
	public Monomio(int coeff, int grado) {
		if (grado < 0) throw new GradoNegativo();
		this.coeff = coeff; this.grado = grado;
	} // Costruttore normale
	public Monomio(Monomio m) {
		coeff = m.coeff; grado = m.grado;
	} // Costruttore di copia
	public int getCoeff() { return coeff; }
	public int getGrado() { return grado; }
	public Monomio add(Monomio m) {
		if (!this.equals(m)) throw new MonomiNonSimili();
		return new Monomio(coeff + m.coeff, grado);
	} // add
	public Monomio mul(Monomio m) {
		return new Monomio(coeff * m.coeff, grado + m.grado);
	} // mul
	public Monomio mul(int s) {
		return new Monomio(coeff * s, grado);
	} // mul
	public int compareTo(Monomio m) {
		return m.grado - grado;
	} // compareTo
	public boolean equals(Object o) { // Similitudine
		if (!(o instanceof Monomio)) return false;
		if (o == this) return true;
		Monomio m = (Monomio)o;
		return grado == m.grado;
	} // equals
	public int hashCode() { return grado; }
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (coeff != 1 || grado == 0)
			sb.append(coeff);
		if (grado > 0) sb.append('x');
		if (grado > 1) {
			sb.append('^'); sb.append(grado);
		}
		return sb.toString();
	} // toString
} // Monomio
