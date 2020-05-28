package insieme;

import java.util.Iterator;

public class InsiemeArray<T> extends InsiemeAstratto<T>{
	
	public InsiemeArray(int n) {
		adt=(T[])new Object[n];
	}
	public InsiemeArray(Insieme<T>i) {
		this.addAll(i);
	}

	T[]adt;
	int size=0;
	
	@Override
	public int size() {
		return size;
	}
	@Override
	public Iterator<T> iterator() {
		return new Iteratore();
	}

	@Override
	public void addAll(Insieme<T> i) {
		for(T e:i) {
			this.add(e);
		}
		
	}

	private class Iteratore implements Iterator<T>{
		int i=-1;
		boolean rimuovibile=false;

		@Override
		public boolean hasNext() {
				return i+1<size;

		}

		@Override
		public T next() {
			if(!hasNext())throw new RuntimeException();
			i++;
			rimuovibile=true;
			return adt[i];
		}
		
		@Override
		public void remove() {
			if(!rimuovibile)throw new RuntimeException("Manca La Next");
			size--;
			rimuovibile=false;
			for(int j=i;j<size;j++) {
				adt[j]=adt[j+1];
			}
			
		}

		
		
	}

	@Override
	public void add(T x) {
		adt[size]=x;
		size++;
		
	}

}
