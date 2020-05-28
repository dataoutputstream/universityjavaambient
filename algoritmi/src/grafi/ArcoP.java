/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafi;

/**
 *
 * @author Sergio
 */
public class ArcoP extends Arco{
    Peso p;

    public ArcoP(int in, int out, Peso p) {
        super(in, out);
        this.p = p;
    }

    public Peso getP() {
        return p;
    }

    public void setP(Peso p) {
        this.p = p;
    }
}
