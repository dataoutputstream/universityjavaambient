package trenino;

public abstract class Trenino {
	
	public final int numCabine=10;
	protected int indice=0;
	int[]Cabine=new int[numCabine];
	
	public Trenino() {
		for(int i=0;i<Cabine.length;i++) {
			Cabine[i]=10;
		}
	}
	
	public void muovi() {
		indice=(indice+1)%numCabine;
	}
	
	public abstract void turSali() throws InterruptedException;
	public abstract void turScendi()throws InterruptedException;
	public abstract void impFaiScendere()throws InterruptedException;
	public abstract void impFaiSalire()throws InterruptedException;
	public abstract void impMuovi()throws InterruptedException;

}
