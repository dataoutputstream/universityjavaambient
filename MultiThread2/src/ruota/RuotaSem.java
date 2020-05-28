/* ============================================================
 * Sistemi Operativi, Corso di Laurea in Ingegneria Informatica
 * DIMES, Universit√† della Calabria
 * ============================================================
 */
package ruota;

import java.util.concurrent.Semaphore;

/**
 * @author Marco Lackovic <mlackovic@deis.unical.it>
 * @version 1.3, Jan 16, 2013
 */
public class RuotaSem extends Ruota {

    /*
     * Questo intero serve 
     * per sapere se ci sono 
     * persone in fila che vogliono
     * salire sulla ruota
     */
    private int numPersoneInFila = 0;

    /*
     * Questo semaforo serve 
     * per sospendere le persone 
     * che vogliono salire sulla
     * ruota, in ordine FIFO
     */
    private Semaphore codaIngresso = new Semaphore(0, true);

    /*
     * Questo array di semafori 
     * serve per sospendere le 
     * persone che vogliono
     * uscire dalla cabina su cui sono salite
     */
    private Semaphore[] cabine = new Semaphore[NUM_CABINE];

    /*
     * Questo semaforo serve 
     * per sospendere l'addetto 
     * in attesa che le persone
     * siano salite sulle cabina
     */
    private Semaphore passeggeriSaliti = new Semaphore(0);

    /*
     * Questo semaforo serve 
     * per accedere in mutua 
     * esclusione alle variabili
     * condivise
     */
    private Semaphore mutex = new Semaphore(1);

    public RuotaSem() {
        super();
        for (int i = 0; i < cabine.length; i++) {
            cabine[i] = new Semaphore(0);
        }
    }

    @Override
    public void faiUnGiro() throws InterruptedException {
        mutex.acquire();
        numPersoneInFila++;
        mutex.release();
        //In fila Per entrare
        codaIngresso.acquire();
        /*
         * idCabinaAlSuolo non viene modificata da nessuno in questo frangente
         * (√® temporaneamente immutabile), quindi pu√≤ essere letta in sicurezza
         * anche da pi√π thread contemporaneamente
         */
        int idCabina = idCabinaAlSuolo;
        
        //release atta a dare un permesso affinchË l'adetto possa andare avanti
        passeggeriSaliti.release();
        
        //fino a quando l'addetto non rilascer‡ i permessi i Thread saranno bloccati
        cabine[idCabina].acquire();
    }

    @Override
    public void ingressoUscita() throws InterruptedException {
        mutex.acquire();
        stampaStato(numPersoneInFila, numPersoneInCabina[idCabinaAlSuolo]);
        // Faccio scendendere gli eventuali occupanti dalla cabina
        cabine[idCabinaAlSuolo].release(numPersoneInCabina[idCabinaAlSuolo]);
        numPersoneInCabina[idCabinaAlSuolo] = 0;

        // Faccio salire le eventuali persone in fila
        if (numPersoneInFila > 0) {
        	//se il num dei passegeri in fila Ë minore del max allora Num Passegeri= Persone in fila
        	//altrimenti faccio entrare il max possibile
            int numPasseggeri = numPersoneInFila < MAX_PERSONE_PER_CABINA ? numPersoneInFila
                    : MAX_PERSONE_PER_CABINA;
            
            //aggiorno le persone in fila
            numPersoneInFila -= numPasseggeri;
            numPersoneInCabina[idCabinaAlSuolo] = numPasseggeri;
            
            //A seguito della seguente release i passeggeri bloccati sulla coda entreranno
            codaIngresso.release(numPasseggeri);
            
            /*
             * Aspetto che i passeggeri siano saliti sulla cabina prima di
             * aggiornare l'id della cabina al suolo
             */
            passeggeriSaliti.acquire(numPasseggeri);
        }
        idCabinaAlSuolo = (idCabinaAlSuolo + 1) % NUM_CABINE;
        mutex.release();
    }

    public static void main(String[] args) throws InterruptedException {
        int numPersone = 200;
        new RuotaSem().test(numPersone);
    }

}
