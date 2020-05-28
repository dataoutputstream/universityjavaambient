package insieme;

import java.util.Iterator;

public class InsiemeConcatenato<T> extends InsiemeAstratto<T> {
	
	Nodo<T>testa;
	int size;
	
	
	private static class Nodo<T>{
		T info;
		Nodo<T>next;
	}

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
		if(!this.contains(x)) {
		Nodo<T>nuovo=new Nodo<>();
		nuovo.info=x;
		if(testa==null) {
			testa=nuovo;
		}else {
			Nodo<T>pre,cor=new Nodo<T>();
			cor=testa;
			while(cor.next!=null) {
				pre=cor;
				cor=cor.next;
				
			}
		cor.next=nuovo;
			
		}size++;
		}
	}

	@Override
	public void addAll(Insieme<T> i) {
		for(T e:i) {
			add(e);
		}
		
	}
	
	private class Iteratore implements Iterator<T>{
		Nodo<T>cor,pre;
		boolean rimuovibile=false;

		@Override
		public boolean hasNext() {
			if(cor==null)return testa!=null;
			
			return cor.next!=null;
		}

		@Override
		public T next() {
			if(!hasNext())throw new RuntimeException();
			rimuovibile=true;
			if(cor==null) {
				cor=testa;
				return cor.info;
			}else {
				pre=cor;
				cor=cor.next;
			}
			return cor.info;
		}
		@Override
		public void remove() {
			if(!rimuovibile)throw new RuntimeException();
			rimuovibile=false;
			pre.next=cor.next;
			size--;
			
		}
	}

}
