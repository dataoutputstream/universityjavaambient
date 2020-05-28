/* ============================================================
 * Sistemi Operativi, Corso di Laurea in Ingegneria Informatica
 * DIMES, Università della Calabria
 * ============================================================
 */
package ruota;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Marco Lackovic <mlackovic@deis.unical.it>
 * @version 1.4, Jan 17, 2013
 */
public class RuotaLC extends Ruota {

    /*
     * Questa lista serve per garantire il risveglio delle persone in ordine
     * FIFO, e per sapere se ci sono persone in fila
     */
    private LinkedList<Thread> fila = new LinkedList<Thread>();

    /*
     * Questa variabile booleana serve per garantire che le persone entrino solo
     * se sono state chiamate dall'addetto
     */
    private boolean ingresso = false;

    /*
     * Uso un solo lucchetto perché non ci sono sezioni indipendenti, si può
     * salire o salire su una sola cabina alla volta
     */
    private Lock lock = new ReentrantLock();

    /*
     * Questa condition serve per sospendere le persone che vogliono salire
     * sulla ruota
     */
    private Condition persone = lock.newCondition();

    /*
     * Questo array di condtion serve per sospendere le persone che vogliono
     * uscire dalla cabina su cui sono salite
     */
    private Condition[] cabine = new Condition[NUM_CABINE];

    /*
     * Questa condition serve per sospendere l'addetto in attesa che le persone
     * siano scese dalla cabina
     */
    private Condition passeggeriScesi = lock.newCondition();

    /*
     * Questa condition serve per sospendere l'addetto in attesa che le persone
     * siano salite sulla cabina
     */
    private Condition passeggeriSaliti = lock.newCondition();

    public RuotaLC() {
        super();
        for (int i = 0; i < cabine.length; i++) {
            cabine[i] = lock.newCondition();
        }
    }

    @Override
    public void faiUnGiro() throws InterruptedException {
        lock.lock();
        try {
            fila.add(Thread.currentThread());
            while (!mioTurno()) {
                persone.await();
            }
            fila.remove(Thread.currentThread());
            int idCabina = idCabinaAlSuolo;
            numPersoneInCabina[idCabina]++;
            passeggeriSaliti.signal();
            /*
             * Uso il do-while per evitare che una persona esca subito dalla
             * cabina su cui è appena salita, e il controllo sulla variabile
             * ingresso in caso avvenga uno spurious wakeup nella fase di
             * ingresso nella cabina
             */
            do {
                cabine[idCabina].await();
            } while (ingresso || idCabina != idCabinaAlSuolo);
            numPersoneInCabina[idCabina]--;
            passeggeriScesi.signal();
        } finally {
            lock.unlock();
        }
    }

    private boolean mioTurno() {
        int postiDisponibili = MAX_PERSONE_PER_CABINA
                - numPersoneInCabina[idCabinaAlSuolo];
        return ingresso && postiDisponibili > 0
                && fila.indexOf(Thread.currentThread()) < postiDisponibili;
    }

    @Override
    public void ingressoUscita() throws InterruptedException {
        lock.lock();
        try {
            int numPersoneInFila = fila.size();
            stampaStato(numPersoneInFila, numPersoneInCabina[idCabinaAlSuolo]);
            // Faccio scendendere gli eventuali occupanti dalla cabina
            cabine[idCabinaAlSuolo].signalAll();
            while (numPersoneInCabina[idCabinaAlSuolo] > 0) {
                passeggeriScesi.await();
            }
            if (numPersoneInFila > 0) {
                ingresso = true;
                int numPasseggeri = numPersoneInFila < MAX_PERSONE_PER_CABINA ? numPersoneInFila
                        : MAX_PERSONE_PER_CABINA;
                persone.signalAll();
                /*
                 * Aspetto che i passeggeri siano saliti sulla cabina prima di
                 * aggiornare l'id
                 */
                while (numPersoneInCabina[idCabinaAlSuolo] < numPasseggeri) {
                    passeggeriSaliti.await();
                }
                ingresso = false;
            }
            idCabinaAlSuolo = (idCabinaAlSuolo + 1) % NUM_CABINE;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numPersone = 200;
        new RuotaLC().test(numPersone);
    }
}
