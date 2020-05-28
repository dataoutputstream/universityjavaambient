package SnowBoard;

import java.util.ArrayList;

public abstract class Gara {
	
	ArrayList<SnowBoarder> catu=new ArrayList<SnowBoarder>();
	SnowBoarder ultimo;
	int N;
	
	public Gara(int N) {
		this.N=N;
	}
	
	abstract void partenza(SnowBoarder s) throws InterruptedException;
	abstract int arrivo(SnowBoarder s) throws InterruptedException;
	abstract boolean stampaEprossimo() throws InterruptedException;
	abstract void classificaFinale() throws InterruptedException;

}
