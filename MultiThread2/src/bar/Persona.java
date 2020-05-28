package bar;

public class Persona extends Thread{
	
	Bar b;
	int operazione;
	
	public Persona(Bar b) {
		this.b=b;
	}

	public void run() {
		try {
			
			operazione=b.scegliInizia();
			b.inizia(operazione);
			b.finisci(operazione);
			
			operazione=1-operazione;
			b.inizia(operazione);
			b.finisci(operazione);
			
		}catch(InterruptedException e) {
			
		}
	}

}
