package Esercizi;

import java.util.concurrent.Semaphore;

public class AAAB {

	private static Semaphore semA = new Semaphore(1);
	private static Semaphore semB = new Semaphore(0);
	private static int a=4;
	private static Semaphore mutex = new Semaphore(1);

	static class A extends Thread {
		public void run() {
			try {
				while(a>0) {
				semA.acquire();
				mutex.acquire();
				for(int j=0;j<=a;j++)
				System.out.print("A");
				a--;
				semB.release();
				mutex.release();
				
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class B extends Thread {
		public void run() {
			try {
				semB.acquire();
				System.out.print("B");
				System.out.println();
				if(a>=0)
				semA.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		while(true){
			new A().start();
			new B().start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

