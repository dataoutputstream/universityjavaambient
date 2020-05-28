package cementificio;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente extends Thread {
	
	public static final int MIN_S=10;
	public static final int MAX_S=30;
	Random r=new Random();
	
	Cementificio p;
	int s;
	
	public Cliente(Cementificio p) {
		this.p=p;
	}
	
	
	public void run() {
		try {
			
		s=scegli();
		p.entra();
		while(s!=0) {
			p.preleva();
			s--;
			attendi();
		}
		p.esci();
		
		}catch(InterruptedException e) {
			
		}
	}


	private void attendi() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}


	private int scegli() {
		return r.nextInt(MAX_S-MIN_S+1)+MIN_S;
		
	}

}
