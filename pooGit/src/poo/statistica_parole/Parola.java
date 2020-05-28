package poo.statistica_parole;

public class Parola implements Comparable<Parola> {
	private String parola;
	private int frequenza;
	public Parola(String parola) {
		this.parola = parola;
		frequenza = 1;
	} // Costruttore
	public String getParola() { return parola; }
	public int getFrequenza() { return frequenza; }
	public void setFrequenza(int frequenza) {
		this.frequenza = frequenza;
	} // setFrequenza
	public String toString() {
		return parola;
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Parola)) return false;
		if (o == this) return true;
		Parola p = (Parola)o;
		return this.parola.equals(p.parola);
	} // equals
	public int hashCode() {
		return parola.hashCode();
	} // hashCode
	public int compareTo(Parola p) {
		return this.parola.compareTo(p.parola);
	} // compareTo
} // Parola
