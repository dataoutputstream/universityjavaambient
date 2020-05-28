package poo.banca;

public class ContoBancario {
	private String numero;
	private double bilancio;
	public ContoBancario(String numero) {
		this.numero = numero;
	} // Costruttore 1
	public ContoBancario(String numero, double bilancio) {
		if (bilancio <= 0) throw new IllegalArgumentException();
		this.numero = numero; this.bilancio = bilancio;
	} // Costruttore 2
	public String conto() { return numero; }   // Metodi
	public double saldo() { return bilancio; } // getter
	public void deposita(double quanto) {
		if (quanto <= 0) throw new IllegalArgumentException();
		bilancio += quanto;
	} // deposita
	public boolean preleva(double quanto) {
		if (quanto <= 0) throw new IllegalArgumentException();
		if (quanto > bilancio) return false;
		bilancio -= quanto;
		return true;
	} // preleva
	public String toString() {
		return numero + " E " + String.format("%1.2f", bilancio);
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof ContoBancario)) return false;
		if (o == this) return true;
		ContoBancario cb = (ContoBancario)o;
	 	return numero.equals(cb.numero);
	} // equals
	public int hashCode() {
		return numero.hashCode();
	} // hashCode
} // ContoBancario
