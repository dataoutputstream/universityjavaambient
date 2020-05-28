package poo.eratostene;

import java.util.*;

public class CrivelloBitSet extends CrivelloAstratto {
	private BitSet crivello;
	private final int N;
	public CrivelloBitSet(int N) {
		if (N < 2) throw new RuntimeException("N minore di 2!");
		this.N = N;
		crivello = new BitSet(N + 1);
		crivello.set(2, N + 1);
	} // Costruttore
	public int size() { return crivello.cardinality(); }
	public void filtra() {
		for (int i = 2; i <= (int)Math.sqrt(N); i++)
			if (crivello.get(i))
				for (int j = 2 * i; j <= N; j += i)
					crivello.set(j, false);
	} // filtra
	public Iterator<Integer> iterator() {
		return new BitSetIterator();
	} // iterator
	private class BitSetIterator implements Iterator<Integer> {
		private int corrente = -1;
		private boolean rimovibile = false;
		public boolean hasNext() {
			return crivello.nextSetBit(corrente + 1) >= 0;
		} // hasNext
		public Integer next() {
			if (!hasNext()) throw new NoSuchElementException();
			corrente = crivello.nextSetBit(corrente + 1);
			rimovibile = true;
			return corrente;
		} // next
		public void remove() {
			if (!rimovibile) throw new IllegalStateException();
			crivello.set(corrente, false); rimovibile = false;
		} // remove
	} // BitSetIterator
} // CrivelloBitSet
