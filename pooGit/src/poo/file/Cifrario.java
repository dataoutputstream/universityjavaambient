package poo.file;

import java.io.*;
import java.util.*;

public class Cifrario {
	public static void main(String[]args) {
		if (args.length < 1) {
			System.out.println("Specificare una chiave intera come parametro!");
			System.exit(-1);
		}
		FileInputStream fin = null; FileOutputStream fout = null;
		try {
			int chiave = Integer.parseInt(args[0]);
			System.out.println("Copia e cifratura di un file");
			Scanner sc = new Scanner(System.in);
			System.out.print("Nome file sorgente: ");
			String s = sc.nextLine();
			System.out.print("Nome file destinazione: ");
			String d = sc.nextLine();
			fin = new FileInputStream(s);
			fout = new FileOutputStream(d);
			int dato = 0;
			for (;;) {
				dato = fin.read();
				if (dato == -1) break;
				fout.write((byte)(dato + chiave));
			}
		} catch (NumberFormatException e) {
			System.out.println("La chiave dev'essere un numero intero!");
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
