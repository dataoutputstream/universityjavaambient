package poo.indice;
import java.util.*;

public class IndiceLinkato extends IndiceAstratto{
	private LinkedList<Parola> indice=new LinkedList<Parola>();
	public Iterator<Parola> iterator(){ return indice.iterator(); }
	public int size(){ return indice.size(); }
	public void add( String ortografia, int nr ){
		Parola p = new Parola( ortografia );
		p.add( nr ); //provvisorio
		ListIterator<Parola> li = indice.listIterator();
		boolean flag = false;
		while( li.hasNext() ){
			Parola q = li.next();
			if( q.equals(p) ){
				q.add( nr );
				return;
			}
			if( q.compareTo(p)>0 ){
				li.previous();
				li.add( p );
				flag=true;
				break;
			}
		}
		if( !flag ) li.add( p );
	}//add
}//IndiceLinkato