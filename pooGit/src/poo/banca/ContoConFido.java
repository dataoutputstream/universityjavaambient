package poo.banca;

public class ContoConFido extends ContoBancario {
	private double fido = 1000, scoperto;
	public ContoConFido(String numero) {
		super(numero);
	} // Costruttore 1
	public ContoConFido(String numero, double bilancio) {
		super(numero, bilancio);
	} // Costruttore 2
	public ContoConFido(String numero, double bilancio, double fido) {
		super(numero, bilancio);
		if (fido < 0) throw new IllegalArgumentException();
		this.fido = fido;
	} // Costruttore 3
	@Override public void deposita(double quanto) {
		if (quanto <= 0) throw new IllegalArgumentException();
		if (quanto <= scoperto) { scoperto -= quanto; return; }
		double residuo = quanto - scoperto;
		scoperto = 0;
		super.deposita(residuo);
	} // deposita
	@Override public boolean preleva(double quanto) {
		if (quanto <= 0) throw new IllegalArgumentException();
		double disp = saldo();
		if (quanto <= disp) {
			super.preleva(quanto); return true;
		}
		if (quanto <= disp + fido - scoperto) {
			double residuo = quanto - disp;
			super.preleva(disp);
			scoperto += residuo;
			return true;
		}
		return false;
	} // preleva
	public double getFido() { return fido; }
	public void setFido(double fido) {
		if (fido <= 0) throw new IllegalArgumentException();
		this.fido = fido;
	} // setFido
	public double getScoperto() { return scoperto; }
	public String toString() {
		return super.toString() + String.format(" Fido = E %1.2f Scoperto = E %1.2f", fido, scoperto);
	} // toString
} // ContoConFido
