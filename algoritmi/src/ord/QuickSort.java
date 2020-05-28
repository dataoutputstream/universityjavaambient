package ord;

import utilAle.Utilita;

public class QuickSort
{
	private QuickSort()
	{
	}
	
	private static void scambia(int[] v,int x,int y)
	{
		int tmp=v[y];
		v[y]=v[x];
		v[x]=tmp;
	}


	private static int partiziona(int[] v, int in, int out)
	{
		int x = v[in];// pivot
		int inf = in;
		int sup = out+1;

		while (inf < sup)
		{
			// cerco un elemento piu grande del pivot x
			do
			{
				inf++;
			} while (v[inf] <= x);

			// cerco un elemento piu piccolo del pivot x
			do
			{
				sup--;
			} while (v[sup] > x);

			if (inf < sup)
			{// scambia
				scambia(v,inf,sup);
			} 
		}// while
		// scambia. sposto l'elemento pivot nella posizione sup
		// e restituisco il suo indice.
			scambia(v,in,sup);	
		return sup;
	}

	public static void qsort(int[] v, int in, int out)
	{
		// condizione di uscita ricorsione
		if (in < out)
		{
			int m = partiziona(v, in, out);
			qsort(v, in, m - 1);
			qsort(v, m + 1, out);
		}// if
	}// qsort

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int[] v = new int[30];
		Utilita.riempiV(v);
		Utilita.stampa(v);
		qsort(v, 0, v.length-1);
		System.out.println("v ordinato..");
		Utilita.stampa(v);
	}

}
