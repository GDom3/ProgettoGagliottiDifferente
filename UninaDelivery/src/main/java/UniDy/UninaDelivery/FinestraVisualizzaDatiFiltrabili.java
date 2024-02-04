package UniDy.UninaDelivery;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;


public class FinestraVisualizzaDatiFiltrabili extends JFrame {

	private static final long serialVersionUID = 1L;
	//Amministratore
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JButton menuB;
	private JTextField utenteTF;
	private JButton FiltraB;
	private JRadioButton sceltaDataConsegna;
	private JRadioButton sceltaDataEsecuzione;
	private ButtonGroup gruppoRadioDate;
	private Color arancioneScuro = new Color(254, 114, 92);
	private Color arancioneChiaro = new Color(254, 126, 115);
	//Tabella
	private DefaultTableModel modelloTabella;
	private JTable tabellaSpedizioni;	
	//oggetti reali
	private LocalDate dataInizio = null;
	private LocalDate dataFine = null;
	private String cliente = null;


	public FinestraVisualizzaDatiFiltrabili(AppBrain appBrain) {
		gestoreApplicazione = appBrain;
		setForeground(new Color(119, 101, 101));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraVisualizzaDatiFiltrabili.class.getResource("/Img/Icon.png")));
		setTitle("UninaDelivery");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane;
		contentPane = new JPanel();
		contentPane.setForeground(new Color(119, 101, 101));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel homePanel;
		homePanel = new JPanel();
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
		homePanel.setLayout(null);
		menuB.setHorizontalTextPosition(SwingConstants.CENTER);
		menuB.setForeground(Color.WHITE);
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		menuB.setFocusPainted(false);
		menuB.setContentAreaFilled(false);
		menuB.setBorderPainted(false);
		menuB.setBorder(null);
		menuB.setBackground(new Color(119, 101, 101));
		homePanel.add(menuB);
		
		JPanel mostraDatiPanel;
		mostraDatiPanel = new JPanel();
		mostraDatiPanel.setBackground(new Color(119, 101, 101));
		mostraDatiPanel.setBounds(69, 37, 715, 524);
		contentPane.add(mostraDatiPanel);
		mostraDatiPanel.setLayout(null);
		
		JPanel filtroPanel;
		filtroPanel = new JPanel();
		filtroPanel.setBounds(530, 35, 175, 433);
		mostraDatiPanel.add(filtroPanel);
		filtroPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		filtroPanel.setBackground(new Color(179, 168, 166));
		filtroPanel.setLayout(null);
		
		utenteTF = new JTextField();
		utenteTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDeleteUtente();
			}
		});
		utenteTF.setText("");
		utenteTF.setHorizontalAlignment(SwingConstants.LEFT);
		utenteTF.setBounds(10, 106, 160, 24);
		utenteTF.setToolTipText("Qui inserire il proprio username");
		utenteTF.setSelectedTextColor(Color.WHITE);
		utenteTF.setForeground(new Color(0, 0, 0));
		utenteTF.setFont(new Font("Century", Font.PLAIN, 16));
		utenteTF.setColumns(10);
		utenteTF.setCaretColor(Color.BLACK);
		utenteTF.setBorder(new LineBorder(new Color(119, 101, 101), 2, true));
		utenteTF.setBackground(new Color(255, 255, 255));
		filtroPanel.add(utenteTF);
		
		JLabel titoloFiltriL = new JLabel("Filtri");
		titoloFiltriL.setFont(new Font("Century", Font.BOLD, 30));
		titoloFiltriL.setForeground(new Color(255, 255, 255));
		titoloFiltriL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloFiltriL.setBounds(10, 11, 136, 37);
		filtroPanel.add(titoloFiltriL);
		

		JLabel utenteL;
		utenteL = new JLabel("Utente");
		utenteL.setFont(new Font("Century", Font.BOLD, 20));
		utenteL.setForeground(new Color(255, 255, 255));
		utenteL.setBounds(10, 73, 94, 31);
		filtroPanel.add(utenteL);
		
		JLabel tempoL;
		tempoL = new JLabel("Tempo");
		tempoL.setForeground(Color.WHITE);
		tempoL.setFont(new Font("Century", Font.BOLD, 20));
		tempoL.setBounds(10, 160, 94, 31);
		filtroPanel.add(tempoL);
		
		JPanel intestazionePanel;
		intestazionePanel = new JPanel();
		intestazionePanel.setBackground(new Color(239, 235, 229));
		intestazionePanel.setBounds(0, 0, 71, 561);
		contentPane.add(intestazionePanel);
		intestazionePanel.setLayout(null);
		
		JLabel logoSXImgL;
		logoSXImgL = new JLabel("New label");
		logoSXImgL.setBackground(new Color(239, 235, 229));
		logoSXImgL.setIcon(new ImageIcon(FinestraVisualizzaDatiFiltrabili.class.getResource("/Img/LogoHSX.png")));
		logoSXImgL.setBounds(0, 0, 71, 65);
		intestazionePanel.add(logoSXImgL);
		
		JLabel titoloSXL;
		titoloSXL = new JLabel("New label");
		titoloSXL.setIcon(new ImageIcon(FinestraVisualizzaDatiFiltrabili.class.getResource("/Img/SxTitoloImg.jpg")));
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloSXL.setBounds(10, 76, 45, 474);
		intestazionePanel.add(titoloSXL);

		JDateChooser tempoInizioDC;
		tempoInizioDC = new JDateChooser();
		tempoInizioDC.getCalendarButton().setFont(new Font("Century", Font.PLAIN, 18));
		tempoInizioDC.getCalendarButton().setBackground(new Color(255, 255, 255));
		tempoInizioDC.setToolTipText("qui devi inserire la data di inizio con cui filtrare");
		tempoInizioDC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tempoInizioDC.setBorder(new LineBorder(new Color(119, 101, 101), 2, true));
		tempoInizioDC.setFont(new Font("Century", Font.PLAIN, 18));
		tempoInizioDC.setBackground(new Color(179, 168, 166));
		tempoInizioDC.setLocation(10, 221);
		tempoInizioDC.setSize(160, 24);
		filtroPanel.add(tempoInizioDC);
		
		JLabel tempoInizioL;
		tempoInizioL = new JLabel("Da");
		tempoInizioL.setForeground(Color.WHITE);
		tempoInizioL.setFont(new Font("Century", Font.BOLD, 18));
		tempoInizioL.setBounds(10, 190, 94, 31);
		filtroPanel.add(tempoInizioL);
		
		JLabel tempoFineL;
		tempoFineL = new JLabel("A");
		tempoFineL.setForeground(Color.WHITE);
		tempoFineL.setFont(new Font("Century", Font.BOLD, 18));
		tempoFineL.setBounds(10, 251, 80, 31);
		filtroPanel.add(tempoFineL);
		
		JDateChooser tempoFineDC;
		tempoFineDC = new JDateChooser();
		tempoFineDC.getCalendarButton().setFont(new Font("Century", Font.PLAIN, 18));
		tempoFineDC.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tempoFineDC.getCalendarButton().setToolTipText("qui devi inserire la data di fine con cui filtrare");
		tempoFineDC.setFont(new Font("Century", Font.PLAIN, 18));
		tempoFineDC.setBorder(new LineBorder(new Color(119, 101, 101), 2, true));
		tempoFineDC.setBackground(new Color(179, 168, 166));
		tempoFineDC.setBounds(10, 282, 160, 24);
		filtroPanel.add(tempoFineDC);
		
		JLabel imgFiltriL;
		imgFiltriL = new JLabel("");
		imgFiltriL.setBounds(132, 11, 33, 37);
		filtroPanel.add(imgFiltriL);
		imgFiltriL.setRequestFocusEnabled(false);
		imgFiltriL.setBackground(new Color(179, 168, 166));
		imgFiltriL.setHorizontalTextPosition(SwingConstants.RIGHT);
		imgFiltriL.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		imgFiltriL.setIcon(new ImageIcon(FinestraVisualizzaDatiFiltrabili.class.getResource("/Img/FiltraImg.jpg")));
		
	
		
		FiltraB = new JButton("Filtra");
		FiltraB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String utente = utenteTF.getText();
				Date inizio = tempoInizioDC.getDate();
				Date fine = tempoFineDC.getDate();
					
				azionaFiltri(utente,inizio,fine);
	
			}
		});
		
		FiltraB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				scurisciBottoneFiltraggio();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				schiarisciBottoneFiltraggio();
				
			}
		});
		FiltraB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FiltraB.setBounds(46, 385, 80, 37);
		filtroPanel.add(FiltraB);
		FiltraB.setToolTipText("premi per filtrare i dati");
		FiltraB.setForeground(Color.WHITE);
		FiltraB.setFont(new Font("Century", Font.BOLD, 20));
		FiltraB.setFocusPainted(false);
		FiltraB.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		FiltraB.setBackground(new Color(254, 126, 115));
		

		sceltaDataConsegna = new JRadioButton("Data di Consegna");
		sceltaDataConsegna.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sceltaDataConsegna.setToolTipText("seleziona questo se vuoi filtrare per date di cosegna");
		sceltaDataConsegna.setFocusPainted(false);
		sceltaDataConsegna.setForeground(new Color(255, 255, 255));
		sceltaDataConsegna.setContentAreaFilled(false);
		sceltaDataConsegna.setFont(new Font("Century", Font.PLAIN, 16));
		sceltaDataConsegna.setBounds(10, 313, 160, 24);
		
		
		sceltaDataEsecuzione = new JRadioButton("Data Esecuzione");
		sceltaDataEsecuzione.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sceltaDataEsecuzione.setToolTipText("seleziona qui se vuoi filtrare per data di esecuzione");
		sceltaDataEsecuzione.setFocusPainted(false);
		sceltaDataEsecuzione.setSelected(true);
		sceltaDataEsecuzione.setForeground(Color.WHITE);
		sceltaDataEsecuzione.setFont(new Font("Century", Font.PLAIN, 16));
		sceltaDataEsecuzione.setContentAreaFilled(false);
		sceltaDataEsecuzione.setBounds(10, 340, 160, 24);
		
		gruppoRadioDate = new ButtonGroup();    
		gruppoRadioDate.add(sceltaDataConsegna);
		gruppoRadioDate.add(sceltaDataEsecuzione);
		
		
		filtroPanel.add(sceltaDataConsegna);
		filtroPanel.add(sceltaDataEsecuzione);
		
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(179, 168, 166));
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setBounds(10, 35, 510, 433);
		
		mostraDatiPanel.add(scrollPane);
		
		
		tabellaSpedizioni = new JTable();
		tabellaSpedizioni.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		tabellaSpedizioni.setFont(new Font("Century", Font.PLAIN, 14));
		tabellaSpedizioni.setToolTipText("Tabella contenete gli ordini");
		tabellaSpedizioni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabellaSpedizioni.setRowSelectionAllowed(false);
		modelloTabella = new DefaultTableModel(
				
				new Object[][] {},
				
				new String[] {"Codice Fiscale", "Codice Ordine", "N° Merci", "Totale €", "Codice Spedizione"}
		){
			
			private static final long serialVersionUID = 1L;
				
				
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Float.class, String.class
			};
			
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
				
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
				
			public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
			}
		};
					
				
		tabellaSpedizioni.setModel(modelloTabella);
		
		tabellaSpedizioni.getColumnModel().getColumn(0).setMinWidth(170);
		tabellaSpedizioni.getColumnModel().getColumn(0).setMaxWidth(170);
	
		tabellaSpedizioni.getColumnModel().getColumn(2).setMinWidth(52);
		tabellaSpedizioni.getColumnModel().getColumn(2).setMaxWidth(52);

		tabellaSpedizioni.getColumnModel().getColumn(4).setMinWidth(108);
		tabellaSpedizioni.getColumnModel().getColumn(4).setMaxWidth(130);
		
		tabellaSpedizioni.getTableHeader().setReorderingAllowed(false);
	
		ListSelectionModel modelloSelezione = tabellaSpedizioni.getSelectionModel();
        modelloSelezione.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tabellaSpedizioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rigaSelezionata = tabellaSpedizioni.getSelectedRow();
                int colonnaSelezionata = tabellaSpedizioni.getSelectedColumn();
                
               
                if(colonnaSelezionata == 1) {
          	
                	try {
						gestoreApplicazione.modificaStatoOrdine(tabellaSpedizioni.getValueAt(rigaSelezionata, colonnaSelezionata));
						
					} catch (CreazioneStatementFallitaException e1) {
						messaggioPopUp(e1.getMessaggioErrore(), e1.getTipoErrore());
					} catch (ConnessionNonRiuscitaException e1) {
						messaggioPopUp(e1.getMessaggioErrore(), e1.getTipoErrore());
					} catch (RisultatoNonRicavabileException e1) {
						messaggioPopUp(e1.getMessaggioErrore(), e1.getTipoErrore());
					}
                	
                	
                } else if(colonnaSelezionata == 4) {
                	
                	try {
						gestoreApplicazione.modificaStatoSpedizione(tabellaSpedizioni.getValueAt(rigaSelezionata, colonnaSelezionata));
					} catch (CreazioneStatementFallitaException e1) {
						messaggioPopUp(e1.getMessaggioErrore(), e1.getTipoErrore());
					} catch (ConnessionNonRiuscitaException e1) {
						messaggioPopUp(e1.getMessaggioErrore(), e1.getTipoErrore());
					} catch (RisultatoNonRicavabileException e1) {
						messaggioPopUp(e1.getMessaggioErrore(), e1.getTipoErrore());
					}
             
                }
			}
		});
     
		
		
		scrollPane.setViewportView(tabellaSpedizioni);	
		
		
	}
	

	private void schiarisciBottoneFiltraggio() {
		FiltraB.setBackground(arancioneChiaro);
	}

	private void scurisciBottoneFiltraggio() {
		FiltraB.setBackground(arancioneScuro);
	}

	private void autoDeleteUtente() {
		//Se è utente si deve levare
		if(utenteTF.getText().equals("Utente")) 
			utenteTF.setText("");
		
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



	protected void aggiungiTupla(String codiceFiscale, String codiceOrdine, int numeroTotaleMerci, Object costoTotaleEuro, Object codiceSpedizione) {
		modelloTabella.addRow (new Object [] { codiceFiscale, codiceOrdine,numeroTotaleMerci,costoTotaleEuro,codiceSpedizione});
		
	}
	
	protected void svuotaTabella() {
		//prendo il numero di elementi
		int NumRighe = tabellaSpedizioni.getRowCount();
		
		//Elimino tante righe quante ne ha
		if(NumRighe > 0)
			for(int i = 0; i < NumRighe;i++) 
				modelloTabella.removeRow(0);	
	
	}
	
	protected boolean IsTabellaVuota() {
		return tabellaSpedizioni.getRowCount() == 0;
		
	}
	
	
	private void azionaFiltri(String utente, Date inizio, Date fine) {
		try {
			//Controllo i dati d'input
			controlloInputFilitri(utente,inizio,fine);
			
			//Filtro in base hai dati dati
			if(dataInizio != null)
				if(cliente != null)
					gestoreApplicazione.filtraConTutto(cliente,dataInizio,dataFine);
				else
					gestoreApplicazione.filtraSoloData(dataInizio,dataFine);
			else 
				gestoreApplicazione.filtraSoloCliente(cliente);
		
		} catch (DatiTrovatiDopoIlFiltraggioVuotiException e) {
			svuotaTabella();
			messaggioPopUp(e.getMessaggioErrore(), e.getTipoErrore());
		} catch (UninaDeliveryException Errore) {
			messaggioPopUp(Errore.getMessaggioErrore(),Errore.getTipoErrore());
		} catch (UninaDeliverySQLException ErroreSQL) {
			messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());	
		}
		
		//Azzera
		cliente = null ;
		dataInizio = null;
		dataFine = null ;
	}


	protected boolean IsDataEsecuzioneSelezionato(){
		return (sceltaDataEsecuzione.isSelected());
	}

	
	private LocalDate DateToLocalDate(Date data) {
		return LocalDate.ofInstant(data.toInstant(), ZoneId.systemDefault());
	}
	
	private void controlloInputFilitri(String utente, Date inizio, Date fine) throws DateCronologicamenteSbagliateException, FiltriVuotiException, DateVuoteException {
		int output;
		//Senza Utente
		if(utente.isEmpty() || utente.isBlank()) {
			cliente = null;
			//Non Ho nulla
			if(inizio == null || fine == null)
				throw new FiltriVuotiException();
			//Ho solo le date
			else{
				//Le convero in LocalDate
				dataFine = DateToLocalDate(fine);
				dataInizio = DateToLocalDate(inizio);
				
				//Verifico la corettezza temporale
				if(dataInizio.isAfter(dataFine)) 
					throw new DateCronologicamenteSbagliateException();
			
				//Chiedo Di procedere
				output = JOptionPane.showConfirmDialog(this, "Vuoi filtrare solo con le date?", "Conferma filtro",0 ,JOptionPane.YES_NO_OPTION);
				if(output != 0)
					throw new FiltriVuotiException();		
			}
		}else
			//Ho tutto
			if(inizio != null && fine != null) {
				dataFine = DateToLocalDate(fine);
				dataInizio = DateToLocalDate(inizio);
				cliente = utente;
				
				//Verifico la corettezza temporale
				if(dataInizio.isAfter(dataFine))
					throw new DateCronologicamenteSbagliateException();
				
			}else {
				//Ho solo il Cliente
				output = JOptionPane.showConfirmDialog(this, "Vuoi filtrare solo con il cliente?", "Conferma filtro",0 ,JOptionPane.YES_NO_OPTION);
				if(output != 0)
					throw new DateVuoteException();
				else
					cliente = utente;
			}
	}

	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}


	private void confermaRitornareMenu() {
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di ritornare al menu?", "Ritorna al menu",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.ritornaMenu(this);
		
	}


	protected void richiestaVisualizzareTutti() {
		int output = JOptionPane.showConfirmDialog(this, "Vuoi vedere tutti gli ordini attivi?", "Ritorna al menu",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			try {
				gestoreApplicazione.filtra();
			} catch (CreazioneStatementFallitaException e) {
				messaggioPopUp(e.getMessaggioErrore(), e.getTipoErrore());
			} catch (ConnessionNonRiuscitaException e) {
				messaggioPopUp(e.getMessaggioErrore(), e.getTipoErrore());
			} catch (RisultatoNonRicavabileException e) {
				messaggioPopUp(e.getMessaggioErrore(), e.getTipoErrore());
			} catch (DatiTrovatiDopoIlFiltraggioVuotiException e) {
				messaggioPopUp(e.getMessaggioErrore(), e.getTipoErrore());
			}
		else
			svuotaTabella();
	}
}
