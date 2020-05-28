package elezioni;

import java.util.List;

public interface Conteggio extends Iterable<String>{
	
	public int voti( String cand );
	public void incVoti( String cand );
	public boolean contieneCandidato( String cand );
	public void addCandidato( String cand );
	public List<String> minoritari();
	public boolean tuttiMinoritari();
	public String vincitore( int maggAss );
}//Conteggio
