package poo.recursion;

public class fattoriale {
	
	public static int fattoriale(int n) {
		if(n==1)return n;
		return n*fattoriale(n-1);
	}
	public static void main(String...Args) {
		int n=9;
		System.out.println(fattoriale(3));
		
		
	}

}
