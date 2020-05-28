package ricorsione;

import java.util.Scanner;

/**
 * @author HPProBook4520s Java aiutami, che io ti aiuto!!
 */
class Media2
{
	private static int cnt, cnt_inverso;
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
			cnt_inverso--;
			if (cnt_inverso == 0)
				return ((media * (cnt) + n) / (cnt - 1));
			else
				return (media * (cnt) + n);
		}
		cnt_inverso = cnt;
		return 0;
	}// media

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		cnt++;
		System.out.print("Primo numero= ");
		int media = calcoloMedia(sc.nextInt());
		System.out.println("Media calcolata =" + media);
	}
}
