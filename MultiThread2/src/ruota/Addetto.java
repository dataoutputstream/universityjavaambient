/* ============================================================
 * Sistemi Operativi, Corso di Laurea in Ingegneria Informatica
 * DIMES, UniversitÃ  della Calabria
 * ============================================================
 */
package ruota;

import java.util.concurrent.TimeUnit;

/**
 * @author Marco Lackovic <mlackovic@deis.unical.it>
 * @version 1.4, Jan 17, 2013
 */
public class Addetto implements Runnable {
    /*
     * Nota: il tempo di attesa è stato ridotto rispetto a quello richiesto
     * dalla traccia affinchè l'output possa essere analizzato in tempi
     * ragionevoli.
     */
    private static final long TEMPO_ATTESA = 600;
    private Ruota ruota;

    public Addetto(Ruota ruota) {
        this.ruota = ruota;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ruota.ingressoUscita();
                attendiProssimaCabina();
            }
        } catch (InterruptedException e) {
        }
    }

    private void attendiProssimaCabina() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(TEMPO_ATTESA);
    }

}
