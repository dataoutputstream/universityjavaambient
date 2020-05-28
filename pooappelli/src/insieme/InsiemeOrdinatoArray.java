package insieme;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class InsiemeOrdinatoArray<T extends Comparable<? super T>> extends InsiemeOrdinatoAstratto<T> {

	private T[] array;
	Comparator<T>c;
	int size=0;
	
	public InsiemeOrdinatoArray(int size) {
		array=(T[]) new Object[size];
	}
	public InsiemeOrdinatoArray(Comparator<T>c,int size) {
		array=(T[]) new Object[size];
		this.c=c;
		
	}
	
	public int size() {
		return size;
	}
	
	
	public void add(T x) {
		if(x==null)throw new RuntimeException();
		if(size==0) {array[size]=x; size++;return;}
		if(c==null && !this.contains(x)) {
			int i=0;
			while(i<size) {
				if(array[i].compareTo(x)>0) {
					if(array.length==size) {array=Arrays.copyOf(array, array.length*2);}
					for(int j=size;j>i;j--) {
						array[j]=array[j-1];
					}
					array[i]=x;
					size++;
					break;
				}
				i++;
			}
			
		}else if(!this.contains(x)) {
			int i=0;
			while(i<size) {
				if(c.compare(array[i],x)>0) {
					if(array.length==size) {array=Arrays.copyOf(array, array.length*2);}
					for(int j=size;j>i;j--) {
						array[j]=array[j-1];
					}
					array[i]=x;
					size++;
					break;
				}
				i++;
			}
		}

	}

	@Override
	public void addAll(Insieme<T> i) {
		for(T e:this) {
			this.add(e);
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new Iteratore();
	}

	@Override
	public InsiemeOrdinato<T> headSet(T x) {
		InsiemeOrdinato<T> i =new InsiemeOrdinatoArray<T>(size);
		for(T e:this) {
			if(e.compareTo(x)<0) {
				i.add(e);
			}break;
		}
		return i;
	}

	@Override
	public InsiemeOrdinato<T> tailSet(T x) {
		InsiemeOrdinato<T> i =new InsiemeOrdinatoArray<T>(size);
		for(T e:this) {
			if(e.compareTo(x)>0) {
				i.add(e);
			}break;
		}
		return i;
	}
	
	private class Iteratore implements Iterator<T>{
		int cor=-1;
		boolean rimuovibile=false;

		@Override
		public boolean hasNext() {
			return cor+1<size;
		}

		@Override
		public T next() {
			if(!hasNext())throw new RuntimeException();
			cor++;
			rimuovibile=true;
			return array[cor];
		}
		@Override
		public void remove() {
			if(!rimuovibile)throw new RuntimeException("Manca La Next");
			size--;
			rimuovibile=false;
			for(int j=cor;j<size;j++) {
				array[j]=array[j+1];
			}
		}
	}

}
