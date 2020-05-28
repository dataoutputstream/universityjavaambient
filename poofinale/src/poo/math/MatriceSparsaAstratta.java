package poo.math;

import java.util.Iterator;

public abstract class MatriceSparsaAstratta implements MatriceSparsa{
	
	public boolean equals( Object x ) {
		if( !(x instanceof MatriceSparsa) ) return false;
		if( x==this ) return true;
		MatriceSparsa m=(MatriceSparsa)x;
		if( this.getN()!=m.getN() ) return false;
		for( int i=0; i<this.getN(); i++ ) {
			if( rigaVuota(i) && m.rigaVuota(i) ) continue;
			if( rigaVuota(i) && !m.rigaVuota(i) ) return false;
			if( !rigaVuota(i) && m.rigaVuota(i) ) return false;
			if( sizeRiga(i)!=m.sizeRiga(i) ) return false;
			Iterator<Elemento> i1=this.riga(i).iterator(), i2=m.riga(i).iterator();
			while( i1.hasNext() && i2.hasNext() ) {
				Elemento e1=i1.next(), e2=i2.next();
				if( !e1.equals(e2) ) return false;
			}
		}
		return true;
	}//equals
	
	public int hashCode() {
		final int M=83;
		int h=0;
		for( int i=0; i<getN(); i++ ) {
			if( rigaVuota(i) ) continue;
			for( Elemento e: riga(i) )
				h=h*M+e.hashCode();
		}
		return h;
	}//hashCode
	
	public String toString() {
		StringBuilder sb=new StringBuilder(500);
		for( int i=0; i<getN(); i++ ) {
			if( rigaVuota(i) ) continue;
			for( Elemento e: riga(i) )
				sb.append(e+" ");
			sb.append("\n");
		}
		return sb.toString();
	}//toString
	
}//MatriceSparsaAstratta
