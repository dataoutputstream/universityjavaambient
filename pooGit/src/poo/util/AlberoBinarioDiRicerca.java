package poo.util;

import java.util.*;

import alberi.AlberoB;

public class AlberoBinarioDiRicerca<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {
	private static class Albero<E> {
		E info;
		Albero<E> figlioSinistro, figlioDestro;
		public String toString() {
			return info.toString();
		}
	} // Albero
	private Albero<T> radice = null;

	public int size() { return size(radice); }
	private int size(Albero<T> radice) {
		if (radice == null) return 0;
		return 1 + size(radice.figlioSinistro) + size(radice.figlioDestro);
	} // size

	public boolean contains(T elem) { return contains(radice, elem); }
	private boolean contains(Albero<T> radice, T elem) {
		if (radice == null) return false;
		if (radice.info.equals(elem)) return true;
		if (radice.info.compareTo(elem) > 0)
			return contains(radice.figlioSinistro, elem);
		return contains(radice.figlioDestro, elem);
	} // contains

	public T get(T elem) { return get(radice, elem); }
	private T get(Albero<T> radice, T elem) {
		if (radice == null) return null;
		if (radice.info.equals(elem)) return radice.info;
		if (radice.info.compareTo(elem) > 0)
			return get(radice.figlioSinistro, elem);
		return get(radice.figlioDestro, elem);
	} // get

	public void clear() { radice = null; }
	public boolean isEmpty() { return radice == null; }
	public boolean isFull() { return false; }

	public void add(T elem) { radice = add(radice, elem); }
	private Albero<T> add(Albero<T> radice, T elem) {
		if (radice == null) {
			radice = new Albero<T>();
			radice.info = elem;
		} else if (radice.info.compareTo(elem) > 0)
			radice.figlioSinistro = add(radice.figlioSinistro, elem);
		else
			radice.figlioDestro = add(radice.figlioDestro, elem);
		return radice;
	} // add


