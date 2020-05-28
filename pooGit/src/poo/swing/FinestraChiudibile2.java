package poo.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinestraChiudibile2 extends JFrame{
	// Listener interno
	class Ascoltatore implements WindowListener{
		public void windowActivated(WindowEvent e) {}
		public void windowClosed(WindowEvent e) {}
		public void windowClosing(WindowEvent e) { System.exit(0); }
		public void windowDeactivated(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowOpened(WindowEvent e) {}
	} // Ascoltatore
	public FinestraChiudibile2() {
		setTitle("Finestra Chiudibile");
		setSize(300, 200);
		setLocation(50, 200);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new Ascoltatore());
	} // Costruttore
	public static void main(String[]args) {
		Frame f = new FinestraChiudibile2();
		f.setVisible(true);
	} // main
} // FinestraChiudibile2
