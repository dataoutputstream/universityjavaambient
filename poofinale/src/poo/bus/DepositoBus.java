package poo.bus;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;
import poo.util.*;

public class DepositoBus{

	static class Bus{
		private String id;
		public Bus( String id ){ this.id=id; }
		public String getId(){ return id; }
		public String toString(){ return "BUS "+id; }
	}//Bus

	static Coda<Bus> codaIspezione1=new CodaConcatenata<Bus>(),
	                 codaIspezione2=new CodaConcatenata<Bus>(),
		             autoLavaggio=new CodaConcatenata<Bus>(),
		             officina=new CodaConcatenata<Bus>();

	static String linea;
	static StringTokenizer st;
	static Scanner sc;

	public static void main( String []args ) throws IOException{
		System.out.println("Deposito Bus Cittadini");
		comandi();
		sc=new Scanner( System.in );
		for(;;){
			System.out.print(">");
			linea=sc.nextLine();
			st=new StringTokenizer(linea, " ");
			String comando=st.nextToken().toUpperCase();
			if( comando.equals("A") ) arrivo();
			else if( comando.equals("IS1") ) is1();
			else if( comando.equals("IS2") ) is2();
			else if( comando.equals("FL") ) fineLavaggio();
			else if( comando.equals("FO") ) fineOfficina();
			else if( comando.equals("Q") ){ quit(); break; }
			else errore();
		}//for
	}//main

	static void arrivo(){
		String id=null;
		try{
			id=st.nextToken().toUpperCase();
		}catch( Exception e ){
			System.out.println("Manca l'ID del bus!");
			return;
		}
		Bus bus=new Bus(id);
		codaIspezione1.put( new Bus(id) );
		System.out.println(bus+" entra in CodaIspezione1");
	}//arrivo

	static void is1(){
		if( codaIspezione1.isEmpty() ){
			System.out.println("CodaIspezione1 vuota!");
		}
		else{
			Bus b=codaIspezione1.get();
			double r=Math.random();
			if( r<0.7 ) System.out.println(b+" va via dal deposito senza manutenzione");
			else{
				codaIspezione2.put(b);
				System.out.println(b+" entra in CodaIspezione2");
			}
		}
	}//is1

	static void is2(){
		if( codaIspezione2.isEmpty() ){
			System.out.println("CodaIspezione2 vuota!");
		}
		else{
			Bus b=codaIspezione2.get();
			double r=Math.random();
			if( r<0.6 ){
				autoLavaggio.put(b);
				System.out.println(b+" entra in coda ad AutoLavaggio");
			}
			else{
				officina.put(b);
				System.out.println(b+" entra in coda ad Officina");
			}
		}
	}//is2

	static void fineLavaggio(){
		if( autoLavaggio.isEmpty() ){
			System.out.println("Autolavaggio vuota!");
		}
		else{
			Bus b=autoLavaggio.get();
			System.out.println(b+" va via dal deposito dopo auto lavaggio");
		}
	}//fineLavaggio

	static void fineOfficina(){
		if( officina.isEmpty() ){
			System.out.println("Officina vuota!");
		}
		else{
			Bus b=officina.get();
			System.out.println(b+" va via dal deposito dopo manutenzione in officina");
		}

	}//fineOfficina

	static void comandi(){
		System.out.println();
		System.out.println("Comandi ammessi:");
		System.out.println("A(rrivo id");
		System.out.println("IS1");
		System.out.println("IS2");
		System.out.println("FL");
		System.out.println("FO");
		System.out.println("Q(uit");
		System.out.println();
	}//comandi

	static void errore(){
		System.out.println("Comando sconosciuto!");
		comandi();
	}//errore

	static void quit() throws IOException{
		System.out.print("Vuoi verificare prima di uscire il contenuto delle code (y/n)?");
		String yesno=sc.nextLine();
		if( yesno.toLowerCase().equals("y") ){
			System.out.println("CodaIspezione1:");
			System.out.println(codaIspezione1);
			System.out.println("CodaIspezione2:");
			System.out.println(codaIspezione2);
			System.out.println("Autolavaggio:");
			System.out.println(autoLavaggio);
			System.out.println("Officina:");
			System.out.println(officina);
			System.out.println();
		}
	}//quit

}//DepositoBus
