package poo.grafo;

import java.util.Iterator;

public abstract class GrafoOrientatoPesatoAstratto<N> extends GrafoOrientatoAstratto<N> implements GrafoOrientatoPesato<N> {
	public void insArco(Arco<N> a) { insArco(new ArcoPesato<N>(a.getOrigine(), a.getDestinazione())); }
	public void insArco(Arco<N> a, Peso p) { insArco(new ArcoPesato<N>(a.getOrigine(), a.getDestinazione(), p)); }
	public void insArco(N u, N v, Peso p) { insArco(new ArcoPesato<N>(u, v, p)); }
	public void rimuoviArco(Arco<N> a) {
		if (!(a instanceof ArcoPesato)) throw new IllegalArgumentException();
		rimuoviArco((ArcoPesato<N>)a);
	} // rimuoviArco
	public void modArco(ArcoPesato<N> ap, Peso p) {
		if (!esisteNodo(ap.getOrigine()) || !esisteNodo(ap.getDestinazione())) return;
		Iterator<ArcoPesato<N>> it = adiacenti(ap.getOrigine());
		while (it.hasNext()) {
			ArcoPesato<N> a = it.next();
			if (a.getDestinazione().equals(ap.getDestinazione())) {
				a.setPeso(p); return;
			}
		}
		insArco(new ArcoPesato<N>(ap.getOrigine(), ap.getDestinazione(), p));
	} // modArco
	public Peso peso(N u, N v) {
		Iterator<ArcoPesato<N>> it = adiacenti(u);
		while (it.hasNext()) {
			ArcoPesato<N> a = it.next();
			if (a.getDestinazione().equals(v)) return a.getPeso();
		}
		return null;
	} // peso

	public abstract void insArco(ArcoPesato<N> ap);
	public abstract void rimuoviArco(ArcoPesato<N> ap);
	public abstract Iterator<ArcoPesato<N>> adiacenti(N u);
} // GrafoOrientatoPesatoAstratto
