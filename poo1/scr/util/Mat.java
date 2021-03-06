package util;

public final class Mat{
	private Mat(){}
	//tolleranza nei confronti tra double
	private static double EPSILON=1.0E-14;

	public static boolean sufficientementeProssimi( double x1, double x2 ){
		if( Math.abs( x1-x2 )<=EPSILON ) return true;
		return false;
	}//sufficientementeProssimi

	public static double getEpsilon(){ return EPSILON; }
	public static void setEpsilon( double EPSILON ){
		Mat.EPSILON=EPSILON;
	}//setEpsilon

	public static int mcm( int n, int m ){
		if( n<=0 || m<=0 ) throw new IllegalArgumentException();
		return (n*m)/mcdEuclide(n,m);
	}//mcm

	public static int mcd( int n, int m ){
		if( n<=0 || m<=0 ) throw new IllegalArgumentException();
		return mcdEuclide(n,m);
	}//mcd

	private static int mcdEuclide( int n, int m ){
		if( m==0 ) return n;
		return mcdEuclide(m,n%m);
	}//mcdEuclide
}//Mat