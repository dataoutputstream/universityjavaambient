package search;

import java.util.Scanner;

import ord.SelectionSort;
import utilAle.Utilita;

class SearchBinary
{
	private static Scanner sc;

	private SearchBinary(){	}

	public static boolean bin(int[] v, int x, int in, int out)
	{
	
		if(in>out)
			return false;
		else{
			int med=(in+out)/2;
			if(v[med]==x)
				return true;
			if( x > v[med] )
				return bin(v,x,med+1,out);
			else
				return bin(v,x,in,med-1);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int[] v = new int[30];
		Utilita.riempiV(v);
		SelectionSort.sel(v);
		sc = new Scanner(System.in);
		Utilita.stampa(v);
		System.out.print("Immettere elemento da cercare x=");
		int x = sc.nextInt();
		if( bin(v,x,0,v.length) )
			System.out.println("Trovato!!");
		else
			System.out.println("NON Trovato!!");
	}

}
