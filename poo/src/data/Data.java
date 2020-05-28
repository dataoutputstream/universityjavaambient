package data;
import java.util.*;

public class Data implements Comparable{
	
	private final int G, M, A;
	public enum Tipo{GIORNO, MESE, ANNO}
	
	public Data(){
		GregorianCalendar gc = new GregorianCalendar();
		this.G = gc.get(GregorianCalendar.DAY_OF_MONTH);
		this.M = gc.get(GregorianCalendar.MONTH)+1;
		this.A = gc.get(GregorianCalendar.YEAR);
	}
	public Data(int g, int m, int a){
		if(g<1 || g>durata(m,a) || m<1 || m>12 || a<0)
			throw new IllegalArgumentException();
		this.G = g;
		this.M = m;
		this.A = a;
	}
	public Data( Data d){
		this.G = d.G;
		this.M = d.M;
		this.A = d.A;
	}
	public static int durata( int m, int a){
		if(m<1 || m>12 || a<0)
			throw new IllegalArgumentException();
		int d = 0;
		switch(m){
			case 1: case 3: case 5:case 7: case 8: case 10: case 12: d=31; break;
			case 2: d=bisestile(a)? 29:28; break;
			default: d=30;
		}
		return d;
	}
	public static boolean bisestile(int a){
		if(a<0) throw new IllegalArgumentException();
		if(a%4 != 0) return false;
		if(a%100 != 0 && a%400 != 0) return false;
		return true;
	}
	public int get(Tipo cosa){
		switch(cosa){
			case GIORNO: return G;
			case MESE: return M;
			default: return A;
		}
	}
	public Data next(){
		int gn, mn, an;
		if(G==durata(M,A)){
			gn=1;
			if(M==12){
				mn=1;
				an= A+1;
			}
			else{mn=M+1; an=A;}
		}
		else{gn=G+1;mn=M;an=A;}
		return new Data(gn,mn,an);
	}
	public String toString(){
		return ""+G+"/"+M+"/"+A;
	}
	public boolean equals(Object o){
		if(!(o instanceof Data)) return false;
		if(o==this) return true;
		Data d = (Data) o;
		if(this.G==d.G && this.M==d.M && this.A==d.A)
			return true;
		return false;
	}
	public int compareTo(Object o){
		Data d = (Data) o;
		if(this.A<d.A || this.A==d.A && this.M<d.M || this.A==d.A && this.M==d.M && this.G<d.G)
			return -1;
		if(this.equals(d))
			return 0;
		return 1;
	}
	public int distanza(Data d){
		Data d1 = this;
		Data d2 = d;
		if(d1.compareTo(d2) > 0){
			d1=d;
			d2=this;
		}
		int cont = 0;
		while(d1.compareTo(d2) < 0){
			cont++;
			d1=d1.next();
		}
		return cont;
	}
	public static void main(String[] args){
		Data oggi = new Data();
		System.out.println("Oggi è il "+ oggi);
		Data domani = oggi.next();
		System.out.println("Domani sarà il "+ domani);
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserisci la data del tuo futuro compleanno!");
		System.out.print("Giorno: ");
		int g = sc.nextInt(); sc.nextLine();
		System.out.print("Mese: ");
		int m = sc.nextInt(); sc.nextLine();
		System.out.print("Anno: ");
		int a = sc.nextInt(); sc.nextLine();
		sc.close();
		Data comple = new Data(g,m,a);
		System.out.println("Mancano "+ oggi.distanza(comple)+ " giorni al tuo compleanno!");
	}
}