package piscinannmiaclasse;

import java.util.concurrent.TimeUnit;

public class Istruttore extends Thread {

	public enum Turno{
		MATTINO(4),POMERIGGIO(5);

		private int durata;

		Turno(int durata){
			this.durata = durata;
		}

		public int getDurata() {
			return durata;
		}
	}

	private static final int TEMPO_PAUSA = 1;

	private Piscina piscina;

	public Istruttore(Piscina piscina) {
		this.piscina = piscina;
	}

	@Override
	public void run() {
		try {
			piscina.iniziaTurno(Turno.MATTINO);
			TimeUnit.MINUTES.sleep(TEMPO_PAUSA);
			piscina.iniziaTurno(Turno.POMERIGGIO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
