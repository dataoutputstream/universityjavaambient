package poo.esempi;

import poo.date.Data;

public class TestData {
	public static void main(String[]args) {
		Data today = new Data();
		System.out.println("Data attuale: " + today);
		System.out.println("L'anno " + today.get(Data.Elemento.ANNO) + (Data.bisestile(today.get(Data.Elemento.ANNO)) ? " " : " non ") + "è bisestile");
		Data d = new Data(29, 2, 2012);
		System.out.println("Giorno successivo al " + d + ": " + d.giornoDopo());
		Data d1 = new Data(13, 3, 2012);
		System.out.println("Giorni trascorsi tra " + d1 + " e oggi: " + d1.distanza(today));
		System.out.println("hashCode di " + today + " " + today.hashCode());
		System.out.println("hashCode di " + d1 + " " + d1.hashCode());
	} // main
} // TestData
