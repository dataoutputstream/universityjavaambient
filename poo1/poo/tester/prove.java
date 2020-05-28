package tester;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class prove {
public static void main(String... Args){
	String su="2+2";
			StringTokenizer st =new StringTokenizer(su,"()+-*/%",true);
			System.out.println(su.substring(0, su.length()/2));//Sottostringa da a
			System.out.println(su.trim());//rimuove spazi
			System.out.println(su.lastIndexOf("6"));//Ultimo indice di 6 nota bene le ""
			while(st.hasMoreElements()){
				System.out.println(st.hasMoreTokens());
				st.nextToken();
			}
			
			
			
			
			
	//StringBuilder la particolarita di quest'ultimo sta nel fatto
	//che a differenza della classe string è un tipo mutabile. Molto piè flessibile
			
			StringBuilder sb= new StringBuilder("LA CASA sul LAgo");
			System.out.println(sb.charAt(sb.length()-1));
			System.out.println(sb.append("pRRR"));
			System.out.println(sb.delete(sb.length()-4, sb.length()));
			System.out.println(sb.capacity());
			System.out.println(sb.toString());
			
			
//linked list
			LinkedList<Integer>ine=new LinkedList<Integer>();
			ine.addLast(1);
			ine.addLast(2);
			ine.addLast(3);
			ine.remove(ine.size()-2);
			System.out.println(ine.get(1));
			
}
}
