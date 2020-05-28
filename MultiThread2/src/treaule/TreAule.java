package treaule;

public abstract class TreAule {
	
	 int ultimAula=-1;
	
	int aggiornaAula() {
		ultimAula=(ultimAula+1)%3;
		while(aule[ultimAula]==0) {
			ultimAula=(ultimAula+1)%3;
		}
		return ultimAula;
	}
	
	public int[]aule= {80,60,40};
	
	public abstract int accedi() throws InterruptedException;

	public abstract void esci(int aula) throws InterruptedException;

	public abstract void chiamaStudente() throws InterruptedException;

}
