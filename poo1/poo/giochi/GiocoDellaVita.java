package giochi;

public class GiocoDellaVita{

	private char [][]mappa, nuovaMappa;
	private int n, m;

	public GiocoDellaVita( int n, int m ){
		if( n<=0 || m<=0 )
			throw new IllegalArgumentException();
		this.n=n; this.m=m;
		mappa=new char[n][m];
		nuovaMappa=new char[n][m];
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				mappa[i][j]='.';
	}

	public void reset(){
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				mappa[i][j]='.';
	}//reset

	public void aggiungiOrganismo( int i, int j ){
		if( i<0 || i>=n || j<0 || j>=m )
			throw new IllegalArgumentException();
		mappa[i][j]='*';
	}//aggiungiOrganismo

	private int vicini( int i, int j ){
		//conta organismi vicini alla cella <i,j>
		int cont=0;
		if( i>0 && mappa[i-1][j]=='*' ) cont++; //NORD
		if( i>0 && j<m-1 && mappa[i-1][j+1]=='*' ) cont++; //NE
		if( j<m-1 && mappa[i][j+1]=='*' ) cont++; //EST
		if( i<n-1 && j<m-1 && mappa[i+1][j+1]=='*' ) cont++; //SE
		if( i<n-1 && mappa[i+1][j]=='*' ) cont++; //SUD
		if( i<n-1 && j>0 && mappa[i+1][j-1]=='*' ) cont++; //SO
		if( j>0 && mappa[i][j-1]=='*' ) cont++; //OVEST
		if( i>0 && j>0 && mappa[i-1][j-1]=='*' ) cont++; //NO
		return cont;
	}//vicini

	public void prossimaGenerazione(){
		for( int i=0; i<nuovaMappa.length; ++i )
			for( int j=0; j<nuovaMappa[0].length; ++j ){
				int v=vicini( i, j );
				if( mappa[i][j]=='*' )
					nuovaMappa[i][j]=( v==2 || v==3 ) ? '*' : '.';
				else
					nuovaMappa[i][j]=( v==3 ) ? '*' : '.';
			}
		//scambia mappa con nuovaMappa
		char [][] tmp=mappa; mappa=nuovaMappa; nuovaMappa=tmp;
	}//prossimaGenerazione

	public String toString(){
		StringBuffer sb=new StringBuffer(500);
		for( int i=0; i<n; i++ ){
			for( int j=0; j<m; j++ )
				sb.append( mappa[i][j] );
			sb.append('\n');
		}
		return sb.toString();
	}//toString

	public static void main( String[] args ){
		GiocoDellaVita gol=new GiocoDellaVita(5,7);
		System.out.println(gol);
		gol.aggiungiOrganismo(0,2);
		gol.aggiungiOrganismo(0,5);
		gol.aggiungiOrganismo(0,6);
		gol.aggiungiOrganismo(1,0);
		gol.aggiungiOrganismo(2,3);
		gol.aggiungiOrganismo(2,5);
		gol.aggiungiOrganismo(3,0);
		gol.aggiungiOrganismo(3,4);
		System.out.println(gol);
		gol.prossimaGenerazione();
		System.out.println(gol);
		gol.prossimaGenerazione();
		System.out.println(gol);
	}//main

}//GiocoDellaVita