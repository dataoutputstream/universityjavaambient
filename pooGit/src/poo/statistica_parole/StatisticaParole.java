package poo.statistica_parole;

import java.io.IOException;

interface StatisticaParole {
	void sequenza(String nomeFile) throws IOException;
	String[] parolaCheSegue(String pc);
} // StatisticaParole
