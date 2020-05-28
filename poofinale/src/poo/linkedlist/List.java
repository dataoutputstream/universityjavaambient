package poo.linkedlist;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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
	
	
	
	default void remove(T e){
		ListIterator<T> lis=this.listIterator();
		while(lis.hasNext()){
			T x=lis.next();
			if(e.equals(x)){lis.remove();return;}
		}
	}//remove
	ListIterator<T> listIterator();
	ListIterator<T> listIterator(int pos);
	
	default void addFirst(T e){
		ListIterator<T>lis=this.listIterator();
		lis.add(e);
	}//addFirst()
	
	default void add(T e){
		addLast(e);
	}//add
	
	default void addLast(T e){
		ListIterator<T>lis=this.listIterator(size());
		lis.add(e);
	}//addLast()
	
	default T removeFirst(){
		ListIterator<T>lis=this.listIterator();
		if(!lis.hasNext())throw new NoSuchElementException();
		T x=lis.next();
		lis.remove();
		
	return x;
		
	}//removeFirst
	
	default T removeLast(){
		ListIterator<T>lis=this.listIterator(size()-1);
		if(!lis.hasNext())throw new NoSuchElementException();
		T x=lis.next();
		lis.remove();
	return x;	
	}//removeLast
	
	default T getFirst(){
		ListIterator<T>lis=this.listIterator();
		if(!lis.hasNext())throw new NoSuchElementException();
			T x = lis.next();
		
		
	return x;
	}//getFirst
	
	default T getLast(){
		ListIterator<T>lis=this.listIterator(size()-1);
		if(!lis.hasNext())throw new NoSuchElementException();
		T x=lis.next();
		
	return x;
	}//getLast
	
	void sort(Comparator<T> c);

}
