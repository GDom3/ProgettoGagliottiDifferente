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
import javax.swing.JSlider;
import javax.swing.JProgressBar;


public class FinestraVisualizzaDatiFiltrabili extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel homePanel;
	private JButton menuB;
	private JPanel mostraDatiPanel;
	private JPanel filtroPanel;
	private JLabel logoSXImgL;
	private JLabel titoloSXL;
	private AppBrain Hal ;
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
	private JTable table;
	private JProgressBar filtraggioP;
	
	/**
	 * Create the frame.
	 * @param appBrain 
	 */
	public FinestraVisualizzaDatiFiltrabili(AppBrain appBrain) {
		Hal = appBrain;
		setForeground(new Color(119, 101, 101));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraVisualizzaDatiFiltrabili.class.getResource("/Img/Icon.png")));
		setTitle("UninaDelivery");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				//Diventa grande gradualmente
				menuB.setFont(new Font("Century", Font.PLAIN, 19));
				menuB.setFont(new Font("Century", Font.PLAIN, 20));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//Diventa Piccolo gradualmente
				menuB.setFont(new Font("Century", Font.PLAIN, 19));
				menuB.setFont(new Font("Century", Font.PLAIN, 18));
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
		filtroPanel.setBounds(494, 35, 211, 433);
		mostraDatiPanel.add(filtroPanel);
		filtroPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		filtroPanel.setBackground(new Color(179, 168, 166));
		filtroPanel.setLayout(null);
		
		utenteTF = new JTextField();
		utenteTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(utenteTF.getText().equals("Utente")) //Se è utente si deve levare
					utenteTF.setText("");
				
			}
		});
		utenteTF.setText("Utente");
		utenteTF.setHorizontalAlignment(SwingConstants.LEFT);
		utenteTF.setBounds(20, 107, 181, 31);
		utenteTF.setToolTipText("Qui inserire il proprio username");
		utenteTF.setSelectedTextColor(Color.WHITE);
		utenteTF.setForeground(new Color(0, 0, 0));
		utenteTF.setFont(new Font("Century", Font.PLAIN, 18));
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
		tempoInizioDC.setLocation(20, 220);
		tempoInizioDC.setSize(177, 31);
		filtroPanel.add(tempoInizioDC);
		
		tempoInizioL = new JLabel("Da");
		tempoInizioL.setForeground(Color.WHITE);
		tempoInizioL.setFont(new Font("Century", Font.BOLD, 18));
		tempoInizioL.setBounds(20, 190, 94, 31);
		filtroPanel.add(tempoInizioL);
		
		tempoFineL = new JLabel("A");
		tempoFineL.setForeground(Color.WHITE);
		tempoFineL.setFont(new Font("Century", Font.BOLD, 18));
		tempoFineL.setBounds(20, 262, 80, 31);
		filtroPanel.add(tempoFineL);
		
		tempoFineDC = new JDateChooser();
		tempoFineDC.getCalendarButton().setFont(new Font("Century", Font.PLAIN, 18));
		tempoFineDC.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tempoFineDC.getCalendarButton().setToolTipText("qui devi inserire la data di fine con cui filtrare");
		tempoFineDC.setFont(new Font("Century", Font.PLAIN, 18));
		tempoFineDC.setBorder(new LineBorder(new Color(119, 101, 101), 2, true));
		tempoFineDC.setBackground(new Color(179, 168, 166));
		tempoFineDC.setBounds(20, 293, 177, 31);
		filtroPanel.add(tempoFineDC);
		
		imgFiltriL = new JLabel("");
		imgFiltriL.setBounds(168, 11, 33, 37);
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
				FiltraB.setBackground(new Color(254, 114, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				FiltraB.setBackground(new Color(254, 126, 115));
			}
		});
		FiltraB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FiltraB.setBounds(65, 357, 80, 37);
		filtroPanel.add(FiltraB);
		FiltraB.setToolTipText("premi per accedere");
		FiltraB.setForeground(Color.WHITE);
		FiltraB.setFont(new Font("Century", Font.BOLD, 20));
		FiltraB.setFocusPainted(false);
		FiltraB.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		FiltraB.setBackground(new Color(254, 126, 115));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setBounds(10, 35, 474, 433);
		mostraDatiPanel.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setToolTipText("Tabella contenente gli ordini avuti");
		table.setName("Ordini");
		table.setForeground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Codice Fiscale", "Codice Ordine", "N\u00B0 Merci", "Totale \u20AC", "Codice Spedizione"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Float.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(77);
		table.getColumnModel().getColumn(0).setMinWidth(77);
		table.getColumnModel().getColumn(1).setPreferredWidth(77);
		table.getColumnModel().getColumn(1).setMinWidth(77);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setMinWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(96);
		table.getColumnModel().getColumn(4).setMinWidth(96);
		table.setBorder(new LineBorder(new Color(119, 101, 101), 2, true));
		table.setBackground(new Color(119, 101, 101));
		table.setFont(new Font("Century", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		
		filtraggioP = new JProgressBar();
		filtraggioP.setValue(0);
		filtraggioP.setBounds(10, 488, 695, 25);
		mostraDatiPanel.add(filtraggioP);

		appBrain.visualizzaTutti();
		
	}
	
	
	protected void azionaFiltri(String utente, Date inizio, Date fine) {
		setValoreProgresso(0);
		try {
			controlloInputFilitri(utente,inizio,fine);
			setValoreProgresso(10);
		} catch (DateCronologicamenteSbagliateException e1) {
			
			messaggioPopUp(e1.getMessaggioErrore(), e1.getNomeErrore());
			return;
		
		} catch (FiltriVuotiException e1) {
			
			messaggioPopUp(e1.getMessaggioErrore(), e1.getNomeErrore());
			return;
		}
		
		
		if(dataInizio != null)
			if(cliente != null)
				Hal.filtraPerUtenteData(cliente,dataInizio,dataFine);
			else
				Hal.filtraPerDate(dataInizio,dataFine);
		
		else 
			Hal.filtraPerCliente(cliente);
		
		setValoreProgresso(100);
	}



	protected void setValoreProgresso(int vaule) {
		filtraggioP.setValue(vaule); 
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
				if(dataInizio.isAfter(dataFine))
					throw new DateCronologicamenteSbagliateException(this);
				
			}else {
				output = JOptionPane.showConfirmDialog(this, "Vuoi filtrare solo con il cliente?", "Conferma filtro",0 ,JOptionPane.YES_NO_OPTION);
				if(output != 0)
					throw new FiltriVuotiException(this);
					
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
			Hal.ritornaMenu(this);
		
	}
}
