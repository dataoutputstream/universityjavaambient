package EspressioneRPN;

import java.util.Scanner;
import java.util.Stack;

public class EspressioneRPN {
	
	public static void main(String...Args) {
		System.out.println("Valutatore Interattivo di Espressioni RPN");
		System.out.println("La stringa . termina il programma");
		Scanner Sc=new Scanner(System.in);
		//Sola Stringa di numeri;
		String Operando="\\d+";
		//Pattern Operatori raddoppiate"\\" per i metasimboli che devono
		//assumere significato operativo;
		String Operatore="[\\+\\-\\*/]";
		//Spazio che comprende tabulazione etc;
		String Spazio="\\s+";
		//Componente degenere solo l'operando
		String Degenere=Operando;
		//Operando+spazio+Operando+Spazio+Operatore o operando 
		//il tutto ripetuto almeno una volta
		String Generale=Operando+Spazio+Operando+"("+Spazio+"("+Operatore+"|"+Operando+"))+";
		//definizione di Stringa RPN
		String RPNEXP="("+Degenere+"|"+Generale+")";
		for(;;){
			System.out.print("> ");
			String linea=Sc.nextLine();
			if( linea.equals(".") ) break;
			if( !linea.matches(RPNEXP) ){
			System.out.println("\t"+linea+" = "+"espressione malformata!");
			}
			else{
			System.out.print("\t"+linea+" = ");
				try{
					System.out.println( valuta(linea) );
				}catch( Exception e ){
					System.out.println("espressione malformata!");
				}//catch
			}//else
		}//for
			Sc.close();
			System.out.println("Bye.");
	}
	
	static int valuta( String linea ){
		Stack<Integer> stack=new Stack<>();
		Scanner sc=new Scanner( linea );
		sc.useDelimiter("\\s+");
		while( sc.hasNext() ){
			String tk=sc.next();
			if( tk.matches("[\\d]+") ) stack.push( Integer.parseInt(tk) );
			else{
				char operatore=tk.charAt(0);
				int op2=stack.pop(), op1=stack.pop();
				switch( operatore ){
					case '+': stack.push( op1+op2 ); break;
					case '-': stack.push( op1-op2 ); break;
					case '*': stack.push( op1*op2 ); break;
					default : stack.push( op1/op2 );
				}
			}
		}
		sc.close();
		int ris=stack.pop();
		if( !stack.isEmpty() ) throw new RuntimeException();
		return ris;
		}//valuta
		}//RPN

