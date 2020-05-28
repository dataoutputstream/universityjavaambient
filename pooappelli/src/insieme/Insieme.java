package insieme;

import java.util.Iterator;

public interface Insieme<T> extends Iterable<T> {
	
	default int size() {
		int s = 0;
		for(T e :this) {
			s++;
		}
		return s;
		
	}
	default void clear() {
		Iterator<T> it=this.iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}
	default boolean contains(T x) {
		for(T e:this) {
			if(e.equals(x));
			return true;
		}
		return false;
	}
	
	void add(T x) ;
	
	default void remove(T x) {
		Iterator<T> it=this.iterator();
		while(it.hasNext()) {
			if(it.next().equals(x))
			it.remove();
		}
		
	}
	void addAll( Insieme<T> i );
	default void removeAll( Insieme<T> i ) {
		for(T x:i) {
			if(this.contains(x))this.remove(x);
		}
	}
	default void retainAll( Insieme<T> i ) {
		for(T x:this) {
			if(!i.contains(x))this.remove(x);
		}
	}

}
