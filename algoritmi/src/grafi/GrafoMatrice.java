/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafi;

import java.util.Iterator;


/**
 *
 * @author Sergio
 */
public class GrafoMatrice implements Grafo{

    int m,n;
    boolean[][] mat;

    public GrafoMatrice(int n) {
        mat = new boolean[n][n];
        this.n=n;
        m=0;
    }

    public int n() {
        return n;
    }

    public int m() {
        return m;
    }

    public boolean getArco(int i, int j) {
        return mat[i][j];
    }

    public void insArco(int i, int j) {
        if(!mat[i][j]){
            mat[i][j]=true;
            m++;
        }

    }

    public void remArco(int i, int j) {
        if(mat[i][j]){
            mat[i][j]=false;
            m--;
        }
    }

    protected class IteratorImpl implements Iterator<Arco> {

        boolean hasnext=false;
        boolean[] adiacenti;
        int pos, in;

        public IteratorImpl(int i) {
            in=i;
            adiacenti=mat[i];
            for(pos=0;(pos<adiacenti.length) && (!adiacenti[pos]);pos++);
            if (pos<adiacenti.length)
                hasnext=true;
        }

        public boolean hasNext() {
            return hasnext;
        }

        public Arco next() {
            if (!hasnext) return null;
            Arco ris = new Arco(in,pos);
            hasnext=false;
            for(pos++;(pos<adiacenti.length) && (!adiacenti[pos]);pos++);
            if (pos<adiacenti.length)
                hasnext=true;
            return ris;
        }

        public void remove() {
            throw new UnsupportedOperationException("Non consentito");
        }
    }

    public Iterator<Arco> adiacenti(int i) {
        return new IteratorImpl(i);

    }

    public Iterator<Arco> archi() {
        return new IteratorGen();
    }

    protected class IteratorGen implements Iterator<Arco>{

        int ind;
        Iterator<Arco> current;
        boolean hasnext=false;

        public IteratorGen() {
            for(ind=0;ind<n();ind++){
                current = adiacenti(ind);
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
                Arco x = current.next();
                if(current.hasNext()){
                    return x;
                }else{
                    hasnext=false;
                    for(ind++;ind<n();ind++){
                        current = adiacenti(ind);
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
