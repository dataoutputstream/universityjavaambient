package elezioni;

import java.util.Iterator;
import java.util.LinkedList;

public class ElezioneLista extends ElezioneAstratta{
	
	private LinkedList<Scheda> urna = new LinkedList<Scheda>();
	private DizionarioCandidati dc;
	
	public ElezioneLista( DizionarioCandidati dc ){
		this.dc=dc;
	}
	
	public void addScheda( Scheda s ){
		urna.addLast(s);
	}//addScheda
	
	public void removeScheda( Scheda s ){
		urna.remove(s);
	}//removeScheda
	
	public Conteggio conta(){
		Conteggio c=new ConteggioMap();
		for( Scheda s: this ){
			for( String cand: s ){
				if( !dc.eliminato(cand) ){
					if( !c.contieneCandidato(cand) )
						c.addCandidato(cand);
					c.incVoti(cand);
					break;
				}
			}
		}
		return c;
	}//conta
	
public Iterator<Scheda> iterator(){
return urna.iterator();
}//iterator

}//ElezioneLista