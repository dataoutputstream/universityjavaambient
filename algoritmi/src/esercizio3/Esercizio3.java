/**
 * 
 */
package esercizio3;

import visita.VisitaAlbero;
import alberi.AlberoBImpl;

/**
 * @author HpProBook4520s
 * Java aiutami, che io ti aiuto!!
 */
public class Esercizio3
{
	public static boolean sonoIdentici(AlberoBImpl<Integer> a,
			AlberoBImpl<Integer> b)
	{
		if(a==null && b==null)
			return true;
		if( a.getVal() != b.getVal()
				||
			a==null || b==null)
			return false;
		return sonoIdentici( (AlberoBImpl<Integer>)a.getSin(), 
				(AlberoBImpl<Integer>)b.getSin()) &&
				sonoIdentici( (AlberoBImpl<Integer>)a.getDes(),
				(AlberoBImpl<Integer>) b.getDes());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		AlberoBImpl<Integer> a=new AlberoBImpl<Integer>();
		VisitaAlbero.crea(a);
		if( sonoIdentici(a, a))
			System.out.println("A == B");
		else
			System.out.println("A != B");
	}

}
