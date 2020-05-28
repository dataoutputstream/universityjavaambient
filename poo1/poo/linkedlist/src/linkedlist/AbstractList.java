package linkedlist;

import java.util.Iterator;


public abstract class AbstractList<T> implements List<T> {
	
	public String toString(){
		StringBuffer sb=new StringBuffer(100);
		sb.append('[');
		Iterator<T> it=this.iterator();
		while( it.hasNext() ){
			sb.append( it.next() );
			if( it.hasNext() ) sb.append(',');
		}
		sb.append(']');
		return sb.toString();
	}
	
	public boolean equals( Object o ){
		if( !(o instanceof List) ) return false;
		if( o==this ) return true;
		List<T> s=(List)o;
		if( s.size()!=this.size() ) return false;
		Iterator<T> it1=this.iterator();
		Iterator<T> it2=s.iterator();
		while( it1.hasNext() ){
			T e1 = it1.next();
			T e2 = it2.next();
			if( !e1.equals(e2) ) return false;
		}//while
		return true;
	}//equals	
	
	public int hashCode(){
		int p=43, h=0;
		for( T e: this )
			h=h*p+e.hashCode();
		return h;	
	}//hashCode

}
