package poo.file;

import java.io.*;
import java.util.*;

public class LeggiObjectFile {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome file di Integer (serializzati) da leggere: ");
		String nomeFile = sc.nextLine();
		ObjectFile<Integer> f = null;
		try {
			f = new ObjectFile<Integer>(nomeFile, ObjectFile.Modo.LETTURA);
			while (!f.eof()) {
				System.out.println(f.peek()); f.get();
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
} // LeggiObjectFile
