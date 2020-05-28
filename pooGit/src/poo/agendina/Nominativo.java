package poo.agendina;

public class Nominativo implements Comparable<Nominativo> {
	private String cognome, nome, prefisso, telefono;
	public Nominativo(String cognome, String nome, String prefisso, String telefono) {
		this.cognome = cognome; this.nome = nome;
		this.prefisso = prefisso; this.telefono = telefono;
	} // Costruttore
	public String getCognome() { return cognome; }
	public String getNome() { return nome; }
	public String getPrefisso() { return prefisso; }
	public String getTelefono() { return telefono; }
	public int hashCode() {
		final int MOLT = 43;
		return cognome.hashCode() * MOLT + nome.hashCode();
	} // hashCode
	public boolean equals(Object o) {
		if (!(o instanceof Nominativo)) return false;
		if (o == this) return true;
		Nominativo n = (Nominativo)o;
		return cognome.equals(n.cognome) && nome.equals(n.nome);
	} // equals
	public String toString() {
		return cognome + " " + nome + " " + prefisso + "-" + telefono;
	} // toString
	public int compareTo(Nominativo n) {
		if (this.equals(n)) return 0;
		if (cognome.compareTo(n.cognome) < 0 ||
			cognome.equals(n.cognome) && nome.compareTo(n.nome) < 0) return -1;
		return 1;
	} // compareTo
} // Nominativo
