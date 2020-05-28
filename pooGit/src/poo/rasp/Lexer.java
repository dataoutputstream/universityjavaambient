package poo.rasp;

import java.util.*;
import java.io.*;

public class Lexer {
	public enum Sim {IDENT, NUMBER, END_LABEL, MODE, SP, UNKNOWN, EOF};
	private int num;
	private String str, nomeFileSorgente, nomeFileListing, tk, linea;
	private StringTokenizer st;
	private BufferedReader br;
	private PrintWriter pw;
	private int lineaCorrente = 0;
	private boolean enabledEcho = true;
	private String ID = "[a-zA-Z][a-zA-Z0-9_$]*";
	private String INT = "-?[0-9]+";
	public Lexer(String nomeFileSorgente, String nomeFileListing) throws IOException {
		this.nomeFileSorgente = nomeFileSorgente;
		this.nomeFileListing = nomeFileListing;
		File f = new File(nomeFileSorgente);
		if (!f.exists()) throw new RuntimeException("File " + nomeFileSorgente + " non esistente!");
		br = new BufferedReader(new FileReader(f));
		pw = new PrintWriter(new FileWriter(nomeFileListing));
		nextLine();
	} // Costruttore
	private void nextLine() throws IOException {
		do {
			linea = br.readLine();
			if (linea != null) {
				st = new StringTokenizer(linea, " :#@;\t", true);
				lineaCorrente++;
				if (enabledEcho) pw.println(lineaCorrente + ": " + linea);
			}
		} while (linea != null && !st.hasMoreTokens());
	} // nextLine
	public void rewind() throws IOException {
		br.close();
		br = new BufferedReader(new FileReader(nomeFileSorgente));
		nextLine();
	} // rewind
	public String getStr() { return str; }
	public int getNum() { return num; }
	protected void finalize() throws IOException { br.close(); pw.close(); }
	public void error(String err) { pw.println(err); pw.flush(); System.exit(-1); }
	public void toListing(String s) { pw.println(); pw.println(s); pw.flush(); }
	public void setEnabledEcho(boolean value) { enabledEcho = value; }
	public Sim prossimoSimbolo() throws IOException {
		if (linea == null) { return Sim.EOF; }
		if (!st.hasMoreTokens()) {
			nextLine();
			if (linea == null) return Sim.EOF;
		}
		tk = st.nextToken();
		if (tk.charAt(0) == ' ' || tk.charAt(0) == '\t') { str = " "; return Sim.SP; }
		if (tk.matches(ID)) { str = tk; return Sim.IDENT; }
		if (tk.matches(INT)) { num = Integer.parseInt(tk); return Sim.NUMBER; }
		if (tk.charAt(0) == ':') { str = tk; return Sim.END_LABEL; }
		if (tk.charAt(0) == '#' || tk.charAt(0) == '@') { str = tk; return Sim.MODE; }
		if (tk.charAt(0) == ';') { nextLine(); str = " "; return Sim.SP; }
		return Sim.UNKNOWN;
	} // prossimoSimbolo
} // Lexer
