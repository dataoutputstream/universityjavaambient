package insieme;

import java.util.Iterator;

public interface InsiemeOrdinato<T extends Comparable<? super T>> extends Insieme<T> {
	
	default T first() {
		Iterator<T> it=this.iterator();
		if(it.hasNext())return it.next();
		throw new RuntimeException();
	}
	default T last() {
		Iterator<T>it=this.iterator();
		if(!it.hasNext())throw new RuntimeException();
		T ret = null;
		while(it.hasNext()) {
			ret =it.next();
		}return ret ;
	}
	InsiemeOrdinato<T>headSet(T x);
	InsiemeOrdinato<T>tailSet(T x);

}
