package visita;

import alberi.AlberoBImpl;

public class VisitaAlbero
{
	public static void visitaAnticipata(AlberoBImpl<Integer> a)
	{
		if (a == null)
			return;
		System.out.println(a.getVal());
		visitaAnticipata((AlberoBImpl<Integer>) a.getSin());
		visitaAnticipata((AlberoBImpl<Integer>) a.getDes());
	}//visitaAnticipata
	
	public static void visitaPosticipata(AlberoBImpl<Integer> a)
	{
		if(a == null)
			return;
		visitaPosticipata((AlberoBImpl<Integer>)a.getSin());
		visitaPosticipata((AlberoBImpl<Integer>) a.getDes());
		System.out.println(a.getVal());
	}//visitaPosticipata

	public static void visitaInFissa(AlberoBImpl<Integer> a)
	{
		if(a == null)
			return;
		visitaInFissa((AlberoBImpl<Integer>)a.getSin());
		System.out.println(a.getVal());
		visitaInFissa((AlberoBImpl<Integer>) a.getDes());
	}//visitaInFissa
	
	public static void crea(AlberoBImpl<Integer> a)
	{
		// radice dell'albero
		a.setVal(new Integer(2));
		// sotto-albero sinistro
		AlberoBImpl<Integer> figlioSx = new AlberoBImpl<Integer>();
		figlioSx.setVal(new Integer(1));
		a.setSin(figlioSx);
		// sotto-albero destro
		AlberoBImpl<Integer> figlioDx = new AlberoBImpl<Integer>();
		figlioDx.setVal(new Integer(5));
		a.setDes(figlioDx);
	}//crea
	

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		AlberoBImpl<Integer> a=new AlberoBImpl<Integer>();
		crea(a);
		System.out.println("Visita ANTICIPATA");
		visitaAnticipata(a);
		System.out.println();
		System.out.println("Visita POSTICIPATA");
		visitaPosticipata(a);
		System.out.println();
		System.out.println("Visita INFISSA");
		visitaInFissa(a);
	}

}
