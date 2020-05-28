package search;

import java.util.Scanner;

import ord.SelectionSort;
import utilAle.Utilita;

class SearchSeq
{
	private static Scanner sc;

	private SearchSeq(){ }
	
	static boolean seq( int[] v, int x )
	{
		for (int i = 0; i < v.length; i++)
		{
			if( v[i] == x ) return true;
		}
		return false;
	}//seq
	
	static int seqPos( int[] v, int x )
	{
		for (int i = 0; i < v.length; i++)
		{
			if( v[i] == x )
				return i;
		}
		return -1;
	}
	
	static boolean seqOrd( int[] v, int x)
	{
		for( int i=0; i< v.length; i++ )
		{
			if( v[i]==x )
				return true;
			if( x < v[i])
				break; 
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int[] v=new int[30];
		Utilita.riempiV(v);
		Utilita.stampa(v);
		int x=Utilita.dammiNumero()+1;
		if( seq(v, x ) )
			System.out.println("x="+x+" presente!!");
		else
			System.out.println("x="+x+" NON presente!!");
		int indice=seqPos(v,x);
		if( indice!=-1 )
			System.out.println("v["+indice+"]="+v[indice]);
		
		int[] v2=new int[20];
		Utilita.riempiV(v2);
		Utilita.stampa(v2);
		SelectionSort.sel(v2);
		Utilita.stampa(v2);
		System.out.println("cerco x="+x);
		sc = new Scanner(System.in);
		System.out.print("immettere l'elemento da cerca x=");
		x=sc.nextInt();
		if( seqOrd(v2,x) )
			System.out.println("Trovato!!");
		else
			System.out.println("NON trovato!!");
	}

}
