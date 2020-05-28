package valutatoreespressione;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ValutaEspressione extends Precedenza{
	Character opc=null;


	int valutaEspressione(StringTokenizer st){
		
		LinkedList<Integer>operandi=new LinkedList<Integer>();
		LinkedList<Character>operatori=new LinkedList<Character>();
		boolean flag=true;//mi serve per similcasi (2+(2+3))+2
		loop:
		while(st.hasMoreTokens()){
			String num=st.nextToken();
			if(num.charAt(0)!= '(' && num.charAt(0)!=')' && num.charAt(0)!='+'&& num.charAt(0)!='-'&& num.charAt(0)!='*'&& num.charAt(0)!='/'&& num.charAt(0)!='%' && num.charAt(0)!='^'){
				if(num.matches("[\\d]+")) {operandi.addLast(Integer.parseInt(num));}else throw new RuntimeException("Espressione Malformata");				
			}
		//Gestore parentesi "(" e gestore degli eventuali problemi relativi alla terminazione di una chiamata ricorsiva del metodo.
			if(num.charAt(0)=='('){
				operandi.addLast(valutaEspressione(st));
				if(st.hasMoreTokens()){
					num=st.nextToken();
						if(num.charAt(0)!=')'){
							if(!Character.toString(num.charAt(0)).matches("[+-//*/%//^]")) throw new RuntimeException("Espressione Malformata");
							operatori.addLast(num.charAt(0));
							continue loop;
						}if(num.charAt(0)==')') flag =false;
				}
			 }
			
			
			//operatore
			if(st.hasMoreTokens() && flag){
				opc=st.nextToken().charAt(0);
				if(!Character.toString(opc).matches("[+-//*/%//^)]")) throw new RuntimeException("Espressione Malformata");
			}else{if(flag)continue loop;}
			
			
			//Non mi preoccupo di rimettere flag a true poichè faccio questo per far operare subito la parentedi ) quindi a breve il metodo passera la palla a quello chiamante.
			if(num.charAt(0)==')'| opc==')'){
				StringBuilder sb=new StringBuilder();
				int i=0;
				while(operandi.size()!=0){
					sb.append(operandi.get(i));	
					operandi.remove(i);
					if(operatori.size()!=0){
						sb.append(operatori.get(i));
						operatori.remove(i);
					}
				}
				StringTokenizer str=new StringTokenizer(sb.toString(),"+-*/%^",true);
				int ris= (valutaEspressione(str));
				return ris;
		
			}
		
		if(operandi.size()==1 && st.hasMoreTokens()){
			operatori.addLast(opc);
			continue loop;
		}
		
		if(super.compare(opc,operatori.getLast())==1 && st.hasMoreTokens()){//&&st.hasMoreTokens()
			operatori.addLast(opc);	
			continue loop;
		}
		if(super.compare(opc, operatori.getLast())<=0 && st.hasMoreTokens()){//&& st.hasMoreTokens()
			char op=operatori.pollLast();
			operatori.addLast(opc);
			Integer o1=operandi.get(operandi.size()-2);
			Integer o2=operandi.pollLast();
			operandi.addLast(operazione(op, o1, o2));
			operandi.remove(operandi.size()-2);
			continue loop;
		}
		
		}//quando non ha più token;
		int sizeop=operatori.size();
		for(int i=1;i<sizeop;i++) {
			if(operatori.size()>1) {
				if(compare(operatori.getLast(),operatori.get(operatori.size()-2))==1) {
					int x=operazione(operatori.pollLast(),operandi.get(operandi.size()-2),operandi.pollLast());
					operandi.addLast(x);
					operandi.remove(operandi.size()-2);
				}
		
			}else {break;}
		}
		
		if(operatori.size()!=0){
			System.out.println(operandi);
			while(operatori.size()!=0){
				if(operandi.size()==2) {
					return(operazione(operatori.pollLast(), operandi.get(operandi.size()-2), operandi.pollLast()));
				}
					StringBuilder sb=new StringBuilder();
					int i=0;
					while(operandi.size()!=0){
						sb.append(operandi.get(i));	
						operandi.remove(i);
						if(operatori.size()!=0){
						sb.append(operatori.get(i));
						operatori.remove(i);
						}
					}
					StringTokenizer str=new StringTokenizer(sb.toString(),"+-*/%^()",true);
					int ris=valutaEspressione(str);
					operandi.addLast(ris);
			}		
		}return operandi.getLast();
}//valutaespressione
	
public static boolean benformata(String espressione) {
	System.out.println("controllata");
	String operando="\\d+";
	String operatore="[\\+\\-\\*/%\\^]";
	String paperta="[\\(]?";
	String pchiusa="[\\)]?";
	String base=paperta+operando+operatore+paperta+operando+pchiusa;
	String continuazione = "("+operatore+"\\(?"+operando+"\\)?"+")+";
	String definitiva=base+"("+continuazione+")"+"?";
	System.out.println(espressione.matches(definitiva));
	if(espressione.matches(definitiva))return true;
	throw new RuntimeException("Espressione Malformata");

	}

//metodo statico di utilità.
	private static Integer operazione(char op, Integer o1, Integer o2) {
		switch(op) {
		case '+': return o1+o2;
		case '-': return o1-o2;
		case '*' : return o1*o2;
		case '/': return o1/o2;
		case '%': return o1%o2;
		case '^': return (int) Math.pow(o1, o2);
		}throw new RuntimeException("Malformazione nell'espressione");
	}
	public static void main(String... args){
		Scanner sc=new Scanner(System.in);
		System.out.println("Dammi l'espressione: ");
		String espressione=sc.nextLine();
		sc.close();
		StringTokenizer st =new StringTokenizer(espressione,")(%+-*/^",true);
		if(benformata(espressione));
		ValutaEspressione ve=new ValutaEspressione();
		System.out.println(ve.valutaEspressione(st));
		
		
}


}	
	



