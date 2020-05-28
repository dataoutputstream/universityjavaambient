//file Data.java

package poo.date;
import java.util.*;

public class Data implements Comparable<Data>{
	private final int G, M, A;
	public static final int GIORNO=0, MESE=1, ANNO=2;
	public Data(){
		GregorianCalendar gc=new GregorianCalendar();
		G=gc.get( GregorianCalendar.DAY_OF_MONTH );
		M=gc.get( GregorianCalendar.MONTH )+1;
		A=gc.get( GregorianCalendar.YEAR );
	}//Data
	public Data( int g, int m, int a ){
		if( a<0 || m<1 || m>12 || g<1 || g>durataMese(m,a) )
			throw new IllegalArgumentException();
		this.G=g; this.M=m; this.A=a;
	}//Data
	public Data( Data d ){
		G=d.G; M=d.M; A=d.A;
	}//Data
	public int get( int cosa ){
		switch( cosa ){
			case GIORNO: return G;
			case MESE: return M;
			case ANNO: return A;
			default: return -1;
		}
	}//get
	public static boolean bisestile( int a ){
		if( a<0 )
			throw new IllegalArgumentException();
		if( a%4!=0 ) return false;
		if( a%100==0 && a%400!=0 ) return false;
		return true;
	}//bisestile
	public static int durataMese( int m, int a ){
		if( m<1 || m>12 || a<0 )
			throw new IllegalArgumentException();		
		int durata;
		switch( m ){
			case 1: case 3: case 5: case 7: case 8:
			case 10: case 12: durata=31; break;
			case 2: durata=bisestile(a) ? 29:28; break;
			default: durata=30;
		}//switch
		return durata;
	}//durataMese
	public Data giornoDopo(){
		int durata=durataMese( M, A );
		int g1, m1, a1;
		if( G==durata ){
			g1=1;
			if( M==12 ){ m1=1; a1=A+1; }
			else{ m1=M+1; a1=A; }
		}
		else{ g1=G+1; m1=M; a1=A; }
		return new Data( g1,m1,a1 );
	}//giornoDopo
	public Data giornoPrima(){
		if( G==1 && M==1 && A==0 )
			throw new RuntimeException("Prima data");
		int g, m=this.M, a=this.A;
		if( this.G==1 ){
			if( this.M>1 ) m=this.M-1;
			else{ m=12; a=this.A-1; }
			g=durataMese(m,a);
		}
		else g=this.G-1;
		return new Data(g,m,a);
	}//giornoPrima
	public int distanza( Data d ){
		//ritorna la distanza in giorni tra min(this,d) e max(this,d)
		if( this.equals(d) ) return 0;
		Data d1=this, d2=d;
		if( this.compareTo(d)>0 ){ d1=d; d2=this; }
		int c=0, g=d1.G, m=d1.M, a=d1.A, dm=durataMese(m,a);
		do{
			c++; //conta questo giorno
			if( g==dm ){ 
				g=1;
				if( m==12 ){ m=1; a++; }
				else m++;
				dm=durataMese(m,a);
			}
			else g++;
		}while( !(g==d2.G && m==d2.M && a==d2.A) );
		return c;
	}//distanza
	public boolean equals( Object o){
		if( !(o instanceof Data) ) return false;
		if( o==this ) return true;
		Data d=(Data)o;
		return G==d.G && M==d.M && A==d.A;
	}//equals
	public int compareTo( Data d ){
		if( A==d.A && M==d.M && G==d.G ) return 0;
		if( A<d.A || A==d.A && M<d.M || A==d.A && M==d.M && G<d.G )
			return -1;
		return 1;
	}//compareTo
	public String toString(){
		return G+"/"+M+"/"+A;
	}//toString
	public int hashCode(){
		final int MOLT=43;
		int h=G;
		h=h*MOLT+M;
		h=h*MOLT+A;
		return h;
	}//hashCode
	public static void main( String []args ){//solo per test
		Data d=new Data();
		System.out.println("Oggi e' il "+d);
		System.out.println("Domani e' il "+d.giornoDopo());
		d=new Data( 28, 2, 2000 );
		System.out.println("Il giorno dopo il "+d+" e' il "+d.giornoDopo());
		d=new Data( 28, 2, 2008 );
		System.out.println("Il giorno dopo il "+d+" e' il "+d.giornoDopo());	
		d=new Data( 28, 2, 2009 );
		System.out.println("Il giorno dopo il "+d+" e' il "+d.giornoDopo());	
		if( Data.bisestile(2008) )
			System.out.println("Il 2008 e' un anno bisestile");
		System.out.println("Giorno di "+d+" = "+d.get(Data.GIORNO));
		System.out.println("Mese di "+d+" = "+d.get(Data.MESE));
		System.out.println("Anno di "+d+" = "+d.get(Data.ANNO));
		Data d1=new Data(1,1,2011);
		Data d2=d1.giornoDopo().giornoDopo().giornoDopo();
		System.out.println("Distanza tra "+d1+" e "+d2+" = "+d1.distanza(d2));
		d2=new Data(1,1,2012);
		System.out.println("Distanza tra "+d1+" e "+d2+" = "+d1.distanza(d2));
		d2=new Data(1,3,2000);
		System.out.println("Giorno precedente a "+d2+" = "+d2.giornoPrima());
	}//main
}//Data