	public void remove(T elem) { radice = remove(radice, elem); }
	private Albero<T> remove(Albero<T> radice, T elem) {
		if (radice == null) return null;
		if (radice.info.compareTo(elem) > 0)
			radice.figlioSinistro = remove(radice.figlioSinistro, elem);
		else if (radice.info.compareTo(elem) < 0)
			radice.figlioDestro = remove(radice.figlioDestro, elem);
		else { // radice.info.equals(elem)
			if (radice.figlioSinistro == null) radice = radice.figlioDestro;
			else if (radice.figlioDestro == null) radice = radice.figlioSinistro;
			else {
				Albero<T> padre = radice;
				Albero<T> figlio = radice.figlioSinistro; // Ricerca elemento più a destra nel sottoalbero sinistro
				while (figlio.figlioDestro != null) {
					padre = figlio;
					figlio = figlio.figlioDestro;
				}
				radice.info = figlio.info; // Trovato
				if (padre == radice) padre.figlioSinistro = figlio.figlioSinistro;
				else padre.figlioDestro = figlio.figlioSinistro;
			}
		}
		return radice;
	} // remove

	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (!(o instanceof AlberoBinarioDiRicerca)) return false;
		if (o == this) return true;
		return equals(this.radice, ((AlberoBinarioDiRicerca)o).radice);
	} // equals
	private boolean equals(Albero<T> a1, Albero<T> a2) {
		if (a1 == null && a2 == null) return true;
		if (a1 == null || a2 == null) return false;
		if (!a1.info.equals(a2.info)) return false;
		return equals(a1.figlioSinistro, a2.figlioSinistro) && equals(a1.figlioDestro, a2.figlioDestro);
	} // equals

	public String toString() {
		StringBuilder sb = new StringBuilder(200);
		sb.append('[');
		toString(radice, sb);
		if (sb.charAt(sb.length() - 1) == ' ') sb.setLength(sb.length() - 2);
		sb.append(']');
		return sb.toString();
	} // toString
	private void toString(Albero<T> radice, StringBuilder sb) {
		if (radice == null) return;
		toString(radice.figlioSinistro, sb);
		sb.append(radice.info); sb.append(", ");
		toString(radice.figlioDestro, sb);
	} // toString

	public int hashCode() { return hashCode(radice); }
	private int hashCode(Albero<T> radice) {
		final int MOLT = 43;
		if (radice == null) return 0;
		return radice.info.hashCode() * MOLT + hashCode(radice.figlioSinistro) + hashCode(radice.figlioDestro);
	} // hashCode

	public void visitaSimmetrica() { visitaSimmetrica(radice); }
	private void visitaSimmetrica(Albero<T> radice) {
		if (radice != null) {
			visitaSimmetrica(radice.figlioSinistro);
			System.out.print(radice.info + " ");
			visitaSimmetrica(radice.figlioDestro);
		}
	} // visitaSimmetrica

	public void visitaSimmetrica(List<T> l) { visitaSimmetrica(radice, l); }
	private void visitaSimmetrica(Albero<T> radice, List<T> l) {
		if (radice != null) {
			visitaSimmetrica(radice.figlioSinistro, l);
			l.add(radice.info);
			visitaSimmetrica(radice.figlioDestro, l);
		}
	} // visitaSimmetrica

	public void visitaPerLivelli() {
		if (radice == null) return;
		Coda<Albero<T>> daVisitare = new CodaConcatenata<Albero<T>>();
		daVisitare.put(radice);
		while (!daVisitare.isEmpty()) {
			Albero<T> cur = daVisitare.get();
			System.out.print(cur.info + " ");
			if (cur.figlioSinistro != null) daVisitare.put(cur.figlioSinistro);
			if (cur.figlioDestro != null) daVisitare.put(cur.figlioDestro);
		}
	} // visitaPerLivelli

	public void visitaPerLivelli(LinkedList<T> l) {
		if (radice == null) return;
		Coda<Albero<T>> daVisitare = new CodaConcatenata<Albero<T>>();
		daVisitare.put(radice);
		while (!daVisitare.isEmpty()) {
			Albero<T> cur = daVisitare.get();
			l.add(cur.info);
			if (cur.figlioSinistro != null) daVisitare.put(cur.figlioSinistro);
			if (cur.figlioDestro != null) daVisitare.put(cur.figlioDestro);
		}
	} // visitaPerLivelli

	public void stampaFrontiera() { stampaFrontiera(radice); }
	private void stampaFrontiera(Albero<T> radice) {
		if (radice == null) return;
		if (radice.figlioSinistro == null && radice.figlioDestro == null)
			System.out.println(radice.info);
		else {
			stampaFrontiera(radice.figlioSinistro);
			stampaFrontiera(radice.figlioDestro);
		}
	} // stampaFrontiera

	public boolean bilanciato() { return bilanciato(radice); }
	private boolean bilanciato(Albero<T> albero) {
		if (albero == null) return true;
		if (Math.abs(size(albero.figlioSinistro) - size(albero.figlioDestro)) > 1) return false;
		return bilanciato(albero.figlioSinistro) && bilanciato(albero.figlioDestro);
	} // bilanciato

	public int altezza() {
		if (radice == null) return 0;
		return altezza(radice);
	} // altezza
	private int altezza(Albero<T> radice) {
		if (radice.figlioSinistro == null && radice.figlioDestro == null) return 0;
		int h1 = 0, h2 = 0;
		if (radice.figlioSinistro != null) h1 = 1 + altezza(radice.figlioSinistro);
		if (radice.figlioDestro != null) h2 = 1 + altezza(radice.figlioDestro);
		return h1 > h2 ? h1 : h2;
	} // altezza

	public Iterator<T> iterator() {
		return new ABRIterator();
	} // iterator
	private class ABRIterator implements Iterator<T> {
		private Albero<T> cur = null, padre = null;
		private Stack<Albero<T>> nodi = new StackConcatenato<Albero<T>>();
		public ABRIterator() {
			cur = radice;
			while (cur != null) {
				nodi.push(cur);
				cur = cur.figlioSinistro;
			}
		} // Costruttore
		public boolean hasNext() {
			return !nodi.isEmpty();
		} // hasNext
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			cur = nodi.pop();
			// Rimuovo eventuali nodi padre su cui ha avuto luogo una remove()
			if (!nodi.isEmpty() && nodi.top().figlioDestro == null && nodi.top().figlioSinistro == null)
				nodi.pop();
			if (cur == radice) padre = null; // La radice non ha padre
			else {
				// Ottengo il padre di cur e rimuovo i nodi padre che non serviranno più
				padre = nodi.top();
				if (padre.figlioDestro == cur && cur.figlioDestro == null) {
					Albero<T> figlio = nodi.pop();
					while (!nodi.isEmpty() && nodi.top().figlioDestro == figlio) figlio = nodi.pop();
				}
			}
			// Se cur ha un figlio destro, lo inserisco nello stack insieme a tutti i figli sinistri
			// Reinserisco cur perché potrebbe successivamente servirmi come padre
			if (cur.figlioDestro != null) {
				nodi.push(cur); nodi.push(cur.figlioDestro);
				Albero<T> figlio = cur.figlioDestro.figlioSinistro;
				while (figlio != null) {
					nodi.push(figlio);
					figlio = figlio.figlioSinistro;
				}
			}
			return cur.info;
		} // next
		public void remove() {
			if (cur == padre) throw new IllegalStateException();
			if (cur.figlioSinistro == null || cur.figlioDestro == null) {
				// Uno dei due figli è null, posso 'bypassare' cur puntando al figlio non nullo
				Albero<T> nuovoFiglio = (cur.figlioDestro == null ? cur.figlioSinistro : cur.figlioDestro);
				if (padre == null) radice = nuovoFiglio;
				else if (cur == padre.figlioDestro) padre.figlioDestro = nuovoFiglio;
				else padre.figlioSinistro = nuovoFiglio;
				cur.figlioSinistro = null; cur.figlioDestro = null;
			} else {
				// cur ha entrambi i figli: cerco il nodo più a destra nel sottoalbero sinistro, da sostituire a cur
				Albero<T> pre = cur, figlio = cur.figlioSinistro;
				while (figlio.figlioDestro != null) {
					pre = figlio; figlio = figlio.figlioDestro;
				}
				cur.info = figlio.info;
				if (pre == cur) pre.figlioSinistro = figlio.figlioSinistro;
				else pre.figlioDestro = figlio.figlioSinistro;
			}
			cur = padre; // Remove eseguita
		} // remove
	} // ABRIterator
	
	public static int ContaPerLivello(AlberoBinarioDiRicerca<Integer> abr, int l) {
		return contaL(abr.radice,l);

	
}


