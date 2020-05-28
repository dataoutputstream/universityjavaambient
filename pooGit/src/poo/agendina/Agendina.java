package poo.agendina;

import java.io.*;

public interface Agendina extends Iterable<Nominativo> {
	public int size();
	public void svuota();
	public void aggiungi(Nominativo n);
	public void rimuovi(Nominativo n);
	public Nominativo cerca(Nominativo n);
	public Nominativo cerca(String prefisso, String telefono);
	public void salva(String nomeFile) throws IOException;
	public void ripristina(String nomeFile) throws IOException;
} // Agendina
