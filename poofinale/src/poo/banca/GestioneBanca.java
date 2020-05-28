package poo.banca;
import java.util.*;

public class GestioneBanca{
   static Banca banca=new Banca(100);//ambiente globale
   static Scanner s=new Scanner( System.in );
   static boolean menu(){
      System.out.println();
      System.out.println("0 - esci");
      System.out.println("1 - crea nuovo conto");
      System.out.println("2 - rimuovi conto esistente");
      System.out.println("3 - mostra il saldo");
      System.out.println("4 - fai un deposito");
      System.out.println("5 - fai un prelievo");
      System.out.println("6 - mostra il fido");
      System.out.println("7 - cambia il fido");    
      System.out.println("8 - salva conti su file");
      System.out.println("9 - ripristina conti da file");
      System.out.println();
      int scelta=0;
      boolean ok=false;
      do{
    	  System.out.print("scelta>");
    	  try{
    		  scelta=s.nextInt(); 
    		  s.nextLine(); //salta il fine linea
    		  ok=true;
    	  }catch(Exception e){
    		  System.out.println("Scelta errata! Devi inserire un numero tra 0 e 9");
    		  s.nextLine();
    	  }
      }while(!ok);
      switch( scelta ){
         case 0: return esci();
         case 1: creaConto(); break;
         case 2: rimuoviConto(); break;
         case 3: mostraSaldo(); break;
         case 4: faiDeposito(); break;
         case 5: faiPrelievo(); break;
         case 6: mostraFido(); break;
         case 7: cambiaFido(); break;
         case 8: salva(); break;
         case 9: ripristina(); break;
         default:
            System.out.println("Comando sconosciuto!");
      }//switch
      return false;
   }//menu
   static boolean esci(){
	   System.out.println("Vuoi salvare i dati sui conti (si/no)?");
	   String risposta=s.nextLine().toLowerCase();
	   if( risposta.equals("no") ) return true;
	   if( risposta.equals("si") ){
		   salva();
		   return true;
	   }
	   System.out.println("Cosa? Uscita negata");
	   return false;
   }//esci
   static void creaConto(){
      System.out.println("Formato input crea conto: numero bilancio [fido] CR");
      String linea=s.nextLine();
      if( linea==null || linea.length()==0 ){
         System.out.println("Nessun conto e' stato creato!"); return;
      }
      StringTokenizer st=new StringTokenizer( linea, " " );
      try{
    	  String conto=st.nextToken();
    	  double bilancio=Double.parseDouble( st.nextToken() );
    	  ContoBancario cb;
    	  if( st.hasMoreTokens() ){
    		  double fido=Double.parseDouble( st.nextToken() );
    		  cb=new ContoConFido( conto, bilancio, fido );
    	  }
    	  else{
    		  cb=new ContoBancario( conto, bilancio );
    	  }
    	  banca.aggiungiConto( cb );
    	  System.out.println("Operazione effettuata.");
      }catch( RuntimeException e ){
    	  System.out.println("Nessun conto e' stato creato!");
      }
   }//creaConto
   static void rimuoviConto(){
      System.out.println("Formato input rimuovi conto: numeroconto CR");
      String numero=s.nextLine();
      if( numero==null || numero.length()==0 ){
         System.out.println("Nessun conto e' rimosso"); return;
      }
      ContoBancario fittizio=new ContoBancario( numero );
      int i=banca.indexOf( fittizio );
      if( i==-1 ){
         System.out.println("Conto inesistente durante un rimuovi conto");
         return;
      }
      else{
         banca.rimuoviConto( i );
         System.out.println("Operazione effettuata.");
      }
   }//rimuoviConto
   static void mostraSaldo(){
      System.out.println("Formato input mostra saldo: numeroConto CR");
      String numero=s.nextLine();
      if( numero==null || numero.length()==0 ){
         System.out.println("Nessun numero di conto!"); return;
      }
      ContoBancario fittizio=new ContoBancario( numero );
      int i=banca.indexOf( fittizio );
      if( i==-1 ){
         System.out.println("Conto inesistente durante un mostra saldo");
         return;
      }
      else{
         ContoBancario cb=banca.getConto( i );
         System.out.println("saldo="+cb.saldo() );
      }
   }//mostraSaldo
   static void faiDeposito(){
	   System.out.println("Formato input fai deposito: numeroConto ammontareDeposito CR");
	   String linea=s.nextLine();
	   if( linea==null || linea.length()==0 ){
		   System.out.println("Nessun dato!"); return;
	   }
	   StringTokenizer st=new StringTokenizer( linea, " " );
	   String conto=st.nextToken();
	   if( !st.hasMoreTokens() ){
		   System.out.println("Manca ammontare!");
		   return;
	   }
	   double quanto=Double.parseDouble( st.nextToken() ); 
	   if( quanto<=0 ){
		   System.out.println("Ammontare invalido!");
	   }
	   ContoBancario fittizio=new ContoBancario( conto );
	   int i=banca.indexOf( fittizio );
	   if( i==-1 ){
		   System.out.println("Conto inesistente durante un deposito!");
	       return;
	   }
	   else{
	       ContoBancario cb=banca.getConto( i );
	       cb.deposita(quanto);
	   }	   
	   System.out.println("Operazione effettuata.");
   }//faiDeposito
   static void faiPrelievo(){
	   System.out.println("Formato input fai prelievo: numeroConto ammontarePrelievo CR");
	   String linea=s.nextLine();
	   if( linea==null || linea.length()==0 ){
		   System.out.println("Nessun dato!"); return;
	   }
	   StringTokenizer st=new StringTokenizer( linea, " " );
	   String conto=st.nextToken();
	   if( !st.hasMoreTokens() ){
		   System.out.println("Manca ammontare!");
		   return;
	   }
	   double quanto=Double.parseDouble( st.nextToken() ); 
	   if( quanto<=0 ){
		   System.out.println("Ammontare invalido!");
	   }
	   ContoBancario fittizio=new ContoBancario( conto );
	   int i=banca.indexOf( fittizio );
	   if( i==-1 ){
		   System.out.println("Conto inesistente durante un prelievo!");
	       return;
	   }
	   else{
	       ContoBancario cb=banca.getConto( i );
	       if( cb.preleva(quanto) )
	    	   System.out.println("Operazione effettuata.");
	       else
	    	   System.out.println("Prelevamento fallito.");
	   }	     
	}//faiPrelievo
   static void mostraFido(){
	   System.out.println("Formato input mostra fido: numeroConto CR");
	   String numero=s.nextLine();
	   if( numero==null || numero.length()==0 ){
	      System.out.println("Nessun numero di conto!"); return;
	   }
	   ContoBancario fittizio=new ContoBancario( numero );
	   int i=banca.indexOf( fittizio );
	   if( i==-1 ){
	      System.out.println("Conto inesistente durante un mostra saldo");
	      return;
	   }
	   else{
	      ContoBancario cb=banca.getConto( i );
	      if( !(cb instanceof ContoConFido ) ){
	    	  System.out.println("Operazione non disponibile su questo conto");
	    	  return;
	      }
	      System.out.println("fido="+((ContoConFido)cb).fido() );
	   }
	   System.out.println("Operazione effettuata.");
   }//mostraFido
   static void cambiaFido(){
	   System.out.println("Formato input cambia fido: numeroConto nuovoFido CR");
	   String linea=s.nextLine();
	   if( linea==null || linea.length()==0 ){
		   System.out.println("Nessun dato!"); return;
	   }
	   StringTokenizer st=new StringTokenizer( linea, " " );
	   String conto=st.nextToken();
	   if( !st.hasMoreTokens() ){
		   System.out.println("Manca nuovo fido!");
		   return;
	   }
	   double nuovoFido=Double.parseDouble( st.nextToken() ); 
	   if( nuovoFido<=0 ){
		   System.out.println("Nuovo fido invalido!");
	   }
	   ContoBancario fittizio=new ContoBancario( conto );
	   int i=banca.indexOf( fittizio );
	   if( i==-1 ){
		   System.out.println("Conto inesistente durante un prelievo!");
	       return;
	   }
	   else{
	       ContoBancario cb=banca.getConto( i );
	       if( !(cb instanceof ContoConFido) ){
	    	   System.out.println("Operazione non disponibile su questo conto");
	    	   return;	    	   
	       }
	       ((ContoConFido)cb).nuovoFido(nuovoFido);
	   }	     
	   System.out.println("Operazione effettuata.");
   }//cambiaFido
   static void salva(){
	   //TODO
   }//salva
   static void ripristina(){
	   //TODO
   }//ripristina
   
   public static void main( String [] args ){
      System.out.println("Gestione Banca");
      System.out.println();
      boolean termine=false;
      do{
         termine=menu();
      }while( !termine );
      System.out.println("Bye.");
   }
}//GestioneBanca
