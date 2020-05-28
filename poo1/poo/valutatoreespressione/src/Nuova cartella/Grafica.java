package valutatoreespressione;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Grafica extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JButton più,meno,per,diviso,modulo,ce,risolvi,potenza,uno,due,tre,quattro,cinque,sei,sette,otto,nove,zero,paperta,pchiusa;
	JTextField Input,Output;
	StringBuilder sb=new StringBuilder();
	
	public Grafica() {
		
		JPanel pannello;
		StringBuilder sb=new StringBuilder();
		setTitle("Valutatore Espressioni v1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setBounds(300, 300, 627, 290);
		pannello = new JPanel();
		pannello.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pannello);
		pannello.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(-100, 0, 800, 40);
		pannello.add(panel);
		
		JLabel espressione = new JLabel("Espressione");
		panel.add(espressione);
		
		Input = new JTextField();
		panel.add(Input);
		Input.setColumns(26);
		
		JLabel risultato = new JLabel("Risultato");
		panel.add(risultato);
		
		Output = new JTextField();
		panel.add(Output);
		Output.setColumns(15);
		
		JPanel tastiera = new JPanel();
		tastiera.setBounds(0, 46, 622, 215);
		pannello.add(tastiera);
		tastiera.setLayout(new GridLayout(4,5));
		
		sette = new JButton("7");
		sette.setFont(new Font("Tahoma", Font.PLAIN, 23));
		sette.addActionListener(this);
		tastiera.add(sette);
		
		otto = new JButton("8");
		otto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		otto.addActionListener(this);
		tastiera.add(otto);
		
		nove = new JButton("9");
		nove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nove.addActionListener(this);
		tastiera.add(nove);
		
		diviso = new JButton("/");
		diviso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		diviso.addActionListener(this);
		tastiera.add(diviso);
		
		paperta = new JButton("(");
		paperta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paperta.addActionListener(this);
		tastiera.add(paperta);
		
		quattro = new JButton("4");
		quattro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		quattro.addActionListener(this);
		tastiera.add(quattro);
		
		cinque = new JButton("5");
		cinque.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cinque.addActionListener(this);
		tastiera.add(cinque);
		
		sei = new JButton("6");
		sei.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sei.addActionListener(this);
		tastiera.add(sei);
		
		per = new JButton("*");
		per.setFont(new Font("Tahoma", Font.PLAIN, 16));
		per.addActionListener(this);
		tastiera.add(per);
		
		pchiusa = new JButton(")");
		pchiusa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pchiusa.addActionListener(this);
		tastiera.add(pchiusa);
		
		uno = new JButton("1");
		uno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		uno.addActionListener(this);
		tastiera.add(uno);
		
		due = new JButton("2");
		due.setFont(new Font("Tahoma", Font.PLAIN, 20));
		due.addActionListener(this);
		tastiera.add(due);
		
		tre = new JButton("3");
		tre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tre.addActionListener(this);
		tastiera.add(tre);
		
		meno = new JButton("-");
		meno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		meno.addActionListener(this);
		tastiera.add(meno);
		
		modulo = new JButton("%");
		modulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		modulo.addActionListener(this);
		tastiera.add(modulo);
		
		zero = new JButton("0");
		zero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		zero.addActionListener(this);
		tastiera.add(zero);
		
		ce = new JButton("CE");
		ce.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ce.addActionListener(this);
		tastiera.add(ce);
		
		risolvi = new JButton("Risolvi");
		risolvi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		risolvi.addActionListener(this);
		tastiera.add(risolvi);
		
		più = new JButton("+");
		più.setFont(new Font("Tahoma", Font.PLAIN, 16));
		più.addActionListener(this);
		tastiera.add(più);
		
		potenza = new JButton("^");
		potenza.setFont(new Font("Tahoma", Font.PLAIN, 16));
		potenza.addActionListener(this);
		tastiera.add(potenza);
		
		
	}
		

	 
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()!=ce && evt.getSource()!=risolvi) {
			sb=new StringBuilder();sb.append(Input.getText());
			if(evt.getSource()==più) sb.append('+');
			if(evt.getSource()==meno) sb.append('-');
			if(evt.getSource()==per) sb.append('*');
			if(evt.getSource()==diviso) sb.append('/');
			if(evt.getSource()==modulo) sb.append('%');
			if(evt.getSource()==potenza) sb.append('^');
			if(evt.getSource()==paperta) sb.append('(');
			if(evt.getSource()==pchiusa) sb.append(')');
			if(evt.getSource()==uno) sb.append('1');
			if(evt.getSource()==due) sb.append('2');
			if(evt.getSource()==tre) sb.append('3');
			if(evt.getSource()==quattro) sb.append('4');
			if(evt.getSource()==cinque) sb.append('5');
			if(evt.getSource()==sei) sb.append('6');
			if(evt.getSource()==sette) sb.append('7');
			if(evt.getSource()==otto) sb.append('8');
			if(evt.getSource()==nove) sb.append('9');
			if(evt.getSource()==zero) sb.append('0');
			Input.setText(sb.toString());
		}else if(evt.getSource()==ce) {
			sb=new StringBuilder();
			Input.setText("");
			Output.setText("");
		}else if(evt.getSource()==risolvi) {
			ValutaEspressione ve=new ValutaEspressione();
			StringTokenizer st=new StringTokenizer(Input.getText(),"()%+-*/^",true);
			Output.setText(String.valueOf(ve.valutaEspressione(st)));
			
			
		}
		
	}


	
}