private static int contaL(Albero<Integer> a, int l) {
			if (a==null||l<0)return 0;
			int cont= contaL(a.figlioSinistro,l-1)+contaL(a.figlioDestro,l-1);
			if(l!=0 && a!=null)cont++;
			return cont;
}

	public static void main(String[]args) {
		AlberoBinarioDiRicerca<Integer> abr = new AlberoBinarioDiRicerca<Integer>();
		abr.add(4); abr.add(12); abr.add(-7); abr.add(15);
		abr.add(2); abr.add(1); abr.add(3); abr.add(-8);
		abr.add(-2); abr.add(10); abr.add(-1); abr.add(25);
		abr.add(-19); abr.add(7); abr.add(15); abr.add(0);
		System.out.println(abr);
		System.out.println("size = " + abr.size());
		System.out.println("Altezza = " + abr.altezza());
		System.out.println("compaaaaaaaaaaaaaaaaaaa  "+ ContaPerLivello(abr, 6));
		System.out.println("Visita per livelli: ");
		abr.visitaPerLivelli();
		System.out.println();
		int r = 2;
		System.out.println("Test iteratore rimuovi " + r);
		Iterator<Integer> it = abr.iterator();
		while (it.hasNext()) {
			if (it.next() == r) it.remove();
		}
		System.out.println(abr);
		System.out.println("abr contains 15: " + abr.contains(15));
		r = 10;
		System.out.println("Test rimozione di " + r);
		abr.remove(r);
		System.out.println(abr);
		System.out.println("Visita Simmetrica");
		abr.visitaSimmetrica();
		System.out.println();
		r = 12;
		System.out.println("Test rimozione di " + r);
		abr.remove(r);
		System.out.println(abr);
	} // main
} // AlberoBinarioDiRicerca
