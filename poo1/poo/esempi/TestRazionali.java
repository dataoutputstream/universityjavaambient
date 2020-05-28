package esempi;

import razionali.Razionale;

public class TestRazionali{
   public static void main( String []args ){//DEMO
	   Razionale r1=new Razionale(2,3);
	   Razionale r2=new Razionale(-3,5);
	   Razionale r3=r1.add(r1.mul(r2));
	   System.out.println(r1+"+"+r1+"*"+r2+"="+r3);
   }//main
}//Testrazionali