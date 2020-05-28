package poo.file;

import java.io.*;
import java.util.*;

public class LeggiFileInteri {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome file di interi da leggere: ");
		String nomeFile = sc.nextLine();
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(nomeFile)));
			for (;;)
				System.out.println(dis.readInt());
		} catch (EOFException e) {
			// Do nothing
		} catch (Exception e) {
			System.out.println("Errore di lettura/scrittura!");
		} finally {
			try {
				if (dis != null) dis.close();
			} catch (IOException e) {
				System.out.println("Errore di lettura/scrittura!");
			}
		}
	} // main
} // LeggiFileInteri
