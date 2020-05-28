package elezioni;

import java.util.Iterator;

public abstract class ElezioneAstratta implements Elezione{
	
	public abstract void addScheda( Scheda s );
	public abstract void removeScheda( Scheda s );
	public abstract Conteggio conta();
	public abstract Iterator<Scheda> iterator();
	
	public String toString(){
		StringBuilder sb=new StringBuilder(500);
		for( Scheda s: this ){
			sb.append( s );
			sb.append('\n');
		}
		return sb.toString();
	}//toString
}//ElezioneAstratta
