/**
 * 
 */
package complessita;

/**
 * @author HPProBook4520s Java aiutami, che io ti aiuto!!
 */
class QuartoEsercizio
{
	public static int f(int n)
	{
		int b, a = 0;
		for (int i = 0; i <= n; i++)
		{
			for (int j = 0; j <= n; j++)
			{
				a++;
			}// for
		}// for
		b = a * n;
		return 2 * b;
	}// f
}
