package aeb;

public class B extends Thread{
	
	 AAAAB a;
	 
	public B(AAAAB o) {
		a=o;
	}

	public void run() {
			try {
				while(true) {
				a.B.acquire();	
				System.out.print("B");
				System.out.println();
				a.count--;
				a.stampe=a.count;
				a.A.release(a.count);
				
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}

