package ricorsione;

import java.util.Scanner;

public class potenza {
	static int potenza(int a,int n){
		//pre n>0
		if(n==0) return 1;
		return a*potenza(a,n-1);
	}
	
public static void main(String[] args){
	Scanner sc=new Scanner(System.in);
	System.out.println("dammi la base: ");
	int base=sc.nextInt();
	System.out.println("dammi l'esponente ");
	int esponente=sc.nextInt();
	System.out.println(potenza(base,esponente));
	
}

}
