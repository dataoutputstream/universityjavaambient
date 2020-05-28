/**
 * 
 */
package esercizio4;

import visita.VisitaAlbero;
import alberi.AlberoBImpl;

/**
 * @author HpProBook4520s
 * Java aiutami, che io ti aiuto!!
 */
class SommaAlbero
{
	public static void creaAlbero(AlberoBImpl<Integer> a)
	{
		// radice dell'albero
		a.setVal(new Integer(2));
		
		// sotto-albero sinistro
		AlberoBImpl<Integer> figlioSx = new AlberoBImpl<Integer>();
		figlioSx.setVal(new Integer(10));
		a.setSin(figlioSx);
		
		// sotto-albero destro
		AlberoBImpl<Integer> figlioDx = new AlberoBImpl<Integer>();
		figlioDx.setVal(new Integer(10));
		a.setDes(figlioDx);
	}//crea
	
	public static int sommaAlbero( AlberoBImpl<Integer> a )
	{
		if( a == null )
			return 0;
		int somma = sommaAlbero( (AlberoBImpl<Integer>) a.getSin() ) + 
				sommaAlbero( (AlberoBImpl<Integer>) a.getDes());
		return somma + a.getVal();
	}//sommaAlbero
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		AlberoBImpl<Integer> a = new AlberoBImpl<Integer>();
		creaAlbero(a);
		VisitaAlbero.visitaAnticipata(a);
		System.out.println("somma albero binario = "+sommaAlbero(a));
	}

}
