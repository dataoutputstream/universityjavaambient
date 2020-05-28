package elezioni;

import java.util.*;
public class DizionarioCandidatiMap extends DizionarioCandidatiAstratto{
	
	private Map<String,Boolean> elenco=new TreeMap<String,Boolean>();
	
	public void add( String cand ){
		elenco.put(cand,false);
	}//add
	
	public boolean eliminato( String cand ){
		if( elenco.containsKey(cand) ){
			return elenco.get(cand);
		}
		throw new RuntimeException("Candidato inesistente");
	}//eliminato
	
	public void elimina( String cand ){
		if( elenco.containsKey(cand) ){
			elenco.put(cand,true);
			return;
		}
		throw new RuntimeException("Candidato inesistente");
	}//elimina

	public int getNumeroCandidati(){
		return elenco.keySet().size();
	}//getNumeroCandidati
	
	public boolean contieneCandidato( String cand ){
		return elenco.containsKey(cand);
	}//contieneCandidato
	
	public Iterator<String> iterator(){
		return elenco.keySet().iterator();
	}//iterator
	
}//DizionarioCandidatiMap
