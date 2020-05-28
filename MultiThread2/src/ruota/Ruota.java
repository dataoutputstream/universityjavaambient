/* ============================================================
 * Sistemi Operativi, Corso di Laurea in Ingegneria Informatica
 * DIMES, Università della Calabria
 * ============================================================
 */
package ruota;

import java.util.concurrent.TimeUnit;

import unical.inginf.sisop.commons.Sleep;

/**
 * @author Marco Lackovic <mlackovic@deis.unical.it>
 * @version 1.4, Jan 17, 2013
 */
public abstract class Ruota {

    public static final int NUM_CABINE = 20;

    public static final int MAX_PERSONE_PER_CABINA = 4;

    // Questo array memorizza il numero di persone in ciascuna cabina.
    protected int[] numPersoneInCabina = new int[NUM_CABINE];

    // Questo intero serve per sapere qual è la cabina più vicina al suolo.
    protected int idCabinaAlSuolo = 0;

    public Ruota() {
        for (int i = 0; i < numPersoneInCabina.length; i++) {
            numPersoneInCabina[i] = 0;
        }
    }

    public abstract void faiUnGiro() throws InterruptedException;

    public abstract void ingressoUscita() throws InterruptedException;

    protected void test(int numPersone) throws InterruptedException {
        System.out.println("Test della classe " + getClass().getSimpleName());
        new Thread(new Addetto(this)).start();
        for (int i = 0; i < numPersone; i++) {
            // Simulo l'arrivo delle persone in tempi diversi
            Sleep.random(TimeUnit.MILLISECONDS, 50, 400);
            new Thread(new Persona(this)).start();
        }
    }

    protected void stampaStato(int numPersoneInFila, int numPersoneCheEscono) {
        synchronized (System.out) {
            System.out.print(numPersoneInFila + " -> \t[");
            for (int i = 0; i < numPersoneInCabina.length; i++) {
                System.out.print(numPersoneInCabina[i] + " ");
            }
            System.out.println("] -> " + numPersoneCheEscono);
            System.out.print("\t");
            for (int i = 0; i < idCabinaAlSuolo; i++) {
                System.out.print("  ");
            }
            System.out.println(" ^");
        }
    }
}
