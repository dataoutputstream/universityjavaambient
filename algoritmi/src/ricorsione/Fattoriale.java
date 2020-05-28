package ricorsione;

/**
 * 
 */

/**
 * @author HPProBook4520s Java aiutami, che io ti aiuto!!
 */
class Fattoriale
{
	protected static int fattoriale(int n)
	{
		if (n == 0)
			return 1;
		return n * fattoriale(n - 1);
	}// fattoriale

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("" + fattoriale(3));
	}

}
