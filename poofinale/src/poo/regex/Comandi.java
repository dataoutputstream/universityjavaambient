package poo.regex;
import java.util.*;
import java.util.regex.Pattern;
public class Comandi {
	/*
	 * Riconoscitore del linguaggio interattivo di GestioneAgendina:
	 * A cog nom pre tel
	 * R cog nom
	 * T cog nom
	 * P pre tel
	 * E
	 * Z
	 * Q
	 * S nomeFile
	 * C nomeFile
	 */
	public static void main( String[] args ){
		String STR="[A-Za-z][A-Za-z0-9]*";
		String PRE="[0-9]{0,4}";
		String TEL="[0-9]{3,10}";
		String PATHNAME="([A-Za-z]:\\\\|\\\\)?("+STR+"\\\\)*"+STR+"(\\."+STR+")?";

		String AGG="[Aa]\\s+"+STR+"\\s+"+STR+"\\s+"+PRE+"\\s+"+TEL; //A(ggiungi
		String RIM="[Rr]\\s+"+STR+"\\s+"+STR; //R(imuovi
		String TDI="[Tt]\\s+"+STR+"\\s+"+STR; //T(elefono di
		String PDI="[Pp]\\s+"+PRE+"\\s+"+TEL; //P(ersona di
		String ELN="[Ee]"; //E(lenco
		String AZZ="[Zz]"; //aZ(zera
		String QUIT="[Qq]"; //Q(uit
		String SALVA="[Ss]\\s+"+PATHNAME; //S(alva
		String CARICA="[Cc]\\s+"+PATHNAME; //C(arica


		Scanner sc=new Scanner( System.in );
		String linea=null;
		System.out.println("Fornire un comando seguito da INVIO o solo INVIO per terminare");
		for(;;){
			System.out.print("> ");
			linea=sc.nextLine();
			if( linea.length()==0 ) break;
			if( linea.matches(AGG) ) System.out.println("Riconosciuto comando A(ggiungi");
			else if( linea.matches(RIM) ) System.out.println("Riconosciuto comando R(imuovi");
			else if( linea.matches(TDI) ) System.out.println("Riconosciuto comando T(elefonoDi");
			else if( linea.matches(PDI) ) System.out.println("Riconosciuto comando P(ersonaDi");
			else if( linea.matches(ELN) ) System.out.println("Riconosciuto comando E(lenco");
			else if( linea.matches(AZZ) ) System.out.println("Riconosciuto comando aZ(zera");
			else if( linea.matches(QUIT) )   System.out.println("Riconosciuto comand Q(uit");
			else if( linea.matches(SALVA) )	 System.out.println("Riconosciuto comando S(alva");
			else if( linea.matches(CARICA) ) System.out.println("Riconosciuto comando C(arica");
			else System.out.println("Comando non riconosciuto");
		}
		System.out.println("Bye.");
		sc.close();
	}//main
}//Comandi
