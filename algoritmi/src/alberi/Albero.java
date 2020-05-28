/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alberi;

import java.util.List;

/**
 *
 * @author Sergio
 */
public interface Albero<T> {
    public T getVal();
    public void setVal(T v);

    List<Albero<T>> getFigli();
    int nfigli();
}
