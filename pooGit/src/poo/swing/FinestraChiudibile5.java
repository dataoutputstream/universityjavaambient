package poo.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinestraChiudibile5 extends JFrame{
	public FinestraChiudibile5() {
		setTitle("Finestra Chiudibile");
		setSize(300, 200);
		setLocation(50, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default (senza Listener)
	} // Costruttore
	public static void main(String[]args) {
		JFrame f = new FinestraChiudibile5();
		f.setVisible(true);
	} // main
} // FinestraChiudibile5
