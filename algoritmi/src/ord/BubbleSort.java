package ord;

import utilAle.Utilita;

public class BubbleSort
{
	private BubbleSort(){ }
	
	public static void bubble( int[] v ){
		
		for( int i=0; i < v.length; i++ )
		{
			boolean scambia=false;
			
			for( int j=0; j < v.length-1; j++ )
			{
				if( v[j] > v[j+1] )
				{
					int tmp=v[j+1];
					v[j+1]=v[j];
					v[j]=tmp;
					scambia=true;
				}
			}//for
			
			if(!scambia)
				break;
		}//for
	}//bubble
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int[] v=new int[30];
		Utilita.riempiV(v);
		Utilita.stampa(v);
		bubble(v);
		Utilita.stampa(v);


	}

}
