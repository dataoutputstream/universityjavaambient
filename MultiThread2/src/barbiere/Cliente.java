package barbiere;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente implements Runnable {
	private final static int TEMPO_PERCORRENZA_MIN = 0;
	private final static int TEMPO_PERCORRENZA_MAX = 30;

	private Random random = new Random();
	private Salone salone;

	public Cliente(Salone s) {
		salone = s;		
	}

	@Override
	public void run() {
		try {
			raggiungiSalone();
			if (salone.entra()) {
				System.out.println("Servito    "+Thread.currentThread().getId()+"\t:)");
			} else {
				System.out.println("Non Servito"+Thread.currentThread().getId()+"\t:(");
			}
		} catch (InterruptedException e) {
		}
	}

	private void raggiungiSalone() throws InterruptedException {
		TimeUnit.SECONDS.sleep((random.nextInt(TEMPO_PERCORRENZA_MAX
				- TEMPO_PERCORRENZA_MIN + 1) + TEMPO_PERCORRENZA_MIN));
	}
}
