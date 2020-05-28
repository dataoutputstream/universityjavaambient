package n1;

//Scrivere una funzione ricorsiva che, assegnati due interi N1 ed N2,
//restituisca la somma di tutti gli interi compresi tra N1 ed N2.

class SommaRicorsiva
{
	protected static int somma(int n1, int n2)
	{
		if (n1 != n2 && n1 < n2)
			return (n1 + n2) + somma(n1 + 1, n2 - 1);
		return n1;
	}// somma

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("" + somma(1, 5));
	}

}
