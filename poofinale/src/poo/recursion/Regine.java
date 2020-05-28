package poo.recursion;
//Problema n-regine

import java.util.*;

class Scacchiera{
   private int n, numSol=0;
   private int []c; //vettore delle colonne
   private boolean []su; //vettore diagonali nord-est
   private boolean []giu;//vettore diagonali nord-ovest
   public Scacchiera( int n ){
	  if( n<4 )
		  throw new IllegalArgumentException();
      this.n=n;
      c=new int[n];
      su=new boolean[2*n-1];
      giu=new boolean[2*n-1];
      for( int i=0; i<2*n-1; i++ ){
         if( i<n ) c[i]=-1;
         su[i]=false; giu[i]=false;
      }
   }
   public void risolvi(){
      collocaRegina( 0 );
   }//risolvi
   private void collocaRegina( int rig ){
      for( int col=0; col<n; col++ )
         if( assegnabile( rig, col ) ){
            assegna( rig, col );
            if( rig==n-1 ) scriviSoluzione();
            else collocaRegina( rig+1 );
            deassegna( rig, col );
         }
   }//collocaRegina
   private boolean assegnabile( int rig, int col ){
      if( c[col]!=-1 ) return false;
      if( rig-col<0 && giu[(rig-col)+2*n-1] ) return false;
      if( rig-col>=0 && giu[rig-col] ) return false;
      if( su[rig+col] ) return false;
      return true;
   }//assegnabile
   private void assegna( int rig, int col ){
      c[col]=rig; su[rig+col]=true;
      if( rig-col<0 ) giu[(rig-col)+2*n-1]=true;
      else giu[rig-col]=true;
   }//assegna
   private void deassegna( int rig, int col ){
      c[col]=-1; su[rig+col]=false;
      if( rig-col<0 ) giu[(rig-col)+2*n-1]=false;
      else giu[rig-col]=false;
   }//deassegna
   private void scriviSoluzione(){
      numSol++;
      System.out.print( numSol+" " );
      for( int i=0; i<n; i++ )
         System.out.print( "<"+i+","+c[i]+"> " );
      System.out.println();
   }//scriviSoluzione
}//Scacchiera

public class Regine{
   public static void main( String []args ){
	  System.out.println("Problema delle N regine");
      Scanner sc=new Scanner( System.in );
      System.out.print("N(>3): ");
      int n=sc.nextInt();
      new Scacchiera(n).risolvi();
   }//main
}//Regine
