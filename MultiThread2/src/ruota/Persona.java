/* ============================================================
 * Sistemi Operativi, Corso di Laurea in Ingegneria Informatica
 * DIMES, Università della Calabria
 * ============================================================
 */
package ruota;

/**
 * @author Marco Lackovic <mlackovic@deis.unical.it>
 * @version 1.4, Jan 17, 2013
 */
public class Persona implements Runnable {

    private Ruota ruota;

    public Persona(Ruota ruota) {
        this.ruota = ruota;
    }

    @Override
    public void run() {
        try {
            ruota.faiUnGiro();
        } catch (InterruptedException e) {
        }
    }

}
