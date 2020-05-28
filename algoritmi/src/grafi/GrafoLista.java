/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafi;

import java.util.*;


/**
 *
 * @author Sergio
 */
public class GrafoLista implements Grafo{

    ArrayList<Set<Integer>> archi;
    int m;

    public GrafoLista(int n){
        archi = new ArrayList<Set<Integer>>(n);
        for(int i =0; i<n;i++)
            archi.add(i, new LinkedHashSet<Integer>());
        m=0;
    }

    public int n() {
        return archi.size();
    }

    public int m() {
        return m;
    }

    public boolean getArco(int i, int j) {
        return archi.get(i).contains(j);
    }

    public void insArco(int i, int j) {
        if(getArco(i,j)) return;
        archi.get(i).add(j);
        m++;
    }

    public void remArco(int i, int j) {
        if(!getArco(i,j)) return;
        archi.get(i).remove(new Integer(j));
        m--;
    }

    public Iterator<Arco> adiacenti(final int i) {
        return new Iterator<Arco>() {
            Iterator<Integer> it = archi.get(i).iterator();
            public boolean hasNext() {
                return it.hasNext();
            }

            public Arco next() {
                int j = it.next();
                return new Arco(i,j);
            }

            public void remove() {
                throw new UnsupportedOperationException("Non si può usare remove");
            }
        };
    }

    public Iterator<Arco> archi() {
        return new IteratorGen();
    }

    protected class IteratorGen implements Iterator<Arco>{

        int ind;
        Iterator<Integer> current;
        boolean hasnext=false;

        public IteratorGen() {
            for(ind=0;ind<archi.size();ind++){
                current = archi.get(ind).iterator();
                if(current.hasNext()){
                    hasnext=true;
                    return;
                }
            }
        }

        public boolean hasNext() {
            return hasnext;
        }

        public Arco next() {
            if (hasnext){
                Arco x = new Arco(ind,current.next());
                if(current.hasNext()){
                    return x;
                }else{
                    hasnext=false;
                    for(ind++;ind<archi.size();ind++){
                        current = archi.get(ind).iterator();
                        if(current.hasNext()){
                            hasnext=true;
                            return x;
                        }
                    }
                    return x;
                }
            }else
                return null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Non utilizzabile in questa implementazione");
        }

    }

}
