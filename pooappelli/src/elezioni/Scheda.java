package elezioni;

import java.util.*;
public class Scheda implements Iterable<String>{
	
private LinkedList<String> preferenze=new LinkedList<String>();

	public void add( String cand ){
		preferenze.add(cand);
	}//add
	
	public void remove( String cand ){
		preferenze.remove( cand );
	}//remove
	
	public boolean equals( Object x ){
		if( !(x instanceof Scheda ) ) return false;
		if( x==this ) return true;
		Scheda s=(Scheda)x;
		return preferenze.equals( s.preferenze );
	}//equals
	
	public int hashCode(){
		return preferenze.hashCode();
	}//hashCode
	
	public String toString(){
		return preferenze.toString();
	}//toString
	
	public Iterator<String> iterator(){
		return preferenze.iterator();
	}//iterator
	
}//Scheda
