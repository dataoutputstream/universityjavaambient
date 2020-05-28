package poo.razionali;
import poo.util.*;

public class Razionale{
   //Esempio di classe di oggetti immutabili
   private final int NUM, DEN;
   private static int contatore=0;
   public Razionale( int num, int den ){
      if( den==0 ){
      	  System.out.println("Denominatore nullo.");
      	  System.exit(-1);
	  }
      if( num!=0 ){
    	  int n=Math.abs( num ), d=Math.abs( den );
    	  int cd=Mat.mcd( n, d );
    	  num=num/cd; den=den/cd;
      }
      if( den<0 ){
         num *= -1;
         den *= -1;
      }
      this.NUM=num;
      this.DEN=den;
      contatore++;
   }
   public Razionale( Razionale r ){//costruttore di copia
      this.NUM=r.NUM;
      this.DEN=r.DEN;
      contatore++;
   }
   public int getNum(){ return NUM; }
   public int getDen(){ return DEN; }

   public Razionale add( Razionale r ){
      int mcm=Mat.mcm(r.DEN,DEN);
      int num=(mcm/DEN)*NUM + (mcm/r.DEN)*r.NUM;
      int den=mcm;
      return new Razionale( num, den );
   }//add
   public Razionale sub( Razionale r ){
	      int mcm=Mat.mcm(r.DEN,DEN);
	      int num=(mcm/DEN)*NUM - (mcm/r.DEN)*r.NUM;
	      int den=mcm;
	      return new Razionale( num, den );
   }//sub
   public Razionale mul( Razionale r ){
      return new Razionale( NUM*r.NUM, DEN*r.DEN );
   }//mul
   public Razionale mul( int s ){
      return new Razionale( NUM*s, DEN );
   }//mul

   public Razionale div( Razionale r ){
      return new Razionale( NUM*r.DEN, DEN*r.NUM );
   }//div
   public static int razionaliCreati(){ return contatore; }
   public String toString(){
      if( DEN==1 ) return ""+NUM;
      if( NUM==0 ) return "0";
      return ""+NUM+"/"+DEN;
   }//toString

   protected void finalize(){ contatore--; }

}//Razionale

