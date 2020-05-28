package poo.agendina;

import java.io.*;
import java.util.*;

public abstract class AgendinaAstratta implements Agendina {
	
	public abstract void aggiungi(Nominativo n);
	public abstract Iterator<Nominativo> iterator();
	
	public int size() {
		int c = 0;
		for (Nominativo n: this) c++;
		return c;
	} // size
	public void svuota() {
		Iterator<Nominativo> it = iterator();
		while (it.hasNext()) {
			it.next(); it.remove();
		}
	} // svuota
	public void rimuovi(Nominativo n) {
		Iterator<Nominativo> it = iterator();
		while (it.hasNext()) {
			Nominativo x = it.next();
			if (x.equals(n)) { it.remove(); return; }
			if (x.compareTo(n) > 0) return;
		}
	} // rimuovi
	public Nominativo cerca(Nominativo n) {
		for (Nominativo x: this) {
			if (x.equals(n)) return x;
			if (x.compareTo(n) > 0) return null;
		}
		return null;
	} // cerca
	public Nominativo cerca(String prefisso, String telefono) {
		for (Nominativo x: this)
			if (x.getPrefisso().equals(prefisso) && x.getTelefono().equals(telefono))
				return x;
		return null;
	} // cerca
	public void salva(String nomeFile) throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nomeFile)));
		for (Nominativo n: this) pw.println(n);
		pw.close();
	} // salva
	public void ripristina(String nomeFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(nomeFile));
		String linea = null; StringTokenizer st = null;
		LinkedList<Nominativo> tmp = new LinkedList<Nominativo>();
		for (;;) {
			linea = br.readLine();
			if (linea == null) break; // fine del file
			st = new StringTokenizer(linea, " -");
			try {
				String cognome = st.nextToken();
				String nome = st.nextToken();
				String prefisso = st.nextToken();
				String telefono = st.nextToken();
				tmp.add(new Nominativo(cognome, nome, prefisso, telefono));
			} catch(Exception e) {
				br.close();
				throw new IOException();
			}
		}
		br.close(); svuota();
		for (Nominativo n: tmp) aggiungi(n);
	} // ripristina
	public String toString() {
		StringBuilder sb = new StringBuilder(40 * size());
		for (Nominativo x: this) {
			sb.append(x); sb.append('\n');
		}
		return sb.toString();
	} // toString
	public int hashCode() {
		int h = size(); final int MOLT = 43;
		for (Nominativo n: this)
			h = h * MOLT + n.hashCode();
		return h;
	} // hashCode
	public boolean equals(Object o) {
		if (!(o instanceof Agendina)) return false;
		if (o == this) return true;
		Agendina a = (Agendina)o;
		if (this.size() != a.size()) return false;
		Iterator<Nominativo> i1 = iterator();
		Iterator<Nominativo> i2 = a.iterator();
		while (i1.hasNext())
			if (!i1.next().equals(i2.next()))
				return false;
		return true;
	} // equals
} // AgendinaAstratta
