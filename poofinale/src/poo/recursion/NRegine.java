package poo.recursion;
//Problema n-regine

import java.util.*;

class Board{
   private int n, numSol=0;
   private boolean [][]board;
   public Board( int n ){
	  if( n<4 ) 
		  throw new IllegalArgumentException();
      this.n=n;
      board=new boolean[n][n];
      for( int i=0; i<n; i++ )
    	  for( int j=0; j<n; j++ )
    		  board[i][j]=false;
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
	  //verifica a nord
	  for( int i=rig-1; i>=0; i-- )
		  if( board[i][col] ) return false;
	  //verifica a nord-est
	  for( int i=rig-1, j=col+1; i>=0 && j<n; i--, j++ )
		  if( board[i][j] ) return false;
	  //verifica a nord-ovest
	  for( int i=rig-1, j=col-1; i>=0 && j>=0; i--, j-- )
		  if( board[i][j] ) return false;	  
      return true;
   }//assegnabile
   private void assegna( int rig, int col ){
	   board[rig][col]=true;
   }//assegna
   private void deassegna( int rig, int col ){
	   board[rig][col]=false;
   }//deassegna
   private void scriviSoluzione(){
	   numSol++;
	   System.out.print( numSol+" " );
	   for( int i=0; i<n; i++ )
    	  for( int j=0; j<n; j++ )
    		  if( board[i][j] ){
    			  System.out.print( "<"+i+","+j+"> " );
    			  break;
    		  }
	   System.out.println();
   }//scriviSoluzione
}//Board

public class NRegine{
   public static void main( String []args ){
	   System.out.println("Problema delle N regine");
	   Scanner sc=new Scanner( System.in );
	   System.out.print("N(>3): ");
	   int n=sc.nextInt();
	   new Board(n).risolvi();
   }//main
}//Regine
