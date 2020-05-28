package poo.math;

public class Elemento implements Comparable<Elemento>{
	private int i, j, v;
	public Elemento( int i, int j ) {
		this.i=i; this.j=j;
	}
	public Elemento( int i, int j, int v ) {
		this.i=i; this.j=j;	this.v=v;
	}
	public int getI() { return i; }
	public int getJ() { return j; }
	public int getV() { return v; }
	public void setV( int v ) {
		this.v=v;
	}
	public boolean equals( Object x ) {
		if( !(x instanceof Elemento) ) return false;
		if( x==this ) return true;
		Elemento e=(Elemento)x;
		return this.i==e.i && this.j==e.j;
	}
	public int hashCode() {
		return i*83+j;
	}
	public String toString() {
		return "["+i+","+j+"]="+v;
	}
	public int compareTo( Elemento e ) {
		if( this.i<e.i || this.i==e.i && this.j<e.j ) return -1;
		if( this.equals(e) ) return 0;
		return 1;
	}
}
