package poo.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinestraChiudibile3 extends JFrame{
	// Listener interno estende WindowAdapter
	class Ascoltatore extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
	} // Ascoltatore
	public FinestraChiudibile3() {
		setTitle("Finestra Chiudibile");
		setSize(300, 200);
		setLocation(50, 200);
		addWindowListener(new Ascoltatore());
	} // Costruttore
	public static void main(String[]args) {
		Frame f = new FinestraChiudibile3();
		f.setVisible(true);
	} // main
} // FinestraChiudibile3
