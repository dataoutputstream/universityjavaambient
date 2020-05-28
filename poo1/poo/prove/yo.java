package prove;

import java.util.LinkedList;
import java.util.ListIterator;

public class yo {
	public static void main(String...args){
		LinkedList<Integer>ll=new LinkedList<>();
		
		ListIterator<Integer> lis=ll.listIterator();
		lis.add(20);
		lis.add(21);
		System.out.println(lis.hasNext());
		System.out.println(lis.next());
		System.out.println(ll.toString());
	}
}
