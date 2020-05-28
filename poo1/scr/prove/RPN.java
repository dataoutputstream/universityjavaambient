package prove;

	import java.util.Scanner;
	import util.*;
	
	public class RPN {
	public static void main( String[] args ){
		System.out.println("Valutatore Interattivo di Espressioni RPN");
		System.out.println("La stringa . termina il programma");
		Scanner sc=new Scanner( System.in );
		String OPERAND="\\d+";
		String OPERATOR="[\\+\\-\\*/]";
		String BLANK="\\s+";
		String DEGENERE=OPERAND;
		String GENERALE=OPERAND+BLANK+OPERAND+
				"("+BLANK+"("+OPERATOR+"|"+OPERAND+"))+";
		String RPNEXP="("+DEGENERE+"|"+GENERALE+")";
		for(;;){
			System.out.print("> ");
			String linea=sc.nextLine();
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
				}
			}
		}
		sc.close();
		System.out.println("Bye.");
	}//main
	static int valuta( String linea ){
		Stack<Integer> stack=new StackConcatenato<>();
		Scanner sc=new Scanner( linea );
		sc.useDelimiter("\\s+");
		while( sc.hasNext() ){
			String tk=sc.next();
			if( tk.matches("[\\d]+") ) stack.push( Integer.parseInt(tk) );
			else{
				char op=tk.charAt(0);
				int op2=stack.pop(), op1=stack.pop();
				switch( op ){
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


