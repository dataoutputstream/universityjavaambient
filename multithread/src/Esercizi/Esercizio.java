package Esercizi;

public class Esercizio extends Thread {
	  
	  public static String nome = "ciao";
	  
	  public void prova(){
	    start();
	    nome  = nome + " mondo";
	  }
	  
	  public void run() {
	    nome  += "1 2 3";
	  }
	  
	  public static void main (String[] args) {
	    new Esercizio().prova();
	    System.out.println(nome);
	  
	  }
	}
