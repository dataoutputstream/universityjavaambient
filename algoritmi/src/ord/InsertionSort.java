package ord;

import utilAle.Utilita;

public class InsertionSort
{
	private InsertionSort(){ }
	
	public static void ins( int[] v ){
		
		for( int i=0; i < v.length-1; i++)
		{
			int pos=i+1;
			for( int j=i-1; j >= 0; j-- )
			{
				if( v[j] > v[pos] )
				{
					int tmp=v[pos];
					v[pos]=v[j];
					v[j]=tmp;
					pos=j;
				}//if
			}//for
		}//for
		
	}//ins
	
    /*
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int[] v=new int[30];
		Utilita.riempiV(v);
		Utilita.stampa(v);
		ins(v);
		Utilita.stampa(v);


	}

}
