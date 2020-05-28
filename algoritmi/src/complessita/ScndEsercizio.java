/**
 * 
 */
package complessita;

/**
 * @author HPProBook4520s Java aiutami, che io ti aiuto!!
 */
/* calcolare la complessità del metodo
 * nei 3casi:
 * 1.caso: E = i - 1
 * 2.caso: E = i - n
 * 3.caso: E = i / 2
 */


class ScndEsercizio
{
	public void metodo(int n, int E)
	{
		@SuppressWarnings("unused")
		int a = 0;
		int i = n;
		while (i >= 1)
		{
			for (int j = 0; j <= n; j++)
				a++;
			i = E;
		}
	}// metodo
}
