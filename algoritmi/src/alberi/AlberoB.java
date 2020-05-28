/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alberi;

/**
 *
 * @author Sergio
 */
public interface AlberoB<T> {
    public T getVal();
    int nfigli();
    public AlberoB<T> getSin();
    public AlberoB<T> getDes();

}
