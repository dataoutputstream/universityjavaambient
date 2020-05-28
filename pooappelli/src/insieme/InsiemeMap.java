package insieme;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class InsiemeMap<T> implements Insieme<T> {
	
	private Map<Integer,T> m=new TreeMap<Integer,T>();
	int size=0;

	@Override
	public Iterator<T> iterator() {
		return new Iteratore();
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(T x) {
		if(!m.containsValue(x)) {
			m.put(size, x);
			size++;
		}

	}

	@Override
	public void addAll(Insieme<T> i) {
		for(T e:i) {
			this.add(e);
		}
	}

	
	private class Iteratore implements Iterator<T>{
		int i=-1;

		@Override
		public boolean hasNext() {
			if(i==-1)return size!=0;
			return i+1<size;
		}

		@Override
		public T next() {
			
			return null;
		}
		@Override
		public void remove() {
			
		}
	}
}
