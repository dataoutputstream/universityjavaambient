package valutatoreespressione;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ValutaEspressione extends Precedenza{
	


	int valutaEspressione(StringTokenizer st){
		
		Character opc=null;
		LinkedList<Integer>operandi=new LinkedList<Integer>();
		LinkedList<Character>operatori=new LinkedList<Character>();
		boolean flag=true;
		loop:
		while(st.hasMoreTokens()){
			String num=st.nextToken();
			if(!num.matches("[\\(]")){
				if(num.matches("[\\d]+")) {operandi.addLast(Integer.parseInt(num));}else throw new RuntimeException("Espressione Malformata");				
			}
		//Gestore parentesi "(" e gestore degli eventuali problemi relativi alla terminazione di una chiamata ricorsiva del metodo.
			if(num.charAt(0)=='('){
				operandi.addLast(valutaEspressione(st));
				if(st.hasMoreTokens()){
					num=st.nextToken();
						if(num.charAt(0)!=')'){
							if(!Character.toString(num.charAt(0)).matches("[\\+\\-\\*/%\\^]")) throw new RuntimeException("Espressione Malformata");
							operatori.addLast(num.charAt(0));
							continue loop;
						}if(num.charAt(0)==')'){ opc=num.charAt(0);flag =false;}
				}
			 }
			
			
			//operatore
			if(st.hasMoreTokens() && flag){
				opc=st.nextToken().charAt(0);
				if(!Character.toString(opc).matches("[\\+\\-\\*/%\\^\\)]")) throw new RuntimeException("Espressione Malformata");
			}else{if(flag)continue loop;}
			
			
			//Non mi preoccupo di rimettere flag a true poichè faccio questo per far operare subito la parentesi ) quindi a breve il metodo passera la palla a quello chiamante.
			if(opc==')'){
				StringBuilder sb=new StringBuilder();
				while(operandi.size()!=0){
					sb.append(operandi.pollFirst());	
					if(operatori.size()!=0){
						sb.append(operatori.pollFirst());
					}
				}
				StringTokenizer str=new StringTokenizer(sb.toString(),"+-*/%^",true);
				int ris= (valutaEspressione(str));
				return ris;
		
			}
		
		if(operandi.size()==1){
			operatori.addLast(opc);
			continue loop;
		}
		
		if(super.compare(opc,operatori.getLast())==1){
			operatori.addLast(opc);	
			continue loop;
		}
		if(super.compare(opc, operatori.getLast())<=0){
			char op=operatori.pollLast();
			operatori.addLast(opc);
			Integer o2=operandi.pollLast();
			Integer o1=operandi.pollLast();
			operandi.addLast(operazione(op, o1, o2));
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
				}else {break;}
		
			}else {break;}
		}
		
		if(operatori.size()!=0){
			System.out.println(operandi);
			while(operatori.size()!=0){
				if(operandi.size()==2) {
					return(operazione(operatori.pollLast(), operandi.get(operandi.size()-2), operandi.pollLast()));
				}
					StringBuilder sb=new StringBuilder();
					while(operandi.size()!=0){
						sb.append(operandi.pollFirst());	
						if(operatori.size()!=0){
						sb.append(operatori.pollFirst());
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
	String continuazione = "("+operatore+"\\(?"+operando+"(\\)?)+"+")+";
	String definitiva=base+"("+continuazione+")"+"?";
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
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		
		String espressione;
		for(;;){
			System.out.println("Dammi l'espressione: ");
			System.out.print(">");
			espressione=sc.nextLine();
			try{
			StringTokenizer st =new StringTokenizer(espressione,")(%+-*/^",true);
			if(benformata(espressione));
			ValutaEspressione ve=new ValutaEspressione();
			System.out.println(ve.valutaEspressione(st));
			}catch(Exception e){
				System.out.println("Malformazione nell'espresione.");
			}
		}
		
		
}


}	
	



