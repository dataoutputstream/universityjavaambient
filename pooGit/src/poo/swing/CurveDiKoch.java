package poo.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CurveDiKoch {
	public static void main(String[]args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainWindow mw = new MainWindow();
				mw.setVisible(true);
			}
		});
	} // main
} // CurveDiKoch

class MainWindow extends JFrame {
	public MainWindow(){
		setTitle("Fiocco di Neve - Curve di Koch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Pannello p = new Pannello();
		add(p);	
		pack();
		setSize(800,600);
	}
	private class Pannello extends JPanel {
		private double distanzaMinima = 5D;
		private double RADICE_DI_3 = Math.sqrt(3D);
		private double d;
		private Graphics g;
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.g = g;
			System.out.println("paintComponent chiamata...");
			setBackground(Color.white);
			koch(50, 300, 750, 300, 1); // Triangolo superiore
			koch(50, 300, 750, 300, -1); // Triangolo inferiore
		} // paintComponent
		public void koch(int x1, int y1, int x2, int y2, int up) {
			int x3, x4, x5, y3, y4, y5;
			d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
			if (d < distanzaMinima) g.drawLine(x1, y1, x2, y2);
			else {
				x3 = x1 + Math.round((x2 - x1) / 3); // Calcola i quattro segmenti
				y3 = y1 + Math.round((y2 - y1) / 3);
				x4 = x1 + Math.round((x2 - x1) * 2 / 3);
				y4 = y1 + Math.round((y2 - y1) * 2 / 3);
				x5 = Math.round((x1 + x2) / 2 - (int)((y2 - y1) * up / RADICE_DI_3 / 2));
				y5 = Math.round((y1 + y2) / 2 + (int)((x2 - x1) * up / RADICE_DI_3 / 2));
				// Chiamate ricorsive sui nuovi quattro segmenti
				koch(x1, y1, x3, y3, up);
				koch(x3, y3, x5, y5, up);
				koch(x5, y5, x4, y4, up);
				koch(x4, y4, x2, y2, up);
			}
		} // koch
	} // Pannello
} // MainWindow
