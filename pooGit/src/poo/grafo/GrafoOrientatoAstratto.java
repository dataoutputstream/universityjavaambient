package poo.grafo;

import java.util.Iterator;

public abstract class GrafoOrientatoAstratto<N> extends GrafoAstratto<N> implements GrafoOrientato<N> {
	public int gradoEntrata(N u) {
		int gE = 0;
		if (esisteNodo(u)) {
			for (N v: this) {
				Iterator<? extends Arco<N>> it = adiacenti(v);
				while (it.hasNext())
					if (it.next().getDestinazione().equals(u)) { gE++; break; }
			}
		}
		return gE;
	} // gradoEntrata
	public int gradoUscita(N u) {
		int gU = 0;
		if (esisteNodo(u)) {
			Iterator<? extends Arco<N>> it = adiacenti(u);
			while (it.hasNext()) { it.next(); gU++; }
		}
		return gU;
	} // gradoUscita
} // GrafoOrientatoAstratto
