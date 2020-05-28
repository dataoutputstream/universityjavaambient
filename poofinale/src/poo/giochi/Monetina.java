package poo.giochi;

public class Monetina{
	public enum Esiti {TESTA, CROCE};
	private Esiti faccia;
	public Monetina(){ lancia(); }
	public void lancia(){
		faccia=(Math.random()<0.5) ? Esiti.TESTA : Esiti.CROCE;
	}
	public Esiti getFaccia(){ return faccia; }
	public String toString(){
		return (faccia==Esiti.TESTA)? "testa":"croce";
	}
}//Monetina
