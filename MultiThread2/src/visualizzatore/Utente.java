package visualizzatore;

import java.util.Random;

class Utente extends Thread {

	  private int id; // id utente, tra 0 e 9
	  private Coda coda;
	  private Random random = new Random();
	  
	  private int MAX_ATTESA = 1000;
	  
	  private int MIN_STRINGHE = 1;
	  private int MAX_STRINGHE = 5;

	  public Utente (int id, Coda coda) {
	    this.id = id;
	    this.coda = coda;
	  }

	  public void run () {
		int ns = 0; // numero stringhe generate finora dall'utente
	    while (true) {
	      long attesaCasuale = random.nextInt(MAX_ATTESA); // numero tra 0 e 999
	      try {
	        sleep(attesaCasuale); // attesa casuale tra 0 e 999 ms
	      } catch (InterruptedException e) {
	        System.err.println(e);
	      }
	      int numStringhe = MIN_STRINGHE+random.nextInt(MAX_STRINGHE); // numero di stringhe da inserire tra 1 e 5
	      String stringheDaInserire[] = new String[numStringhe];
	      for (int i = 0; i <numStringhe; i++) {
	        stringheDaInserire[i] = new String("S"+"_"+id+"_"+(ns++)); // ogni stringa creata è composta dalla lettera "S", seguita dall'id dell'utente, seguita dal numero progressivo della stringa
	      }
	      coda.inserisci(stringheDaInserire);
	      System.out.println("Utente ha inserito " + numStringhe + " stringhe.");
	      
	    }
	  }

	}