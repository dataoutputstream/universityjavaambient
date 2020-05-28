package utilAle;

public class Utilita
{
	private Utilita(){ }
	
	public static void stampa(int[] v)
	{
		System.out.print("{\t");
		for(int i=0; i<v.length; i++)
		{
			System.out.print("\t"+v[i]);
	        System.out.print(",");
		}
		System.out.println("\t}");
	}//stampa
	
	public static void riempiV(int[] v)
	{
		for (int i = 0; i < v.length; i++)
		{
			v[i]= ( (int) (Math.random()*100)+1 );
		}
	}
	
	public static int dammiNumero(){ return ( (int) (Math.random()*100)+1); }
}
