package ricorsione;

import java.util.Scanner;

/**
 * @author HPProBook4520s Java aiutami, che io ti aiuto!!
 */
class Media
{
	private static int cnt;
	private static Scanner sc = new Scanner(System.in);

	/**
	 * @param args
	 */
	static int calcoloMedia(int n)
	{
		// finchè n!=0 calcolo
		// media <- calcoloMedia( input n )
		// return (media+n)/cnt
		if (n != 0)
		{
			System.out.print("" + (cnt + 1) + "°chiamata =");
			cnt++;
			int media = calcoloMedia(sc.nextInt());
			return (media*(cnt)+ n);
		}
		return 0;
	}// media

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		cnt++;
		System.out.print("Primo numero= ");
		int media = calcoloMedia(sc.nextInt());
		System.out.println("Media calcolata =" + (media/(cnt-1)));
	}
}
