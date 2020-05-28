package poo.file;

import java.io.*;
import java.util.*;

public class WCL {
	public static void main(String[]args) {
		System.out.println("Conta parole, caratteri e linee di un file di testo");
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome file: ");
		String nomeFile = sc.nextLine();
		BufferedReader br = null; int cw = 0, cc = 0, cl = 0;
		try {
			br = new BufferedReader(new FileReader(nomeFile));
			for (;;) {
				String linea = br.readLine();
				if (linea == null) break;
				cl++; cc += linea.length();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("[^A-Za-z0-9]+");
				while (sl.hasNext()) { sl.next(); cw++; }
			}
		} catch (Exception e) {
			System.out.println("Errore di lettura!");
		} finally {
			try {
				if (br != null) br.close();
			} catch(Exception e) {
				System.out.println("Errore di lettura!");
			}
		}
		System.out.println("Parole: " + cw + ", Caratteri: " + cc + ", Linee: " + cl);
	} // main
} // WCL
