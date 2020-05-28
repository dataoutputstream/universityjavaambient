package poo.fpfpq;
import java.util.*;
import java.io.*;
import poo.testo.*;
public class StatisticaMap implements Statistica{
	private Map<String,Integer> fp=new TreeMap<>();
	private Map<String,Map<String,Integer>> fpq=new HashMap<>();
	private int ntp=0;
	public void raccoltaInfo( File f ) throws IOException{
		GestoreTesto gt=new GestoreTesto(f);
		GestoreTesto.Simbolo sim=gt.prossimoSimbolo();
		ntp=0;
		String ppre=null; //parola precedente
		while( sim!=GestoreTesto.Simbolo.EOF ){
			String pcor=gt.getString().toUpperCase(); //parola corrente
			ntp++;
			if( !fp.containsKey( pcor ) ){
				fp.put( pcor,0 );
			}
			fp.put( pcor,fp.get(pcor)+1 );
			if( ppre!=null ){
				if( !fpq.containsKey( ppre ) )
					fpq.put( ppre, new TreeMap<>() );
				Map<String,Integer> adiacenti=fpq.get( ppre );
				if( !adiacenti.containsKey( pcor ) )
					adiacenti.put( pcor,0 );
				adiacenti.put( pcor, adiacenti.get(pcor)+1 );
			}
			ppre=pcor;
			sim=gt.prossimoSimbolo();
		}
	}//raccoltaInfo
	public String[] parolaPiuMenoVerosimile( String k ){
		k=k.toUpperCase();
		if( !fp.containsKey(k) ) return null;
		Map<String,Integer> adiacenti=fpq.get(k);
		int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
		String ppv=null, pmv=null;
		for( String ad: adiacenti.keySet() ){
			int f=adiacenti.get(ad);
			if( f>max ){ max=f; ppv=ad; }
			else if( f<min ){ min=f; pmv=ad; }
		}
		return new String[]{ppv,pmv};
	}//parolaPiuMenoVerosimile
	public String toString(){
		StringBuilder sb=new StringBuilder(500);
		//fp & fpq
		Set<String> parole=fp.keySet();
		for( String p: parole ){
			int fAssoluta=fp.get(p);
			double statp=(double)fAssoluta/ntp;
			sb.append(p+"\tfAssoluta="+fAssoluta+" fp="+String.format("%1.2f%n",statp));
			sb.append("Parole adiancenti a <"+p+">\n");
			Map<String,Integer> adiacenti=fpq.get(p);
			Set<String> pad=adiacenti.keySet();
			sb.append("\t");
			for( String ad: pad ){
				int freqAd=adiacenti.get(ad);
				sb.append(ad+" fAssoluta="+freqAd+" fpq="+String.format("%1.2f%n\t",(double)freqAd/fAssoluta) );
			}
		}
		return sb.toString();
	}//toString
	public static void main( String []args ) throws IOException{
		Scanner sc=new Scanner( System.in );
		File f=null;
		do{
			System.out.print("Nome file testo: ");
			String nome=sc.nextLine();
			f=new File(nome);
			if( !f.exists() )
				System.out.println(nome+" inesistente. Ridarlo.");
		}while( !f.exists() );
		Statistica s=new StatisticaMap();
		s.raccoltaInfo(f);
		System.out.println(s);
		System.out.print("Parola campione: ");
		String k=sc.nextLine();
		System.out.println("Parole che seguono piu/meno verosimilmente "+k+":");
		String[] pv=s.parolaPiuMenoVerosimile(k);
		System.out.println("Parola più frequente: <"+pv[0]+"> ");
		System.out.println("Parola meno frequente: <"+pv[1]+">");
		sc.close();
	}//main
}//StatisticaMap
