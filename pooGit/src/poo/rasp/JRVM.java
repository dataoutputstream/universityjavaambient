package poo.rasp;

import java.io.*;
import java.util.*;

public class JRVM {
	private int[] mem;
	private int acc, ip, opcode, modo, operando;
	public void loader(ObjectModule om) {
		mem = new int[om.size()];
		int i = 0;
		for (int cod: om) mem[i++] = cod;
	} // loader
	public void interpreter() throws IOException {
		Scanner ni = new Scanner(System.in);
		ip = 0;
		while (true) {
			String opc = null;
			try {
				opcode = mem[ip];
				opc = Assembler.opCodeString.get(opcode);
				if (opc == null) throw new RuntimeException();
			} catch (Exception e) {
				System.out.println("Codice operativo illegale all'indirizzo " + ip);
				System.exit(-1);
			}
			if (opc.equals("HALT")) break;
			modo = mem[ip + 1];
			operando = mem[ip + 2];
			ip = ip + 3;
			if (opc.equals("LOAD")) {
				if (modo == 0) acc = mem[operando];
				else if (modo == 1) acc = operando;
				else acc = mem[mem[operando]];
			} else if (opc.equals("STORE")) {
				if (modo == 0) mem[operando] = acc;
				else if (modo == 2) mem[mem[operando]] = acc;
			} else if (opc.equals("READ")) {
				int dato = 0;
				do {
					System.out.print("int> ");
					try {
						dato = ni.nextInt();
					} catch (Exception e) {
						System.out.println("?");
						if (e instanceof NoSuchElementException) ni = new Scanner(System.in);
						else ni.nextLine();
						continue;
					}
					break;
				} while (true);
				if (modo == 0) mem[operando] = dato;
				else if (modo == 2) mem[mem[operando]] = dato;
			} else if (opc.equals("WRITE")) {
				if (modo == 0) System.out.println(mem[operando]);
				else if (modo == 1) System.out.println(operando);
				else System.out.println(mem[mem[operando]]);
			} else if (opc.equals("ADD")) {
				if (modo == 0) acc += mem[operando];
				else if (modo == 1) acc += operando;
				else acc += mem[mem[operando]];
			} else if (opc.equals("SUB")) {
				if (modo == 0) acc -= mem[operando];
				else if (modo == 1) acc -= operando;
				else acc -= mem[mem[operando]];
			} else if (opc.equals("MUL")) {
				if (modo == 0) acc *= mem[operando];
				else if (modo == 1) acc *= operando;
				else acc *= mem[mem[operando]];
			} else if (opc.equals("DIV")) {
				if (modo == 0) acc /= mem[operando];
				else if (modo == 1) acc /= operando;
				else acc /= mem[mem[operando]];
			} else if (opc.equals("JZ")) {
				if (acc == 0) {
					if (modo == 0) ip = operando;
					else if (modo == 2) ip = mem[operando];
				}
			} else if (opc.equals("JNZ")) {
				if (acc != 0) {
					if (modo == 0) ip = operando;
					else if (modo == 2) ip = mem[operando];
				}
			} else if (opc.equals("JLZ")) {
				if (acc < 0) {
					if (modo == 0) ip = operando;
					else if (modo == 2) ip = mem[operando];
				}
			} else if (opc.equals("JLEZ")) {
				if (acc <= 0) {
					if (modo == 0) ip = operando;
					else if (modo == 2) ip = mem[operando];
				}
			} else if (opc.equals("JGZ")) {
				if (acc > 0) {
					if (modo == 0) ip = operando;
					else if (modo == 2) ip = mem[operando];
				}
			} else if (opc.equals("JGEZ")) {
				if (acc >= 0) {
					if (modo == 0) ip = operando;
					else if (modo == 2) ip = mem[operando];
				}
			} else if (opc.equals("JUMP")) {
				if (modo == 0) ip = operando;
				else if (modo == 2) ip = mem[operando];
			} else {
				System.out.println("Internal error");
				System.exit(-1);
			}
		}
	} // interpreter
	public static void main(String[]args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String nomeFileSorgente = null, nomeFileListing = null;
		File f = null; boolean ok;
		do {
			ok = true;
			System.out.print("Nome file sorgente: ");
			nomeFileSorgente = sc.nextLine();
			f = new File(nomeFileSorgente);
			if (!f.exists()) {
				System.out.println("File non esistente. Ridarlo");
				ok = false;
			}
			if (ok) {
				int i = nomeFileSorgente.lastIndexOf('.');
				if (i == 1) {
					System.out.println("Il file deve essere .rasp. Ridarlo");
					ok = false;
				}
				if (ok) {
					String estensione = nomeFileSorgente.substring(i + 1);
					if (!estensione.equalsIgnoreCase("rasp")) {
						System.out.println("Il file deve essere .rasp. Ridarlo");
						ok = false;
					}
				}
			}
		} while (!ok);
		int i = nomeFileSorgente.lastIndexOf('.');
		nomeFileListing = nomeFileSorgente.substring(0, i) + ".listing";
		Assembler as = new Assembler(nomeFileSorgente, nomeFileListing);
		as.compile();
		ObjectModule om = as.getObjectModule();
		JRVM jrvm = new JRVM();
		jrvm.loader(om);
		jrvm.interpreter();
	} // main
} // JRVM
