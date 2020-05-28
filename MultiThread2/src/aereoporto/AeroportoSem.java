/* ============================================================
 * Sistemi Operativi, Corso di Laurea in Ingegneria Informatica
 * DIMES, Università della Calabria
 * ============================================================
 */
package aereoporto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author Marco Lackovic <mlackovic@deis.unical.it>
 * @version 1.2, Jan 15, 2012
 */
public class AeroportoSem extends Aeroporto {

    private int[] numAereiInAttesa = new int[2];

    private HashMap<Thread, Semaphore> aereiInAttesaDiSbarco = new HashMap<Thread, Semaphore>();
    private LinkedList<Thread> aereiInAttesaDellaNavetta = new LinkedList<Thread>();

    private Semaphore servizioNavetta = new Semaphore(0);
    private Semaphore mutexPiste = new Semaphore(1);
    private Semaphore mutexNavette = new Semaphore(1);
    private Semaphore[] piste = new Semaphore[2];

    public AeroportoSem(int numPiste) {
        numPisteLibere = numPiste;
        for (int i = 0; i < piste.length; i++) {
            piste[i] = new Semaphore(0, true);
            numAereiInAttesa[i] = 0;
        }
    }

    @Override
    public void richiediPista(int tipo) throws InterruptedException {
        mutexPiste.acquire();
        numAereiInAttesa[tipo]++;
        if (!pistaDisponibile(tipo)) {
            mutexPiste.release();
            piste[tipo].acquire();
        }
        numAereiInAttesa[tipo]--;
        numPisteLibere--;
        mutexPiste.release();
    }

    private boolean pistaDisponibile(int tipo) {
        return numPisteLibere > 0
                && numAereiInAttesa[tipo] == 1
                && (tipo == ATTERRAGGIO || numAereiInAttesa[ATTERRAGGIO] < numPisteLibere);
    }

    @Override
    public void rilasciaPista() throws InterruptedException {
        mutexPiste.acquire();
        numPisteLibere++;
        if (numAereiInAttesa[ATTERRAGGIO] > 0) {
            piste[ATTERRAGGIO].release();
        } else if (numAereiInAttesa[DECOLLO] > 0) {
            piste[DECOLLO].release();
        } else {
            mutexPiste.release();
        }
    }

    @Override
    public void richiediNavetta() throws InterruptedException {
        Thread aereo = Thread.currentThread();
        Semaphore fineSbarco = new Semaphore(0);
        mutexNavette.acquire();
        aereiInAttesaDellaNavetta.add(aereo);
        aereiInAttesaDiSbarco.put(aereo, fineSbarco);
        mutexNavette.release();
        servizioNavetta.release();
        fineSbarco.acquire();
    }

    @Override
    public Thread getAereo() throws InterruptedException {
        servizioNavetta.acquire();
        mutexNavette.acquire();
        Thread aereo = aereiInAttesaDellaNavetta.remove();
        mutexNavette.release();
        return aereo;
    }

    @Override
    public void fineSbarco(Thread aereo) throws InterruptedException {
        mutexNavette.acquire();
        aereiInAttesaDiSbarco.remove(aereo).release();
        mutexNavette.release();
    }

}
