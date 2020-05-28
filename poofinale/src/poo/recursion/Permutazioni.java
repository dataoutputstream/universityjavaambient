package poo.recursion;
public final class Permutazioni{
   public Permutazioni(){}
   public static void main( String []args ){
      int a[]={1, 2, 3};
      permuta( a, 0 );
   }
   public static void permuta( int []a, int i ){
      if( i==a.length-1 ){
          for( int k=0; k<a.length; k++ ) System.out.print( a[k]+" " );
          System.out.println();
      }
      else{
          for( int j=i; j<a.length; j++ ){
              int park=a[i]; a[i]=a[j]; a[j]=park; /*scambia a[i] con a[j]*/
              permuta( a, i+1 );
              park=a[i]; a[i]=a[j]; a[j]=park; /*scambia a[i] con a[j] */
          }
      }
   }//permuta
}//Permutazioni
