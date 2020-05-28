package poo.grafo;

public class ArcoPesato<N> extends Arco<N> {
	private Peso peso;
	public ArcoPesato(N u, N v) { super(u, v); }
	public ArcoPesato(N u, N v, Peso p) {
		super(u, v); peso = p;
	} // Costruttore
	public Peso getPeso() { return peso; }
	public void setPeso(Peso p) { peso = p; }
	public String toString() {
		return "<" + super.toString() + ", " + peso + ">";
	} // toString
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (!(o instanceof ArcoPesato)) return false;
		if (o == this) return true;
		ArcoPesato<N> a = (ArcoPesato)o;
		return super.equals(a) && peso.equals(a.peso);
	} // equals
	public int hashCode() {
		final int MOLT = 811;
		return super.hashCode() * MOLT + peso.hashCode();
	} // hashCode
} // ArcoPesato
