package aeb;

import java.util.concurrent.Semaphore;

public class AAAAB {
	Semaphore A=new Semaphore(4);
	Semaphore B=new Semaphore(0);
	int count=4;
	int stampe;
	
	public static void main(String...Args) {
		AAAAB o=new AAAAB();
		A a=new A(o);
		B b=new B(o);
		a.start();
		b.start();
	}

}
