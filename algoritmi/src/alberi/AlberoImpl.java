/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alberi;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sergio
 */
public class AlberoImpl<T> implements Albero<T>{

    T val;
    List<Albero<T>> figli = new LinkedList<Albero<T>>();
    public T getVal() {
        return val;
    }

    public void setVal(T v) {
        val = v;
    }

    public List<Albero<T>> getFigli() {
        return (List<Albero<T>>) figli;
    }

    public int nfigli() {
        return figli.size();
    }

}
