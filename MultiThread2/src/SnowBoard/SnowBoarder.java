package SnowBoard;

import java.util.Random;

public class SnowBoarder extends Thread implements Comparable<SnowBoarder>{
	int id;
	Gara g;
	int posizione;
	int tempo;
	Random r=new Random();
	final int Min_Tempo=1;
	final int Max_Tempo=5;
	
	public SnowBoarder(int id,Gara g) {
		this.g=g;
		this.id=id;
	}
	
	@Override
	public void run() {
		try {
			g.partenza(this);
			tempo();
			posizione=g.arrivo(this);
		}catch(InterruptedException e) {
			
		}
	}
	
	private void tempo() {
		tempo=r.nextInt(Max_Tempo-Min_Tempo+1)+Min_Tempo;
	}

	public int getMaglia() {
		return id;
		
	}
	
	public int getPosizione() {
		return posizione;
		
	}
	@Override
	public boolean equals(Object o) {
		if(this==o)return true;
		if(!(o instanceof SnowBoarder))return false;
		SnowBoarder s=(SnowBoarder)o;
		return this.id==s.id;
	}

	@Override
	public int compareTo(SnowBoarder o) {
		if(this.tempo>o.tempo)return 1;
		if(this.tempo<o.tempo)return -1;
		return 0;
		
	}

}
