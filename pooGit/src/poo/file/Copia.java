package poo.file;

import java.io.*;
import java.util.*;

public class Copia {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome file sorgente: ");
		String s = sc.nextLine();
		System.out.print("Nome file destinazione: ");
		String d = sc.nextLine();
		FileInputStream fin = null; FileOutputStream fout = null;
		try {
			fin = new FileInputStream(s);
			fout = new FileOutputStream(d);
			int dato = 0;
			for (;;) {
				dato = fin.read();
				if (dato == -1) break;
				fout.write(dato);
			}
		} catch (Exception e) {
			System.out.println("Errore di lettura/scrittura!");
		} finally {
			try {
				if (fin != null) fin.close();
				if (fout != null) fout.close();
			} catch (IOException e) {
				System.out.println("Errore di lettura/scrittura!");
			}
		}
	} // main
} // Copia
