package piscinannmiaclasse;

import java.util.Random;

public abstract class Piscina {

	protected static final int NUM_CORSIE = 5;
	protected static final int MAX_PERSONE = 4;

	protected Random random = new Random();
	protected int[] numPermessi = {4,4,4,4,4};

	public abstract void nuota() throws InterruptedException;
	public abstract void iniziaTurno(Istruttore.Turno turno) throws InterruptedException;

	protected int scegliCorsia() {
		int max = 0;
		int index = 0;
		for (int i = 0; i < NUM_CORSIE; i++) {
			if(numPermessi[i] > max){
				max = numPermessi[i];
				index = i;
			}
		}
		if(max == 0)
			return random.nextInt(NUM_CORSIE);
		return index;
	}

	public void test(int numPersone){
		new Istruttore(this).start();
		for (int i = 0; i < numPersone; i++) {
			new Persona(this,i).start();
		}
	}
}
