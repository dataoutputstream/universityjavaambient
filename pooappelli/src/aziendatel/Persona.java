package aziendatel;

public class Persona implements Comparable<Persona>{
	private String cognome,nome;
	
	public Persona(String cognome,String nome) {
		this.cognome=cognome;
		this.nome=nome;
		
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public boolean equals(Object o ) {
		if(o==this)return true;
		if(!(o instanceof Persona))return false;
		Persona p=(Persona)o;
		if(cognome.equals(p.cognome) && nome.equals(p.nome))return true;
		return false;
	}
	public String toString() {
		return cognome+" "+nome;
	}

	@Override
	public int compareTo(Persona p) {
		if(!cognome.equals(p.cognome))return cognome.compareTo(p.cognome);
		if(!cognome.equals(p.cognome))return nome.compareTo(p.nome);
		return 0;
	}
	public int hashCode() {
		return cognome.hashCode()+nome.hashCode();
	}

}
