package insieme;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class InsiemeOrdinatoAlbero<T extends Comparable<? super T>> extends InsiemeOrdinatoAstratto<T> {

	private static class Albero<T>{
		T info;
		Albero<T>figlioS,figlioD;
	}
	
	public InsiemeOrdinatoAlbero(Comparator<T>c) {
		if(c!=null) {
			this.c=c;
		}
	}
	
	Comparator<T>c=null;
	private Albero<T>radice;
	
	@Override
	public void add(T x) {
		if(c==null && !this.contains(x)) {
			radice=add(radice,x);
		}else if(!this.contains(x)) {
			radice=addc(radice,x);
		}

	}

	private Albero<T> addc(Albero<T> radice, T x) {
		if(radice==null) {
			radice=new Albero<T>();
			radice.info=x;
		}if(c.compare(radice.info, x)<0) {
			radice.figlioD=add(radice.figlioD,x);
		}else {
			radice.figlioS=add(radice.figlioS,x);
		}
		
		return radice;
	}

	private Albero<T> add(Albero<T> radice, T x) {
		if(radice==null) {
			radice=new Albero<T>();
			radice.info=x;
		}if(radice.info.compareTo(x)<0) {
			radice.figlioD=add(radice.figlioD,x);
		}else {
			radice.figlioS=add(radice.figlioS,x);
		}
		
		return radice;
	}

	@Override
	public void addAll(Insieme<T> i) {
		for(T e:i) {
			this.add(e);
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new Iteratore();
	}

	@Override
	public InsiemeOrdinato<T> headSet(T x) {
		InsiemeOrdinato<T>i=new InsiemeOrdinatoAlbero<T>(null);
		for(T e:this) {
			if(e.compareTo(x)<0) {
				i.add(e);
			}break;
		}
		return i;
	}

	@Override
	public InsiemeOrdinato<T> tailSet(T x) {
		InsiemeOrdinato<T>i=new InsiemeOrdinatoAlbero<T>(null);
		for(T e:this) {
			if(e.compareTo(x)>=0) {
				i.add(e);
			}break;
		}
		return i;
	}
	
	private class Iteratore implements Iterator<T>{
		private Albero<T> cur = null, padre = null;
		private Stack<Albero<T>> nodi = new StackConcatenato<Albero<T>>();
		public Iteratore() {
			cur = radice;
			while (cur != null) {
				nodi.push(cur);
				cur = cur.figlioS;
			}
		} // Costruttore
		public boolean hasNext() {
			return !nodi.isEmpty();
		} // hasNext
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			cur = nodi.pop();
			// Rimuovo eventuali nodi padre su cui ha avuto luogo una remove()
			if (!nodi.isEmpty() && nodi.top().figlioD == null && nodi.top().figlioS == null)
				nodi.pop();
			if (cur == radice) padre = null; // La radice non ha padre
			else {
				// Ottengo il padre di cur e rimuovo i nodi padre che non serviranno più
				padre = nodi.top();
				if (padre.figlioD == cur && cur.figlioD == null) {
					Albero<T> figlio = nodi.pop();
					while (!nodi.isEmpty() && nodi.top().figlioD == figlio) figlio = nodi.pop();
				}
			}
			// Se cur ha un figlio destro, lo inserisco nello stack insieme a tutti i figli sinistri
			// Reinserisco cur perché potrebbe successivamente servirmi come padre
			if (cur.figlioD != null) {
				nodi.push(cur); nodi.push(cur.figlioD);
				Albero<T> figlio = cur.figlioD.figlioS;
				while (figlio != null) {
					nodi.push(figlio);
					figlio = figlio.figlioS;
				}
			}
			return cur.info;
		} // next
		public void remove() {
			if (cur == padre) throw new IllegalStateException();
			if (cur.figlioS == null || cur.figlioD == null) {
				// Uno dei due figli è null, posso 'bypassare' cur puntando al figlio non nullo
				Albero<T> nuovoFiglio = (cur.figlioD == null ? cur.figlioS : cur.figlioD);
				if (padre == null) radice = nuovoFiglio;
				else if (cur == padre.figlioD) padre.figlioD = nuovoFiglio;
				else padre.figlioS = nuovoFiglio;
				cur.figlioS = null; cur.figlioD = null;
			} else {
				// cur ha entrambi i figli: cerco il nodo più a destra nel sottoalbero sinistro, da sostituire a cur
				Albero<T> pre = cur, figlio = cur.figlioS;
				while (figlio.figlioD != null) {
					pre = figlio; figlio = figlio.figlioD;
				}
				cur.info = figlio.info;
				if (pre == cur) pre.figlioS = figlio.figlioS;
				else pre.figlioD = figlio.figlioS;
			}
			cur = padre; // Remove eseguita
		} // remove
	}

}
