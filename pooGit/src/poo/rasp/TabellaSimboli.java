package poo.rasp;

import java.util.*;

public class TabellaSimboli implements Iterable<Simbolo> {
	private Map<Simbolo, Simbolo> tabella = new TreeMap<Simbolo, Simbolo>();
	public Iterator<Simbolo> iterator() { return tabella.keySet().iterator(); }
	public void add(Simbolo s) { tabella.put(s, s); }
	public void add(String s) { add(new Simbolo(s)); }
	public void remove(Simbolo s) { tabella.remove(s); }
	public void remove(String s) { remove(new Simbolo(s)); }
	public Simbolo find(Simbolo s) { return tabella.get(s); }
	public Simbolo find(String s) { return find(new Simbolo(s)); }
	public void clear() { tabella.clear(); }
	public String toString() {
		StringBuilder sb = new StringBuilder(500);
		sb.append("Contenuto tabella dei simboli\n");
		for (Simbolo s: this) sb.append(s + "\n");
		return sb.toString();
	} // toString
} // TabellaSimboli
