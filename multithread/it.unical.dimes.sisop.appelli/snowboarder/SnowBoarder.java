package snowboarder;

import java.util.Comparator;
import java.util.Random;

public class SnowBoarder extends Thread implements Comparator<SnowBoarder>,Comparable<SnowBoarder>{
	int id;
	int tempo;
	Random random=new Random();
	public SnowBoarder(int id) {
		this.id=id;
	}
	
	public void run() {
			tempo+=random.nextInt(5)+1;
		
	}

	@Override
	public int compare(SnowBoarder arg0, SnowBoarder arg1) {
		if(arg0.tempo>arg1.tempo)return 1;
		else if(arg0.tempo<arg1.tempo)return -1;
		return 0;
	}

	@Override
	public int compareTo(SnowBoarder o) {
		if(this.tempo>o.tempo)return 1;
		if(this.tempo<o.tempo)return -1;
		return 0;
		
	}
}
