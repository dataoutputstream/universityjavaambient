/**
 * 
 */
package n6;

//Scrivere un metodo statico
//public static void inverti(int[] a)
//che, dato un array di interi a, inverta le posizioni
//dei suoi elementi. Ad esempio l'invocazione inverti(a) 
//dove a è un riferimento all'array {1, 2, 3, 4} 
//deve modificare l'array a cui a si riferisce 
//in {4, 3, 2, 1} .

/**
 * @author HPProBook4520s Java aiutami, che io ti aiuto!!
 */
class InvertiR
{

	public static void inverti(int a[])
	{
		inverti(a, 0, (a.length - 1));
	}// inverti

	private static void inverti(int a[], int i, int j)
	{
		if (i < a.length/2)
		{
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
			inverti(a, i + 1, j - 1);
		}// if
	}// inverti

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int a[] = { 1, 2, 3, 4 };
		stampa(a);
		inverti(a);
		stampa(a);
	}

	private static void stampa(int a[])
	{
		for (int i = 0; i < a.length; i++)
		{
			if (i == 0)
				System.out.print("{ ");
			System.out.print(a[i]);
			if (i + 1 != a.length)
				System.out.print(" , ");
		}

		System.out.println(" }");
	}
}
