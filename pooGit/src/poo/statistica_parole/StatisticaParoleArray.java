package poo.statistica_parole;

import poo.testo.*;
import java.io.*;
import java.util.*;

public class StatisticaParoleArray implements StatisticaParole {
	private Map<String, Integer> p2i = new HashMap<String,Integer>();
	private Map<Integer, String> i2p = new HashMap<Integer,String>();
	private int[] fp;
	private int[][] fpq;
	private String ppre, pcor; // Parola precedente, corrente
	private int size = 0;
	private int numTotParole = 0;
	private final int NUM_MAX_PAROLE = 500;
	public StatisticaParoleArray() {
		fp = new int[NUM_MAX_PAROLE];
		fpq = new int[NUM_MAX_PAROLE][NUM_MAX_PAROLE];
		for (int i = 0; i < NUM_MAX_PAROLE; i++) {
			fp[i] = 0;
			for (int j = 0; j < NUM_MAX_PAROLE; j++)
				fpq[i][j] = 0;
		}
	} // StatisticaParoleArray
	public void sequenza(String nomeFile) throws IOException {
		File f = new File(nomeFile);
		if (!f.exists()) throw new RuntimeException(nomeFile + " non esistente!");
		GestoreTesto gt = new GestoreTesto(f);
		ppre = null;
		GestoreTesto.Simbolo simbolo = gt.prossimoSimbolo();
		while (simbolo != GestoreTesto.Simbolo.EOF) {
			pcor = gt.getString().toUpperCase();
			numTotParole++;
			if (!p2i.containsKey(pcor)) {
				p2i.put(pcor, size);
				i2p.put(size, pcor);
				size++;
			}
			int indc = p2i.get(pcor);
			fp[indc]++;
			if (ppre != null) {
				int indp = p2i.get(ppre);
				fpq[indp][indc]++;
			}
			ppre = pcor;
			simbolo = gt.prossimoSimbolo();
		}
	} // sequenza
	public String[] parolaCheSegue(String target) {
		String[] p = {"", ""};
		target = target.toUpperCase();
		if (!p2i.containsKey(target)) return p;
		int indp = p2i.get(target);
		String ppv = null, pmv = null;
		double max = 0, min = 0;
		for (int j = 0; j < size; j++) {
			if (fpq[indp][j] == 0) continue;
			if (ppv == null || fpq[indp][j] > max) {
				max = fpq[indp][j];
				ppv = i2p.get(j);
			}
			if (pmv == null || fpq[indp][j] < min) {
				min = fpq[indp][j];
				pmv = i2p.get(j);
			}
		}
		p[0] = ppv; p[1] = pmv;
		return p;
	} // parolaCheSegue
	public String toString() {
		StringBuilder sb = new StringBuilder(500);
		double f = 0;
		for (int i = 0; i < size; i++) {
			f = ((double)fp[i]) / numTotParole;
			sb.append('f');	sb.append('(');
			sb.append(i2p.get(i));
			sb.append(')'); sb.append('=');
			sb.append(String.format("%1.3f", f));
			sb.append('\n');
		}
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (fpq[i][j] != 0) {
					f = ((double)fpq[i][j]) / fp[i];
					sb.append('f'); sb.append('(');
					sb.append(i2p.get(i));
					sb.append(',');
					sb.append(i2p.get(j));
					sb.append(')'); sb.append('=');
					sb.append(String.format("%1.3f", f));
					sb.append('\n');
				}
		return sb.toString();
	} // toString
	public static void main( String[] args ) throws IOException{
		StatisticaParole sp = new StatisticaParoleArray();
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserire percorso file di testo di cui si desidera calcolare la statistica delle parole:");
		sp.sequenza(sc.nextLine());
		System.out.println(sp);
		System.out.println("Inserire parola di cui si vuole conoscere la statistica, solo INVIO per uscire.");
		for (;;) {
			System.out.print("> ");
			String target = sc.nextLine();
			if (target.equals("")) break;
			String[] pv = sp.parolaCheSegue(target);
			System.out.println("ppv(" + target + ") = " + pv[0]);
			System.out.println("pmv(" + target + ") = " + pv[1]);
		}
	} // main
} // StatisticaParoleArray
