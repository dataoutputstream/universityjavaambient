package poo.util;

import java.util.*;
import poo.grafo.*;

public final class Grafi {
	private Grafi() {}
	public static <N> void visitaInAmpiezza(Grafo<N> g, N u, LinkedList<N> lista) {
		if (g == null || !g.esisteNodo(u)) throw new IllegalArgumentException();
		Set<N> visitati = new HashSet<N>();
		visitaInAmpiezza(g, u, visitati, lista);
	} // visitaInAmpiezza
	private static <N> void visitaInAmpiezza(Grafo<N> g, N u, Set<N> visitati, LinkedList<N> lista) {
		LinkedList<N> coda = new LinkedList<N>();
		lista.addLast(u); visitati.add(u);
		coda.addLast(u);
		while (!coda.isEmpty()) {
			N v = coda.removeFirst();
			Iterator<? extends Arco<N>> it = g.adiacenti(v);
			while (it.hasNext()) {
				N adiacente = it.next().getDestinazione();
				if (!visitati.contains(adiacente)) {
					lista.addLast(adiacente); visitati.add(adiacente);
					coda.addLast(adiacente);
				}
			}
		}
	} // visitaInAmpiezza
	private static <N> void visitaInAmpiezza(Grafo<N> g, N u, Set<N> visitati) {
		LinkedList<N> coda = new LinkedList<N>();
		visitati.add(u); coda.addLast(u);
		while (!coda.isEmpty()) {
			N v = coda.removeFirst();
			Iterator<? extends Arco<N>> it = g.adiacenti(v);
			while (it.hasNext()) {
				N adiacente = it.next().getDestinazione();
				if (!visitati.contains(adiacente)) {
					visitati.add(adiacente); coda.addLast(adiacente);
				}
			}
		}
	} // visitaInAmpiezza
	public static <N> void visitaInProfondita(Grafo<N> g, N u, LinkedList<N> lista) {
		if (g == null || !g.esisteNodo(u)) throw new IllegalArgumentException();
		Set<N> visitati = new HashSet<N>();
		visitaInProfondita(g, u, visitati, lista);
	} // visitaInProfondita
	private static <N> void visitaInProfondita(Grafo<N> g, N u, Set<N> visitati, LinkedList<N> lista) {
		lista.addLast(u); visitati.add(u);
		Iterator<? extends Arco<N>> it = g.adiacenti(u);
		while (it.hasNext()) {
			N adiacente = it.next().getDestinazione();
			if (!visitati.contains(adiacente))
				visitaInProfondita(g, adiacente, visitati, lista);
		}
	} // visitaInProfondita
	public static <N> Grafo<N> raggiungibilita(Grafo<N> g) {
		Grafo<N> gr = g.copia();
		for (int n = 2; n < gr.numNodi(); n++)
			for (N i: gr)
				for (N j: gr)
					for (N k: gr)
						if (gr.esisteArco(i, k) && gr.esisteArco(k, j))
							gr.insArco(i, j);
		return gr;
	} // raggiungibilita
	public static <N> int componentiConnesse(Grafo<N> g) {
		Set<N> visitati = new HashSet<N>();
		int ncc = 0;
		for (N u: g)
			if (!visitati.contains(u)) {
				ncc++;
				visitaInAmpiezza(g, u, visitati);
			}
		return ncc;
	} // componentiConnesse
	public static <N> boolean aciclico(GrafoOrientato<N> g) {
		Set<N> rimossi = new HashSet<N>();
		Map<N, Integer> gradoEntrata = new HashMap<N, Integer>();
		LinkedList<N> daRimuovere = new LinkedList<N>();
		int gE = 0;
		for (N u: g) {
			gE = g.gradoEntrata(u);
			gradoEntrata.put(u, gE);
			if (gE == 0) daRimuovere.addLast(u);
		}
		while (!daRimuovere.isEmpty()) {
			N u = daRimuovere.removeFirst();
			rimossi.add(u);
			Iterator<? extends Arco<N>> it = g.adiacenti(u);
			while (it.hasNext()) {
				N adiacente = it.next().getDestinazione();
				gE = gradoEntrata.get(adiacente) - 1;
				gradoEntrata.put(adiacente, gE);
				if (gE == 0) daRimuovere.addLast(adiacente);
			}
		}
		for (N u: g) if (!rimossi.contains(u)) return false;
		return true;
	} // aciclico
	public static void main(String[]args) {
		GrafoOrientato<Integer> g = new GrafoOrientatoImpl<Integer>();
		// GrafoNonOrientato<Integer> g = new GrafoNonOrientatoImpl<Integer>();
		g.insNodo(1); g.insNodo(2); g.insNodo(3); g.insNodo(4);
		g.insNodo(5); g.insNodo(6); g.insNodo(7); g.insArco(1, 2);
		g.insArco(1, 3); g.insArco(2, 4); g.insArco(3, 5); g.insArco(4, 5);
		g.insArco(5, 2); g.insArco(5, 3); g.insArco(4, 6); g.insArco(7, 6);
		GrafoOrientato<Integer> ge = new GrafoOrientatoImpl<Integer>();
		ge.insNodo(5); ge.insNodo(6); ge.insNodo(7); ge.insNodo(1);
		ge.insNodo(2); ge.insNodo(3); ge.insNodo(4); ge.insArco(1, 3);
		ge.insArco(1, 2); ge.insArco(4, 6); ge.insArco(2, 4); ge.insArco(3, 5);
		ge.insArco(4, 5); ge.insArco(5, 3); ge.insArco(5, 2); ge.insArco(7, 6);
		System.out.println("g == ge ? " + g.equals(ge));
		System.out.println("g.hashCode() = " + g.hashCode() + ", ge.hashCode() = " + ge.hashCode());
		System.out.println(g);
		LinkedList<Integer> l = new LinkedList<Integer>();
		visitaInAmpiezza(g, 1, l);
		System.out.println("Visita in ampiezza: " + l);
		l.clear();
		visitaInProfondita(g, 1, l);
		System.out.println("Visita in profondita': " + l);
		System.out.println();
		System.out.println("Grafo g aciclico? " + aciclico(g));
		Grafo<Integer> gr = raggiungibilita(g);
		System.out.println("Grafo di raggiungibilita':\n" + gr);
		Arco<Integer> a = new Arco<Integer>(2, 4);
		System.out.println("Rimozione arco: " + a);
		g.rimuoviArco(a);
		System.out.println(g);
		gr = raggiungibilita(g);
		System.out.println("Nuovo grafo di raggiungibilità:\n" + gr);
		System.out.println("Numero componenti connesse: " + componentiConnesse(g));
		int n = 5;
		System.out.println("Rimozione nodo " + n);
		g.rimuoviNodo(n);
		System.out.println(g);
		g.insArco(a); g.insNodo(5); g.insArco(3, 5);
		g.insArco(4, 5); g.insArco(5, 2); g.insArco(5, 3);
		System.out.println("Grafo ricomposto:\n" + g);
		System.out.println("Rimozione nodo 5 con iteratore:");
		Iterator<Integer> it = g.iterator();
		while (it.hasNext())
			if (it.next() == 5) { it.remove(); break; }
		System.out.println(g);
	} // main
} // Grafi
