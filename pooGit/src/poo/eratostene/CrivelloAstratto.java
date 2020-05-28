package poo.eratostene;

public abstract class CrivelloAstratto implements Crivello {
	public int size() {
		int c = 0;
		for (int x: this) c++;
		return c;
	} // size
	public String toString() {
		StringBuilder sb = new StringBuilder(1000);
		int c = 0;
		for (int x: this) {
			sb.append(String.format("%8d", x));
			if (++c % 8 == 0) sb.append('\n');
		}
		return sb.toString();
	} // toString
} // CrivelloAstratto
