package poo.rasp;

public class Simbolo implements Comparable<Simbolo> {
	public enum Tipo {ISTR, DATO};
	private String nome;
	private int indirizzo = -1;
	private int size = 1;
	private Tipo tipo = Tipo.ISTR;
	public Simbolo(String nome) { this.nome = nome; }
	public String getNome() { return nome; }
	public int getIndirizzo() { return indirizzo; }
	public int getSize() { return size; }
	public Tipo getTipo() { return tipo; }
	public void setIndirizzo(int indirizzo) { this.indirizzo = indirizzo; }
	public void setSize(int size) {
		if (size <= 0) throw new IllegalArgumentException();
		this.size = size;
	}
	public void setTipo(Tipo tipo) { this.tipo = tipo; }
	public int hashCode() { return nome.hashCode(); }
	public boolean equals(Object o) {
		if (!(o instanceof Simbolo)) return false;
		if (o == this) return true;
		Simbolo s = (Simbolo)o;
		return nome.equals(s.nome);
	} // equals
	public int compareTo(Simbolo s) { return nome.compareTo(s.nome); }
	public String toString() { return nome + " @ " + indirizzo + " size: " + size + " tipo: " + tipo; }
} // Simbolo
