package cementificio;

public abstract class Cementificio {
	int n;
	int p;
	
	public Cementificio(int n,int p) {
		this.n=n;
		this.p=p;
		
	}
	
	public abstract void entra() throws InterruptedException;
	public abstract void esci()throws InterruptedException;
	public abstract void preleva() throws InterruptedException;
	
	public abstract void iniziaRifornimento() throws InterruptedException;
	public abstract void terminaRifornimento() throws InterruptedException;

}
