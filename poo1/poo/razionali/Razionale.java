package razionali;
import util.Mat;

public class Razionale{

   private final int NUM, DEN;
   private static int contatore=0;
   public Razionale( int num, int den ){
      if( den==0 )
      	throw new RuntimeException("Denominatore nullo!");
      if( num!=0 ){
    	  //riduzione ai minimi termini
    	  int n=Math.abs( num ), d=Math.abs( den );
    	  int maxCD=Mat.mcd( n, d );
    	  num=num/maxCD; den=den/maxCD;
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
   public int getNum(){//metodo accessore
      return NUM;
   }// getNumeratore
   public int getDen(){//metodo accessore
      return DEN;
   }//getDenominatore
   public Razionale add( Razionale r ){
      int mcm=Mat.mcm(this.DEN,r.DEN);
      int num=(mcm/DEN)*NUM +
                    (mcm/r.DEN)*r.NUM;
      int den=mcm;
      return new Razionale( num, den );
   }//add
   public Razionale sub( Razionale r ){
	  return this.add( r.mul(-1) );
   }//sub
   public Razionale mul( Razionale r ){
      return new Razionale( this.NUM*r.NUM,
                            this.DEN*r.DEN );
   }//mul
   public Razionale mul( int s ){
      return new Razionale( NUM*s, DEN );
   }//mul

   public Razionale div( Razionale r ){
      return new Razionale( NUM*r.DEN,
                            DEN*r.NUM );
   }//div

   public static int razionaliEsistenti(){ return contatore; }

   public String toString(){
      if( DEN==1 ) return ""+NUM;
      if( NUM==0 ) return "0";
      return ""+NUM+"/"+DEN;
   }//toString

   protected void finalize(){ contatore--; }
}//Razionale



