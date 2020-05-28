package puzzle;

import java.util.Arrays;

public enum Direzione {
	
	Nord, NordEst, Est, SudEst, Sud,
	SudOvest, Ovest, NordOvest;
	
	public Direzione operazione(Direzione d) {
		
		switch(d) {
	
		case NordEst :
			System.out.println("E' situata a Nord-Est");
			return NordEst;
			
		case Ovest :
			System.out.println("E' occidentale");
		
		}
		return null;
		
	}
	
	
	
	
	public static void main(String...args) {
		Direzione d;
		System.out.println(Arrays.toString(Direzione.values()));
		d=Direzione.NordEst;
		d.operazione(d);
		
		
	}

}
