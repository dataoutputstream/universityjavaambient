/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alberi;


/**
 *
 * @author Sergio
 */
public class AlberoBImpl<T> implements AlberoB<T> {

    T val;
    AlberoBImpl<T> sin = null;
    AlberoBImpl<T> des = null;

    public T getVal() {
        return val;
    }

    public void setVal(T v) {
        val = v;
    }

    public int nfigli() {
        return ((sin==null)?0:1)+((des==null)?0:1);

    }

    public AlberoB<T> getSin() {
        return (AlberoB<T>) sin;
    }
    
    public void setSin(AlberoBImpl<T> x) {
        sin= x;
    }

    public AlberoB<T> getDes() {
        return (AlberoB<T>) des;
    }

    public void setDes(AlberoBImpl<T> x) {
        des= x;
    }

}
