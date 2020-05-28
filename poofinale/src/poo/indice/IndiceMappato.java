package poo.indice;
import java.util.*;
public class IndiceMappato extends IndiceAstratto{
	private Map<String,Parola> indice=new TreeMap<String,Parola>();
	public Iterator<Parola> iterator(){ return indice.values().iterator(); }
	public int size(){ return indice.size(); }
	public int occorrenze( String ortografia ){
		Parola p = indice.get( ortografia );
		if( p == null ) return 0;
		return p.size();
	}//occorrenze
	public void add( String ortografia, int nr ){
		Parola p=indice.get( ortografia );
        if( p==null ){
			p=new Parola( ortografia );
            p.add( nr ); indice.put( ortografia, p );
		}
        else{
			p.add( nr );
		}
	}//add
}//IndiceMappato