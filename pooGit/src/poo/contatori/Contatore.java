package poo.contatori;

public class Contatore {
	protected int val;
	public Contatore() {} // Costruttore di default
	public Contatore(int val) {
		this.val = val;
	} // Costruttore normale
	public Contatore(Contatore c) {
		val = c.val;
	} // Costruttore di copia
	public int getVal() { return val; }
	public void incr() { val++; }
	public void decr() { val--; }
	public String toString() {
		return "val = " + val;
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof Contatore)) return false;
		if (o == this) return true;
		Contatore c = (Contatore)o;
		return val == c.val;
	} // equals
	public int hashCode() {
		return val;
	} // hashCode
} // Contatore
