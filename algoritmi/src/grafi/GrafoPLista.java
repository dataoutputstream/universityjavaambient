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
public class GrafoPLista implements GrafoP{

    ArrayList<Set<Coppia>> archi;
    int m;

    public GrafoPLista(int n){
        archi = new ArrayList<Set<Coppia>>(n);
        for(int i =0; i<n;i++)
            archi.add(i, new LinkedHashSet<Coppia>());
        m=0;
    }

    public Peso getArcoP(int i, int j) {
        Coppia c_app = new Coppia(j,null);
        for(Coppia c: archi.get(i))
            if (c_app.equals(c))
                return c.getP();
        return null;
    }

    /**
     * Il metodo effettua l'inserimento di un arco fra i nodi i e j di peso p.
     * Se un arco è già presente fra i nodi i e j ne cambia il peso.
     *
     * @param i Nodo iniziale dell'arco
     * @param j Nodo finale dell'arco
     * @param p Peso dell'arco
     */
    public void insArcoP(int i, int j, Peso p) {
        Coppia c_app = new Coppia(j,p);
        if(getArco(i,j)){
            for(Coppia c: archi.get(i))
                if (c.equals(c_app))
                    c.setP(p);
        }else{
            archi.get(i).add(c_app);
            m++;
        }
    }

    public Iterator<ArcoP> adiacentiP(final int i) {
        return new Iterator<ArcoP>() {
            Iterator<Coppia> it = archi.get(i).iterator();
            public boolean hasNext() {
                return it.hasNext();
            }

            public ArcoP next() {
                Coppia c = it.next();
                return new ArcoP(i,c.getNodo(), c.getP());
            }

            public void remove() {
                throw new UnsupportedOperationException("Non si può usare remove");
            }
        };
    }

    public Iterator<ArcoP> archiP() {
        return new IteratorGenP();
    }

    public int n() {
        return archi.size();
    }

    public int m() {
        return m;
    }

    public boolean getArco(int i, int j) {
        return archi.get(i).contains(new Coppia(i,null));
    }

    public void insArco(int i, int j) {
        insArcoP(i,j,new Peso(Peso.DEFAULTPESO));
    }

    public void remArco(int i, int j) {
        archi.get(i).remove(new Coppia(j,null));
        m--;
    }

    public Iterator<Arco> adiacenti(final int i) {
        return new Iterator<Arco>() {
            Iterator<Coppia> it = archi.get(i).iterator();
            public boolean hasNext() {
                return it.hasNext();
            }

            public Arco next() {
                Coppia c = it.next();
                return new Arco(i,c.getNodo());
            }

            public void remove() {
                throw new UnsupportedOperationException("Non si può usare remove");
            }
        };
    }

    public Iterator<Arco> archi() {
        return new IteratorGen();
    }

    protected class Coppia{
        int nodo;
        Peso p;

        public Peso getP() {
            return p;
        }

        public int getNodo() {
            return nodo;
        }

        public void setP(Peso p) {
            this.p = p;
        }

        public Coppia(int nodo, Peso p) {
            this.nodo = nodo;
            this.p = p;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Coppia other = (Coppia) obj;
            if (this.nodo != other.nodo) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 13 * hash + this.nodo;
            return hash;
        }

        
    }
    protected class IteratorGen implements Iterator<Arco>{

        int ind;
        Iterator<Coppia> current;
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
                Arco x = new Arco(ind,current.next().getNodo());
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

        protected class IteratorGenP implements Iterator<ArcoP>{

        int ind;
        Iterator<Coppia> current;
        boolean hasnext=false;

        public IteratorGenP() {
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

        public ArcoP next() {
            if (hasnext){
                Coppia c = current.next();
                ArcoP x = new ArcoP(ind,c.getNodo(),c.getP());
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
