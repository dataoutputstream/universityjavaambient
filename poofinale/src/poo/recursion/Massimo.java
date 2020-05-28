package poo.recursion;

public class Massimo {
	
	public static int massimo(int[]v,int inf,int sup) {
		
		if(inf==sup)return v[inf];
		int med=(sup+inf)/2;
		int m1=massimo(v,inf,med);
		int m2=massimo(v,med+1,sup);
		
		return (m1>m2) ? m1:m2;
		
	}
	
	public static void main(String...args) {
		final int[] v= {1,4,5,2,45,2};
		System.out.println(massimo(v,0,v.length-1));
	}

}
