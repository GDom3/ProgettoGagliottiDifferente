package UniDy.UninaDelivery;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;

public class FinestraNuovaSpedizione extends JFrame {

	private static final long serialVersionUID = 1L;
	//Amministratore
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JButton menuB;
	private JComboBox mezzoBox;
	private JComboBox corriereBox;
	private JComboBox ordineBox;
	private JSpinner regolatoreKM;
	//Oggetti Reali
	private ArrayList<Ordine> ordini;
	private ArrayList<Corriere> corrieri;
	private ArrayList<MezzoTrasporto> mezzi;
	
	public FinestraNuovaSpedizione(AppBrain appBrain) {
		setFont(new Font("Century", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraNuovaSpedizione.class.getResource("/Img/Icon.png")));
		setResizable(false);
		setTitle("UninaDelivery");
		gestoreApplicazione = appBrain;
		setDefaultCloseOperation(gestoreApplicazione.exit());
		setBounds(100, 100, 800, 600);
		JPanel contentPane;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 101, 101));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel intestazionePanel;
		intestazionePanel = new JPanel();
		intestazionePanel.setLayout(null);
		intestazionePanel.setBackground(new Color(239, 235, 229));
		intestazionePanel.setBounds(0, 0, 71, 561);
		contentPane.add(intestazionePanel);
		
		JLabel titoloSXL;
		titoloSXL = new JLabel("");
		titoloSXL.setIcon(new ImageIcon(FinestraNuovaSpedizione.class.getResource("/Img/SxTitoloImg.jpg")));
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloSXL.setBounds(10, 76, 45, 474);
		intestazionePanel.add(titoloSXL);
		
		JLabel logoSXImgL;
		logoSXImgL = new JLabel("New Label");
		logoSXImgL.setIcon(new ImageIcon(FinestraNuovaSpedizione.class.getResource("/Img/LogoHSX.png")));
		logoSXImgL.setBounds(0, 0, 71, 65);
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		intestazionePanel.add(logoSXImgL);
		
		JPanel homePanel;
		homePanel = new JPanel();
		homePanel.setLayout(null);
		homePanel.setBackground(new Color(119, 101, 101));
		homePanel.setBounds(69, 0, 715, 37);
		contentPane.add(homePanel);
		
		menuB = new JButton("Home");
		menuB.setToolTipText("premi per ritornare al menu");
		menuB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confermaRitornareMenu();
				
			}
		});
		menuB.setBounds(326, 11, 60, 22);
		menuB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneMenu();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneMenu();
				
			}
		});
		menuB.setHorizontalTextPosition(SwingConstants.CENTER);
		menuB.setForeground(Color.WHITE);
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		menuB.setFocusPainted(false);
		menuB.setContentAreaFilled(false);
		menuB.setBorderPainted(false);
		menuB.setBorder(null);
		menuB.setBackground(new Color(119, 101, 101));
		menuB.setBounds(326, 11, 60, 22);
		homePanel.add(menuB);
		
		ordineBox = new JComboBox();
		ordineBox.setForeground(new Color(255, 255, 255));
		ordineBox.setBackground(new Color(179, 168, 166));
		ordineBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ordineBox.setToolTipText("Qui puoi selezionare l'ordine");
		ordineBox.setFont(new Font("Century", Font.PLAIN, 20));
		ordineBox.setBounds(114, 194, 200, 41);
		contentPane.add(ordineBox);
		
		corriereBox = new JComboBox();
		corriereBox.setForeground(new Color(255, 255, 255));
		corriereBox.setBackground(new Color(179, 168, 166));
		corriereBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		corriereBox.setToolTipText("Qui puoi selezionare il corriere");
		corriereBox.setFont(new Font("Century", Font.PLAIN, 20));
		corriereBox.setBounds(324, 194, 200, 41);
		contentPane.add(corriereBox);
		
		
		mezzoBox = new JComboBox();
		mezzoBox.setForeground(new Color(255, 255, 255));
		mezzoBox.setBackground(new Color(179, 168, 166));
		mezzoBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mezzoBox.setToolTipText("Qui puoi selezionare il mezzo di trasporto");
		mezzoBox.setFont(new Font("Century", Font.PLAIN, 20));
		mezzoBox.setBounds(534, 194, 200, 41);
		contentPane.add(mezzoBox);

		JLabel ordineLabel;
		ordineLabel = new JLabel("Ordine");
		ordineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ordineLabel.setForeground(new Color(255, 255, 255));
		ordineLabel.setFont(new Font("Century", Font.PLAIN, 30));
		ordineLabel.setBounds(114, 146, 200, 37);
		contentPane.add(ordineLabel);
	
		JLabel corriereLabal;
		corriereLabal = new JLabel("Corriere");
		corriereLabal.setHorizontalAlignment(SwingConstants.CENTER);
		corriereLabal.setForeground(Color.WHITE);
		corriereLabal.setFont(new Font("Century", Font.PLAIN, 30));
		corriereLabal.setBounds(324, 146, 200, 37);
		contentPane.add(corriereLabal);

		JLabel mezzoLabel;
		mezzoLabel = new JLabel("Mezzo");
		mezzoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mezzoLabel.setForeground(Color.WHITE);
		mezzoLabel.setFont(new Font("Century", Font.PLAIN, 30));
		mezzoLabel.setBounds(534, 146, 200, 37);
		contentPane.add(mezzoLabel);
		
		JButton aggiungiOrdineButton;
		aggiungiOrdineButton = new JButton("Aggiungi ordine");
		aggiungiOrdineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiOrdineButton);
				
				gestoreApplicazione.mostramiSchermataCreaOrdine();
				
				
			}
		});
		aggiungiOrdineButton.setFocusPainted(false);
		aggiungiOrdineButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiOrdineButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneAnnulla(aggiungiOrdineButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiOrdineButton);
			}
		});
		aggiungiOrdineButton.setForeground(new Color(254, 126, 115));
		aggiungiOrdineButton.setContentAreaFilled(false);
		aggiungiOrdineButton.setBorderPainted(false);
		aggiungiOrdineButton.setFont(new Font("Century", Font.PLAIN, 18));
		aggiungiOrdineButton.setToolTipText("Qui puoi crere un nuovo ordine");
		aggiungiOrdineButton.setBounds(114, 246, 200, 33);
		contentPane.add(aggiungiOrdineButton);
		
		JButton aggiungiCorriereButton;
		aggiungiCorriereButton = new JButton("Aggiungi corriere");
		aggiungiCorriereButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiCorriereButton.setToolTipText("Qui puoi assumere un nuovo corriere");
		aggiungiCorriereButton.setForeground(new Color(254, 126, 115));
		aggiungiCorriereButton.setFont(new Font("Century", Font.PLAIN, 18));
		aggiungiCorriereButton.setFocusPainted(false);
		aggiungiCorriereButton.setContentAreaFilled(false);
		aggiungiCorriereButton.setBorderPainted(false);
		aggiungiCorriereButton.setBounds(324, 246, 200, 33);
		aggiungiCorriereButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiCorriereButton);
			}
		});
		aggiungiCorriereButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneAnnulla(aggiungiCorriereButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiCorriereButton);
			}
		});
		contentPane.add(aggiungiCorriereButton);
		
		JButton aggiungiMezzoButton;
		aggiungiMezzoButton = new JButton("Aggiungi mezzo");
		aggiungiMezzoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiMezzoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiMezzoButton);
			}
		});
		aggiungiMezzoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneAnnulla(aggiungiMezzoButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiMezzoButton);
			}
		});
		aggiungiMezzoButton.setToolTipText("Qui puoi acquisire un nuovo mezzo");
		aggiungiMezzoButton.setForeground(new Color(254, 126, 115));
		aggiungiMezzoButton.setFont(new Font("Century", Font.PLAIN, 18));
		aggiungiMezzoButton.setFocusPainted(false);
		aggiungiMezzoButton.setContentAreaFilled(false);
		aggiungiMezzoButton.setBorderPainted(false);
		aggiungiMezzoButton.setBounds(534, 246, 200, 33);
		contentPane.add(aggiungiMezzoButton);
		
		JButton nuovaSpedizioneB;
		nuovaSpedizioneB = new JButton("Crea Nuova Spedizione");
		nuovaSpedizioneB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creaSpedizioneDaInput();
			}
		});
		nuovaSpedizioneB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nuovaSpedizioneB.setToolTipText("premi per creare una nuova sepdizione con i valori inseriti");
		nuovaSpedizioneB.setForeground(Color.WHITE);
		nuovaSpedizioneB.setFont(new Font("Century", Font.BOLD, 20));
		nuovaSpedizioneB.setFocusPainted(false);
		nuovaSpedizioneB.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		nuovaSpedizioneB.setBackground(new Color(254, 126, 115));
		nuovaSpedizioneB.setBounds(293, 416, 265, 50);
		contentPane.add(nuovaSpedizioneB);
		
		JLabel TitoloLabel = new JLabel("Creazione Nuova Spedizione");
		TitoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitoloLabel.setForeground(new Color(255, 255, 255));
		TitoloLabel.setFont(new Font("Century", Font.BOLD, 30));
		TitoloLabel.setBounds(81, 59, 693, 37);
		contentPane.add(TitoloLabel);
		
		JLabel KMLabel = new JLabel("KM:");
		KMLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		KMLabel.setFont(new Font("Century", Font.PLAIN, 30));
		KMLabel.setForeground(new Color(255, 255, 255));
		KMLabel.setBounds(277, 317, 71, 41);
		contentPane.add(KMLabel);
		
		regolatoreKM = new JSpinner();
		regolatoreKM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		regolatoreKM.setForeground(new Color(255, 255, 255));
		regolatoreKM.setBackground(new Color(119, 101, 101));
		regolatoreKM.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		regolatoreKM.setFont(new Font("Century", Font.PLAIN, 20));
		regolatoreKM.setBounds(358, 321, 200, 41);
		contentPane.add(regolatoreKM);
		
		JButton aggiungiOrdineASpedizione;
		aggiungiOrdineASpedizione = new JButton("Aggiungi Ordine A Spedizione Esistente");
		aggiungiOrdineASpedizione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ordineBox.getItemCount() > 0)
					addOrdineASpedizione();
				else 
					messaggioPopUp("Non puoi aggiungere ordini, in quanto non ci sono ordini attesi", "Non Puoi aggiungere ordini");
			}
		});
		aggiungiOrdineASpedizione.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiOrdineASpedizione.setToolTipText("Premi qui per aggiungere l'ordine ad una spedizione");
		aggiungiOrdineASpedizione.setForeground(Color.WHITE);
		aggiungiOrdineASpedizione.setFont(new Font("Century", Font.BOLD, 20));
		aggiungiOrdineASpedizione.setFocusPainted(false);
		aggiungiOrdineASpedizione.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		aggiungiOrdineASpedizione.setBackground(new Color(254, 126, 115));
		aggiungiOrdineASpedizione.setBounds(224, 477, 408, 50);
		contentPane.add(aggiungiOrdineASpedizione);
		

	}
	
	private void addOrdineASpedizione() {
		int input = 0;
		
		ArrayList<Spedizione> spedizioniNonPartite = new ArrayList<Spedizione>();
		try {
			//Ricavo le spedizioni disponibili
			spedizioniNonPartite = gestoreApplicazione.dammiSpedizioniNonPartite();
			
			//Gestisco la combobox, e la riempio
			JComboBox spedizioni = new JComboBox(spedizioniNonPartite.toArray());
			
			//Richiedo cosa fare all'utente
			input = JOptionPane.showConfirmDialog(this,spedizioni,"Seleziona Spedizione",JOptionPane.OK_CANCEL_OPTION);
			if(input == 0)
				input = JOptionPane.showConfirmDialog(this,"Vuoi aggiungere l'ordine "+ ordini.get(ordineBox.getSelectedIndex()).getCodOrdine() + " alla spedizione "+spedizioniNonPartite.get(spedizioni.getSelectedIndex()),"Conferma Scelta",JOptionPane.OK_CANCEL_OPTION);
				if (input == 0) {
					
					inserisciOrdineInSpedizione(spedizioniNonPartite.get(spedizioni.getSelectedIndex()),ordini.get(ordineBox.getSelectedIndex()));
					avviati();
				}
		} catch (NonCiSonoSpedizioniNonPartite e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (RisultatoNonRicavabileException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		}
		
		
	
		
		
	}

	private void inserisciOrdineInSpedizione(Spedizione spedizione, Ordine ordine) {
		try {
			gestoreApplicazione.inserisciOrdineInSpedizione(spedizione,ordine);
		} catch (OperazioneUpdateNonRiuscitaException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		}
		avviati();
		
	}

	private void creaSpedizioneDaInput() {
	
		
		Spedizione nuovaSpedizione;
	
		try {
			//Preparo la nuova spedizione
			nuovaSpedizione = new Spedizione(ordini.get(ordineBox.getSelectedIndex()),mezzi.get(mezzoBox.getSelectedIndex()),corrieri.get(corriereBox.getSelectedIndex()));
			
			//la provo ad inserire
			gestoreApplicazione.creamiNuovaSpedizione(nuovaSpedizione,(int) regolatoreKM.getValue());
			messaggioPopUp("E' stata creata la nuova spedizione","Creazione Riuscita");
			
			avviati();
		} catch (OperazioneUpdateNonRiuscitaException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (RisultatoNonRicavabileException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonPossibileCreareSpedizioneException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (Exception e) {
			messaggioPopUp("Non puoi procedere con questa funzionalità, in quanto non ci sono dati coerenti e non vuoti disponibili","Errore");
		}	
		
	}

	private void confermaRitornareMenu() {
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di ritornare al menu?", "Ritorna al menu",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.ritornaMenu(this);
		
	}
	
	
	private void rimpicciolisciGradualmenteBottoneMenu() {
		//Diventa Piccolo gradualmente
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		
	}
	
	private void ingradisciGradualmenteBottoneMenu() {
		//Diventa grande gradualmente
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 20));
	}
	
	private void rimpicciolisciGradualmenteBottoneAnnulla(JButton bottone) {
		//Diventa Piccolo gradualmente
		bottone.setFont(new Font("Century", Font.PLAIN, 19));
		bottone.setFont(new Font("Century", Font.PLAIN, 18));
		
	}

	private void ingradisciGradualmenteBottoneAnnulla(JButton bottone) {
		//Diventa grande gradualmente
		bottone.setFont(new Font("Century", Font.PLAIN, 19));
		bottone.setFont(new Font("Century", Font.PLAIN, 20));
	}

	

	protected void avviati() {
		setVisible(true);
		try {
			ordini = gestoreApplicazione.estraiOrdiniSenzaSpedOFalliti();
			riempiOrdini();
			
			corrieri = gestoreApplicazione.estraiCorrieriSenzaSped();
			riempiCorriere();
			
			mezzi = gestoreApplicazione.estraiMezziSenzaSped();
			riempiMezzi();
			
		} catch (RisultatoNonRicavabileException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonCiSonoOrdiniAttesiException e) {
			ordineBox.setModel(new DefaultComboBoxModel());
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonCiSonoCorrieriDisponibiliException e) {
			corriereBox.setModel(new DefaultComboBoxModel());
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonCiSonoMezziTrasportoDisponibiliException e) {
			mezzoBox.setModel(new DefaultComboBoxModel());
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		}
		
		
	}
	
	private void riempiMezzi() {
		ArrayList<String> mezziCod = new ArrayList<String>(mezzi.size());
		
		//Bado all'estetica
		for (MezzoTrasporto mezzo : mezzi) 
			mezziCod.add( "(" + mezzo.getCodMezzo() +") " + mezzo.getTarga());
		
		//Gestisco la comboBox
		DefaultComboBoxModel modelloMezzo = new DefaultComboBoxModel(mezziCod.toArray());
		mezzoBox.setModel(modelloMezzo);
		
	}

	private void riempiOrdini() {
		ArrayList<String> codici = new ArrayList<String>(ordini.size());
		
		//Bado all'estetica
		for (Ordine ordine : ordini) 
			codici.add(ordine.getCodOrdine() + " (" + ordine.getStatoOrdine() + ")");
		
		//Gestisco la comboBox
		DefaultComboBoxModel modelloOrdini = new DefaultComboBoxModel(codici.toArray());
		ordineBox.setModel(modelloOrdini);
		
	}
	
	
	private void riempiCorriere() {
		ArrayList<String> codici = new ArrayList<String>(corrieri.size());
		
		//Bado all'estetica
		for (Corriere corriere : corrieri) 
			codici.add( "(" + corriere.getCodCorriere() +") " + corriere.getNome() + " " + corriere.getCognome());
		
		//Gestisco la comboBox
		DefaultComboBoxModel modelloCorrieri = new DefaultComboBoxModel(codici.toArray());
		corriereBox.setModel(modelloCorrieri);
		
	}
	

	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}
}
