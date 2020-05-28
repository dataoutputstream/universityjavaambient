package n4;

//Scrivere un metodo statico
//public static int occorrenze(int[] a, int n)
//che, dato un array di interi a ed un intero n, 
//restituisca il numero di occorrenze di n in a.
//Ad esempio l'invocazione occorrenze({1,2,3,2,4,2}, 2) deve restituire 3 .

class ContaR
{

	/**
	 * @param args
	 */
	public static Numero occorrenze(int[] a, int n)
	{
		Numero cnt = new Numero();
		// in Java, i tipi di base primitivi vengono passati ad un metodo
		// per valore e quindi risultano immutabili.
		conta(a, n, cnt, 0);
		return cnt;
	}// occorrenze

	private static void conta(int a[], int n, Numero cnt, int indice)
	{
		if (indice != a.length)
		{
			if (a[indice] == n)
				cnt.incrementa();
			conta(a, n, cnt, indice + 1);
		}
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int a[] = { 1, 2, 3, 2, 4, 2 };
		System.out.println(occorrenze(a, 1));
	}

}
