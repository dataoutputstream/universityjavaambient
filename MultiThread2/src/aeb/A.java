package aeb;

public class A extends Thread{
	
	 AAAAB a;
	
	public A(AAAAB o) {
		a=o;
	}

	public void run() {
		while(true) {
			while(a.stampe!=0) {		
				try {
					a.A.acquire();
					System.out.print("A");
					a.stampe--;
					if(a.stampe==0)a.B.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
			}
		}
		
		
		
		
	}

}
