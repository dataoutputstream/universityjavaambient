package linkedlist;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class LinkedList<T> extends AbstractList<T>{
	private static class Nodo<E>{
		E info;
		Nodo<E> next, prior;
	}//Nodo

	
	private Nodo<T> testa=null;
	private int size;
	
	@Override
	public void add(T e) {
		
		Nodo<T>nuovo=new Nodo<T>();
		nuovo.info=e;
		if(testa==null) {
			nuovo.prior=null;
			nuovo.next=testa;
			testa=nuovo;
		}else{
			Nodo<T> cor=testa.next,pre=testa;		
			while(cor!=null) {
				pre=cor;
				cor=cor.next;
			}
			nuovo.next=cor;
			nuovo.prior=pre;
			pre.next=nuovo;
			size++;
		}//if
	}//add

	@Override
	public void addFirst(T e) {
		Nodo<T>nuovo=new Nodo<T>();
		nuovo.info=e;
			nuovo.prior=null;
			nuovo.next=testa;
			testa=nuovo;
			size++;

	}//addFirst

	@Override
	public void addLast(T e) {
		add(e);
	}//addLast

	

	@Override
	public T removeFirst() {
		if(testa==null)throw new NoSuchElementException();
		T ret;
		ret=testa.info;
		if(testa.next!=null)testa=testa.next;
		else {	
			testa=null;		
		}
		size--;
		return ret;	
	}//removeFirst
	@Override
	public T removeLast() {
		if(testa==null)throw new NoSuchElementException();
		Nodo<T> cor=testa.next,pre=testa;		
		while(cor!=null) {
			pre=cor;
			cor=cor.next;
		}
		T ret=pre.info;
		pre.prior.next=null;
		size--;
		return ret;
	}//removeLast

	@Override
	public T getFirst() {
		if(testa==null)throw new NoSuchElementException();
		return testa.info;
	}//getFirst

	@Override
	public T getLast() {
		if(testa==null)throw new NoSuchElementException();
		Nodo<T> cor=testa.next,pre=testa;	
		while(cor!=null) {
			pre=cor;
			cor=cor.next;
		}//while
		return pre.info;
	
	}//getLast
	
	@Override
	public void sort(Comparator<T> c) {
		Nodo<T> cor=testa;
		for(int i=size()-1;i>=1;i--){
			int scambi = 0;
			for(int j=0;j<i;j++){
				T info1=cor.info;
				T info2=cor.next.info;
				if(c.compare(info1, info2)>=1){
					T aus=cor.info;
					cor.info=cor.next.info;
					cor.next.info=aus;
					scambi++;
				}
				
				if(j!=i-1)cor=cor.next;
				else cor=testa;
			}
			if(scambi==0)break;
				
		}
	}//sort
	
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder(100);
		Nodo<T> cor=testa;
		sb.append('[');
		while( cor!=null ){
			sb.append( cor.info );
			if( cor.next!=null ) sb.append(", ");
			cor=cor.next;
		}
		sb.append(']');
		return sb.toString();
	}//toString

	@Override
	public ListIterator<T> iterator() {
		return new LinkedListIterator(0);
	}//iterator
	
	@Override
	public void clear(){
		size=0;testa=null;
	}//clear

	public enum stato {
		FORWARD, BACKWARD, UNKNOWN;

	}//enum
	
	private class LinkedListIterator implements ListIterator<T> {
		private Nodo<T> cor=null;
		int index=-1;
		stato s=null;
		private boolean rimuovibile=false;
		int previous=-1,next;
		
		public LinkedListIterator(int pos) {
			if(pos==0){return;}
			int cont = 0;
			if(cor==null && hasNext()) {
				cor=testa;
				indiceup();
				cont++;
				while(cont!=pos) {
					cor=cor.next;
					indiceup();
					cont++;
				}//while
			}if(cont!=pos)throw new RuntimeException();  
		}//LinkedListIterator(int pos) 

		private void indiceup() {
			index++;
			previous=index;
			next=index+1;
			
		}//indiceup

		@Override
		public void add(T arg0) {
			s=stato.UNKNOWN;
			indiceup();
			size++;
			Nodo<T>nuovo=new Nodo<>();
			nuovo.info=arg0;
			if(cor==null && testa==null) {testa=nuovo;cor=nuovo;return;}
			if(cor==null && testa!=null) {nuovo.next=testa;testa.prior=nuovo;testa=nuovo;cor=nuovo;return;}
			if(cor.next==null) {cor.next=nuovo;nuovo.prior=cor;cor=nuovo;return;}
			nuovo.prior=cor;
			nuovo.next=cor.next;
			if(cor.next!=null)cor.next.prior=nuovo;
			cor.next=nuovo;
			cor=nuovo;
			
		}//add

		@Override
		public boolean hasNext(){
			if( cor==null ) return testa!=null;
			return cor.next!=null;
		}//hasNext
		
		public T next(){
			if( !hasNext() ) 
				throw new NoSuchElementException();
			rimuovibile=true;
			if( cor==null ) cor=testa;
			else {
				cor=cor.next;	
			}
			s=stato.FORWARD;
			indiceup();
			return cor.info;
		}//next
		
		public void remove(){
			if(!rimuovibile)throw new IllegalStateException();
			s=stato.UNKNOWN;
			rimuovibile=false;
			if( cor==testa ){
				testa=testa.next;
				if( testa!=null ) testa.prior=null;
			}
			else{
				cor.prior.next=cor.next;
				if( cor.next!=null )
					cor.next.prior=cor.prior;
			}
			indicedown();
			cor=cor.prior;
			size--;
		}//remove

		private void indicedown() {
			index--;
			previous=index-1;
			next=index+1;
			
		}//indicedown

		@Override
		public boolean hasPrevious() {
			if(cor.prior==null && cor!=null){
				return true;
			}
			if(cor==null)return false;
			
			return cor.prior!=null;
		}//hasPrevious

		@Override
		public int nextIndex() {
				if(index==size-1)return size;
			return next;
		}//nextIndex

		@Override
		public T previous() {
			if(!hasPrevious()) throw new NoSuchElementException();
			s=stato.BACKWARD;
			T info;
			info=cor.info;
			cor=cor.prior;
			indicedown();
			return info;
		}//previous

		@Override
		public int previousIndex() {
			if(index<=-1) {
				return -1;
			}
		return previous;
		}//previousIndex

		@Override
		public void set(T arg0) {
			if(s!=stato.UNKNOWN && s!=null) {
				if(s==stato.FORWARD){
				cor.info=arg0;
				return;
				}if(s==stato.BACKWARD){
					cor.next.info=arg0;
					return;
				}
			}
			throw new IllegalStateException();//se prima non è stata chiamata ne una next ne una previous;
		}//set

	}
	@Override
	public ListIterator<T> listIterator() {
		return new LinkedListIterator(0);
	}//listIterator

	@Override
	public ListIterator<T> listIterator(int pos) {
		return new LinkedListIterator(pos);
	}//ListIterator(int pos)

}
