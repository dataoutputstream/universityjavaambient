package statisticha_fp;

import java.util.HashMap;
import java.util.Map;

public class StatisticaMap implements Statistica{
	HashMap<String,Integer>fp=new HashMap<String,Integer>();
	private Map<String,Map<String,Integer>> fpq=new HashMap<>();
	@Override
	public void arrivoParola(String p) {
		if(!fp.containsKey(p)) {
			fp.put(p,0);
			fpq.put(p, new HashMap<>());
		}
		fp.put(p, fp.get(p)+1);
	}
	public void paroleConsecutive( String p, String q ){
		if( !fp.containsKey(p) || !fp.containsKey(q) )
			throw new RuntimeException("parole "+p+" e/o "+q+" assenti");
		Map<String,Integer> pad=fpq.get(p);
			if( !pad.containsKey(q) ) pad.put(q,0);
				pad.put( q, pad.get(q)+1 );
	}//paroleConsecutive
	
		public int frequenza( String p ){
			if( !fp.containsKey(p) ) return 0;
		return fp.get(p);
		}//frequenza
		
		public int frequenzaCoppia( String p, String q ){
			if( !fp.containsKey(p) || !fp.containsKey(q) ) return 0;
			Map<String,Integer> pad=fpq.get(p);
			if( !fpq.containsKey(q) ) return 0;
		return pad.get(q);
		}//frequenzaCoppia
		
		public String parolaCheSeguePiuFrequente( String target ){
			if( !fp.containsKey(target) ) throw new RuntimeException(target+" inesistente");
			Map<String,Integer> adiacenti=fpq.get(target);
			String ppf=null;
			int max=0;
			for( String p: adiacenti.keySet() )
				if( adiacenti.get(p)>max ){ ppf=p; max=adiacenti.get(p); }
			return ppf;
		}//parolaCheSeguePiuFrequente
		
		public String parolaCheSegueMenoFrequente( String target ){
			if( !fp.containsKey(target) ) throw new RuntimeException(target+" inesistente");
			Map<String,Integer> adiacenti=fpq.get(target);
			String pmf=null;
			int min=Integer.MAX_VALUE;
			for( String p: adiacenti.keySet() )
				if( adiacenti.get(p)<min ){ pmf=p; min=adiacenti.get(p); }
			return pmf;
		}//parolaCheSegueMenoFrequente
		
		@Override
		public int numTotaleParole() {
			Integer numparole=0;
			for(String p:fp.keySet()) {
				numparole+=(fp.get(p));
			}
			return numparole;
		}

}
