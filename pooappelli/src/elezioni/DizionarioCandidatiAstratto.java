package elezioni;

abstract class DizionarioCandidatiAstratto implements DizionarioCandidati{
	
	
	public int getNumeroCandidati(){
		int n=0;
		for( String cand: this ) n++;
		return n;
	}//getNumeroCandidati
	
	public boolean contieneCandidato( String cand ){
		for( String cnd: this )
			if( cnd.equals(cand) ) return true;
		return false;
	}//contieneCandidato
	
	public String toString(){
		StringBuilder sb=new StringBuilder(500);
		for( String cand: this ){
			sb.append( cand );
			if( eliminato(cand) ){
				sb.append(' ');
				sb.append("Eliminato");
			}
			sb.append('\n');
		}
		return sb.toString();
	}//toString
	
}//DizionarioCandidatiAstratto
