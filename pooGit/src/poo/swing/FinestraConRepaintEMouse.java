package poo.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FinestraRepaintMouse extends JFrame {
	private Pannello p = null;
	private Font f = new Font("Helvetica", Font.BOLD, 20);
	private Color col = new Color(57, 128, 110);
	private MouseList ml = null;
	private int x, y;
	public FinestraRepaintMouse() {
		setTitle("Finestra con Repaint");
		setSize(400, 200);
		setLocation(50, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ml = new MouseList();
		add(p = new Pannello());
	} // Costruttore
	class Pannello extends JPanel {
		public Pannello() {
			 addMouseListener(ml);
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			System.out.println("paintComponent chiamata");
			setBackground(Color.white);
			g.setFont(f);
			g.setColor(col);
			g.drawString("Swing", x, y);
		}
	} // Pannello
	class MouseList extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			FinestraRepaintMouse.this.x = e.getX();
			FinestraRepaintMouse.this.y = e.getY();
			e.getComponent().repaint();
		}
	} // MouseList
} // FinestraRepaintMouse

public class FinestraConRepaintEMouse {
	public static void main(String[]args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame f = new FinestraRepaintMouse();
				f.setVisible(true);
			}
		});
	} // main
} // FinestraConRepaint
