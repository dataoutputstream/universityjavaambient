package poo.indice;
import java.util.*;
public class Parola implements Comparable<Parola>{
	private String ortografia;
	private Set<Integer> elenco=new TreeSet<Integer>();
	public Parola( String ortografia ){ this.ortografia=ortografia; }
	public void add( int nr ){
		elenco.add( nr );
	}//add
	public int size(){ return elenco.size(); }
	public String getOrtografia(){ return ortografia; }
	public boolean equals( Object o ){
		if( !(o instanceof Parola) ) return false;
		if( o==this ) return true;
		Parola p=(Parola)o;
		return ortografia.equals( p.ortografia );
	}//equals
	public int compareTo( Parola p ){
		if( ortografia.length()<p.ortografia.length() ||
		    ortografia.length()==p.ortografia.length() &&
		    ortografia.compareTo(p.ortografia)<0 ) return -1;
		if( this.equals(p) ) return 0;
		return +1;
	}//compareTo
	public String toString(){
		String s=ortografia+"\n";
		Iterator<Integer> i=elenco.iterator();
		while( i.hasNext() ){
			s+=i.next()+" ";
		}
		s+="\n";
		return s;
	}//toString
	public int hashCode(){
		return ortografia.hashCode();
	}//hashCode
}//Parola