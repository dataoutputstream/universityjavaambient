/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafi;

/**
 *
 * @author Sergio
 */

public class Arco {

    private int in;

    private int out;


    public Arco(int in, int out){

        this.in=in;

        this.out=out;

    }

    /**
     * @return the in

     */

    public int getIn() {

        return in;

    }


    /**
     * @param in the in to set
     */

    public void setIn(int in) {

        this.in = in;

    }

    /**
     * @return the out
     */

    public int getOut() {

        return out;

    }

    /**
     * @param out the out to set
     */

    public void setOut(int out) {

        this.out = out;

    }


}
