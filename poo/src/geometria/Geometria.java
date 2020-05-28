//file Geometria.java
package geometria;

class Punto{
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
	public void sposta( double x, double y ){
	   this.x=x; this.y=y;
	}//sposta
	public double distanza( Punto p ){
	   return Math.sqrt((p.x-this.x)*(p.x-this.x)+(p.y-this.y)*(p.y-this.y));
	}//distanza
	public String toString(){
	   return "Punto<"+String.format("%1.2f",x)+","+String.format("%1.2f",y)+">";
	}//toString
}//Punto

class Triangolo{
	private Punto p1, p2, p3;
	private double a, b, c;
	public Triangolo( Punto p1, Punto p2, Punto p3 ){
	   //verifica esistenza triangolo
	   a=p1.distanza(p2);
	   b=p2.distanza(p3);
	   c=p3.distanza(p1);
	   if( a>=b+c || b>=a+c || c>=a+b ){
		   System.out.println("Triangolo inesistente");
		   System.exit(-1);
	   }
	   this.p1=new Punto( p1 );
	   this.p2=new Punto( p2 );
	   this.p3=new Punto( p3 );
	}
	public Triangolo( Triangolo t ){
	   p1=new Punto( t.p1 );
	   p2=new Punto( t.p2 );
	   p3=new Punto( t.p3 );
	   this.a=t.a;
	   this.b=t.b;
	   this.c=t.c;
	}
	public Punto[] getVertici(){
		//Punto []v=new Punto[3];
		//v[0]=new Punto(p1);
		//v[1]=new Punto(p2);
		//v[2]=new Punto(p3);
		//o più sinteticamente:
		Punto [] v={ new Punto(p1), new Punto(p2), new Punto(p3) };
		return v;
	}//lati
	public double perimetro(){
	   return a+b+c;
	}//perimetro
	public double area(){
	   double s=this.perimetro()/2;
	   return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}//area
	public String tipo(){
	   if( a==b && b==c ) return "Equilatero";
	   if( a==b || a==c || b==c ) return "Isoscele";
	   return "Scaleno";
	}//tipo
	public String toString(){
	   return "Triangolo "+
	          p1+" "+p2+" "+p3+ " tipo "+tipo();
	}//toString
}//Triangolo

public class Geometria {
	public static void main( String []args ){
		   Triangolo t=null;
		   Punto p0=new Punto(0,1);
		   Punto p1=new Punto(3,2);
		   Punto p2=new Punto(4,7);
		   t=new Triangolo( p0, p1, p2 );
		   System.out.println(t);
		   System.out.printf("perimetro=%1.2f area=%1.2f\n",t.perimetro(),t.area());
		   p0.sposta(-4,5);
		   System.out.println(t);
		   System.out.printf("perimetro=%1.2f area=%1.2f\n",t.perimetro(),t.area());

		   t=new Triangolo( new Punto(2,2), new Punto(4,2), new Punto(4,5) );
		   System.out.println(t);
		   System.out.printf("perimetro=%1.2f area=%1.2f\n",t.perimetro(),t.area());
		   t=new Triangolo( new Punto(2,0), new Punto(2,2), new Punto() );
		   System.out.println(t);
		   System.out.printf("perimetro=%1.2f area=%1.2f\n",t.perimetro(),t.area());
	}//main
}//Geometria
