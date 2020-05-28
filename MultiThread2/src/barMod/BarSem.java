package barMod;

import java.util.concurrent.Semaphore;

public class BarSem extends Bar {
	private Semaphore mutex = new Semaphore(1); // per modificare le variabili interne in mutua esclusione
	private Semaphore[] fila = new Semaphore[NUM_FILE];

	protected int[] numPersoneInFila = new int[NUM_FILE];

	public BarSem() {
		super();
		for (int i = 0; i < NUM_FILE; i++) {
			fila[i] = new Semaphore(MAX_PERSONE_FILA[i], true); // assegno un numero di permessi uguale al numero di posti liberi
			numPersoneInFila[i] = 0;//inizialmente nessuna persona è in fila
		}
		System.out.println(this);
	}

	@Override
	public int scegli() throws InterruptedException {
		int i = -1; // 0
		mutex.acquire();

		if (numPostiLiberi[CASSA] > 0)
			i = CASSA;
		else if (numPostiLiberi[BANCONE] > 0) {
			i = BANCONE;
		} else if (numPersoneInFila[CASSA] <= numPersoneInFila[BANCONE]) {
			i = CASSA;
		} else {
			i = BANCONE;
		}
		System.out.format("Persona[%s] vuole andare %s%n", Thread
				.currentThread().getName(), (i == CASSA ? "in CASSA"
				: "al BANCONE"));
		mutex.release();
		return i;
	}

	@Override
	public void inizia(int i) throws InterruptedException {
		mutex.acquire();
		numPersoneInFila[i]++;
		mutex.release();

		fila[i].acquire(); //acquisisco un solo permesso relativo alla i-sima fila

		mutex.acquire();
		numPersoneInFila[i]--;
		numPostiLiberi[i]--; //decremento il numero di posti liberi della i-sima fila
		System.out.format("Persona[%s] si trova in %s%n", Thread
				.currentThread().getName(), (i == CASSA ? "in CASSA"
				: "al BANCONE"));
		System.out.println(this);
		mutex.release();
	}

	@Override
	public void finisci(int i) throws InterruptedException {
		mutex.acquire();
		numPostiLiberi[i]++; //incremento il numero di posti liberi della i-sima fila

		fila[i].release(); //rilascio un permesso relativo alla i-sima fila

		System.out.format("Persona[%s] è uscita %s%n", Thread.currentThread()
				.getName(), (i == CASSA ? "dalla CASSA" : "dal BANCONE"));
		System.out.println(this);
		mutex.release();
	}

	public String toString() {
		String cassa = numPostiLiberi[CASSA] == 0 ? "X" : " ";
		String bancone = "";
		for (int i = 0; i < MAX_PERSONE_FILA[BANCONE]; i++) {
			if (i < MAX_PERSONE_FILA[BANCONE] - numPostiLiberi[BANCONE])
				bancone += "X";
			else
				bancone += " ";
		}

		String ret = String.format("CASSA[%s](%d) BANCONE[%s](%d)", cassa,
				numPersoneInFila[CASSA], bancone, numPersoneInFila[BANCONE]);
		return ret;
	}

	public static void main(String[] args) throws InterruptedException {
		int numPersone = 20;
		new BarSem().test(numPersone);
	}
}
