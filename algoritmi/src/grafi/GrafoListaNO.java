/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafi;

/**
 *
 * @author Sergio
 */
public class GrafoListaNO extends GrafoLista implements GrafoNO{
    
    public GrafoListaNO(int n){
        super(n);
    }

    @Override
    public void insArco(int i, int j) {
        if(getArco(i,j)) return;
        if(i!=j){
            super.insArco(i, j);
            super.insArco(j, i);
            m--;
        }else
            super.insArco(i, j);
    }


    @Override
    public void remArco(int i, int j) {
        if(!getArco(i,j)) return;
        if (i!=j){
            super.remArco(i, j);
            super.remArco(j, i);
            m++;
        }else
            super.remArco(i, j);
    }
}
