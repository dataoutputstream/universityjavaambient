package poo.agendina;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class FinestraGUI extends JFrame {
	private File fileDiSalvataggio = null;
	private JMenuItem tipoAL, tipoLL, tipoHashSet, tipoTreeSet, tipoMap,
		apri, salva, salvaConNome, esci, about,
		aggiungiNominativo, rimuoviNominativo,
		numeroNominativi, svuota,
		telefonoDi, personaDi, elenco;
	private String titolo = "Agendina GUI ";
	private String impl = "";
	private Agendina agendina = null;
	private int capacita = 50; // Default per ArrayList
	private FrameAggiungiNominativo fAN = null;
	private String cognome, nome, prefisso, telefono;
	private boolean cognomeOk = false, nomeOk = false,
			prefissoOk = false, telefonoOk = false;
	private FrameRimuoviNominativo fRN = null;
	private FrameTelefonoDi fTD = null;
	private FramePersonaDi fPD = null;
	public FinestraGUI() {
		setTitle(titolo + impl);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (consensoUscita()) System.exit(0);
			}
		});
		AscoltatoreEventiAzione listener = new AscoltatoreEventiAzione();

		// Creazione barra dei menu
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		// Creazione menu 'File'
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		// Creazione voci menu 'File'
		JMenu tipoImpl = new JMenu("Nuova");
		tipoAL = new JMenuItem("ArrayList");
		tipoAL.addActionListener(listener);
		tipoImpl.add(tipoAL);
		tipoLL = new JMenuItem("LinkedList");
		tipoLL.addActionListener(listener);
		tipoImpl.add(tipoLL);
		tipoHashSet = new JMenuItem("HashSet");
		tipoHashSet.addActionListener(listener);
		tipoImpl.add(tipoHashSet);
		tipoTreeSet = new JMenuItem("TreeSet");
		tipoTreeSet.addActionListener(listener);
		tipoImpl.add(tipoTreeSet);
		tipoMap = new JMenuItem("Map");
		tipoMap.addActionListener(listener);
		tipoImpl.add(tipoMap);
		fileMenu.add(tipoImpl);
		fileMenu.addSeparator();
		apri = new JMenuItem("Apri");
		apri.addActionListener(listener);
		fileMenu.add(apri);
		salva = new JMenuItem("Salva");
		salva.addActionListener(listener);
		fileMenu.add(salva);
		salvaConNome = new JMenuItem("Salva con nome");
		salvaConNome.addActionListener(listener);
		fileMenu.add(salvaConNome);
		fileMenu.addSeparator();
		esci = new JMenuItem("Esci");
		esci.addActionListener(listener);
		fileMenu.add(esci);
		
		// Creazione menu 'Comandi'
		JMenu commandMenu = new JMenu("Comandi");
		menuBar.add(commandMenu);
		// Creazione voci menu 'Comandi'
		aggiungiNominativo = new JMenuItem("Aggiungi nominativo");
		aggiungiNominativo.addActionListener(listener);
		commandMenu.add(aggiungiNominativo);
		rimuoviNominativo = new JMenuItem("Rimuovi nominativo");
		rimuoviNominativo.addActionListener(listener);
		commandMenu.add(rimuoviNominativo);
		numeroNominativi = new JMenuItem("Numero nominativi");
		numeroNominativi.addActionListener(listener);
		commandMenu.add(numeroNominativi);
		svuota = new JMenuItem("Svuota agendina");
		svuota.addActionListener(listener);
		commandMenu.add(svuota);
		commandMenu.addSeparator();
		telefonoDi = new JMenuItem("Telefono di");
		telefonoDi.addActionListener(listener);
		commandMenu.add(telefonoDi);
		personaDi = new JMenuItem("Persona di");
		personaDi.addActionListener(listener);
		commandMenu.add(personaDi);
		elenco = new JMenuItem("Elenco");
		elenco.addActionListener(listener);
		commandMenu.add(elenco);

		// Creazione menu 'Help'
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		// Creazione voci menu 'Help'
		about = new JMenuItem("About");
		about.addActionListener(listener);
		helpMenu.add(about);
		menuIniziale();
		
		pack();
		setLocation(200, 200);
		setSize(500, 400);
	} // Costruttore
	private class FrameAL extends JFrame implements ActionListener {
		private JTextField capacita;
		private JButton ok;
		public FrameAL() {
			setTitle("Capacità Array List");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.add(new JLabel("Capacità", JLabel.RIGHT));
			p.add(capacita = new JTextField("50", 12));
			p.add(ok = new JButton("OK"));
			add(p);
			capacita.addActionListener(this);
			ok.addActionListener(this);
			setLocation(250, 340);
			setSize(400, 100);
		} // Costruttore
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == capacita) {
				FinestraGUI.this.capacita = Integer.parseInt(capacita.getText());
			} else if (e.getSource() == ok) {
				agendina = new AgendinaAL(FinestraGUI.this.capacita);
				menuAvviato();
				this.dispose();
			}
		} // actionPerformed
	} // FrameAL
	private class FrameVector extends JFrame implements ActionListener {
		private JTextField capacita;
		private JButton ok;
		public FrameVector() {
			setTitle("Capacità Vector");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.add(new JLabel("Capacità", JLabel.RIGHT));
			p.add(capacita = new JTextField("50", 12));
			p.add(ok = new JButton("OK"));
			add(p);
			capacita.addActionListener(this);
			ok.addActionListener(this);
			setLocation(250, 340);
			setSize(400, 100);
		} // Costruttore
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == capacita) {
				FinestraGUI.this.capacita = Integer.parseInt(capacita.getText());
			} else if (e.getSource() == ok) {
				agendina = new AgendinaVector(FinestraGUI.this.capacita);
				menuAvviato();
				this.dispose();
			}
		} // actionPerformed
	} // FrameVector
	private class FrameAggiungiNominativo extends JFrame implements ActionListener {
		private JTextField cognome, nome, prefisso, telefono;
		private JButton ok;
		public FrameAggiungiNominativo() {
			setTitle("Aggiunta Nominativo");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					if (verificaUscita()) {
						agendina.aggiungi(new Nominativo(
							FinestraGUI.this.cognome.toUpperCase(),
							FinestraGUI.this.nome.toUpperCase(),
							FinestraGUI.this.prefisso,
							FinestraGUI.this.telefono));
					}
					clear();
					FrameAggiungiNominativo.this.setVisible(false);
				} // windowClosing
			}); // WindowAdapter
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(2, 4, 5, 5));
			p.add(new JLabel("Cognome: ", JLabel.RIGHT));
			p.add(cognome = new JTextField("", 12));
			p.add(new JLabel("Nome: ", JLabel.RIGHT));
			p.add(nome = new JTextField("", 12));
			p.add(new JLabel("Prefisso: ", JLabel.RIGHT));
			p.add(prefisso = new JTextField("", 12));
			p.add(new JLabel("Telefono: ", JLabel.RIGHT));
			p.add(telefono = new JTextField("", 12));
			add(p, BorderLayout.NORTH);
			JPanel p2 = new JPanel();
			p2.add(ok = new JButton("OK"));
			add(p2, BorderLayout.CENTER);
			cognome.addActionListener(this);
			nome.addActionListener(this);
			prefisso.addActionListener(this);
			telefono.addActionListener(this);
			ok.addActionListener(this);
			setLocation(250, 340);
			setSize(400, 150);
		} // Costruttore
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cognome) {
				FinestraGUI.this.cognome = cognome.getText();
				FinestraGUI.this.cognomeOk = true;
			} else if (e.getSource() == nome) {
				FinestraGUI.this.nome = nome.getText();
				FinestraGUI.this.nomeOk = true;
			} else if (e.getSource() == prefisso) {
				FinestraGUI.this.prefisso = prefisso.getText();
				FinestraGUI.this.prefissoOk = true;
			} else if (e.getSource() == telefono) {
				FinestraGUI.this.telefono = telefono.getText();
				FinestraGUI.this.telefonoOk = true;
			} else if (e.getSource() == ok) {
				if (verificaUscita()) {
					agendina.aggiungi(new Nominativo(
						FinestraGUI.this.cognome.toUpperCase(),
						FinestraGUI.this.nome.toUpperCase(),
						FinestraGUI.this.prefisso,
						FinestraGUI.this.telefono));
					this.setVisible(false);
					clear();
				}
			}
		} // actionPerformed
		private boolean verificaUscita() {
			return cognomeOk && nomeOk && prefissoOk && telefonoOk;
		} // verificaUscita
		private void clear() {
			cognomeOk = false;
			nomeOk = false;
			prefissoOk = false;
			telefonoOk = false;
			cognome.setText("");
			nome.setText("");
			prefisso.setText("");
			telefono.setText("");
		} // clear
	} // FrameAggiungiNominativo
	private class FrameRimuoviNominativo extends JFrame implements ActionListener {
		private JTextField cognome, nome;
		private JButton ok;
		public FrameRimuoviNominativo() {
			setTitle("Rimozione Nominativo");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					if (verificaUscita()) {
						agendina.rimuovi(new Nominativo(
							FinestraGUI.this.cognome.toUpperCase(),
							FinestraGUI.this.nome.toUpperCase(), "", ""));
					}
					FrameRimuoviNominativo.this.setVisible(false);
					clear();
				} // windowClosing
			}); // WindowAdapter
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.add(new JLabel("Cognome: ", JLabel.RIGHT));
			p.add(cognome = new JTextField("", 12));
			p.add(new JLabel("Nome: ", JLabel.RIGHT));
			p.add(nome = new JTextField("", 12));
			add(p, BorderLayout.NORTH);
			JPanel p2 = new JPanel();
			p2.add(ok = new JButton("OK"));
			add(p2, BorderLayout.CENTER);
			cognome.addActionListener(this);
			nome.addActionListener(this);
			ok.addActionListener(this);
			setLocation(225, 340);
			setSize(450, 150);
		} // Costruttore
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cognome) {
				FinestraGUI.this.cognome = cognome.getText();
				FinestraGUI.this.cognomeOk = true;
			} else if (e.getSource() == nome) {
				FinestraGUI.this.nome = nome.getText();
				FinestraGUI.this.nomeOk = true;
			} else if (e.getSource() == ok) {
				if (verificaUscita()) {
					agendina.rimuovi(new Nominativo(
						FinestraGUI.this.cognome.toUpperCase(),
						FinestraGUI.this.nome.toUpperCase(), "","" ));
					this.setVisible(false);
					clear();
				}
			}
		} // actionPerformed
		private boolean verificaUscita() {
			return cognomeOk && nomeOk;
		} // verificaUscita
		private void clear() {
			cognomeOk = false;
			nomeOk = false;
			cognome.setText("");
			nome.setText("");
		} // clear
	} // FrameRimuoviNominativo
	private class FrameTelefonoDi extends JFrame implements ActionListener {
		private JTextField cognome, nome;
		private JButton ok;
		public FrameTelefonoDi() {
			setTitle("Ricerca telefono di un nominativo");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					if (verificaUscita()) cercaTelefono();
					else FrameTelefonoDi.this.setVisible(false);
					clear();
				} // windowClosing
			}); // WindowAdapter
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.add(new JLabel("Cognome: ", JLabel.RIGHT));
			p.add(cognome = new JTextField("", 12));
			p.add(new JLabel("Nome: ", JLabel.RIGHT));
			p.add(nome = new JTextField("", 12));
			add(p, BorderLayout.NORTH);
			JPanel p2 = new JPanel();
			p2.add(ok = new JButton("OK"));
			add(p2, BorderLayout.CENTER);
			cognome.addActionListener(this);
			nome.addActionListener(this);
			ok.addActionListener(this);
			setLocation(225, 340);
			setSize(450, 150);
		} // Costruttore
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cognome) {
				FinestraGUI.this.cognome = cognome.getText();
				FinestraGUI.this.cognomeOk = true;
			} else if (e.getSource() == nome) {
				FinestraGUI.this.nome = nome.getText();
				FinestraGUI.this.nomeOk = true;
			} else if (e.getSource() == ok) {
				if (verificaUscita()) { cercaTelefono(); clear(); }
			}
		} // actionPerformed
		private void cercaTelefono() {
			Nominativo n = agendina.cerca(new Nominativo(
				FinestraGUI.this.cognome.toUpperCase(),
				FinestraGUI.this.nome.toUpperCase(), "", ""));
			this.setVisible(false);
			if (n != null)
				JOptionPane.showMessageDialog(null, "Telefono della persona cercata: " +
					n.getPrefisso() + "-" + n.getTelefono());
			else JOptionPane.showMessageDialog(null, "Nominativo inesistente!");
		} // cercaTelefono
		private boolean verificaUscita() {
			return cognomeOk && nomeOk;
		} // verificaUscita
		private void clear() {
			cognomeOk = false;
			nomeOk = false;
			cognome.setText("");
			nome.setText("");
		} // clear
	} // FrameTelefonoDi
	private class FramePersonaDi extends JFrame implements ActionListener {
		private JTextField prefisso, telefono;
		private JButton ok;
		public FramePersonaDi() {
			setTitle("Ricerca persona di prefisso e telefono");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					if (verificaUscita()) cercaPersona();
					else FramePersonaDi.this.setVisible(false);
					clear();
				} // windowClosing
			}); // WindowAdapter
			JPanel p=new JPanel();
			p.setLayout(new FlowLayout());
			p.add(new JLabel("Prefisso: ", JLabel.RIGHT));
			p.add(prefisso = new JTextField("", 12));
			p.add(new JLabel("Telefono: ", JLabel.RIGHT));
			p.add(telefono = new JTextField("", 12));
			add(p, BorderLayout.NORTH);
			JPanel p2 = new JPanel();
			p2.add(ok = new JButton("OK"));
			add(p2, BorderLayout.CENTER);
			prefisso.addActionListener(this);
			telefono.addActionListener(this);
			ok.addActionListener(this);
			setLocation(225, 340);
			setSize(450, 150);
		} // Costruttore
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == prefisso) {
				FinestraGUI.this.prefisso = prefisso.getText();
				FinestraGUI.this.prefissoOk = true;
			} else if (e.getSource() == telefono) {
				FinestraGUI.this.telefono = telefono.getText();
				FinestraGUI.this.telefonoOk = true;
			} else if (e.getSource() == ok) {
				if (verificaUscita()) { cercaPersona(); clear(); }
			}
		} // actionPerformed
		private void cercaPersona() {
			Nominativo n = agendina.cerca(
				FinestraGUI.this.prefisso,
				FinestraGUI.this.telefono);
			this.setVisible(false);
			if (n != null)
				JOptionPane.showMessageDialog(null, "Persona trovata: " +
					n.getCognome().toUpperCase() + " " + n.getNome().toUpperCase());
			else JOptionPane.showMessageDialog(null, "Nominativo inesistente!" );
		} // cercaPersona
		private boolean verificaUscita() {
			return prefissoOk && telefonoOk;
		} // verificaUscita
		private void clear() {
			prefissoOk = false;
			telefonoOk = false;
			prefisso.setText("");
			telefono.setText("");
		} // clear
	} // FramePersonaDi
	private class FrameElenco extends JFrame {
		private JTextArea area;
		public FrameElenco() {
			setTitle("Elenco Nominativi");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			JTextArea area = new JTextArea(10, 25);
			area.setEditable(false);
			JScrollPane sp = new JScrollPane(area);
			p.add(sp);
			add(p);
			if (agendina.size() != 0)
				area.append(agendina.toString());
			else area.append("Agendina vuota!");
			setLocation(250, 340);
			setSize(400, 200);
		} // Costruttore
	} // FrameElenco
	private void menuIniziale() {
		apri.setEnabled(false);
		salva.setEnabled(false);
		salvaConNome.setEnabled(false);
		aggiungiNominativo.setEnabled(false);
		rimuoviNominativo.setEnabled(false);
		numeroNominativi.setEnabled(false);
		svuota.setEnabled(false);
		telefonoDi.setEnabled(false);
		personaDi.setEnabled(false);
		elenco.setEnabled(false);		
	} // menuIniziale
	private void menuAvviato() {
		tipoAL.setEnabled(false);
		tipoLL.setEnabled(false);
		tipoHashSet.setEnabled(false);
		tipoTreeSet.setEnabled(false);
		tipoMap.setEnabled(false);
		apri.setEnabled(true);
		salva.setEnabled(true);
		salvaConNome.setEnabled(true);
		aggiungiNominativo.setEnabled(true);
		rimuoviNominativo.setEnabled(true);
		numeroNominativi.setEnabled(true);
		svuota.setEnabled(true);
		telefonoDi.setEnabled(true);
		personaDi.setEnabled(true);
		elenco.setEnabled(true);			
	} // menuAvviato
	private boolean consensoUscita() {
		int option = JOptionPane.showConfirmDialog(null,
			"Uscendo si perderanno tutti i dati! Continuare?", "Conferma", JOptionPane.YES_NO_OPTION);
		return option == JOptionPane.YES_OPTION;
	} // consensoUscita
	private class AscoltatoreEventiAzione implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == esci) {
				if (consensoUscita()) System.exit(0);
			} else if (e.getSource() == tipoAL) {
				impl = "AL";
				FinestraGUI.this.setTitle(titolo + impl);
				FrameAL frameAL = new FrameAL();
				frameAL.setVisible(true);
			} else if (e.getSource() == tipoLL) {
				impl = "LL";
				FinestraGUI.this.setTitle(titolo + impl);
				agendina = new AgendinaLL();
				menuAvviato();
			} else if (e.getSource() == tipoHashSet) {
				impl = "HashSet";
				FinestraGUI.this.setTitle(titolo + impl);
				agendina = new AgendinaHashSet();
				menuAvviato();
			} else if (e.getSource() == tipoTreeSet) {
				impl = "TreeSet";
				FinestraGUI.this.setTitle(titolo + impl);
				agendina = new AgendinaTreeSet();
				menuAvviato();
			} else if (e.getSource() == tipoMap) {
				impl = "Map";
				FinestraGUI.this.setTitle(titolo + impl);
				agendina = new AgendinaMap();
				menuAvviato();
			} else if (e.getSource() == salva) {
				JFileChooser chooser = new JFileChooser();
				try {
					if (fileDiSalvataggio != null) {
						int ans = JOptionPane.showConfirmDialog(null, "Sovrascrivere " + fileDiSalvataggio.getAbsolutePath() + " ?");
						if (ans == JOptionPane.YES_OPTION)
							agendina.salva(fileDiSalvataggio.getAbsolutePath());
						else JOptionPane.showMessageDialog(null, "Nessun salvataggio!");
						return;
					}
					if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						fileDiSalvataggio = chooser.getSelectedFile();
						FinestraGUI.this.setTitle(titolo + impl + " - " + fileDiSalvataggio.getName());
					}
					if (fileDiSalvataggio != null)
						agendina.salva(fileDiSalvataggio.getAbsolutePath());
					else JOptionPane.showMessageDialog(null, "Nessun Salvataggio!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Errore scrittura!");
				}
			} else if (e.getSource() == salvaConNome) {
				JFileChooser chooser = new JFileChooser();
				try {
					if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						fileDiSalvataggio = chooser.getSelectedFile();
						FinestraGUI.this.setTitle(titolo + impl + " - " + fileDiSalvataggio.getName());
					}
					if (fileDiSalvataggio != null)
						agendina.salva(fileDiSalvataggio.getAbsolutePath());
					else JOptionPane.showMessageDialog(null,"Nessun Salvataggio!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Errore di scrittura!");
				}
			} else if (e.getSource() == apri) {
				JFileChooser chooser = new JFileChooser();
				try {
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						if (!chooser.getSelectedFile().exists()) {
							JOptionPane.showMessageDialog(null, "File inesistente!");
						} else {
							fileDiSalvataggio = chooser.getSelectedFile();
							FinestraGUI.this.setTitle(titolo + impl + " - " + fileDiSalvataggio.getName());
							try {
								agendina.ripristina(fileDiSalvataggio.getAbsolutePath());
							} catch (IOException ex){
								JOptionPane.showMessageDialog(null, "Errore di lettura: file malformato!");
							}
						}
					} else JOptionPane.showMessageDialog(null, "Nessuna apertura!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Errore di lettura!");
				}
			} else if (e.getSource() == aggiungiNominativo) {
				if (fAN == null) fAN = new FrameAggiungiNominativo();
				fAN.setVisible(true);
			} else if (e.getSource() == rimuoviNominativo) {
				if (fRN == null) fRN = new FrameRimuoviNominativo();
				fRN.setVisible(true);
			} else if (e.getSource() == telefonoDi) {
				if (fTD == null) fTD = new FrameTelefonoDi();
				fTD.setVisible(true);
			} else if (e.getSource() == personaDi) {
				if (fPD == null) fPD = new FramePersonaDi();
				fPD.setVisible(true);
			} else if (e.getSource() == numeroNominativi) {
				JOptionPane.showMessageDialog(null, "Numero nominativi in agenda: " + agendina.size());
			} else if (e.getSource() == svuota) {
				agendina.svuota();
			} else if (e.getSource() == elenco) {
				FrameElenco fE = new FrameElenco();
				fE.setVisible(true);
			} else if (e.getSource() == about) {
				JOptionPane.showMessageDialog(null,
					"Programma di Gestione di un'Agendina Telefonica\n",
					"About", JOptionPane.PLAIN_MESSAGE);
			}
		} // actionPerformed
	} // AscoltatoreEventiAzione
} // FinestraGUI

public class AgendinaGUI {
	public static void main(String[]args) {
		FinestraGUI f = new FinestraGUI();
		f.setVisible(true);
	} // main
} // AgendinaGUI
