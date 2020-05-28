package poo.grafo;

import java.util.Iterator;

public interface GrafoPesato<N> extends Grafo<N> {
	public void insArco(ArcoPesato<N> ap);
	public void insArco(Arco<N> a, Peso p);
	public void insArco(N u, N v, Peso p);
	public void modArco(ArcoPesato<N> a, Peso p);
	public void rimuoviArco(ArcoPesato<N> ap);
	public Iterator<ArcoPesato<N>> adiacenti(N u);
	public Peso peso(N u, N v);
} // GrafoPesato
