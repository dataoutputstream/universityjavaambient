package elezioni;

public class Situazione{
	
	private int schedeNulle, schedeBianche, schedeValide;
	public int getSchedeNulle(){ return schedeNulle; }
	public int getSchedeBianche(){ return schedeBianche; }
	public int getSchedeValide(){
		return schedeValide;
	}//getSchedeValide
	public void setSchedeNulle( int schedeNulle ){
		this.schedeNulle=schedeNulle;
	}//setSchedeNulle
	public void setSchedeBianche( int schedeBianche ){
		this.schedeBianche=schedeBianche;
	}//setSchedeBianche
	public void setSchedeValide( int schedeValide ){
		this.schedeValide=schedeValide;
	}//setSchedeValide
	public int maggioranzaAssoluta(){
		return (schedeNulle+schedeBianche+schedeValide)/2+1;
	}//maggioranzaAssoluta
	public String toString(){
		return "schede nulle: "+schedeNulle+"\n"+
	"schede bianche: "+schedeBianche+"\n"+
	"schede valide: "+schedeValide+"\n"+
	"maggioranza assoluta: "+maggioranzaAssoluta()+"\n";
}//toString
}//Situazione
