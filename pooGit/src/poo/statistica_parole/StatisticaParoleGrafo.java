package poo.statistica_parole;

import java.io.*;
import java.util.*;
import poo.grafo.*;
import poo.testo.*;

public class StatisticaParoleGrafo implements StatisticaParole {
	private GrafoOrientatoPesato<Parola> gp = new GrafoOrientatoPesatoImpl<Parola>();
	private Parola ppre, pcor; // Parola precedente, corrente
	private int numTotParole = 0;
	public void sequenza(String nomeFile) throws IOException {
		File f = new File(nomeFile);
		if (!f.exists())
			throw new RuntimeException(nomeFile + " non esistente!");
		GestoreTesto gt = new GestoreTesto(f);
		ppre = null;
		GestoreTesto.Simbolo simbolo = gt.prossimoSimbolo();
		while (simbolo!=GestoreTesto.Simbolo.EOF) {
			pcor = new Parola(gt.getString().toUpperCase());
			numTotParole++;
			Iterator<Parola> it = gp.iterator();
			boolean flag = false;
			while (it.hasNext()) {
				Parola p = it.next();
				if (p.equals(pcor)) {
					p.setFrequenza(p.getFrequenza() + 1);
					flag = true; break;
				}
			} // while
			if (!flag) gp.insNodo(pcor);
			if (ppre != null) {
				Iterator<ArcoPesato<Parola>> ia = gp.adiacenti(ppre);
				ArcoPesato<Parola> tmp = new ArcoPesato<Parola>(ppre, pcor);
				tmp.setPeso(new Peso(1));
				flag = false;
				while (ia.hasNext()) {
					ArcoPesato<Parola> ap = ia.next();
					if (ap.equals(tmp)) {
						Peso peso = ap.getPeso();
						peso.setVal(peso.val() + 1);
						flag = true; break;
					}
				}
				if (!flag) gp.insArco(tmp);
			}
			ppre = pcor;
			simbolo = gt.prossimoSimbolo();
		}
		System.out.println("Grafo Orientato Pesato Costruito:");
		System.out.println(gp);
		System.out.println();
	} // sequenza
	public String[] parolaCheSegue( String pc ){
		Parola target = new Parola(pc.toUpperCase());
		String[] p = {"", ""};
		if (!gp.esisteNodo(target)) return p;
		String ppv = null, pmv = null;
		double max = 0, min = 0;
		Iterator<ArcoPesato<Parola>> ip = gp.adiacenti(target);
		while (ip.hasNext()) {
			ArcoPesato<Parola> ap = ip.next();
			if (ppv == null || ap.getPeso().val() > max) {
				max = ap.getPeso().val(); ppv = ap.getDestinazione().getParola();
			}
			if (pmv == null || ap.getPeso().val() < min) {
				min = ap.getPeso().val(); pmv = ap.getDestinazione().getParola();
			}
		}
		p[0] = ppv; p[1] = pmv;
		return p;
	} // parolaCheSegue
	public String toString() {
		StringBuilder sb = new StringBuilder(500);
		double f = 0;
		for (Parola p: gp) {
			f = p.getFrequenza() / (double)numTotParole;
			sb.append('f');	sb.append('(');
			sb.append(p.getParola());
			sb.append(')'); sb.append('=');
			sb.append(String.format("%1.2f%n",f));
		}
		for (Parola p: gp) {
			Iterator<ArcoPesato<Parola>> ia = gp.adiacenti(p);
			while (ia.hasNext()) {
				ArcoPesato<Parola> a = ia.next();
				f = a.getPeso().val() / p.getFrequenza();
				sb.append('f'); sb.append('(');
				sb.append(a.getOrigine().getParola());
				sb.append(',');
				sb.append( a.getDestinazione().getParola());
				sb.append(')'); sb.append('=');
				sb.append(String.format("%1.2f%n", f));
			}
		}
		return sb.toString();
	} // toString
	public static void main(String[]args) throws IOException {
		StatisticaParole sp = new StatisticaParoleGrafo();
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
} // StatisticaParoleGrafo
