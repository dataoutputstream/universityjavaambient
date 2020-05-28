/**
 * 
 */
package ord;

import utilAle.Utilita;

/**
 * @author HpProBook4520s Java aiutami, che io ti aiufine!!
 */
public class MergeSort
{
 
	static void merge(int v[], int inizio, int center, int fine) 
	{
	  int i=inizio;
	  int j=center+1;
	  int k=0;
	  //vettore di appoggio 
	  int b[]=new int[v.length];

	  //fusione delle 2 meta'
	  while ((i<=center) && (j<=fine)) 
	  {
	    if (v[i] <= v[j]) 
	    {
	      b[k] = v[i];
	      i++;
	    } 
	    else 
	    {
	      b[k] = v[j];
	      j++;
	    }
	    k++;
	  }
	  
	  //se i e' minore di center significa che alcuni elementi 
	  //della prima meta' non sono stati inseriti nel vettore
	  for(; i<=center; i++,k++ )
		  b[k]=v[i];//aggiungo in coda
	 
	  //se j a' minore di right significa che alcuni elementi 
	  //della seconda meta' non sono stati inseriti nel vettore
	  for(; j<=fine; j++,k++)
		  b[k]=v[j];//aggiungo in coda al vettore
	   	 
	  //alla fine copio il vettore di appoggio b nel vettore v 
	  for (k=inizio; k<=fine; k++)
	  {
	    v[k] = b[k-inizio];
	  }
	}
	
	
	//ALTRA SOLUZIONE
	// public static void merge(int[] a, int inizio, int med, int fine)
	// {
	//
	// int[] aux = new int[fine - inizio + 1];
	// int i = inizio;
	// int j = med + 1;
	//
	// for (int k = 0; k < aux.length; k++)
	// {
	//
	// if (i > med)
	// {
	// aux[k] = a[j];
	// j++;
	//
	// } else if (j > fine)
	// {
	// aux[k] = a[i];
	// i++;
	//
	// } else if (a[i] <= a[j])
	// {
	// aux[k] = a[i];
	// i++;
	//
	// } else
	// {
	// aux[k] = a[j];
	// j++;
	//
	// }
	//
	// }
	// // copia il vettore temporaneo in a
	// for (int q = 0; q < aux.length; q++)
	// a[inizio + q] = aux[q];
	// }

	public static void mergeSort(int[] v, int inizio, int fine)
	{
		if (inizio < fine)
		{
			int med = (inizio + fine) / 2;
			mergeSort(v, inizio, med);
			mergeSort(v, med + 1, fine);
			merge(v, inizio, med, fine);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// fineDO Aufine-generated method stub
		int[] v = new int[30];
		Utilita.riempiV(v);
		Utilita.stampa(v);
		mergeSort(v, 0, v.length-1);
		System.out.println("v ordinato..");
		Utilita.stampa(v);
	}

}
