package poo.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinestraChiudibile0 extends JFrame implements WindowListener {
	public FinestraChiudibile0() {
		setTitle("Finestra Chiudibile");
		setSize(300, 200);
		setLocation(50, 200);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this); // this implementa WindowListener
	} // Costruttore

	// Metodi di WindowListener
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) { System.exit(0); } // Esce dal programma se viene chiusa la finestra
	public void windowDeactivated(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}

	public static void main(String[]args) {
		Frame f = new FinestraChiudibile0();
		f.setVisible(true);
	} // main
} // FinestraChiudibile0
