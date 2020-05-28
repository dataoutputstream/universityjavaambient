package poo.grafo;

import java.util.*;

public class GrafoNonOrientatoImpl<N> extends GrafoNonOrientatoAstratto<N> {
	private Map<N, LinkedList<Arco<N>>> grafo = new HashMap<N, LinkedList<Arco<N>>>();
	public int numNodi() { return grafo.size(); }
	public boolean esisteNodo(N u) { return grafo.containsKey(u); }
	public void insNodo(N u) {
		if (esisteNodo(u)) throw new RuntimeException("Nodo gia' presente durante insNodo");
		grafo.put(u, new LinkedList<Arco<N>>());
	} // insNodo
	public void insArco(Arco<N> a) {
		if (!grafo.containsKey(a.getOrigine()) || !grafo.containsKey(a.getDestinazione()))
			throw new RuntimeException("Nodo/i non presente/i durante insArco");
		LinkedList<Arco<N>> l = grafo.get(a.getOrigine());
		Iterator<Arco<N>> it = l.iterator();
		while (it.hasNext())
			if (it.next().getDestinazione().equals(a.getDestinazione())) it.remove();
		l.add(a);
		Arco<N> inverso = new Arco<N>(a.getDestinazione(), a.getOrigine());
		l = grafo.get(inverso.getOrigine());
		it = l.iterator();
		while (it.hasNext())
			if (it.next().getDestinazione().equals(inverso.getDestinazione())) it.remove();
		l.add(inverso);
	} // insArco
	public void rimuoviNodo(N u) {
		grafo.remove(u);
		Iterator<N> it = grafo.keySet().iterator();
		while (it.hasNext()) {
			N v = it.next();
			Iterator<Arco<N>> itAr = grafo.get(v).iterator();
			while (itAr.hasNext())
				if (itAr.next().getDestinazione().equals(u)) {
					itAr.remove(); break;
				}
		}
	} // rimuoviNodo
	public void rimuoviArco(Arco<N> a) {
		N u = a.getOrigine(), v = a.getDestinazione();
		if (!grafo.containsKey(u)) return;
		Iterator<Arco<N>> itAr = grafo.get(u).iterator();
		while (itAr.hasNext())
			if (itAr.next().getDestinazione().equals(v)) {
				itAr.remove();
				itAr = grafo.get(v).iterator();
				while (itAr.hasNext())
					if (itAr.next().getDestinazione().equals(u)) {
						itAr.remove(); break;
					}
				break;
			}
	} // rimuoviArco
	public void clear() { grafo.clear(); }
	public Iterator<Arco<N>> adiacenti(N u) {
		if (!esisteNodo(u)) throw new IllegalArgumentException();
		return new IteratoreAdiacenti(u);
	} // adiacenti
	private class IteratoreAdiacenti implements Iterator<Arco<N>> {
		private Iterator<Arco<N>> it;
		private Arco<N> a = null;
		public IteratoreAdiacenti(N u) { it = grafo.get(u).iterator(); }
		public boolean hasNext() { return it.hasNext(); }
		public Arco<N> next() { return a = it.next(); }
		public void remove() {
			it.remove();
			Arco<N> inverso = new Arco<N>(a.getDestinazione(), a.getOrigine());
			Iterator<Arco<N>> itAr = grafo.get(inverso.getOrigine()).iterator();
			while (itAr.hasNext())
				if (itAr.next().getDestinazione().equals(inverso.getDestinazione())) {
					itAr.remove(); break;
				}
		} // remove
	} // IteratoreAdiacenti
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
				N v = it2.next();
				Iterator<Arco<N>> itAr = grafo.get(v).iterator();
				while (itAr.hasNext())
					if (itAr.next().getDestinazione().equals(u)) {
						itAr.remove(); break;
					}
			}
		} // remove
	} // IteratoreGrafo
	public Grafo<N> factory() { return new GrafoNonOrientatoImpl<N>(); }
} // GrafoNonOrientatoImpl
