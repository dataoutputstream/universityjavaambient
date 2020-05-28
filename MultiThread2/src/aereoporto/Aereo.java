/* ============================================================
 * Sistemi Operativi, Corso di Laurea in Ingegneria Informatica
 * DIMES, Università della Calabria
 * ============================================================
 */
package aereoporto;

import java.util.Random;
import java.util.concurrent.TimeUnit;



/**
 * @author Marco Lackovic <mlackovic@deis.unical.it>
 * @version 1.2, Jan 15, 2012
 */
public class Aereo implements Runnable {
	Random r=new Random();
    private static final int MIN_TEMPO_VOLO = 600;
    private static final int MAX_TEMPO_VOLO = 900;
    private static final int TEMPO_USO_PISTA = 300;

    private Aeroporto aeroporto;

    public Aereo(Aeroporto p) {
        this.aeroporto = p;
    }

    @Override
    public void run() {
        try {
            while (true) {
                vola();
                aeroporto.richiediPista(Aeroporto.ATTERRAGGIO);
                atterra();
                aeroporto.rilasciaPista();
                aeroporto.richiediNavetta();
                aeroporto.richiediPista(Aeroporto.DECOLLO);
                decolla();
                aeroporto.rilasciaPista();
            }
        } catch (InterruptedException e) {
        }
    }

    private void decolla() throws InterruptedException {
        System.out.println("L'aereo " + Thread.currentThread().getId()
                + " sta decollando.");
        Thread.sleep(TEMPO_USO_PISTA);
    }

    private void atterra() throws InterruptedException {
        System.out.println("L'aereo " + Thread.currentThread().getId()
                + " sta atterrando.");
        Thread.sleep(TEMPO_USO_PISTA);
    }

    private void vola() throws InterruptedException {
        System.out.println("L'aereo " + Thread.currentThread().getId()
                + " sta volando.");
        TimeUnit.MILLISECONDS.sleep(r.nextInt(MAX_TEMPO_VOLO-MIN_TEMPO_VOLO+1)+MIN_TEMPO_VOLO);
    }
}
