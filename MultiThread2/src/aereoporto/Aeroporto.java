/* ============================================================
 * Sistemi Operativi, Corso di Laurea in Ingegneria Informatica
 * DIMES, Università della Calabria
 * ============================================================
 */
package aereoporto;

/**
 * @author Marco Lackovic <mlackovic@deis.unical.it>
 * @version 1.2, Jan 15, 2012
 */
public abstract class Aeroporto {

    public static final int ATTERRAGGIO = 0;
    public static final int DECOLLO = 1;

    protected int numPisteLibere;

    public abstract void richiediPista(int tipo) throws InterruptedException;

    public abstract void rilasciaPista() throws InterruptedException;

    public abstract Thread getAereo() throws InterruptedException;

    public abstract void fineSbarco(Thread aereo) throws InterruptedException;

    public abstract void richiediNavetta() throws InterruptedException;
}
