package esercizio2;

import visita.VisitaAlbero;
import alberi.AlberoBImpl;

public class Esercizio2
{
	public static int contaDispari(AlberoBImpl<Integer> a)
	{
		if(a==null)
			return 0;
		int contatoreDispari=contaDispari((AlberoBImpl<Integer>) a.getSin()) +
				contaDispari((AlberoBImpl<Integer>) a.getDes());
		if( a.getVal()%2!=0)
			return contatoreDispari+1;
		return contatoreDispari;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		AlberoBImpl<Integer> a=new AlberoBImpl<Integer>();
		VisitaAlbero.crea(a);
		System.out.println("Visita anticipata\n\n");
		VisitaAlbero.visitaAnticipata(a);
		System.out.println("\n\n\n");
		System.out.println(contaDispari(a));
	}

}
