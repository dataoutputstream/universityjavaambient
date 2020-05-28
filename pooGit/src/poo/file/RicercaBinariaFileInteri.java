package poo.file;

import java.io.*;
import java.util.*;

public class RicercaBinariaFileInteri {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome file di interi ordinato in cui cercare: ");
		String nomeFile = sc.nextLine();
		System.out.print("Intero da cercare: ");
		int x = sc.nextInt();
		try {
			if (esiste(nomeFile, x)) System.out.println("Numero intero presente!");
			else System.out.println("Numero intero non presente!");
		} catch (Exception e) {
			System.out.println("Errore in lettura!");
		}
	} // main
	public static boolean esiste(String nome, int x) throws IOException {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(nome, "r");
			long inf = 0; long sup = raf.length() / 4 - 1; long med; int y;
			while (inf <= sup) {
				med = (inf + sup) / 2;
				raf.seek(med * 4);
				y = raf.readInt();
				if (y == x) return true;
				if (y < x) inf = med + 1;
				else sup = med - 1;
			}
		} finally {
			if (raf != null) raf.close();
		}
		return false;
	} // esiste
} // RicercaBinariaFileInteri
