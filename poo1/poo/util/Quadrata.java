package util;

public class Quadrata {
	public static boolean quadrata(double[][]a){
		for(int i=0;i<a.length;i++){
			if (a.length != a[i].length)
				throw new RuntimeException("LA MATRICE NON E' QUADRATA");	
		}
		return true;
}
}
