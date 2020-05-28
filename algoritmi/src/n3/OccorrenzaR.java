/**
 * 
 */
package n3;

/**
 * @author HPProBook4520s Java aiutami, che io ti aiuto!!
 */
// 1. Scrivere una funzione ricorsiva per contare il numero di interi
// memorizzati nel file 2. Scrivere una funzione ricorsiva che dato un file i
// restituisca un file o ottenuto da i sostituento gli interi negativi con -1.
// Esercizi sulla ricorsione (array)
// public static boolean appartiene(int[] a, int n)
// che, dato un array di interi a ed un intero n, verifichi se n compare in a.
// Ad esempio l'invocazione appartiene({1,2,3}, 2) deve restituire true .

class OccorrenzaR
{

	/**
	 * @param args
	 */
	public static boolean appartiene(int[] a, int n)
	{
		return cerca(a, n, 0);
	}

	private static boolean cerca(int[] a, int n, int indice)
	{
		// TODO Auto-generated method stub
		if (a[indice] == n)
			return true;
		if ((indice + 1) < a.length)
			return cerca(a, n, indice + 1);
		return false;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int a[] = { 1, 2, 3 };
		System.out.println(appartiene(a, 1));
	}

}
