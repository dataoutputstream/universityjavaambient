package poo.file;

import java.io.*;
import java.util.*;

public class CreaFileInteri {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome file di interi da creare: ");
		String nomeFile = sc.nextLine();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nomeFile)));
			System.out.println("Inserire numeri interi (solo INVIO per terminare):");
			for (;;) {
				System.out.print("int> ");
				String line = sc.nextLine();
				if (line.equals("")) break;
				dos.writeInt(Integer.parseInt(line));
			}
		} catch (Exception e) {
			System.out.println("Errore di lettura/scrittura!");
		} finally {
			try {
				if (dos != null) dos.close();
			} catch (IOException e) {
				System.out.println("Errore di lettura/scrittura!");
			}
		}
	} // main
} // CreaFileInteri
