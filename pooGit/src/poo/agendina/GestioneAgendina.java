package poo.agendina;

import java.io.*;
import java.util.*;

public class GestioneAgendina {
	static Agendina agenda;
	static String linea;
	static StringTokenizer st;
	static Scanner sc;
	public static void main(String[]args) {
		System.out.println("Programma agendina telefonica\n"
				+ "Scegli tra: 0-AgendinaAL 1-AgendinaLL 2-AgendinaTreeSet 3-AgendinaHashSet 4-AgendinaMap 5-AgendinaVector");
		sc = new Scanner(System.in);
		int tipo = 0, capacita = 0;
		do {
			tipo = sc.nextInt();
			if (tipo < 0 || tipo > 5)
				System.out.print("Digitare un numero da 0 a 5: ");
		} while (tipo < 0 || tipo > 5);
		if (tipo == 0 || tipo == 5) {
			System.out.print("Capacita' = ");
			capacita = sc.nextInt();
		}
		switch (tipo) {
			case 0: 
				agenda = new AgendinaAL(capacita); break;
			case 1: 
				agenda = new AgendinaLL(); break;
			case 2: 
				agenda = new AgendinaTreeSet(); break;
			case 3: 
				agenda = new AgendinaHashSet(); break;
			case 4: 
				agenda = new AgendinaMap(); break;
			default:
				agenda = new AgendinaVector(capacita);
		}
		sc.nextLine();
		comandi(); char comando;
		for (;;) {
			System.out.print("> ");
			String linea = sc.nextLine();
			if (linea.equals("")) continue;
			st = new StringTokenizer(linea, " ");
			try {
				comando = st.nextToken().toUpperCase().charAt(0);
				switch (comando) {
					case 'A': aggiungiNominativo(); break;
					case 'R': rimuoviNominativo(); break;
					case 'T': ricercaTelefono(); break;
					case 'P': ricercaPersona(); break;
					case 'Z': azzera(); break;
					case 'E': mostraElenco(); break;
					case 'S': salva(); break;
					case 'C': carica(); break;
					case 'Q': quit(); return;
					default: errore();
				}
			} catch (Exception e) {
				System.out.println("Dati incompleti!");
				e.printStackTrace();
			}
		}
	} // main
	static void comandi() {
		System.out.println(	"\nComandi ammessi e relativi parametri:" +
					"\nA(ggiungi  cog  nom  pre  tel" +
					"\nR(imuovi  cog  nom" +
					"\nZ(azzera" +
					"\nT(elefono  cog  nom" +
					"\nP(persona  pre  tel" +
					"\nE(lenco" +
					"\nS(alva  nomefile" +
					"\nC(arica  nomefile" +
					"\nQ(uit\n"			);
	}//comandi
	static void aggiungiNominativo() {
		String cog = st.nextToken().toUpperCase();
		String nom = st.nextToken().toUpperCase();
		String pre = st.nextToken();
		String tel = st.nextToken();
		Nominativo n = new Nominativo(cog, nom, pre, tel);
		agenda.aggiungi(n);
	} // aggiungiNominativo
	static void rimuoviNominativo() {
		String cog = st.nextToken().toUpperCase();
		String nom = st.nextToken().toUpperCase();
		agenda.rimuovi(new Nominativo(cog, nom, "", ""));
	} // rimuoviNominativo
	static void ricercaTelefono() {
		String cog = st.nextToken().toUpperCase();
		String nom = st.nextToken().toUpperCase();
		Nominativo n = agenda.cerca(new Nominativo(cog, nom, "", ""));
		if (n == null) System.out.println("Nominativo inesistente!");
		else System.out.println(n.getPrefisso() + "-" + n.getTelefono());
	} // ricercaTelefono
	static void ricercaPersona() {
		String pre = st.nextToken();
		String tel = st.nextToken();
		Nominativo n = agenda.cerca(pre, tel);
		if (n == null) System.out.println("Nominativo inesistente!");
		else System.out.println(n.getCognome() + " " + n.getNome());
	} // ricercaPersona
	static void azzera() { agenda.svuota(); }
	static void mostraElenco() {
		System.out.println(agenda);
	} // mostraElenco
	static void salva() {
		String nomeFile = st.nextToken();
		try {
			agenda.salva(nomeFile);
		} catch (Exception e) {
			System.out.println("Errore di scrittura!");
		}
	} // salva
	static void carica() {
		String nomeFile = st.nextToken();
		if (!(new File(nomeFile)).exists())
			System.out.println("File inesistente!");
		else
			try {
				agenda.ripristina(nomeFile);
			} catch (IOException e) {
				System.out.println("Errore di lettura!");
			}
	} // carica
	static void errore() {
		System.out.println("Comando sconosciuto!");
		comandi();
	} // errore
	static void quit() throws IOException {
		System.out.print("Vuoi salvare il contenuto dell'agenda prima di terminare? (y/n) ");
		if (sc.nextLine().toLowerCase().equals("y")) {
			String nomeFile = null;
			do {
				try {
					System.out.print("Nome file = ");
					nomeFile = sc.nextLine();
				} catch (Exception e) { nomeFile = null; }
				if (nomeFile == null || nomeFile.length() == 0)
					System.out.println("Errore di input. Ridare il nome del file.");
				else
					try {
						agenda.salva(nomeFile);
					} catch (IOException e) {
						System.out.println("Errore di scrittura! Reinserire:");
						continue;
					}
			} while (nomeFile == null || nomeFile.length() == 0);
		}
		System.out.println("Bye!");
	}//quit
} // GestioneAgendina
