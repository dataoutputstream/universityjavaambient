package Esercizi;

public class Esercizio26 {
	
	
	public static void main(String[] args) {
		stampa();
		Mythread t1=new Mythread("T1");
		t1.start();
	}

	public static void stampa() {
		Thread t= Thread.currentThread();
		System.out.println(t.getName()+" "+t.getState());
	}
}
 
class Mythread extends Thread{
	public Mythread(String name) {
		super.setName(name);
	}
	public void run() {
		System.out.print(getName()+" "+getState());
	}
}