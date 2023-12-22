package UniDy.UninaDelivery;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;


public class FinestraVisualizzaDatiFiltrabili extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel homePanel;
	private JButton menuB;
	private JPanel mostraDatiPanel;
	private JPanel filtroPanel;
	private JLabel logoSXImgL;
	private JLabel titoloSXL;
	private AppBrain gestoreApplicazione ;
	private JTextField utenteTF;
	private JLabel utenteL;
	private JPanel intestazionePanel;
	private JLabel tempoL;
	private JDateChooser tempoInizioDC;
	private JLabel tempoInizioL;
	private JLabel tempoFineL;
	private JDateChooser tempoFineDC;
	private JLabel imgFiltriL;
	private JButton FiltraB;
	private LocalDate dataInizio = null;
	private LocalDate dataFine = null;
	private String cliente = null;
	private DefaultTableModel modelloTabella;
	private JTable tabellaOrdini;
	private JRadioButton sceltaDataConsegna;
	private JRadioButton sceltaDataEsecuzione;
	private ButtonGroup gruppoRadioDate;
	private Color arancioneScuro = new Color(254, 114, 92);
	private Color arancioneChiaro = new Color(254, 126, 115);
	
	public FinestraVisualizzaDatiFiltrabili(AppBrain appBrain) {
		gestoreApplicazione = appBrain;
		setForeground(new Color(119, 101, 101));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraVisualizzaDatiFiltrabili.class.getResource("/Img/Icon.png")));
		setTitle("UninaDelivery");
		setResizable(false);
		setDefaultCloseOperation(appBrain.exit());
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(119, 101, 101));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		homePanel = new JPanel();
		homePanel.setBackground(new Color(119, 101, 101));
		homePanel.setBounds(69, 0, 715, 37);
		contentPane.add(homePanel);
		
		menuB = new JButton("Home");
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
		
		mostraDatiPanel = new JPanel();
		mostraDatiPanel.setBackground(new Color(119, 101, 101));
		mostraDatiPanel.setBounds(69, 37, 715, 524);
		contentPane.add(mostraDatiPanel);
		mostraDatiPanel.setLayout(null);
		
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
		utenteTF.setText("Utente");
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
		
		utenteL = new JLabel("Utente");
		utenteL.setFont(new Font("Century", Font.BOLD, 20));
		utenteL.setForeground(new Color(255, 255, 255));
		utenteL.setBounds(10, 73, 94, 31);
		filtroPanel.add(utenteL);
		
		tempoL = new JLabel("Tempo");
		tempoL.setForeground(Color.WHITE);
		tempoL.setFont(new Font("Century", Font.BOLD, 20));
		tempoL.setBounds(10, 160, 94, 31);
		filtroPanel.add(tempoL);
		
		intestazionePanel = new JPanel();
		intestazionePanel.setBackground(new Color(239, 235, 229));
		intestazionePanel.setBounds(0, 0, 71, 561);
		contentPane.add(intestazionePanel);
		intestazionePanel.setLayout(null);
		
		logoSXImgL = new JLabel("New label");
		logoSXImgL.setBackground(new Color(239, 235, 229));
		logoSXImgL.setIcon(new ImageIcon(FinestraVisualizzaDatiFiltrabili.class.getResource("/Img/LogoHSX.png")));
		logoSXImgL.setBounds(0, 0, 71, 65);
		intestazionePanel.add(logoSXImgL);
		
		titoloSXL = new JLabel("New label");
		titoloSXL.setIcon(new ImageIcon(FinestraVisualizzaDatiFiltrabili.class.getResource("/Img/SxTitoloImg.jpg")));
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloSXL.setBounds(10, 76, 45, 474);
		intestazionePanel.add(titoloSXL);
		
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
		
		tempoInizioL = new JLabel("Da");
		tempoInizioL.setForeground(Color.WHITE);
		tempoInizioL.setFont(new Font("Century", Font.BOLD, 18));
		tempoInizioL.setBounds(10, 190, 94, 31);
		filtroPanel.add(tempoInizioL);
		
		tempoFineL = new JLabel("A");
		tempoFineL.setForeground(Color.WHITE);
		tempoFineL.setFont(new Font("Century", Font.BOLD, 18));
		tempoFineL.setBounds(10, 251, 80, 31);
		filtroPanel.add(tempoFineL);
		
		tempoFineDC = new JDateChooser();
		tempoFineDC.getCalendarButton().setFont(new Font("Century", Font.PLAIN, 18));
		tempoFineDC.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tempoFineDC.getCalendarButton().setToolTipText("qui devi inserire la data di fine con cui filtrare");
		tempoFineDC.setFont(new Font("Century", Font.PLAIN, 18));
		tempoFineDC.setBorder(new LineBorder(new Color(119, 101, 101), 2, true));
		tempoFineDC.setBackground(new Color(179, 168, 166));
		tempoFineDC.setBounds(10, 282, 160, 24);
		filtroPanel.add(tempoFineDC);
		
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
		FiltraB.setToolTipText("premi per accedere");
		FiltraB.setForeground(Color.WHITE);
		FiltraB.setFont(new Font("Century", Font.BOLD, 20));
		FiltraB.setFocusPainted(false);
		FiltraB.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		FiltraB.setBackground(new Color(254, 126, 115));
		

		sceltaDataConsegna = new JRadioButton("Data di Consegna");
		sceltaDataConsegna.setToolTipText("seleziona questo se vuoi filtrare per date di cosegna");
		sceltaDataConsegna.setFocusPainted(false);
		sceltaDataConsegna.setForeground(new Color(255, 255, 255));
		sceltaDataConsegna.setContentAreaFilled(false);
		sceltaDataConsegna.setFont(new Font("Century", Font.PLAIN, 16));
		sceltaDataConsegna.setBounds(10, 313, 160, 24);
		
		
		sceltaDataEsecuzione = new JRadioButton("Data Esecuzione");
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
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setBounds(10, 35, 510, 433);
		mostraDatiPanel.add(scrollPane);
		
		tabellaOrdini = new JTable();
		tabellaOrdini.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		tabellaOrdini.setFont(new Font("Century", Font.PLAIN, 14));
		tabellaOrdini.setToolTipText("Tabella contenete gli ordini");
		tabellaOrdini.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabellaOrdini.setRowSelectionAllowed(false);
		modelloTabella = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"Codice Fiscale", "Codice Ordine", "N° Merci", "Totale €", "Codice Spedizione"
				}
			) {
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
						
				
		tabellaOrdini.setModel(modelloTabella);
		
		tabellaOrdini.getColumnModel().getColumn(0).setMinWidth(170);
		tabellaOrdini.getColumnModel().getColumn(0).setMaxWidth(170);
	
		tabellaOrdini.getColumnModel().getColumn(2).setMinWidth(52);
		tabellaOrdini.getColumnModel().getColumn(2).setMaxWidth(52);

		tabellaOrdini.getColumnModel().getColumn(4).setMinWidth(108);
		tabellaOrdini.getColumnModel().getColumn(4).setMaxWidth(130);
		
		ListSelectionModel modelloSelezione = tabellaOrdini.getSelectionModel();
        modelloSelezione.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        modelloSelezione.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verifica se l'evento di selezione è ancora in fase di regolazione
                if (!e.getValueIsAdjusting()) {
                    // Ottieni la riga e la colonna selezionate
                    int rigaSelezionata = tabellaOrdini.getSelectedRow();
                    int colonnaSelezionata = tabellaOrdini.getSelectedColumn();
  
                    // Puoi quindi utilizzare questi valori per fare ulteriori operazioni
                    System.out.println("Riga selezionata: " + rigaSelezionata);
                    System.out.println("Colonna selezionata: " + colonnaSelezionata);
                }
            }
        });
		
		
		scrollPane.setViewportView(tabellaOrdini);	
		
		
	}
	
	/* DA SPERIMENTARE
	private void mostraOptionPane(int vaule) {
        // Creazione di un array di opzioni per il JComboBox
        String[] opzioni = {"Conclusa","Partita","Preso In Carico","Ritardo"};

        // Creazione di un pannello contenente un JComboBox
        JPanel panel = new JPanel();
        JComboBox<String> comboBox = new JComboBox<>(opzioni);
        panel.add(comboBox);

        // Creazione del JOptionPane con un pannello personalizzato (contenente il JComboBox)
        int result =  JOptionPane.showOptionDialog(this,"Scegli Stato Spedizione","Stato Spedizione " + vaule,JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,opzioni,null);
        
        /*int result = JOptionPane.showOptionDialog(
                this,
                panel,
                "Seleziona un'opzione",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null);
	
        // Verifica se l'utente ha premuto "OK"
        if (result == JOptionPane.OK_OPTION) {
            // Ottieni l'opzione selezionata
            String opzioneSelezionata = (String) comboBox.getSelectedItem();
            System.out.println("Opzione selezionata: " + opzioneSelezionata);
        }
    }
	
	*/

	protected void schiarisciBottoneFiltraggio() {
		FiltraB.setBackground(arancioneChiaro);
	}

	protected void scurisciBottoneFiltraggio() {
		FiltraB.setBackground(arancioneScuro);
	}

	protected void autoDeleteUtente() {
		//Se è utente si deve levare
		if(utenteTF.getText().equals("Utente")) 
			utenteTF.setText("");
		
	}



	protected void rimpicciolisciGradualmenteBottoneMenu() {
		//Diventa Piccolo gradualmente
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		
	}



	protected void ingradisciGradualmenteBottoneMenu() {
		//Diventa grande gradualmente
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 20));
	}



	protected void aggiungiTupla(String codiceFiscale, String codiceOrdine, int numeroTotaleMerci, Object costoTotaleEuro, Object codiceSpedizione) {
		modelloTabella.addRow (new Object [] { codiceFiscale, codiceOrdine,numeroTotaleMerci,costoTotaleEuro,codiceSpedizione});
		
	}
	
	protected void svuotaTabella() {
		
		int NumRighe = tabellaOrdini.getRowCount();
	
		if(NumRighe > 0)
			for(int i = 0; i < NumRighe;i++) 
				modelloTabella.removeRow(0);	
		
	}
	
	protected boolean IsTabellaVuota() {
		return tabellaOrdini.getRowCount() == 0;
		
	}
	
	protected void azionaFiltri(String utente, Date inizio, Date fine) {
		try {
			controlloInputFilitri(utente,inizio,fine);
		} catch (DateCronologicamenteSbagliateException e1) {
			
			messaggioPopUp(e1.getMessaggioErrore(), e1.getNomeErrore());
			return;
		
		} catch (FiltriVuotiException e1) {
			
			messaggioPopUp(e1.getMessaggioErrore(), e1.getNomeErrore());
			return;
		}
		
		
		try {
			if(dataInizio != null)
				if(cliente != null)
					gestoreApplicazione.filtra(cliente,dataInizio,dataFine);
				else
					gestoreApplicazione.filtra(dataInizio,dataFine);
			else 
				gestoreApplicazione.filtra(cliente);
				
		
		} catch (CreazioneStatementFallitaException e) {
			messaggioPopUp(e.getMessaggioErrore(), e.getTipoErrore());
		} catch (ConnessionNonRiuscitaException e) {
			messaggioPopUp(e.getMessaggioErrore(), e.getTipoErrore());
		} catch (RisultatoNonRicavabileException e) {
			messaggioPopUp(e.getMessaggioErrore(), e.getTipoErrore());
		} catch (DatiTrovatiDopoIlFiltraggioVuotiException e) {
			svuotaTabella();
			messaggioPopUp(e.getMessaggioErrore(), e.getTipoErrore());
		}
	}


	protected boolean IsDataEsecuzioneSelezionato(){
		return (sceltaDataEsecuzione.isSelected());
	}

	
	protected void controlloInputFilitri(String utente, Date inizio, Date fine) throws DateCronologicamenteSbagliateException, FiltriVuotiException {
		int output;
		if(utente.equals("Utente") || utente.isBlank()) {
			cliente = null;
			if(inizio == null || fine == null)
				throw new FiltriVuotiException(this);
			else{
				dataFine = LocalDate.ofInstant(fine.toInstant(), ZoneId.systemDefault());
				dataInizio = LocalDate.ofInstant(inizio.toInstant(), ZoneId.systemDefault());
				if(dataInizio.isAfter(dataFine)) 
					throw new DateCronologicamenteSbagliateException(this);
				output = JOptionPane.showConfirmDialog(this, "Vuoi filtrare solo con le date?", "Conferma filtro",0 ,JOptionPane.YES_NO_OPTION);
				if(output != 0)
					throw new FiltriVuotiException(this);
				
					
			}
		}else
			if(inizio != null && fine != null) {
				dataFine = LocalDate.ofInstant(fine.toInstant(), ZoneId.systemDefault());
				dataInizio = LocalDate.ofInstant(inizio.toInstant(), ZoneId.systemDefault());
				cliente = utente;
				if(dataInizio.isAfter(dataFine))
					throw new DateCronologicamenteSbagliateException(this);
				
			}else {
				output = JOptionPane.showConfirmDialog(this, "Vuoi filtrare solo con il cliente?", "Conferma filtro",0 ,JOptionPane.YES_NO_OPTION);
				if(output != 0)
					throw new FiltriVuotiException(this);
				else
					cliente = utente;
			}
	}

	
	protected void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}


	protected void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}


	protected void setCliente(String cliente) {
		this.cliente = cliente;
	}


	private void messaggioPopUp(String testo, String titolo) {
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
		int output = JOptionPane.showConfirmDialog(this, "Vuoi vedere tutti gli ordini?", "Ritorna al menu",0 ,JOptionPane.YES_NO_OPTION);
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
