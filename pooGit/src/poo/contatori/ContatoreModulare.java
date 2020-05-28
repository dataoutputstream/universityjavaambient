package poo.contatori;

public class ContatoreModulare extends Contatore {
	private int modulo;
	public ContatoreModulare(int modulo) {
		if (modulo <= 0) throw new IllegalArgumentException();
		this.modulo = modulo;
	} // Costruttore normale (Utilizza il costruttore di default di Contatore
	public ContatoreModulare(int val, int modulo) {
		super(val);
		if (modulo <= 0 || val < 0 || val >= modulo)
			throw new IllegalArgumentException();
		this.modulo = modulo;
	} // Costruttore normale
	public ContatoreModulare(ContatoreModulare cm) {
		super(cm);
		modulo = cm.modulo;
	} // Costruttore di copia
	public int getModulo() { return modulo; }
	@Override public void incr() {
		val = (val + 1) % modulo;
	} // incr
	@Override public void decr() {
		val = (val - 1 + modulo) % modulo;
	} // decr
	public String toString() {
		return super.toString() + " modulo = " + modulo;
	} // toString
	public boolean equals(Object o) {
		if (!(o instanceof ContatoreModulare)) return false;
		if (o == this) return true;
		ContatoreModulare cm = (ContatoreModulare)o;
		return val == cm.val && modulo == cm.modulo;
	} // equals
	public int hashCode() {
		final int PRIMO = 43;
		return val * PRIMO + modulo;
	} // hashCode
} // ContatoreModulare
