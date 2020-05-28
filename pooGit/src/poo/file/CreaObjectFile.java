package poo.file;

import java.io.*;
import java.util.*;

public class CreaObjectFile {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome file di Oggetti Integer (serializzati) da creare: ");
		String nomeFile = sc.nextLine();
		ObjectFile<Integer> f = null;
		try {
			f = new ObjectFile<Integer>(nomeFile, ObjectFile.Modo.SCRITTURA);
			System.out.println("Inserire numeri interi (solo INVIO per terminare):");
			for (;;) {
				System.out.print("int> ");
				String line = sc.nextLine();
				if (line.equals("")) break;
				f.put(Integer.parseInt(line));
			}
		} catch (Exception e) {
			System.out.println("Errore di lettura/scrittura!");
		} finally {
			try {
				if (f != null) f.close();
			} catch (IOException e) {
				System.out.println("Errore di lettura/scrittura!");
			}
		}
	} // main	
} // CreaObjectFile
