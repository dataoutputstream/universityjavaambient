package poo.linkedlist;

import java.util.Comparator;
import java.util.ListIterator;

public class Main {

	public static void main(String[] args){
		
		LinkedList<Integer>ll=new LinkedList<>();
		ll.addLast(3);
		ll.addFirst(2);
		ll.addLast(1);
		System.out.println(ll);
		System.out.println("La LL contiene (2) ? "+ll.contains(2));
		System.out.println("Il primo elemento è:"+ll.getFirst());
		System.out.println("L'ultimo elemento è:"+ll.getLast());
		ll.remove(1);
		System.out.println(ll);
		
		System.out.println("LISTITERATOR");
		ListIterator<Integer>lis=ll.listIterator();
		System.out.println("L'indice precedente è: "+lis.previousIndex());
		System.out.println("L'indice successivo dell'elemento che verra restituito è: "+lis.nextIndex());
		System.out.println("Il primo elemento è: "+lis.next());
		System.out.println("L'indice precedente è: "+lis.previousIndex());
		System.out.println("L'indice successivo è: "+lis.nextIndex());
		System.out.println("Il primo elemento è sempre : "+lis.previous());
		System.out.println("L'indice precedente è: "+lis.previousIndex());
		System.out.println("L'indice successivo è: "+lis.nextIndex());
		lis.add(7);
		lis.add(6);
		System.out.println(ll.toString());
		System.out.println("Il successivo è sempre: "+lis.next());
		System.out.println("next(): "+lis.next());
		lis.add(6);
		System.out.println("aggiunto 6 ed è previous(): "+lis.previous());
		System.out.println("next(): "+lis.next());
		System.out.println(ll.toString());
		System.out.println("L'indice precedente è: "+lis.previousIndex());
		System.out.println("L'indice successivo è l'ultimo quindi deve essere size() : "+lis.nextIndex());
		lis.previous();
		lis.set(9);
		System.out.println("previous()+set(9) " +ll.toString() );
		lis.next();
		lis.set(11);
		System.out.println("next()+set(11) " +ll.toString() );
		
		System.out.println("ITERATORE CHE PARTE DA POS 2");
		ListIterator<Integer>lispos=ll.listIterator(2);
		System.out.println("next(): "+lispos.next());
		lispos.remove();
		System.out.println("lispos.remove()");
		System.out.println("Sort dell'array:");
		class Comparatore implements Comparator<Integer>{

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}
			
		}
		Comparator<Integer>c=new Comparatore();
		ll.sort(c);
		System.out.println(ll);
		
	
		
		
		

		
	}
}
