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
public interface GrafoP extends Grafo{
    Peso getArcoP(int i, int j);
    void insArcoP(int i, int j, Peso p);
    Iterator<ArcoP> adiacentiP(int i);
    Iterator<ArcoP> archiP();
}
