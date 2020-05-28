package Albero;

public class TreeChar {
	
	private class Albero<T>{
		T info;
		Albero<T>figlioS,figlioD;
	}
	Albero<Character>radice=new Albero<>();
	
	void build(String clp ) {
		
		char[]stringa=clp.toCharArray();
		
		for(int i=0;i<stringa.length;i++) {
			Albero<Character>nuovo=new Albero<>();
			nuovo.info=stringa[i];
			add(radice,stringa[i]);
		}
		
	}

	private void add(Albero<Character> r, char c) {
		
		
	}

	@Override
	public int hashCode() {
		return 0;
		
	}

	@Override
	public boolean equals(Object obj) {
		return false;
		
	}

}
