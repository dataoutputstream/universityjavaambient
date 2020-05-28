package poo.file;

import java.util.*;
import java.io.*;

public class SelectionSortDataIOStream {
	static enum Direzione {F_TO_TMP2, TMP2_TO_F};
	public static void main(String[]args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome file di interi da ordinare: ");
		String nomeFile = sc.nextLine();
		while (!(new File(nomeFile)).exists()) {
			System.out.print(nomeFile + " non esiste! Ridare nome file: ");
			nomeFile = sc.nextLine();
		}
		DataInputStream f = null; DataOutputStream tmp1 = null, tmp2 = null;
		Direzione d = Direzione.F_TO_TMP2; // Direzione iniziale
		try {
			f = new DataInputStream(new FileInputStream(nomeFile));
			tmp1 = new DataOutputStream(new FileOutputStream("tmp1"));
			tmp2 = new DataOutputStream(new FileOutputStream("tmp2"));
			boolean hasNext = selectMin(f, tmp1, tmp2);
			while (hasNext) {
				f.close(); tmp2.close();
				switch (d) {
					case F_TO_TMP2:
						d = Direzione.TMP2_TO_F;
						f = new DataInputStream(new FileInputStream("tmp2"));
						tmp2 = new DataOutputStream(new FileOutputStream(nomeFile));
						break;
					default:
						d = Direzione.F_TO_TMP2;
						f = new DataInputStream(new FileInputStream(nomeFile));
						tmp2 = new DataOutputStream(new FileOutputStream("tmp2"));
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
	static boolean selectMin(DataInputStream f, DataOutputStream tmp1, DataOutputStream tmp2) throws IOException {
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
} // SelectionSortDataIOStream
