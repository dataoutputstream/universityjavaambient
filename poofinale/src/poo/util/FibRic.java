package poo.util;

public class FibRic {
	int fibonacci(int i)
	{
	  if (i < 0) return -1; /* F(i) non e' definito per interi i negativi! */

	  if (i == 0) return 0;
	  else if (i == 1) return 1;
	  else return fibonacci(i-1) + fibonacci(i-2);
	}
	public static void main(String[]args) {
		FibRic r=new FibRic();
		int x=r.fibonacci(17);
		System.out.println(x);
	}
	
}
