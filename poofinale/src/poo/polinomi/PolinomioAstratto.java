package poo.polinomi;
import java.util.*;

public abstract class PolinomioAstratto implements Polinomio{
	public int size(){
		int c=0;
		for( Monomio m: this ) c++;
		return c;
	}//size
	public abstract void add( Monomio m );

	protected abstract Polinomio create();

	public Polinomio add( Polinomio p ){
		Polinomio somma=create();
		for( Monomio m: this ) somma.add(m);
		for( Monomio m: p ) somma.add(m);
		return somma;
	}//add
	public Polinomio mul( Polinomio p ){
		Polinomio prodotto=create();
		for( Monomio m: this )
			prodotto=prodotto.add( p.mul(m) );
		return prodotto;
	}//mul
	public Polinomio mul( Monomio m ){
		Polinomio prodotto=create();
		for( Monomio m1: this )
			prodotto.add( m1.mul( m ) );
		return prodotto;
	}//mul
	public Polinomio derivata(){
		//TODO come esercizio
		return null; //per ora
	}//derivata
	public double valore( double x ){
		//TODO come esercizio
		return 0;//per ora
	}//valore

	public String toString(){
		StringBuilder sb=new StringBuilder();
		Iterator<Monomio> it=this.iterator();
		boolean flag=true;
		while( it.hasNext() ){
			Monomio m=it.next();
			if( m.getCoeff()>0 && !flag ) sb.append('+');
			sb.append( m );
			if( flag ) flag=!flag;
		}
		return sb.toString();
	}//toString

	public boolean equals( Object o ){
		if( !(o instanceof Polinomio) ) return false;
		if( o==this ) return true;
		Polinomio p=(Polinomio)o;
		if( this.size()!=p.size() ) return false;
		Iterator<Monomio> it=this.iterator();
		for( Monomio m: p){
			Monomio q=it.next();
			if( m.getCoeff()!=q.getCoeff() ||
				m.getGrado()!=q.getGrado() ) return false;
		}
		return true;
	}//equals
	public int hashCode(){
		int p=17, hash=0;
		for( Monomio m: this ){
			int hc=(String.valueOf(m.getCoeff())+String.valueOf(m.getGrado())).hashCode();
			hash=hash*p+hc;
		}
		return hash;
	}//hashCode
}//PolinimoAstratto