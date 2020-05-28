package barMod;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Persona implements Runnable {

	private static final int[] MIN_ATTESA = { 5, 20 };
	private static final int[] MAX_ATTESA = { 10, 40 };

	private Bar bar;
	private Random r = new Random();

	public Persona(Bar bar) {
		this.bar = bar;
	}

	@Override
	public void run() {
		try {
			//attesa iniziale
			attendi(0, 20);
			
			// 0 indica l'operazione di pagamento, 1 indica l'operazione di bere il caffè
			int operazione = bar.scegli();
			
			bar.inizia(operazione);
			attendi(MIN_ATTESA[operazione], MAX_ATTESA[operazione]);
			bar.finisci(operazione);
			
			//cambia operazione
			operazione = 1 - operazione;
			
			//esegue la secoda operazione
			bar.inizia(operazione);
			attendi(MIN_ATTESA[operazione], MAX_ATTESA[operazione]);
			bar.finisci(operazione);
		} catch (InterruptedException e) {
		}

	}

	private void attendi(int min, int max) throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt(max - min + 1) + min);
	}
}
