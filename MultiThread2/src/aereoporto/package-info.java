/* ============================================================
 * Sistemi Operativi, Corso di Laurea in Ingegneria Informatica
 * DIMES, Università della Calabria
 * ============================================================
 */

/**
 * Tratto dalla prova scritta del 6 luglio 2011
 * 
 * Un aeroporto comprende P piste di decollo/atterraggio, Q navette per il
 * trasporto passeggeri, e gestisce R aerei, con P < Q < R.
 * 
 * Ogni aereo effettua ciclicamente le seguenti operazioni: vola per un tempo
 * casuale compreso tra 600 e 900 ms, quindi atterra (impiegando per questa
 * operazione 300ms), aspetta che i passeggeri scendano dall'aereo e che ne
 * salgano dei nuovi ed infine decolla (impiegando per questa operazione 300ms).
 * Gli atterraggi hanno la precedenza sui decolli, e le richieste di decollo e
 * atterraggio devono essere servite ciascuna in ordine FIFO. Si supponga che ci
 * siano abbastanza parcheggi nell'aeroporto per ospitare tutti gli R aerei.
 * 
 * Ogni navetta raggiunge un aereo appena atterrato, sbarca i suoi passeggeri e
 * ne imbarca dei nuovi (impiegando per queste operazioni 400ms), dopodiché
 * raggiunge un altro aereo in attesa o, se non ce ne sono, rimane in attesa
 * dell'atterraggio di qualche aereo.
 */
package aereoporto;

