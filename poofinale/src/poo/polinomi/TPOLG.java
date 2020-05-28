package poo.polinomi;

public class TPOLG{
	public static void main( String []args ){
		Polinomio p1=new PolinomioLL();
		p1.add( new Monomio( 2, 3 ) );
		p1.add( new Monomio( 0, 1 ) );
		p1.add( new Monomio( -3, 2 ) );
		p1.add( new Monomio( -2, 0 ) );
		Polinomio p2=new PolinomioConcatenato();
		p2.add( new Monomio( -2, 3 ) );
		p2.add( new Monomio( 3, 1 ) );
		p2.add( new Monomio( -4, 5 ) );
		System.out.println("p1="+p1);
		System.out.println("p2="+p2);
		System.out.println("p1(4)="+p1.valore(4));
		System.out.println("p2(2)="+p2.valore(2));
		System.out.println("p1+p2="+p1.add(p2) );
		Polinomio pm=p1.mul(p2);
		System.out.println("p1*p2="+pm );
		System.out.println("(p1*p2)(2)="+pm.valore(2));
		System.out.println();
	}//main
}//TPOLG