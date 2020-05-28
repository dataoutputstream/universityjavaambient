/* ============================================================
 * Sistemi Operativi, Corso di Laurea in Ingegneria Informatica
 * DIMES, Università della Calabria
 * ============================================================
 */
/**
 * Tratto dalla prova scritta del 13 settembre 2012
 * 
 * Delle persone si recano presso una ruota panoramica per fare un giro su di
 * essa. La ruota panoramica ha 20 cabine, ciascuna delle quali può ospitare 4
 * persone. La ruota gira molto lentamente, ad una velocità tale per cui è
 * possibile scendere e salire dalla cabina che si trova vicino al suolo, senza
 * che la ruota debba fermarsi. Ogni 60 secondi, una cabina raggiunge il punto
 * più vicino al suolo; in quel momento un addetto alla ruota esegue le seguenti
 * operazioni: fa scendendere gli eventuali occupanti dalla cabina, fa salire in
 * ordine FIFO nella cabina le eventuali persone in fila.
 * 
 * Si modelli il sistema descritto in Java, dove le persone e l'addetto sono dei
 * thread che interagiscono tramite un oggetto ruota. La ruota esporta due
 * metodi che permettono alle persone di salire e scendere dalla cabina più
 * vicina al suolo, ed un metodo che permette all'addetto di eseguire la
 * procedura di uscita ed ingresso delle persone dalla cabina più vicina al
 * suolo.
 * 
 * Si implementino due soluzioni che riproducano il funzionamento del problema
 * sopra descritto utilizzando (all’interno dell’oggetto parco divertimenti):
 * (1) la classe Semaphore del package java.util.concurrent (2) gli strumenti di
 * mutua esclusione e sincronizzazione del package java.util.concurrent.locks.
 * 
 * Si scriva infine un main d'esempio che, facendo uso di una delle due
 * soluzioni precedenti, inizializzi 200 persone, 1 addetto, e ne avvii
 * l’esecuzione.
 */
package ruota;

