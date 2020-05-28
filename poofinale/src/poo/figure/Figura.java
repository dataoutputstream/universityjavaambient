package poo.figure;
import poo.geometria.*;

public abstract class Figura implements FiguraPiana{
	private double dimensione;
	public Figura( double dimensione ){
		if( dimensione<=0 ) throw new IllegalArgumentException();
		this.dimensione=dimensione;
	}
	protected double getDimensione(){ return dimensione; }
}//Figura
