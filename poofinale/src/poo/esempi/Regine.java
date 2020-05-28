//Problema n-regine
package poo.esempi;
class Scacchiera{
   int n, numSol=0;
   int []c;
   boolean []su;
   boolean []giu;
   public Scacchiera( int n ){
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
   void collocaRegina( int rig ){
      for( int col=0; col<n; col++ )
         if( assegnabile( rig, col ) ){
            assegna( rig, col );
            if( rig==n-1 ) scriviSoluzione();
            else collocaRegina( rig+1 );
            deassegna( rig, col );
         }
   }//collocaRegina
   boolean assegnabile( int rig, int col ){
      if( c[col]!=-1 ) return false;
      if( rig-col<0 && giu[(rig-col)+2*n-1] ) return false;
      if( rig-col>=0 && giu[rig-col] ) return false;
      if( su[rig+col] ) return false;
      return true;
   }//assegnabile
   void assegna( int rig, int col ){
      c[col]=rig; su[rig+col]=true;
      if( rig-col<0 ) giu[(rig-col)+2*n-1]=true;
      else giu[rig-col]=true;
   }//assegna
   void deassegna( int rig, int col ){
      c[col]=-1; su[rig+col]=false;
      if( rig-col<0 ) giu[(rig-col)+2*n-1]=false;
      else giu[rig-col]=false;
   }//deassegna
   void scriviSoluzione(){
      numSol++;
      System.out.print( numSol+" " );
      for( int i=0; i<n; i++ )
         System.out.print( "<"+c[i]+","+i+"> " );
      System.out.println();
   }//scriviSoluzione
}//Scacchiera

public class Regine{
   public static void main( String []args ){
      if( args.length!=1 ){
         System.out.println("Dimensione del problema attesa");
         System.exit(-1);
      }
      int n=Integer.parseInt( args[0] );
      new Scacchiera(n).risolvi();
   }//main
}//Regine

