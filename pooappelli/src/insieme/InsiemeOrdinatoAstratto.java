package insieme;

public abstract class InsiemeOrdinatoAstratto<T extends Comparable<? super T>> implements InsiemeOrdinato<T> {
	@Override
	public abstract InsiemeOrdinato<T>headSet(T x);
	public abstract InsiemeOrdinato<T>tailSet(T x);
 
	public boolean equals(Object o) {
		if(o==this)return true;
		if(!(o instanceof Insieme))return false;
		Insieme<T>i=(Insieme)o;
		if(i.size()!=this.size())return false;
		for(T e:this) {
			if(!i.contains(e))return false;
		}
		return true;
		
	}
	
	public int hashcode() {
		int h=0;
		final int M=43;
		for(T e:this) {
			h=h*M+e.hashCode();
		}
		return h;
	}
	
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("[");
		for(T e:this) {
			sb.append(e);
			sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}
}
