package linkedlist;

import java.util.Iterator;
import java.util.ListIterator;

public class main {

	public static void main(String...args){
		LinkedList<Integer>ll=new LinkedList<>();
	

		
		
		ListIterator<Integer> lis0=ll.listIterator();
		ll.add(1);
		ll.add(3);
		ll.add(7);
		lis0.add(10);
		ll.add(2);
		ll.add(4);
		lis0.add(31);
		ll.add(5);
		System.out.println(lis0.previous());
		System.out.println(lis0.previous());
		System.out.println(lis0.next());
		
		lis0.set(11);
	
		
		System.out.println(ll.size());
		System.out.println(lis0.next());
		System.out.println(lis0.next());
		System.out.println(lis0.previousIndex());
		System.out.println(lis0.nextIndex());
		System.out.println(lis0.next());
		System.out.println(lis0.next());	
		System.out.println(lis0.next());
	
		
		System.out.println(lis0.previous());
		System.out.println(lis0.previous());
		System.out.println(lis0.previous());
		System.out.println(lis0.previous());
		System.out.println(lis0.previous());
		

		System.out.println(lis0.previousIndex());
		System.out.println(lis0.nextIndex());
		System.out.println(ll.toString());
		ListIterator<Integer> lis=ll.listIterator(1);
		System.out.println(lis.nextIndex());
		Integer u =lis.next();
		System.out.println(u);
		u=lis.previous();
		System.out.println(u);
		ll.clear();
		Iterator<Integer>it=ll.iterator();
		System.out.println(ll.toString());
		System.out.println(it.hasNext());
		

		
	}
}
