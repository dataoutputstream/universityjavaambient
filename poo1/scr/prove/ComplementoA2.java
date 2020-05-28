package prove;

import java.util.Scanner;

public class ComplementoA2 {
	public static void main(String[]Args) {
		int[]v=new int[4];
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<4;i++) {
			v[i]=sc.nextInt();
		}
		int i=0;
		for(int j=v.length-1;j>=0;j--) {
			if(v[j]==1) {
				i=j;
				break;
			}
		}
		for(i=i-1;i>=0;i--) {
			if(v[i]==1)v[i]=0;
			else {
				v[i]=1;
			}
		}
		for(int k=0;k<v.length;k++)System.out.println(v[k]);
}
}
