package poo.recursion;
import java.util.*;
import poo.util.*;

public class AlberoEspressione {
	
	private static enum Op{ VISITA, SCRIVI }
	
	//struttura dei nodi
	private static class Nodo{
		Nodo figlioS, figlioD;
	}//Nodo
	private static class NodoOperando extends Nodo{
		int info;
		public String toString(){ return ""+info; }
	}//NodoOperando
	private static class NodoOperatore extends Nodo{
		char op;
		public String toString(){ return ""+op; }
	}//NodoOperatore
	
	private Nodo radice=null;
	
	//metodi privati helper
	private void inOrder( Nodo radice ){
		if( radice!=null ){
			if( radice instanceof NodoOperatore ) 
				System.out.print('(');
			inOrder( radice.figlioS );
			System.out.print( radice );
			inOrder( radice.figlioD );
			if( radice instanceof NodoOperatore ) 
				System.out.print(')');
		}
	}//inOrder
	
	private void preOrder( Nodo radice ){
		if( radice!=null ){
			System.out.print( radice+" " );
			preOrder( radice.figlioS );
			preOrder( radice.figlioD );
		}		
	}//preOrder
	
	private void postOrder( Nodo radice ){
		if( radice!=null ){
			postOrder( radice.figlioS );
			postOrder( radice.figlioD );
			System.out.print( radice+" " );
		}		
	}//postOrder
	
	private void postOrder_ite( Nodo radice ){
		class Pair{//simula area dati del metodo ricorsivo
			Nodo radice;
			Op op;
			public Pair( Nodo radice, Op op ){
				this.radice=radice;
				this.op=op;
			}
			public Nodo getRadice(){ return this.radice; }
			public Op getOp(){ return this.op; }
		}//Pair
		
		poo.util.Stack<Pair> pila=new StackConcatenato<Pair>();
		//simula prima chiamata
		pila.push( new Pair(radice,Op.VISITA) );
		while( !pila.isEmpty() ){
			Pair p=pila.pop();
			if( p.getOp()==Op.SCRIVI ){
				Nodo rad=p.getRadice();
				System.out.print( rad+" " );
			}
			else{//simula chiamate ricorsive - in ordine inverso
				if( p.getRadice()!=null ){
					pila.push( new Pair(p.getRadice(),Op.SCRIVI) );
					pila.push( new Pair(p.getRadice().figlioD, Op.VISITA) );
					pila.push( new Pair(p.getRadice().figlioS, Op.VISITA) );
				}
			}
		}
	}//postOrder_ite	
	
	private Nodo buildEspressione( StringTokenizer st ){
		Nodo radice=buildOperando(st);
		while( st.hasMoreTokens() ){
			char op=st.nextToken().charAt(0);
			if( op==')' ) return radice;
			NodoOperatore operatore=new NodoOperatore();
			operatore.op=op;
			operatore.figlioS=radice;
			Nodo opnd=buildOperando(st);
			operatore.figlioD=opnd;
			radice=operatore;
		}
		return radice;
	}//buildEspressione

	private Nodo buildOperando( StringTokenizer st ){
		String opnd=st.nextToken();
		if( opnd.charAt(0)=='(' ){
			Nodo operand=buildEspressione(st);
			return operand;
		}
		else{
			NodoOperando numero=new NodoOperando();
			numero.info=Integer.parseInt(opnd);
			numero.figlioS=null; numero.figlioD=null;
			return numero;
		}
	}//buildOperando
	
	private Nodo buildPre( StringTokenizer st ){
		//lasciato come esercizio
		return null;//TODO
	}//buildPre
	
