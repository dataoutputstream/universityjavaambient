package tester;

import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JOptionPane;

public class iterator {
	static LinkedList<Integer> topo;
	static ListIterator<Integer> lit;
	static Integer ris=0;
	static Integer add(LinkedList<Integer> lista){
		while(lit.hasNext()){
			ris+=lit.next();
		}
		
		
		return ris;
	}
	
public static void main(String[] Args){
	int x;
	for(;;){
		String input=JOptionPane.showInputDialog("dammi dimensione LinkedList");
			try{
				x=Integer.parseInt(input);
				topo=new LinkedList<Integer>();
				lit= topo.listIterator(topo.size());
				break;
				
			}catch(RuntimeException e){
				JOptionPane.showMessageDialog(null, "Dammi sta minchia di dimensione");
			}
		
		
	}
	for(int i=0;i<x;i++){
		Integer input=Integer.parseInt(JOptionPane.showInputDialog("dammi l'elemento "+i+" :"));
			try{
				lit.add(input);
			}catch(RuntimeException e){
				JOptionPane.showMessageDialog(null, "Damme sti numeriiiiiiii");
			}
	}

JOptionPane.showMessageDialog(null, "IL RISULTATO DELLA SOMMA E' :"+ add(topo));
}
}
