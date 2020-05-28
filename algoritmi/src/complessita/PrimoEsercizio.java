/**
 * 
 */
package complessita;

/**
 * @author HPProBook4520s 
 * Java aiutami, che io ti aiuto!!
 */

/*Calcolare la complessità del metodo
 * restituisciA, individuando i due casi
 * in esame. CASO PEGGIORE e CASO MIGLIORE.
 */
class PrimoEsercizio
{
	private final static int DIM = 500;

	@SuppressWarnings("unused")
	private static int restituisciA(int n)
	{
		int a = n;
		if (n <= DIM)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					a += n;
		else
			for (int i = 0; i < n; i++)
				a += n;
		return a;
	}// restituisciA
}
