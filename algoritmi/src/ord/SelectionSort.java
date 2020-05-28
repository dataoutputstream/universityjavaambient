package ord;

import utilAle.Utilita;

public class SelectionSort
{
	private SelectionSort(){ }
	
	public static void sel( int[] v ){
		
		for( int i=0; i<v.length; i++)
		{
			//cerco il minimo
			int min = i;
			for( int j=i; j< v.length; j++ )
			{
				if( v[j] < v[min] )
				{
					min=j;
				}//if
			}//for
			//ho il minimo
			//scambio
			if( min != i )
			{
				int tmp=v[i];
				v[i]=v[min];
				v[min]=tmp;
			}
		}//for
		
	}//sel
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int[] v=new int[30];
		Utilita.riempiV(v);
		Utilita.stampa(v);
		sel(v);
		Utilita.stampa(v);


	}

}
