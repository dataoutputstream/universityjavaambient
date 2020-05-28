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
public class Navetta implements Runnable {
    private static final int TEMPO_SCARICO_CARICO_PASSEGGERI = 400;
    private Aeroporto aeroporto;

    public Navetta(Aeroporto p) {
        this.aeroporto = p;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread aereo = aeroporto.getAereo();
                serviAereo();
                aeroporto.fineSbarco(aereo);
            }
        } catch (InterruptedException e) {
        }
    }

    private void serviAereo() throws InterruptedException {
        System.out.println("La navetta " + Thread.currentThread().getId()
                + " sta servendo un aereo");
        Thread.sleep(TEMPO_SCARICO_CARICO_PASSEGGERI);
    }
}
