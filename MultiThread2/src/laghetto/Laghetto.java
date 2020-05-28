package laghetto;

public abstract class Laghetto {
	
	 int maxPesci;
	 int minPesci;
	 
	 int pesci;
	 
	 int[]tipo;
	 boolean rilasciati=false;
	 boolean rilasciatiA=false;
	 
	 public Laghetto(int minPesci,int maxPesci) {
		 this.maxPesci=maxPesci;
		 this.minPesci=minPesci;
		 tipo=new int[2];
		 tipo[0]=0;
		 tipo[1]=1;
		 pesci=maxPesci;
	 }
	 
	 public abstract void inizia(int t) throws InterruptedException;
	 public abstract void finisci(int t)throws InterruptedException;

}
