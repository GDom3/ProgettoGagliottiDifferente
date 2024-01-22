package UniDy.UninaDelivery;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FinestraInserimentoCorriere extends JFrame {

	private static final long serialVersionUID = 1L;
	//Amministratorre
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JPanel contentPane;
	private JDateChooser dataNascitaDataChoser;
	private JButton indietroBottone ; 
	private JTextField codiceFiscaleTxf;
	private JTextField cognomeTxf;
	private JTextField nomeTxf;
	private JTextField emailTxf;
	private JTextField numeroCellulareTxf;
	private JTextField patentiTxf;
	private LocalDate dataNascita;
	private JScrollPane scrollPane;
	//Gestione ComboBox
	private  JComboBox capoBox;
	//Oggetti reali
	//private ArrayList<Corriere> corrieri;
	
	
	
	public FinestraInserimentoCorriere(AppBrain appBrain) {
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("UninaDelivery");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraNuovaSpedizione.class.getResource("/Img/Icon.png")));
		gestoreApplicazione = appBrain;
		setDefaultCloseOperation(appBrain.exit());
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 101, 101));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel intestazionePanel = new JPanel();
		intestazionePanel.setBackground(new Color(239, 235, 229));
		intestazionePanel.setBounds(0, 0, 71, 561);
		contentPane.add(intestazionePanel);
		intestazionePanel.setLayout(null);
		
		JLabel logoSXImgL = new JLabel("New label");
		logoSXImgL.setBackground(new Color(239, 235, 229));
		logoSXImgL.setIcon(new ImageIcon(FinestraVisualizzaDatiFiltrabili.class.getResource("/Img/LogoHSX.png")));
		logoSXImgL.setBounds(0, 0, 71, 65);
		intestazionePanel.add(logoSXImgL);
		
		JLabel titoloSXL = new JLabel("New label");
		titoloSXL.setIcon(new ImageIcon(FinestraVisualizzaDatiFiltrabili.class.getResource("/Img/SxTitoloImg.jpg")));
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloSXL.setBounds(10, 76, 45, 474);
		intestazionePanel.add(titoloSXL);
		
		
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(119, 101, 101));
		homePanel.setBounds(69, 0, 715, 37);
		contentPane.add(homePanel);
		
		indietroBottone = new JButton("Indietro");
		indietroBottone.setToolTipText("premi per ritornare al menu");
		indietroBottone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		indietroBottone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confermaRitornareIndietro();
				resetCampi();
			}
		});
		indietroBottone.setBounds(612, 11, 93, 22);
		indietroBottone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneIndietro();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneIndietro();
				
			}
		});
		homePanel.setLayout(null);
		indietroBottone.setHorizontalTextPosition(SwingConstants.CENTER);
		indietroBottone.setForeground(Color.WHITE);
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 18));
		indietroBottone.setFocusPainted(false);
		indietroBottone.setContentAreaFilled(false);
		indietroBottone.setBorderPainted(false);
		indietroBottone.setBorder(null);
		indietroBottone.setBackground(new Color(119, 101, 101));
		homePanel.add(indietroBottone);
		
		JLabel titoloLabel = new JLabel("Registrazione nuovo corriere ");
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLabel.setForeground(Color.WHITE);
		titoloLabel.setFont(new Font("Century", Font.BOLD, 30));
		titoloLabel.setBounds(12, -1, 693, 37);
		homePanel.add(titoloLabel);
		
		JPanel parteSpecificheVeicoloPanel = new JPanel();
		parteSpecificheVeicoloPanel.setLayout(null);
		parteSpecificheVeicoloPanel.setBackground(new Color(119, 101, 101));
		parteSpecificheVeicoloPanel.setBounds(81, 48, 347, 397);
		contentPane.add(parteSpecificheVeicoloPanel);
		
		
		JLabel AnagraficaLabel = new JLabel("Anagrafica");
		AnagraficaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AnagraficaLabel.setForeground(Color.WHITE);
		AnagraficaLabel.setFont(new Font("Century", Font.PLAIN, 20));
		AnagraficaLabel.setBounds(10, 11, 327, 25);
		parteSpecificheVeicoloPanel.add(AnagraficaLabel);
		
		JPanel parteAmministrativaPanel = new JPanel();
		parteAmministrativaPanel.setLayout(null);
		parteAmministrativaPanel.setBackground(new Color(119, 101, 101));
		parteAmministrativaPanel.setBounds(427, 48, 347, 397);
		contentPane.add(parteAmministrativaPanel);
		
		JLabel parteAmministrativaLabel = new JLabel("Parte amministrativa");
		parteAmministrativaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parteAmministrativaLabel.setForeground(Color.WHITE);
		parteAmministrativaLabel.setFont(new Font("Century", Font.PLAIN, 20));
		parteAmministrativaLabel.setBounds(10, 11, 327, 25);
		parteAmministrativaPanel.add(parteAmministrativaLabel);
		
		JLabel immagineContrattoLabel = new JLabel("");
		immagineContrattoLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineContrattoLabel.setIcon(new ImageIcon(FinestraInserimentoCorriere.class.getResource("/Img/contractresize.png")));
		immagineContrattoLabel.setOpaque(true);
		immagineContrattoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineContrattoLabel.setBackground(new Color(179, 168, 166));
		immagineContrattoLabel.setBounds(55, 239, 49, 41);
		parteAmministrativaPanel.add(immagineContrattoLabel);
		
		JSpinner contrattoFild = new JSpinner();
		contrattoFild.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		contrattoFild.setToolTipText("Inserisci stipendio contratto");
		contrattoFild.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		contrattoFild.setForeground(Color.WHITE);
		contrattoFild.setFont(new Font("Century", Font.PLAIN, 20));
		contrattoFild.setBackground(new Color(179, 168, 166));
		contrattoFild.setBounds(104, 239, 200, 41);
		parteAmministrativaPanel.add(contrattoFild);
		
		JLabel lblContratto = new JLabel("Contratto");
		lblContratto.setHorizontalAlignment(SwingConstants.CENTER);
		lblContratto.setForeground(Color.WHITE);
		lblContratto.setFont(new Font("Century", Font.PLAIN, 20));
		lblContratto.setBounds(10, 203, 327, 25);
		parteAmministrativaPanel.add(lblContratto);
		
		patentiTxf = new JTextField();
		patentiTxf.setToolTipText("Inserisci patenti");
		patentiTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDeletePatente();
				vaiAvanti(patentiTxf,emailTxf,e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(patentiTxf.getText(),"Patenti",patentiTxf);
			}
		});
		patentiTxf.setText("Patenti");
		patentiTxf.setForeground(Color.WHITE);
		patentiTxf.setFont(new Font("Century", Font.PLAIN, 20));
		patentiTxf.setColumns(10);
		patentiTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		patentiTxf.setBackground(new Color(179, 168, 166));
		patentiTxf.setBounds(104, 47, 200, 41);
		parteAmministrativaPanel.add(patentiTxf);
		
		JLabel immagineEmailLabel = new JLabel("");
		immagineEmailLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineEmailLabel.setBounds(55, 99, 49, 41);
		parteAmministrativaPanel.add(immagineEmailLabel);
		immagineEmailLabel.setIcon(new ImageIcon(FinestraInserimentoCorriere.class.getResource("/Img/emailridimensionata.png")));
		immagineEmailLabel.setOpaque(true);
		immagineEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineEmailLabel.setBackground(new Color(179, 168, 166));
		
		emailTxf = new JTextField();
		emailTxf.setToolTipText("Inserisci E-mail");
		emailTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDeleteEmail();
				vaiAvanti(emailTxf,numeroCellulareTxf,e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(emailTxf.getText(),"E-mail",emailTxf);
			}
		});
		emailTxf.setBounds(104, 99, 200, 41);
		parteAmministrativaPanel.add(emailTxf);
		emailTxf.setText("E-mail");
		emailTxf.setForeground(Color.WHITE);
		emailTxf.setFont(new Font("Century", Font.PLAIN, 20));
		emailTxf.setColumns(10);
		emailTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		emailTxf.setBackground(new Color(179, 168, 166));
		
		JLabel immagineNumerotelefonoLabel = new JLabel("");
		immagineNumerotelefonoLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineNumerotelefonoLabel.setBounds(55, 151, 49, 41);
		parteAmministrativaPanel.add(immagineNumerotelefonoLabel);
		immagineNumerotelefonoLabel.setIcon(new ImageIcon(FinestraInserimentoCorriere.class.getResource("/Img/telefonoridimensionato.png")));
		immagineNumerotelefonoLabel.setOpaque(true);
		immagineNumerotelefonoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineNumerotelefonoLabel.setBackground(new Color(179, 168, 166));
		
		numeroCellulareTxf = new JTextField();
		numeroCellulareTxf.setToolTipText("Inserisci numero cellulare");
		numeroCellulareTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDeleteNumeroTelefono();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(numeroCellulareTxf.getText(),"Numero cellulare",numeroCellulareTxf);
			}
		});
		numeroCellulareTxf.setBounds(104, 151, 200, 41);
		parteAmministrativaPanel.add(numeroCellulareTxf);
		numeroCellulareTxf.setText("Numero cellulare");
		numeroCellulareTxf.setForeground(Color.WHITE);
		numeroCellulareTxf.setFont(new Font("Century", Font.PLAIN, 20));
		numeroCellulareTxf.setColumns(10);
		numeroCellulareTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		numeroCellulareTxf.setBackground(new Color(179, 168, 166));
		
		JLabel immagineAnniContributiLabel = new JLabel("");
		immagineAnniContributiLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineAnniContributiLabel.setIcon(new ImageIcon(FinestraInserimentoCorriere.class.getResource("/Img/level.png")));
		immagineAnniContributiLabel.setOpaque(true);
		immagineAnniContributiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineAnniContributiLabel.setBackground(new Color(179, 168, 166));
		immagineAnniContributiLabel.setBounds(55, 325, 49, 41);
		parteAmministrativaPanel.add(immagineAnniContributiLabel);
		
		JLabel immaginePatenteLabel = new JLabel("");
		immaginePatenteLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immaginePatenteLabel.setBounds(55, 47, 49, 41);
		parteAmministrativaPanel.add(immaginePatenteLabel);
		immaginePatenteLabel.setIcon(new ImageIcon(FinestraInserimentoCorriere.class.getResource("/Img/patentridimensionato.png")));
		immaginePatenteLabel.setOpaque(true);
		immaginePatenteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immaginePatenteLabel.setBackground(new Color(179, 168, 166));
		
		JSpinner anniContributiFild = new JSpinner();
		anniContributiFild.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		anniContributiFild.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		anniContributiFild.setToolTipText("Inserisci stipendio contratto");
		anniContributiFild.setForeground(Color.WHITE);
		anniContributiFild.setFont(new Font("Century", Font.PLAIN, 20));
		anniContributiFild.setBackground(new Color(179, 168, 166));
		anniContributiFild.setBounds(104, 325, 200, 41);
		parteAmministrativaPanel.add(anniContributiFild);
		
		JLabel anniContributiLabal = new JLabel("Anni Contributi");
		anniContributiLabal.setHorizontalAlignment(SwingConstants.CENTER);
		anniContributiLabal.setForeground(Color.WHITE);
		anniContributiLabal.setFont(new Font("Century", Font.PLAIN, 20));
		anniContributiLabal.setBounds(10, 291, 327, 25);
		parteAmministrativaPanel.add(anniContributiLabal);
		
		
		JLabel immagineCodiceFiscaleLabel = new JLabel("");
		immagineCodiceFiscaleLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineCodiceFiscaleLabel.setOpaque(true);
		parteSpecificheVeicoloPanel.add(immagineCodiceFiscaleLabel);
		immagineCodiceFiscaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCodiceFiscaleLabel.setIcon(new ImageIcon(FinestraInserimentoCorriere.class.getResource("/Img/personal-data resize.png")));
		immagineCodiceFiscaleLabel.setBounds(46, 47, 49, 41);
		immagineCodiceFiscaleLabel.setBackground(new Color(179, 168, 166));
		
		codiceFiscaleTxf = new JTextField();
		codiceFiscaleTxf.setToolTipText("Inserisci codice fiscale");
		codiceFiscaleTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDeleteCodiceFiscale();
				vaiAvanti(codiceFiscaleTxf,nomeTxf,e);
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(codiceFiscaleTxf.getText(),"Codice fiscale",codiceFiscaleTxf);	
			}
		});
		codiceFiscaleTxf.setText("Codice fiscale");
		codiceFiscaleTxf.setForeground(Color.WHITE);
		codiceFiscaleTxf.setFont(new Font("Century", Font.PLAIN, 20));
		codiceFiscaleTxf.setColumns(10);
		codiceFiscaleTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		codiceFiscaleTxf.setBackground(new Color(179, 168, 166));
		codiceFiscaleTxf.setBounds(95, 47, 200, 41);
		parteSpecificheVeicoloPanel.add(codiceFiscaleTxf);
		
		JLabel immagineCognomeLabel = new JLabel("");
		immagineCognomeLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineCognomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCognomeLabel.setIcon(new ImageIcon(FinestraInserimentoCorriere.class.getResource("/Img/cognomeridimensionata.png")));
		immagineCognomeLabel.setOpaque(true);
		immagineCognomeLabel.setBackground(new Color(179, 168, 166));
		immagineCognomeLabel.setBounds(46, 151, 49, 41);
		parteSpecificheVeicoloPanel.add(immagineCognomeLabel);
		
		cognomeTxf = new JTextField();
		cognomeTxf.setToolTipText("Inserisci cognome");
		cognomeTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDeleteCognome();
				vaiAvanti(cognomeTxf,patentiTxf,e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(cognomeTxf.getText(),"Cognome",cognomeTxf);
			}
		});
		cognomeTxf.setText("Cognome");
		cognomeTxf.setForeground(Color.WHITE);
		cognomeTxf.setFont(new Font("Century", Font.PLAIN, 20));
		cognomeTxf.setColumns(10);
		cognomeTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		cognomeTxf.setBackground(new Color(179, 168, 166));
		cognomeTxf.setBounds(95, 151, 200, 41);
		parteSpecificheVeicoloPanel.add(cognomeTxf);
		
		JLabel immagineNomeLabel = new JLabel("");
		immagineNomeLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineNomeLabel.setIcon(new ImageIcon(FinestraInserimentoCorriere.class.getResource("/Img/nameridimensionata.png")));
		immagineNomeLabel.setOpaque(true);
		immagineNomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineNomeLabel.setBackground(new Color(179, 168, 166));
		immagineNomeLabel.setBounds(46, 99, 49, 41);
		parteSpecificheVeicoloPanel.add(immagineNomeLabel);
		
		nomeTxf = new JTextField();
		nomeTxf.setToolTipText("Inserisci nome");
		nomeTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDeleteNome();
				vaiAvanti(nomeTxf,cognomeTxf,e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(nomeTxf.getText(),"Nome",nomeTxf);
			}
			
		});
		nomeTxf.setText("Nome");
		nomeTxf.setForeground(Color.WHITE);
		nomeTxf.setFont(new Font("Century", Font.PLAIN, 20));
		nomeTxf.setColumns(10);
		nomeTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		nomeTxf.setBackground(new Color(179, 168, 166));
		nomeTxf.setBounds(95, 99, 200, 41);
		parteSpecificheVeicoloPanel.add(nomeTxf);
		
		JLabel lblDataDiNascita = new JLabel("Data di nascita");
		lblDataDiNascita.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDiNascita.setForeground(Color.WHITE);
		lblDataDiNascita.setFont(new Font("Century", Font.PLAIN, 20));
		lblDataDiNascita.setBounds(10, 203, 322, 25);
		parteSpecificheVeicoloPanel.add(lblDataDiNascita);
		
		dataNascitaDataChoser = new JDateChooser();
		dataNascitaDataChoser.getCalendarButton().setFont(new Font("Century", Font.PLAIN, 18));
		dataNascitaDataChoser.getCalendarButton().setBackground(Color.WHITE);
		dataNascitaDataChoser.setToolTipText("Inserisci data di nascita");
		dataNascitaDataChoser.setFont(new Font("Century", Font.PLAIN, 18));
		dataNascitaDataChoser.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		dataNascitaDataChoser.setBackground(new Color(179, 168, 166));
		dataNascitaDataChoser.setBounds(95, 239, 200, 41);
		parteSpecificheVeicoloPanel.add(dataNascitaDataChoser);
		
		JLabel immagineDataNascitaLabel = new JLabel("");
		immagineDataNascitaLabel.setIcon(new ImageIcon(FinestraInserimentoCorriere.class.getResource("/Img/BDate.png")));
		immagineDataNascitaLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineDataNascitaLabel.setOpaque(true);
		immagineDataNascitaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineDataNascitaLabel.setBackground(new Color(179, 168, 166));
		immagineDataNascitaLabel.setBounds(46, 239, 49, 41);
		parteSpecificheVeicoloPanel.add(immagineDataNascitaLabel);
		
		JLabel lblSupervisore = new JLabel("Supervisore");
		lblSupervisore.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupervisore.setForeground(Color.WHITE);
		lblSupervisore.setFont(new Font("Century", Font.PLAIN, 20));
		lblSupervisore.setBounds(10, 291, 327, 25);
		parteSpecificheVeicoloPanel.add(lblSupervisore);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(95, 325, 200, 41);
		parteSpecificheVeicoloPanel.add(scrollPane);
		
		capoBox = new JComboBox();
		scrollPane.setViewportView(capoBox);
		capoBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		capoBox.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		capoBox.setToolTipText("Qui puoi selezionare il corriere");
		capoBox.setForeground(Color.WHITE);
		capoBox.setFont(new Font("Century", Font.PLAIN, 20));
		capoBox.setBackground(new Color(179, 168, 166));
		
		JLabel immagineCodiceFiscaleLabel_1 = new JLabel("");
		immagineCodiceFiscaleLabel_1.setIcon(new ImageIcon(FinestraInserimentoCorriere.class.getResource("/Img/supervisore.png")));
		immagineCodiceFiscaleLabel_1.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineCodiceFiscaleLabel_1.setOpaque(true);
		immagineCodiceFiscaleLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCodiceFiscaleLabel_1.setBackground(new Color(179, 168, 166));
		immagineCodiceFiscaleLabel_1.setBounds(46, 325, 49, 41);
		parteSpecificheVeicoloPanel.add(immagineCodiceFiscaleLabel_1);
		
		JButton nuovoCorriereBtn = new JButton("Registra nuovo corriere");
		nuovoCorriereBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
					controlloInput(codiceFiscaleTxf.getText() , nomeTxf.getText() , cognomeTxf.getText() , patentiTxf.getText() , emailTxf.getText() , numeroCellulareTxf.getText());
				
					gestoreApplicazione.assumiCorriere(codiceFiscaleTxf.getText(), nomeTxf.getText() , cognomeTxf.getText() , dataNascita, patentiTxf.getText() , emailTxf.getText() , numeroCellulareTxf.getText(),(int) contrattoFild.getValue(),(int) anniContributiFild.getValue(),capoBox.getSelectedIndex() - 1);
					gestoreApplicazione.mandaMailAssunzione(codiceFiscaleTxf.getText(), nomeTxf.getText() , cognomeTxf.getText() , dataNascita, patentiTxf.getText() , emailTxf.getText() , numeroCellulareTxf.getText(),(int) contrattoFild.getValue(),(int) anniContributiFild.getValue(),capoBox.getSelectedIndex() - 1);
					messaggioPopUp("Il Corriere inserito è stato assunto correttamente", "Assunzione Corriere");
					avviati();
					
				}catch (CampoCodiceFiscaleVuotoException vuotoErrore) {
					messaggioPopUp(vuotoErrore.getMessaggioErrore(),vuotoErrore.getTipoErrore());
				} catch (CampoNomeVuotoException vuotoErrore) {
					messaggioPopUp(vuotoErrore.getMessaggioErrore(),vuotoErrore.getTipoErrore());
				} catch (CampoCognomeVuotoException vuotoErrore) {
					messaggioPopUp(vuotoErrore.getMessaggioErrore(),vuotoErrore.getTipoErrore());
				} catch (CampoPatentiVuotoException vuotoErrore) {
					messaggioPopUp(vuotoErrore.getMessaggioErrore(),vuotoErrore.getTipoErrore());
				} catch (CampoEmailVuotoException vuotoErrore) {
					messaggioPopUp(vuotoErrore.getMessaggioErrore(),vuotoErrore.getTipoErrore());
				} catch (CampoNumeroCellulareVuotoException vuotoErrore) {
					messaggioPopUp(vuotoErrore.getMessaggioErrore(),vuotoErrore.getTipoErrore());
				} catch (DateVuoteException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getNomeErrore());
				} catch (OperazioneUpdateNonRiuscitaException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getTipoErrore());
				}
			}
		});
		nuovoCorriereBtn.setToolTipText("premi per creare un nuovo corriere");
		nuovoCorriereBtn.setForeground(Color.WHITE);
		nuovoCorriereBtn.setFont(new Font("Century", Font.BOLD, 20));
		nuovoCorriereBtn.setFocusPainted(false);
		nuovoCorriereBtn.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		nuovoCorriereBtn.setBackground(new Color(254, 126, 115));
		nuovoCorriereBtn.setBounds(294, 466, 265, 50);
		contentPane.add(nuovoCorriereBtn);
		
		
	}
	
	protected void avviati() {
	
		try {
			riempiCorriere();
	
		} catch (RisultatoNonRicavabileException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonCiSonoCorrieriException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		}
		
		
	}


	private void riempiCorriere() throws RisultatoNonRicavabileException, NonCiSonoCorrieriException {
		
		DefaultComboBoxModel modelloCorrieri = new DefaultComboBoxModel(gestoreApplicazione.dammiFormatoComboBoxSupervisori());
		capoBox.setModel(modelloCorrieri);
		
	
		
	}

	private void rimpicciolisciGradualmenteBottoneIndietro() {
		//Diventa Piccolo gradualmente
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 19));
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 18));
		
	}
	
	private void ingradisciGradualmenteBottoneIndietro() {
		//Diventa grande gradualmente
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 19));
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 20));
	}

	private void confermaRitornareIndietro() {
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 19));
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di ritornare indietro?", "Ritorna a crea spedizione",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.ritornaNuovaSpedizione(this);
		
	}
	
	protected void autoDeleteCodiceFiscale() {
		//resetta il TextField
		if(codiceFiscaleTxf.getText().equals("Codice fiscale"))
		codiceFiscaleTxf.setText("");
	}
	
	protected void autoDeleteNome() {
		//resetta il TextField
		if(nomeTxf.getText().equals("Nome"))
		nomeTxf.setText("");
	}
	
	protected void autoDeleteCognome() {
		//resetta il TextField
		if(cognomeTxf.getText().equals("Cognome"))
		cognomeTxf.setText("");
	}
	
	protected void autoDeletePatente() {
		//resetta il TextField
		if(patentiTxf.getText().equals("Patenti")) 
			patentiTxf.setText("");
		
	}
	
	protected void autoDeleteEmail() {
		//resetta il TextField
		if(emailTxf.getText().equals("E-mail")) 
			emailTxf.setText("");
		
	}
	
	protected void autoDeleteNumeroTelefono() {
		//resetta il TextField
		if(numeroCellulareTxf.getText().equals("Numero cellulare")) 
			numeroCellulareTxf.setText("");
		
	}
	
	private void sonoNonVuoti(String codiceFiscale, String nome , String cognome ,String patenti ,String email , String numeroCellulare  ) throws CampoCodiceFiscaleVuotoException, CampoNomeVuotoException,CampoCognomeVuotoException,CampoPatentiVuotoException,CampoEmailVuotoException,CampoNumeroCellulareVuotoException{
		//Controlli  sulla correttezza dell'input
		
		if(codiceFiscale.isBlank() || codiceFiscale.equals("Codice fiscale")) 
			throw new CampoCodiceFiscaleVuotoException();
		
		if(nome.isBlank() || nome.equals("Nome"))
			throw new CampoNomeVuotoException();	
		
		if(cognome.isBlank() || cognome.equals("Cognome"))
			throw new CampoCognomeVuotoException();
		
		if(patenti.isBlank() || patenti.equals("Patenti"))
			throw new CampoPatentiVuotoException();
		
		if(email.isBlank() || email.equals("E-mail"))
			throw new CampoEmailVuotoException();
		
		if(numeroCellulare.isBlank() || numeroCellulare.equals("Numero cellulare"))
			throw new CampoNumeroCellulareVuotoException();
		
	}
	
	//Procedura che ci permetterà di mostrare con un messaggio PopUp  
	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}
	private LocalDate DateToLocalDate(Date data) {
		return LocalDate.ofInstant(data.toInstant(), ZoneId.systemDefault());
	}
	
	private void controlloInput(String codiceFiscale, String nome , String cognome ,String patenti ,String email , String numeroCellulare) throws CampoCodiceFiscaleVuotoException, CampoNomeVuotoException, CampoCognomeVuotoException, CampoPatentiVuotoException, CampoEmailVuotoException, CampoNumeroCellulareVuotoException, DateVuoteException {
		
		
			sonoNonVuoti(codiceFiscale,nome,cognome,patenti,email,numeroCellulare);
			dataVuota();
			dataNascita = DateToLocalDate(dataNascitaDataChoser.getDate());
		
	}
	
	private void dataVuota() throws DateVuoteException {
		if(dataNascitaDataChoser.getDate() == null)
			throw new DateVuoteException();
		
	}
	
	private void vaiAvanti(JTextField primoCampo, JTextField secondoCampo, KeyEvent e) {
		//Mi permette di andare al prossimo TextFild con il semplice spazio
		if(e.getKeyCode() == 10 && !primoCampo.getText().isBlank() && !primoCampo.getText().isEmpty())
			secondoCampo.requestFocusInWindow();
		
	}

	private void autoRitornaTesto(String testoDentro, String testoOrginale, JTextField txtFild) {
		//Riporto il testo originale se non si è scritto niente
		if(testoDentro.isEmpty() || testoDentro.isBlank())
			txtFild.setText(testoOrginale);
	}

	protected void resetCampi() {
		codiceFiscaleTxf.setText("Codice fiscale");
		nomeTxf.setText("Nome");
		cognomeTxf.setText("Cognome");
		patentiTxf.setText("Patenti");
		emailTxf.setText("E-mail");
		numeroCellulareTxf.setText("Numero cellulare");
		
	}
}
