package elezioni;

public interface Elezione extends Iterable<Scheda>{
	
	public void addScheda( Scheda s );
	public void removeScheda( Scheda s );
	public Conteggio conta();
}//Elezione