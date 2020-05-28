/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafi;

/**
 *
 * @author Sergio
 *
 * L'implementazione è non ottimizzata in particolare non prevede di risparmiare
 * spazio di memorizzazione nella matrice sfruttandone la simmetria
 * 
 */
public class GrafoMatriceNO extends GrafoMatrice implements GrafoNO{

    public GrafoMatriceNO(int n) {
        super(n);
    }

    @Override
    public void insArco(int i, int j) {
        if(getArco(i,j)) return;
        if (i==j)
            super.insArco(i, j);
        else{
            super.insArco(i, j);
            super.insArco(j, i);
            m--;
        }
    }

    @Override
    public void remArco(int i, int j) {
        if(!getArco(i,j)) return;
        if (i==j)
            super.remArco(i, j);
        else{
            super.remArco(i, j);
            super.remArco(j, i);
            m++;
        }

    }

}
