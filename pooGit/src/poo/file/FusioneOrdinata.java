package poo.file;

import java.io.*;
import java.util.*;

public class FusioneOrdinata {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome primo file di interi ordinato: ");
		String f1 = sc.nextLine();
		System.out.print("Nome secondo file di interi ordinato: ");
		String f2 = sc.nextLine();
		System.out.print("Nome file di destinazione: ");
		String f3 = sc.nextLine();
		RandomAccessFile raf1 = null; RandomAccessFile raf2 = null;
		LinkedHashSet<Integer> s = new LinkedHashSet<Integer>();
		int x1 = 0, x2 = 0;
		try {
			raf1 = new RandomAccessFile(f1, "r");
			raf2 = new RandomAccessFile(f2, "r");
			for (;;) {
				x1 = raf1.readInt(); x2 = raf2.readInt();
				if (x1 < x2) {
					s.add(x1); raf2.seek(raf2.getFilePointer() - 4);
				} else {
					s.add(x2); raf1.seek(raf1.getFilePointer() - 4);
				}
			}
		} catch (EOFException e) {
			try {
				while (raf1.getFilePointer() < raf1.length()) s.add(raf1.readInt());
				while (raf2.getFilePointer() < raf2.length()) s.add(raf2.readInt());
				raf1.close(); raf2.close();
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f3)));
				for (int x : s) dos.writeInt(x);
				dos.close();
			} catch (Exception ex) {
				System.out.println("Errore di lettura/scrittura!");
			}
		} catch (Exception e) {
			System.out.println("Errore di lettura!");
		}
	} // main
} // FusioneOrdinata
