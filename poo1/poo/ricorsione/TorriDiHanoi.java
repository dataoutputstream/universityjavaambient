package ricorsione;

public class TorriDiHanoi {
	public static enum Pin{
		SX,CL,DX;
	}
	private void sposta1disco(Pin sorg, Pin dest){
		System.out.println("Sposta un disco da " +sorg +" a "+dest);
	}
	public void muovi(int n,Pin sorg, Pin aus,Pin dest){
		if(n==1) sposta1disco(sorg,dest);
		else{
			muovi(n-1,sorg,dest,aus);
			sposta1disco(sorg,dest);
			muovi(n-1,aus,sorg,dest);
			
		}
	}
	public static void main(String... Args){
		new TorriDiHanoi().muovi(20, Pin.SX, Pin.CL, Pin.DX);
	}

}
