package poo.testo;

import java.io.*;
import java.util.*;

public class GestoreTesto {
	public enum Simbolo {WORD, EOF}
	private boolean EOF = false;
	private String linea = null;
	private Scanner input, scan;
	private int numeroLineaCorrente = 0;
	private String word;
	public GestoreTesto(File f) throws IOException {
		input = new Scanner(f);
	} // costruttore
	private void avanza() {
		try {
			if (linea == null || !scan.hasNext()) {
				linea = input.nextLine();
				numeroLineaCorrente++;
				System.out.println(numeroLineaCorrente + ": " + linea);
				scan=new Scanner(linea);
				scan.useDelimiter("[^a-zA-Z_]+");
			}
		} catch(Exception ioe) {
			EOF = true; input.close();
		}
	} // avanza
	public Simbolo prossimoSimbolo() throws IOException {
		do {
			avanza();
		} while(!EOF && !scan.hasNext());
		if (EOF) return Simbolo.EOF;
		word = scan.next();
		return Simbolo.WORD;
	} // prossimoSimbolo
	public String getString() { return word; }
	public int getNumeroLinea() { return numeroLineaCorrente; }
} // GestoreTesto
