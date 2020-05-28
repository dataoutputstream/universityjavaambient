package sistema;
import java.util.Arrays;
import java.util.Scanner;

import util.Array;
public class Cramer extends Sistema {

	public double[] Soluzioni;
	public Cramer(double[][] a, double[] y) {
		super(a, y);
		Soluzioni=risolvi(a , y);
		
	}

	public double[] risolvi(double[][]a,double[]y){
		double detA=Array.determinante(a);
		double []solS=new double[a.length];
		double[][]m=Arrays.copyOf(a, a.length);
		for(int i=0;i<a.length;i++){
			for(int j =0;j<a.length;j++)
				m[i][j]=y[i];
				
			double detAi=Array.determinante(m);
			solS[i]=detAi/detA;
			for(int j=0;j<m.length;j++){
				m[i][j]=a[i][j];
			}
		}
		
		return solS;
		
	}
	@Override
	public double[] risolvi() {
		return null;
	}
	public static void main(String[]Args){
		Scanner sc=new Scanner(System.in);
		double [][] a= new double[3][3];
		double []y= new double[3];
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				a[i][j]=sc.nextDouble();
			}
				y[i]=sc.nextDouble();
		
		
	}sc.close();
	Cramer c= new Cramer(a,y);
	Arrays.toString(c.Soluzioni);
}

	
}
