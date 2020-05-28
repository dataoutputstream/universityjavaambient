package poo.grafo;

import java.util.*;

public class GrafoOrientatoPesatoImpl<N> extends GrafoOrientatoPesatoAstratto<N> {
	private Map<N, LinkedList<ArcoPesato<N>>> grafo = new HashMap<N, LinkedList<ArcoPesato<N>>>();
	public int numNodi() { return grafo.size(); }
	public boolean esisteNodo(N u) { return grafo.containsKey(u); }
	public void insNodo(N u) {
		if (esisteNodo(u)) throw new RuntimeException("Nodo gia' presente durante insNodo");
		grafo.put(u, new LinkedList<ArcoPesato<N>>());
	} // insNodo
	public void insArco(ArcoPesato<N> ap) {
		if (!grafo.containsKey(ap.getOrigine()) || !grafo.containsKey(ap.getDestinazione()))
			throw new RuntimeException("Nodo/i non presente/i durante insArco");
		LinkedList<ArcoPesato<N>> l = grafo.get(ap.getOrigine());
		Iterator<ArcoPesato<N>> it = l.iterator();
		while (it.hasNext())
			if (it.next().getDestinazione().equals(ap.getDestinazione())) it.remove();
		l.add(ap);
	} // insArco
	public void rimuoviNodo(N u) {
		grafo.remove(u);
		Iterator<N> it = grafo.keySet().iterator();
		while (it.hasNext()) {
			N v = it.next();
			Iterator<ArcoPesato<N>> itAr = grafo.get(v).iterator();
			while (itAr.hasNext())
				if (itAr.next().getDestinazione().equals(u)) {
					itAr.remove(); break;
				}
		}
	} // rimuoviNodo
	public void rimuoviArco(ArcoPesato<N> ap) {
		N u = ap.getOrigine(), v = ap.getDestinazione();
		if (!grafo.containsKey(u)) return;
		Iterator<ArcoPesato<N>> itAr = grafo.get(u).iterator();
		while (itAr.hasNext())
			if (itAr.next().getDestinazione().equals(v)) {
				itAr.remove(); break;
			}
	} // rimuoviArco
	public void clear() { grafo.clear(); }
	public Iterator<ArcoPesato<N>> adiacenti(N u) {
		if (!esisteNodo(u)) throw new IllegalArgumentException();
		return grafo.get(u).iterator();
	} // adiacenti
	public Iterator<N> iterator() { return new IteratoreGrafo(); }
	private class IteratoreGrafo implements Iterator<N> {
		private Iterator<N> it = grafo.keySet().iterator();
		private N u = null;
		public boolean hasNext() { return it.hasNext(); }
		public N next() { return u = it.next(); }
		public void remove() {
			it.remove();
			Iterator<N> it2 = grafo.keySet().iterator();
			while (it2.hasNext()) {
				N v = it.next();
				Iterator<ArcoPesato<N>> itAr = grafo.get(v).iterator();
				while (itAr.hasNext())
					if (itAr.next().getDestinazione().equals(u)) {
						itAr.remove(); break;
					}
			}
		} // remove
	} // IteratoreGrafo
	public Grafo<N> factory() { return new GrafoOrientatoPesatoImpl<N>(); }
} // GrafoOrientatoPesatoImpl
