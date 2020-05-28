package poo.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinestraChiudibile4 extends JFrame {
	public FinestraChiudibile4() {
		setTitle("Finestra Chiudibile");
		setSize(300, 200);
		setLocation(50, 200);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Estensione di WindowAdapter ed istanziazione 'al volo'
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	} // Costruttore
	public static void main(String[]args) {
		JFrame f = new FinestraChiudibile4();
		f.setVisible(true);
	} // main
} // FinestraChiudibile4
