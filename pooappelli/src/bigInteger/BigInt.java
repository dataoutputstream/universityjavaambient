package bigInteger;

import java.util.ArrayList;
import java.util.List;

public class BigInt implements Comparable<BigInt>{
	
	List<Integer>numero;
	
	
	public BigInt(String s) {
		if(!s.matches("[0-9]+"))throw new RuntimeException("Malformazione");
		char[]array=s.toCharArray();
		numero=new ArrayList<>();
		for(int i=0;i<s.length();i++) {
			numero.add(array[i]-'0');	
		}
		}
	
	public BigInt(BigInt b) {
		this.numero=b.numero;
	}
	
	public BigInt add(BigInt b) {
		List<Integer>numerob=b.numero;
		Integer x1;
		Integer x2;
		Integer riporto=0;
		Integer somma;
		int index=numero.size()-1;
		for(int i=numerob.size()-1;i>=0;i--) {
			
			x1=numero.get(index);
			x2=numerob.get(i);
			somma=x1+x2+riporto;
			StringBuilder sb=new StringBuilder();
			sb.append(somma);
			sb.reverse();
			if(somma>9) {
				riporto=sb.charAt(1)-'0';
			}else riporto=0;
			numero.set(index, sb.charAt(0)-'0');
			index--;
		}
		while(riporto!=0) {
			x1=numero.get(index);
			somma=x1+riporto;
			StringBuilder sb=new StringBuilder();
			sb.append(somma);
			sb.reverse();
			if(somma>9) {
				riporto=sb.charAt(1)-'0';
			}else riporto=0;
			numero.set(index, sb.charAt(0)-'0');
			index--;
		}
		return this;	
	}
	
	public BigInt sub(BigInt b) {
		if(this.compareTo(b)<0)throw new RuntimeException();
		List<Integer>numerob=b.numero;
		boolean rip=false;
		Integer x1;
		Integer x2;
		Integer sottrazione;
		Integer prestito=0;
		for(int i=numero.size()-1;i>=0;i--) {
			x1=numero.get(i);
			x2=numerob.get(i);
			if(x1.compareTo(x2)<0) {
				x1+=10;
				rip=true;
			}else {
				rip=false;
			}
			sottrazione=x1-x2-prestito;
			StringBuilder sb=new StringBuilder();
			sb.append(sottrazione);
			if(rip) {
				prestito=1;
			}else prestito=0;
			
			numero.set(i, sb.charAt(0)-'0');
		}
		return this;
		
	}
	
	public BigInt mul(BigInt b) {
		while(!(b.compareTo(new BigInt("0"))==0)) {
			this.add(this);
			b.sub(new BigInt("1"));
		}
		return this;
	}
	
	
	
	@Override
	public int compareTo(BigInt o) {
		if(numero.size()>o.numero.size()) {
			return 1;
		}else if(numero.size()<o.numero.size()) {
			return -1;
		}
		for(int i=0;i<this.numero.size();i++) {
			if(numero.get(i)>o.numero.get(i)) {
				return 1;
			}else if(numero.get(i)<o.numero.get(i)) {
				return -1;
			}
		}
		return 0;
	}
	
	public static void main(String...args) {
		BigInt b=new BigInt("2939");
		BigInt b1=new BigInt("22939");
		System.out.println(b.add(b1).numero);
		
		
	}

}
