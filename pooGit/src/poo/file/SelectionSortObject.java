package poo.file;

import java.io.*;
import java.util.*;

public class SelectionSortObject {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome file di Integer (serializzati) da ordinare: ");
		String nomeFile = sc.nextLine();
		while (!(new File(nomeFile)).exists()) {
			System.out.print(nomeFile + " non esiste! Ridare nome file: ");
			nomeFile = sc.nextLine();
		}
		ObjectFile<Integer> f = null, tmp1 = null, tmp2 = null;
		try {
			f = new ObjectFile<Integer>(nomeFile, ObjectFile.Modo.LETTURA);
			tmp1 = new ObjectFile<Integer>("tmp1", ObjectFile.Modo.SCRITTURA);
			tmp2 = new ObjectFile<Integer>("tmp2", ObjectFile.Modo.SCRITTURA);
			while (!f.eof()) {
				selectMin(f, tmp1, tmp2);
				String fName = f.getNomeFile();
				String tmpName = tmp2.getNomeFile();
				f.close(); tmp2.close();
				f = new ObjectFile<Integer>(tmpName, ObjectFile.Modo.LETTURA);
				tmp2 = new ObjectFile<Integer>(fName, ObjectFile.Modo.SCRITTURA);
			}
			// File ordinato in tmp1
			File f1 = new File(f.getNomeFile()); f1.delete(); 
			(new File(tmp1.getNomeFile())).renameTo(f1);
			(new File(tmp2.getNomeFile())).delete();
			f.close(); tmp1.close(); tmp2.close();
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
	static void selectMin(ObjectFile<Integer> f, ObjectFile<Integer> tmp1, ObjectFile<Integer> tmp2) throws IOException {
		int min = f.peek(); f.get(); int x = 0;
		while (!f.eof()) {
			x = f.peek();
			if (x < min) { tmp2.put(min); min = x; }
			else tmp2.put(x);
			f.get();
		}
		tmp1.put(min);
	} // selectMin
} // SelectionSortObject
