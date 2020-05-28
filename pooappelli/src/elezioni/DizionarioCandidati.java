package elezioni;

public interface DizionarioCandidati extends Iterable<String>{
	public void add( String cand );
	public boolean eliminato( String cand );
	public void elimina( String cand );
	public int getNumeroCandidati();
	public boolean contieneCandidato( String cand );
}//DizionarioCandidati
