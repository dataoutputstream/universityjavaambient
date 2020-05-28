/**
 * 
 */
package n5;

//Scrivere un metodo statico
//public static int[] azzeraNegativi(int[] a)
//che, dato un array di interi a, restituisca un nuovo array
//di interi ottenuto da a sostituendo ogni numero negativo con 0.
//Ad esempio l'invocazione azzeraNegativi({1,-2, 3, 4, -5}).

/**
 * @author HPProBook4520s Java aiutami, che io ti aiuto!!
 */
class NegativiR
{
	public static int[] azzeraNegativi(int[] a)
	{
		int b[] = new int[a.length];
		copia(a, b, 0);
		azzera(b, 0);
		return b;
	}

	private static void copia(int a[], int b[], int indice)
	{
		if (indice != a.length)
		{
			b[indice] = a[indice];
			copia(a, b, indice + 1);
		}
	}// copia

	private static void azzera(int b[], int indice)
	{
		if (indice != b.length)
		{
			if (b[indice] < 0)
				b[indice] = 0;
			azzera(b, indice + 1);
		}// if
	}// azzera

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int a[] = { 1, -2, 3, 4, -5 };
		stampa(a, 0);
		int b[] = azzeraNegativi(a);
		stampa(b, 0);
	}

	private static void stampa(int[] a, int indice)
	{
		// TODO Auto-generated method stub
		if (indice == 0)// inizio
			System.out.print("{ ");
		if (indice != a.length)
		{
			System.out.print(a[indice] + ",");
			stampa(a, indice + 1);
		}
		if (indice == a.length)// fine
			System.out.println(" }");
	}

}
