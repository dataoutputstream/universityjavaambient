package poo.file;

import java.io.*;
import java.util.*;

public class MergeFile {
	public static void main(String[]args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Fusione ordinata di f1 e f2 su f3 (su file di oggetti Integer)");
		System.out.print("Nome primo file di interi ordinato: ");
		String nome1 = sc.nextLine();
		System.out.print("Nome secondo file di interi ordinato: ");
		String nome2 = sc.nextLine();
		System.out.print("Nome file di destinazione: ");
		String nome3 = sc.nextLine();
		ObjectFile<Integer> f1 = null, f2 = null, f3 = null;
		try {
			f1 = new ObjectFile<Integer>(nome1, ObjectFile.Modo.LETTURA);
			f2 = new ObjectFile<Integer>(nome2, ObjectFile.Modo.LETTURA);
			f3 = new ObjectFile<Integer>(nome3, ObjectFile.Modo.SCRITTURA);
			int x1 = 0, x2 = 0, prev = 0; boolean first = true;
			while (!f1.eof() && !f2.eof()) {
				x1 = f1.peek(); x2 = f2.peek();
				if (x1 < x2) {
					f1.get();
					if (first || x1 != prev) { f3.put(x1); prev = x1; }
				} else {
					f2.get();
					if (first || x2 != prev) { f3.put(x2); prev = x2; }
				}
				first = false;
			}
			while (!f1.eof()) {
				x1 = f1.peek(); f1.get();
				if (first || x1 != prev) { f3.put(x1); prev = x1; }
			}
			while (!f2.eof()) {
				x2 = f2.peek(); f2.get();
				if (first || x2 != prev) { f3.put(x2); prev = x2; }
			}
		} catch (Exception e) {
			System.out.println("Errore di lettura/scrittura!");
		} finally {
			try {
				if (f1 != null) f1.close();
				if (f2 != null) f2.close();
				if (f3 != null) f3.close();
			} catch (IOException e) {
				System.out.println("Errore di lettura/scrittura!");
			}
		}
	} // main
} // MergeFile
