package sistema;

public abstract class Sistema {
	private int n;
	private int getN(){ return n; }
	public abstract double[] risolvi();
	
	public Sistema(double[][]a,double[]y){
		for(int i=0;i<a.length;i++){
			if (a.length != a[i].length)
				throw new RuntimeException("Sistema Incompatibile");	
		}
		if (a.length!=y.length){
			throw new RuntimeException("S.E.");
		}
		this.n = a.length;
	}
	

}
