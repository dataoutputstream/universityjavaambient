package elezioni;

import java.util.*;
public class ConteggioMap extends ConteggioAstratto{
	
	
	private Map<String,Integer> voti=new HashMap<String,Integer>();
	
	public int voti( String cand ){
		if( voti.containsKey(cand) )
			return voti.get(cand);
		throw new RuntimeException("Candidato illegale");
	}//voti
	
	public void incVoti( String cand ){
		if( voti.containsKey(cand) ){
			int v=voti.get(cand);
			v++;
			voti.put(cand,v);
			return;
		}
		throw new RuntimeException("Candidato illegale");
	}//incVoti
	
	public boolean contieneCandidato( String cand ){
		return voti.containsKey(cand);
	}//contieneCandidato
	
	public void addCandidato( String cand ){
		voti.put(cand,0);
	}//addCandidato
	
	public List<String> minoritari(){
		int min=Integer.MAX_VALUE;
		for( String cand: this ){
			int v=voti(cand);
			if( v<min ) min=v;
		}
		LinkedList<String> minori=new LinkedList<String>();
		for( String cand: this ){
			int v=voti(cand);
			if( v==min ) minori.add(cand);
		}
		return minori;
	}//daEliminare
	
	
	public boolean tuttiMinoritari(){
		List<String> min=minoritari();
		for( String cand: voti.keySet() ){
			if( !min.contains(cand) ) return false;
		}
		return true;
	}//tuttiMinoritari
	
	public String vincitore( int maggAss ){
		for( String cand: this ){
			if( voti(cand)>=maggAss ) return cand;
		}
		return null;
	}//vincitore
	
	public Iterator<String> iterator(){
		return voti.keySet().iterator();
	}//iterator
	
}//ConteggioMap