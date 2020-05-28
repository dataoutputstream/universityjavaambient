package poo.file;

import java.io.*;
import java.util.*;

public class SelectionSortRandomAccessFile {
	static enum Direzione {F_TO_TMP2, TMP2_TO_F};
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome file di interi da ordinare: ");
		String nomeFile = sc.nextLine();
		while (!(new File(nomeFile)).exists()) {
			System.out.print(nomeFile + " non esiste! Ridare nome file: ");
			nomeFile = sc.nextLine();
		}
		RandomAccessFile f = null, tmp1 = null, tmp2 = null;
		Direzione d = Direzione.F_TO_TMP2; // Direzione iniziale
		try {
			f = new RandomAccessFile(nomeFile, "r");
			tmp1 = new RandomAccessFile("tmp1", "rw");
			tmp2 = new RandomAccessFile("tmp2", "rw");
			boolean hasNext = selectMin(f, tmp1, tmp2);
			File fileToWrite = null; // Prossimo file in cui scrivere
			while (hasNext) {
				f.close(); tmp2.close();
				switch (d) {
					case F_TO_TMP2:
						d = Direzione.TMP2_TO_F;
						f = new RandomAccessFile("tmp2", "r");
						fileToWrite = new File(nomeFile);
						fileToWrite.delete(); fileToWrite.createNewFile();
						tmp2 = new RandomAccessFile(nomeFile, "rw");
						break;
					default:
						d = Direzione.F_TO_TMP2;
						f = new RandomAccessFile(nomeFile, "r");
						fileToWrite = new File("tmp2");
						fileToWrite.delete(); fileToWrite.createNewFile();
						tmp2 = new RandomAccessFile("tmp2", "rw");
				}
				hasNext = selectMin(f, tmp1, tmp2);
			}
		} catch (Exception e) {
			System.out.println("Errore di lettura/scrittura!");
		} finally {
			try {
				if (f != null) f.close();
				if (tmp1 != null) tmp1.close();
				if (tmp2 != null) tmp2.close();
			} catch (IOException e) {
				System.out.println("Errore di lettura/scrittura!");
			}
		}
		// File ordinato in tmp1
		(new File("tmp2")).delete();
		File f1 = new File(nomeFile); f1.delete();
		(new File("tmp1")).renameTo(f1);
	} // main
	static boolean selectMin(RandomAccessFile f, RandomAccessFile tmp1, RandomAccessFile tmp2) throws IOException {
		int min = 0;
		try {
			min = f.readInt();
		} catch (EOFException e) { return false; }
		int x = 0;
		for (;;) {
			try {
				x = f.readInt();
			} catch (EOFException e) { break; }
			if (x < min) { tmp2.writeInt(min); min = x; }
			else tmp2.writeInt(x);
		}
		tmp1.writeInt(min);
		return true;
	} // selectMin
} // SelectionSortRandomAccessFile
