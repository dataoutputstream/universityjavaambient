package linkedlist;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<T> extends Iterable<T> {
	@SuppressWarnings("unused")
	default int size(){
		int c=0;
		for(T x:this){
			c++;
		}
		return c;
	}//size
	
	 default void clear() {
			Iterator<T> it = iterator();
			while (it.hasNext()) {
				it.next(); it.remove();
			}
	}//clear
	
	default boolean contains(T e){
		for(T x:this){
			if(x.equals(e))return true;
		}
		
		return false;	
	}//contains;
	
	default boolean isEmpty(){
		if(this.size()==0)return true;
		return false;
	}//isEmpty
	
	void add(T e);
	
	default void remove(T e){
		ListIterator<T> lis=this.listIterator();
		while(lis.hasNext()){
			T x=lis.next();
			if(e.equals(x)){lis.remove();break;}
		}
	}//remove
	ListIterator<T> listIterator();
	ListIterator<T> listIterator(int pos);
	void addFirst(T e);
	void addLast(T e);
	T removeFirst();
	T removeLast();
	T getFirst();
	T getLast();
	void sort(Comparator<T> c);

}
