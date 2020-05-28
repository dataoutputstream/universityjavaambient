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
public interface Grafo {
    int n();
    int m();
    boolean getArco(int i, int j);
    void insArco(int i, int j);
    void remArco(int i, int j);
    Iterator<Arco> adiacenti(int i);
    Iterator<Arco> archi();

}
