package poo.appello250612;

public class Paziente implements Comparable<Paziente> {
	public static int contatore;
	public enum Priority {LOW, MEDIUM, HIGH}
	private int id, priority;
	public Paziente(Priority p) {
		id = contatore++;
		if (p == Priority.LOW) priority = 2;
		else if (p == Priority.MEDIUM) priority = 1;
		else priority = 0;
	} // Costruttore
	public int getId() { return id; }
	public Priority getPriority() {
		if (priority == 2) return Priority.LOW;
		else if (priority == 1) return Priority.MEDIUM;
		else return Priority.HIGH;
	} // getPriority
	public int compareTo(Paziente p) {
		if (priority != p.priority) return priority - p.priority;
		return id - p.id;
	} // compareTo
	public String toString() {
		return "Paziente #" + id + " - Priority: " + getPriority();
	} // toString
	public int hashCode() { return id; }
	public boolean equals(Object o) {
		if (!(o instanceof Paziente)) return false;
		if (o == this) return true;
		return id == ((Paziente)o).id;
	} // equals
} // Paziente
