package gara_sci;

import java.util.Random;

public class Sciatore extends Thread implements Comparable<Sciatore>{
	
	Gara g;
	int nMaglia;
	private int tempo;
	private int posizione;
	Random r=new Random();
	
	public Sciatore(int numMaglia,Gara g) {
		this.nMaglia=numMaglia;
		this.g=g;
	}
	
	public void run() {
		try {
			g.partenza(this);
			tempo=tempo();
			posizione=g.arrivo(this);
			System.out.println("Lo sciatore "+ nMaglia+ " momentaneamente detiene la posizione: "+posizione);
			
		}catch(InterruptedException e) {
			
		}
	}

	private int tempo() {
		
		return r.nextInt(200-100+1)+100;
	}

	@Override
	public int compareTo(Sciatore s) {
		if(this.tempo>s.tempo)return 1;
		if(this.tempo<s.tempo)return -1;
		return 0;
	}
	public boolean equals(Object o) {
		if(o==this)return true;
		if(!(o instanceof Sciatore))return false;
		Sciatore s=(Sciatore)o;
		if(nMaglia==s.nMaglia)return true;
		return false;
	}
	public int getTempo() {
		return tempo;
	}
	public void setPosizione(int posizione) {
		this.posizione=posizione;
	}
	public int getPosizione() {
		return posizione;
	}

}
