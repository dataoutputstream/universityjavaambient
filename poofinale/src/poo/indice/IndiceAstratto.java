package poo.indice;

import java.util.Iterator;

public abstract class IndiceAstratto implements Indice {
	public int size(){
		int c=0;
		for( Iterator<Parola> it=this.iterator(); it.hasNext(); it.next(), c++ );
		return c;
	}//size
	public int occorrenze( String ortografia ){
		Parola orto=new Parola( ortografia );
		for( Parola p: this ){
			if( p.equals(orto) ) return p.size();
			if( p.compareTo(orto)>0 ) return 0;
		}
		return 0;
	}//occorrenze
	public String toString(){
		StringBuilder sb=new StringBuilder( 400 );
		for( Parola p: this )
			sb.append(p);
		return sb.toString();
	}//toString
	public boolean equals( Object o ){
		if( !(o instanceof Indice) ) return false;
		if( o==this ) return true;
		Indice ix=(Indice)o;
		if( this.size()!=ix.size() ) return false;
		Iterator<Parola> i1=this.iterator(), i2=ix.iterator();
		while( i1.hasNext() ){
			Parola p1=i1.next(), p2=i2.next();
			if( !p1.equals(p2) ) return false;
		}
		return true;
	}//equals
	public int hashCode(){
		final int MOLT=43;
		int h=0;
		for( Parola p: this ) h=h*MOLT+p.hashCode();
		return h;
	}//hashCode
}//IndiceAstratto
