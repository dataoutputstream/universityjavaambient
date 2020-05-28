package poo.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FinestraRepaint extends JFrame {
	private Pannello p = null;
	private Font f = new Font("Helvetica", Font.BOLD, 20);
	private Color col = new Color(57, 128, 110);
	public FinestraRepaint() {
		setTitle("Finestra con Repaint");
		setSize(400, 200);
		setLocation(50, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(p = new Pannello());
	} // Costruttore
	class Pannello extends JPanel{
		public void paintComponent(Graphics g) { // Repainting del pannello
			super.paintComponent(g);
			System.out.println("paintComponent chiamata");
			setBackground(Color.white);
			g.setFont(f);
			g.setColor(col);
			if (Math.random() < 0.5)
				g.drawString("Repainting the world", 30, 40);
			else
				g.drawString("Repainting the world", 100, 100);
		}
	} // Pannello
} // FinestraRepaint

public class FinestraConRepaint {
	public static void main(String[]args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame f = new FinestraRepaint();
				f.setVisible(true);			  
			}
		});
	} // main
} // FinestraConRepaint
