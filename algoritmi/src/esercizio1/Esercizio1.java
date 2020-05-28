/**
 * 
 */
package esercizio1;

import alberi.AlberoBImpl;
import visita.*;

/**
 * @author HpProBook4520s
 * Java aiutami, che io ti aiuto!!
 */
class Esercizio1
{
	public static boolean maggiore(AlberoBImpl<Integer> a,
			int k)
	{
		if(a==null)
			return true;
		if(a.getSin()==null && a.getDes()==null)
			//sono ad una foglia
			return a.getVal()>=k;
		return maggiore((AlberoBImpl<Integer>)a.getSin(), k) &&
				maggiore((AlberoBImpl<Integer>)a.getDes(),k);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		AlberoBImpl<Integer> a=new AlberoBImpl<Integer>();
		VisitaAlbero.crea(a);
		System.out.println(maggiore(a,50));
	}

}
