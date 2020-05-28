package poo.statistiche_fpfpq;

public class Parola implements Comparable<Parola> {
	String ortografia;
	Integer fp=1;
	public String getOrto() {
		return ortografia;
	}
	public Integer getFp() {
		return fp;
	}
	public void setFp(int frequenza) {
		this.fp=frequenza;	
	}
	public String toString() {
		return ortografia;
	}
	public boolean equals(Object o) {
		if (!(o instanceof Parola)) return false;
		if (o == this) return true;
		Parola p = (Parola)o;
		return this.ortografia.equals(p.ortografia);
	} // equals
	public int hashCode() {
		return ortografia.hashCode();
	} // hashCode
	@Override
	public int compareTo(Parola arg0) {
		return ortografia.compareTo(arg0.ortografia);
	}

}
