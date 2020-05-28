//file Triangolo.java

package poo.geometria;
import poo.util.Mat;

public class Triangolo implements FiguraPiana{
   public enum Tipo { Equilatero, Isoscele, Scaleno }
   private Punto p1, p2, p3;
   private double a, b, c;
   public Triangolo( Punto p1, Punto p2, Punto p3 ){
	   //verifica triangolo
       if( p1.equals(p2) || p1.equals(p3) || p2.equals(p3) ||
           new Retta(p1,p2).parallela(new Retta(p1,p3)) ){
         System.out.println("Triangolo inesistente");
         System.exit(-1);
	   }
       this.p1=new Punto( p1 );
       this.p2=new Punto( p2 );
       this.p3=new Punto( p3 );
	   a=p1.distanza(p2);
	   b=p2.distanza(p3);
	   c=p3.distanza(p1);
   }
   public Triangolo( Triangolo t ){
	   p1=new Punto( t.p1 );
	   p2=new Punto( t.p2 );
	   p3=new Punto( t.p3 );
	   this.a=t.a;
	   this.b=t.b;
	   this.c=t.c;
   }
   public double getA(){ return a; }
   public double getB(){ return b; }
   public double getC(){ return c; }
   public double perimetro(){
	   return a+b+c;
   }//perimetro
   public double area(){
	   double s=this.perimetro()/2;
	   return Math.sqrt(s*(s-a)*(s-b)*(s-c));
   }//area
   public Tipo tipo(){
	   if( Mat.sufficientementeProssimi(a,b) &&
	       Mat.sufficientementeProssimi(b,c) ) return Tipo.Equilatero;
	   if( Mat.sufficientementeProssimi(a,b) ||
	       Mat.sufficientementeProssimi(a,c) ||
	       Mat.sufficientementeProssimi(b,c) ) return Tipo.Isoscele;
	   return Tipo.Scaleno;
   }//tipo
   public String toString(){
	   return "Triangolo con vertici: "+p1+" "+p2+" "+p3+ " tipo "+tipo();
   }//toString
   public static void main( String []args ){
	   Triangolo t=null;
	   //t=new Triangolo( new Punto(1,1), new Punto(-2,-2), new Punto(3,3) );
	   t=new Triangolo( new Punto(0,1), new Punto(3,2), new Punto(4,7) );
	   System.out.println(t);
	   System.out.printf("perimetro=%1.2f area=%1.2f\n",t.perimetro(),t.area());
	   t=new Triangolo( new Punto(2,2), new Punto(4,2), new Punto(4,5) );
	   System.out.println(t);
	   System.out.printf("perimetro=%1.2f area=%1.2f\n",t.perimetro(),t.area());
	   t=new Triangolo( new Punto(2,0), new Punto(2,2), new Punto() );
	   System.out.println(t);
	   System.out.printf("perimetro=%1.2f area=%1.2f\n",t.perimetro(),t.area());

   }//main
}//Triangolo