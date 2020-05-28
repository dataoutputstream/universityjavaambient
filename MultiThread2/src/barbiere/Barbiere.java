package barbiere;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Barbiere implements Runnable {
	private final static int TEMPO_TAGLIO_MIN = 2;
	private final static int TEMPO_TAGLIO_MAX = 6;
	
	protected static enum STATI_BARBIERE {ADDORMENTATO, PRONTO, TAGLIO}

	private Random random = new Random();
	private Salone salone;

	public Barbiere(Salone s) {
		salone = s;
	}

	@Override
	public void run() {
		try {
			while (true) {
				salone.serviCliente();
				tagliaCapelli();
				salone.congedaCliente();
			}
		} catch (InterruptedException e) {
		}
	}

	private void tagliaCapelli() throws InterruptedException {
		TimeUnit.SECONDS.sleep(((random.nextInt(TEMPO_TAGLIO_MAX - TEMPO_TAGLIO_MIN + 1) + TEMPO_TAGLIO_MIN)));
	}
}
