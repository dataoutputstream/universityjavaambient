/**
 * 
 */
package appello12092007;

import visita.VisitaAlbero;
import alberi.AlberoBImpl;

/**
 * @author HpProBook4520s
 * Java aiutami, che io ti aiuto!!
 */
class Ripetuto
{
	public static void creaAlbero(AlberoBImpl<Integer> a)
	{
		// radice dell'albero
		a.setVal(new Integer(2));
		
		// sotto-albero sinistro
		AlberoBImpl<Integer> figlioSx = new AlberoBImpl<Integer>();
		figlioSx.setVal(new Integer(3));
		a.setSin(figlioSx);
		
		// sotto-albero destro
		AlberoBImpl<Integer> figlioDx = new AlberoBImpl<Integer>();
		figlioDx.setVal(new Integer(3));
		a.setDes(figlioDx);
	}//crea
	
	public static boolean eRipetuto( AlberoBImpl<Integer> a, int x)
	{
		// a non esiste
		if( a == null )
			return false;
		boolean trovato = trovaValore((AlberoBImpl<Integer>)
				a.getSin(), x ) && 
				trovaValore( (AlberoBImpl<Integer>) a.getDes(), x);
		if( trovato )
			return true;
		return eRipetuto((AlberoBImpl<Integer>) a.getSin(), x ) ||
				eRipetuto((AlberoBImpl<Integer>) a.getDes(), x );
	}
	
	private static boolean trovaValore( AlberoBImpl<Integer> a, int x)
	{
		//sono arrivato ad una foglia
		if( a == null )
			return false;
		if( a.getVal() == x )
			return true;
		return trovaValore((AlberoBImpl<Integer>)a.getSin(),x) ||
				trovaValore((AlberoBImpl<Integer>)a.getDes(), x);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		AlberoBImpl<Integer> a = new AlberoBImpl<Integer>();
		creaAlbero(a);
		VisitaAlbero.visitaAnticipata(a);
		int x=3;
		System.out.println("x="+x+" ripetuto nell'AlberoA? "+eRipetuto(
				(AlberoBImpl<Integer>)a,x));
		
	}

}
