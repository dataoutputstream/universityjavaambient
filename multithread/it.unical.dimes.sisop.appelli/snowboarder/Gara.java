package snowboarder;

import java.util.ArrayList;


public abstract class Gara {
	ArrayList<SnowBoarder>classifica;
	int idUltimo;
	int n;
	
	public Gara() {
		classifica=new ArrayList<SnowBoarder>();

	}
	
	protected abstract void partenza(SnowBoarder s)throws InterruptedException;
	protected abstract int arrivo(SnowBoarder s) throws InterruptedException;
	protected abstract boolean StampaeProssimo()throws InterruptedException;
	protected abstract void classificaFinale()throws InterruptedException;
}
