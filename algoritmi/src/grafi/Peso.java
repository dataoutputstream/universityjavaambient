/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafi;

/**
 *
 * @author Sergio
 */
public class Peso{

    static final int DEFAULTPESO = 0;
    int value=0;

    public Peso(int value) {
        this.value=value;
    }

    public Peso() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static Peso somma(Peso p1, Peso p2){
        if ((p1==null)||p2==null)
            return null;
        else
            return new Peso(p1.getValue()+p2.getValue());
    }
    
    public static boolean minore(Peso p1, Peso p2){
        if (p1==null)
            return false;
        else if (p2==null)
            return true;
        else
            return p1.getValue()<p2.getValue();
    }
}
