package snowboarder;



public class AddettoDiGara extends Thread{
	Gara g;
	int n;
	boolean flag=true;
	int MIN_TEMPO=1;
	int MAX_TEMPO=5;
	
	
	public AddettoDiGara(int n,Gara g) {
		this.n=n;
		this.g=g;
		}
	
	public void run() {
		for(int i=0;i<n;i++) {
			SnowBoarder s=new SnowBoarder(i);
			try {
				g.partenza(s);
				int pos=g.arrivo(s);
				flag=g.StampaeProssimo();
				System.out.println("Lo SnowBoarder "+i+" ha tagliato il traguardo, tempo: "+ s.tempo);
				System.out.println("Posizione: "+pos);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			
		}
		try {
			while(!flag) {
			g.classificaFinale();
			flag=true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

