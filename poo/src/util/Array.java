package util;

import java.util.Arrays;
import java.util.Scanner;

public class Array {
	public static double determinante(double [][]m){
		double determinante=1.0;
		if(Quadrata.quadrata(m)){
			double [][]a=Arrays.copyOf(m, m.length);
			int scambi =triangolizza(a);
			
			for(int i=0;i<a.length;i++){
				determinante*=a[i][i];
			}
			if((scambi%2)!=0) determinante*=-1;
			
		}
		
			
	
		
		return determinante;
		
	}
	protected static int triangolizza(double[][]m){
		int n=m.length;
		int scambi = 0;
		for(int j=0;j<n;j++){
			if(m[j][j]==0D){
				int p=j+1;
				for(;p<n;p++)
					if(m[p][j]!=0D) 
						break;
				if(p==n) throw new RuntimeException("Determinante Uguale a 0");
				double[]tmp=m[j];
				m[j]=m[p];
				m[p]=tmp;
				scambi++;
			}
			for(int i=j+1;i<n;i++){
				if(m[i][j]!=0D){
					double coeff=m[i][j]/m[j][j];
					for(int k=j;k<n;k++) m[i][k]=m[i][k]-m[j][k]*coeff;
				}
				
				}
			}
				
			for(int i=0;i<m.length;i++)		
			System.out.println(Arrays.toString(m[i]));	
			return scambi;	
			}
		
		
		
			
		
	
	public static void main(String[]args){
		Scanner sc=new Scanner(System.in);
		double [][] m= new double[3][3];
		for(int i=0;i<m.length;i++)
			for(int j=0;j<m[0].length;j++)
				m[i][j]=sc.nextDouble();
		sc.close();
		System.out.println("Il Determinate é" + determinante(m));
		
					
}
		
		
		
		
	}
	
	
	
	

