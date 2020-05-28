package poo.esempi;

import poo.figure.*;
import poo.geometria.*;

public class TestFigure {
	public static FiguraPiana figuraAreaMassima(FiguraPiana[] f) {
		FiguraPiana fmax = f[0];
		double amax = f[0].area();
		for (int i = 1; i < f.length; i++) {
			if (f[i].area() >= amax) {
				fmax = f[i]; amax = f[i].area();
			}
		}
		return fmax;
	} // figuraAreaMassima
	public static void main(String[]args) {
		Punto[] p = { new Punto(2,5), new Punto(4,3), new Punto(1,-1), new Punto(-2,1) };
		FiguraPiana[] a = { 	new Quadrato(5), new Cerchio(3.7), new Rombo(7, 5),
					new Rettangolo(3, 8), new poo.figure.Triangolo(3.2, 3.5, 7), new Poligono(p),
					new Disco(0, 0, 2), new poo.geometria.Triangolo(new Punto(3,4), new Punto(1,3), new Punto(4,2)) };
		FiguraPiana f = figuraAreaMassima(a);
		System.out.println("Area massima = " + String.format("%1.2f", f.area()) + " di: " + f);
	} // main
} // TestFigure
