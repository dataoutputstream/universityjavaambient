package poo.grafo;

public interface GrafoOrientato<N> extends Grafo<N> {
	int gradoEntrata(N u);
	int gradoUscita(N u);
} // GrafoOrientato
