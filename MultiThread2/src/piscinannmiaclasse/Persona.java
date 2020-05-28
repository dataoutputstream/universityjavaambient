package piscinannmiaclasse;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Persona extends Thread {

	public static final int MIN_NUOTO = 30;
	public static final int MAX_NUOTO = 60;
	private static final int TEMPO_DOCCIA = 20;

	private int id;
	private Piscina piscina;

	public Persona(Piscina piscina, int id) {
		this.piscina = piscina;
		this.id = id;
	}

	public long getId(){
		return id;
	}

	@Override
	public void run(){
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(120));
			piscina.nuota();
		} catch (InterruptedException e) {
			System.out.printf("P %d ha interrotto nuotata%n",getId());
		}
		try{
			TimeUnit.SECONDS.sleep(TEMPO_DOCCIA);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