	private Nodo buildPost( StringTokenizer st ){
		StackConcatenato<Nodo> pila=new StackConcatenato<Nodo>();
		String NUMERO="[0-9]+";
		String OPERATORE="[\\+\\-\\*/%]";
		while( st.hasMoreTokens() ){
			String tk=st.nextToken();
			if( tk.matches(NUMERO) ){
				NodoOperando opnd=new NodoOperando();
				opnd.info=Integer.parseInt(tk);
				opnd.figlioS=null; opnd.figlioD=null;
				pila.push(opnd);
			}
			else if( tk.matches(OPERATORE) ){
				NodoOperatore nop=new NodoOperatore();
				nop.op=tk.charAt(0);
				nop.figlioD=pila.pop();
				nop.figlioS=pila.pop();
				pila.push(nop);
			}
		}
		if( pila.size()!=1 )
			throw new RuntimeException();
		return pila.pop();
	}//buildPost
	
	private int valore( Nodo radice ){
		if( radice instanceof NodoOperando ) 
			return ((NodoOperando)radice).info;
		int val1=valore( radice.figlioS );
		int val2=valore( radice.figlioD );
		switch( ((NodoOperatore)radice).op ){
			case '+': return val1+val2; 
			case '-': return val1-val2; 
			case '*': return val1*val2; 
			case '/': return val1/val2; 
			case '%': return val1%val2;
			default: throw new RuntimeException("Simbolo "+
						((NodoOperatore)radice).op+" inatteso");
		}//switch
	}//valore
	
	//metodi pubblici di interfaccia
	public void inOrder(){ inOrder(radice); }
	public void preOrder(){ preOrder(radice); }
	public void postOrder(){ 
		//postOrder(radice);
		postOrder_ite(radice);
	}//postOrder
	public int valore(){ 
		if( radice==null ) throw new RuntimeException("Albero vuoto!");
		return valore(radice);	}
	public void build( String expr ){
		//costruisce l'albero a partire da un'espressione infix
		StringTokenizer	st=
			new StringTokenizer(expr,"+-*/%()",true);
		radice=buildEspressione( st );
	}//build
	public void buildPre( String expr ){
		//costruisce l'albero a partire da un'espressione prefix
		StringTokenizer	st=
			new StringTokenizer(expr," "); //costituenti separati da spazi
		radice=buildPre( st );
	}//buildPre
	public void buildPost( String expr ){
		//costruisce l'albero a partire da un'espressione postfix
		StringTokenizer	st=
			new StringTokenizer(expr," "); //costituenti separati da spazi
		radice=buildPost( st );		
	}//buildPost
	
	public static void main( String []args ){
		Scanner sc=new Scanner(System.in);
		System.out.println("Valutatore di espressioni aritmetiche intere.");
		System.out.println("Forma infissa con operatori + - * / % assunti equiprioritari.");
		System.out.println("Non sono ammessi gli spazi bianchi.");
		System.out.println("E' possibile avviluppare un'espressione in parentesi ( e ).");
		System.out.println("pre visualizza la versione prefissa.");
		System.out.println("post visualizza la versione postfissa.");
		System.out.println("in visualizza la versione infissa parentetica.");
		System.out.println(". chiude il programma.");
		String expr=null;
		String EXPR="[\\+\\-\\*/%0-9\\(\\)]+";
		AlberoEspressione ae=new AlberoEspressione();
		for(;;){
			System.out.print("<<");
			expr=sc.nextLine();
			if( expr.equals(".") ) break;
			if( expr.equalsIgnoreCase("pre") ){
				ae.preOrder();
				System.out.println();
			}
			else if( expr.equalsIgnoreCase("post") ){
				ae.postOrder();
				System.out.println();
			}
			else if( expr.equalsIgnoreCase("in") ){
				ae.inOrder();
				System.out.println();
			}
			else{
				try{
					if( !expr.matches(EXPR) ) throw new RuntimeException();
					//matches: condizione necessaria
					ae.build(expr);
					System.out.println(">>"+ae.valore());
				}catch(Exception e){
					System.out.println("Espressione malformata!");
				}
			}
		}
		sc.close();
		System.out.println("Bye.");
	}//main
}//AlberoEspressione
