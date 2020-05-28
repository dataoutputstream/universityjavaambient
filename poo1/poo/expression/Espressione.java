package expression;
import java.util.*;

public class Espressione {
	private String espressione;
	public Espressione( String espressione ){
		this.espressione=espressione;
	}
	public int valuta(){
		StringTokenizer st=new StringTokenizer( espressione, "()+-*/", true );
		return valuta(st);
	}//valuta
	private int valuta( StringTokenizer st ){
		int ris=valutaOperando(st);	
		while( st.hasMoreTokens() ){
			char op=st.nextToken().charAt(0);
			if( op==')' ) return ris;
			int opnd=valutaOperando(st);
			switch( op ){
				case '+': ris+=opnd; break;
				case '-': ris-=opnd; break;
				case '*': ris*=opnd; break;
				case '/': ris/=opnd; break;
				default : throw new RuntimeException();
			}			
		}
		return ris;
	}//valuta
	private int valutaOperando( StringTokenizer st ){
		String tk=st.nextToken();
		if( tk.charAt(0)=='(' ) return valuta(st);
		int x=Integer.parseInt( tk );
		return x;
	}//valutaOperando
}//Espressione
