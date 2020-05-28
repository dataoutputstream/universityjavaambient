package Esercizi;

public class Esercizio27 {
	public static void main(String[] args) {
		Mythread t1=new Mythread("T1");
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mythread t2=new Mythread("T2");
		System.out.println(t1.getName()+" "+t1.getState());
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}

