//file Punto.java

package poo.geometria;
import poo.util.Mat;

public class Punto{
   private double x, y;
   public Punto(){//costruttore di default
      this(0,0);
   }
   public Punto( double x, double y ){//costruttore normale
      this.x=x; this.y=y;
   }
   public Punto( Punto p ){//costruttore di copia
      x=p.x; y=p.y;
   }
   public double getX(){ return x; }
   public double getY(){ return y; }
   public void sposta( double nuovaX, double nuovaY ){
      x=nuovaX; y=nuovaY;
   }//sposta
   public double distanza( Punto p ){
      return Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y));
   }//distanza
   public String toString(){
      return "<"+String.format("%1.2f",x)+","+String.format("%1.2f",y)+">";
   }//toString
   public boolean equals( Object p ){
      if( !(p instanceof Punto) ) return false;
      if( p==this ) return true;
      Punto pt=(Punto)p;
      return Mat.sufficientementeProssimi(x,pt.x) &&
             Mat.sufficientementeProssimi(y,pt.y);
   }//equals
   public int hashCode(){
		final int MOLT=41;
		int h=0;
		h=h*MOLT+new Double( x ).hashCode();
		h=h*MOLT+new Double( y ).hashCode();	  
		return h;
   }//hashCode
   public static void main( String[] args ){
	   Punto p=new Punto(5,7);
	   System.out.println(p);
	   p.sposta(3.567,-4.6789);
	   System.out.println(p);
   }
}//Punto