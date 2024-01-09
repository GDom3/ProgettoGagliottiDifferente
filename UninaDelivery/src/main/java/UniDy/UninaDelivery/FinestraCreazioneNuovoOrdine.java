package UniDy.UninaDelivery;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FinestraCreazioneNuovoOrdine extends JFrame {

	private static final long serialVersionUID = 1L;
	//Amministratore
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JButton indietroBottone;
	private JTextField cittaTxF;
	private JTextField viaTxF;
	private JTextField numeroCivicoTxF;
	private JTextField CAPTxF;
	private JSpinner costoFild;
	private JComboBox clientiBox;
	private JComboBox esemplariBox;
	//Oggetti Reali
	private LocalDate dataE;
	private LocalDate dataConsegna;
	private ArrayList<Cliente> clienti;
	private ArrayList<Esemplare> esemplari;
	private ArrayList<String> arrayTemp;
	private Ordine nuovoOrd;
	
	
	public FinestraCreazioneNuovoOrdine(AppBrain appBrain) {
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(119, 101, 101));
		setResizable(false);
		setTitle("UninaDelivery");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraCreazioneNuovoOrdine.class.getResource("/Img/Icon.png")));
		gestoreApplicazione = appBrain;
		setDefaultCloseOperation(appBrain.exit());
		setBounds(100, 100, 800, 600);
		JPanel contentPane;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 101, 101));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel intestazionePanel = new JPanel();
		intestazionePanel.setLayout(null);
		intestazionePanel.setBackground(new Color(239, 235, 229));
		intestazionePanel.setBounds(0, 0, 71, 561);
		contentPane.add(intestazionePanel);
		
		JLabel titoloSXL = new JLabel("");
		titoloSXL.setIcon(new ImageIcon(FinestraCreazioneNuovoOrdine.class.getResource("/Img/SxTitoloImg.jpg")));
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloSXL.setBounds(10, 76, 45, 474);
		intestazionePanel.add(titoloSXL);
		
		JLabel logoSXImgL = new JLabel("New Label");
		logoSXImgL.setIcon(new ImageIcon(FinestraCreazioneNuovoOrdine.class.getResource("/Img/LogoHSX.png")));
		logoSXImgL.setBounds(0, 0, 71, 65);
		intestazionePanel.add(logoSXImgL);
		
		JPanel homePanel = new JPanel();
		homePanel.setLayout(null);
		homePanel.setBackground(new Color(119, 101, 101));
		homePanel.setBounds(69, 0, 715, 37);
		contentPane.add(homePanel);
		
		indietroBottone = new JButton("Indietro");
		indietroBottone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		indietroBottone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confermaRitornareIndietro();
			}
		});
		indietroBottone.setToolTipText("premi per ritornare alla finestra precedente");
		indietroBottone.setHorizontalTextPosition(SwingConstants.CENTER);
		indietroBottone.setForeground(Color.WHITE);
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 18));
		indietroBottone.setFocusPainted(false);
		indietroBottone.setContentAreaFilled(false);
		indietroBottone.setBorderPainted(false);
		indietroBottone.setBorder(null);
		indietroBottone.setBackground(new Color(119, 101, 101));
		indietroBottone.setBounds(628, 11, 77, 22);
		homePanel.add(indietroBottone);
		
		JLabel titoloLabel = new JLabel("Creazione Nuovo Ordine");
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLabel.setForeground(Color.WHITE);
		titoloLabel.setFont(new Font("Century", Font.BOLD, 30));
		titoloLabel.setBounds(12, -1, 693, 37);
		homePanel.add(titoloLabel);
		
		JPanel infoOrdineLabel = new JPanel();
		infoOrdineLabel.setBackground(new Color(119, 101, 101));
		infoOrdineLabel.setBounds(81, 48, 347, 397);
		contentPane.add(infoOrdineLabel);
		infoOrdineLabel.setLayout(null);
		
		costoFild = new JSpinner();
		costoFild.setToolTipText("Inserisci costo spedizione");
		costoFild.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		costoFild.setForeground(new Color(255, 255, 255));
		costoFild.setBackground(new Color(179, 168, 166));
		costoFild.setModel(new SpinnerNumberModel(Float.valueOf(1), Float.valueOf(0), null, Float.valueOf(1)));
		costoFild.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		costoFild.setFont(new Font("Century", Font.PLAIN, 20));
		costoFild.setBounds(95, 187, 200, 43);
		infoOrdineLabel.add(costoFild);
		
		JLabel costoLabel = new JLabel("Costo spedizione");
		costoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		costoLabel.setForeground(Color.WHITE);
		costoLabel.setFont(new Font("Century", Font.PLAIN, 20));
		costoLabel.setBounds(0, 151, 347, 25);
		infoOrdineLabel.add(costoLabel);
		
		JLabel logoCliente_1 = new JLabel("New label");
		logoCliente_1.setBorder(new LineBorder(new Color(179, 168, 166), 1, true));
		logoCliente_1.setIcon(new ImageIcon(FinestraCreazioneNuovoOrdine.class.getResource("/Img/costoImg.png")));
		logoCliente_1.setBounds(54, 187, 43, 43);
		infoOrdineLabel.add(logoCliente_1);
		
		JLabel esemplareLabel = new JLabel("Esemplare");
		esemplareLabel.setHorizontalAlignment(SwingConstants.CENTER);
		esemplareLabel.setForeground(Color.WHITE);
		esemplareLabel.setFont(new Font("Century", Font.PLAIN, 20));
		esemplareLabel.setBounds(0, 11, 347, 25);
		infoOrdineLabel.add(esemplareLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setBounds(95, 47, 200, 41);
		infoOrdineLabel.add(scrollPane);
		
		esemplariBox = new JComboBox();
		scrollPane.setViewportView(esemplariBox);
		esemplariBox.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		esemplariBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		esemplariBox.setToolTipText("Qui puoi selezionare il cliente");
		esemplariBox.setForeground(Color.WHITE);
		esemplariBox.setFont(new Font("Century", Font.PLAIN, 18));
		esemplariBox.setBackground(new Color(179, 168, 166));
		
		JLabel logoCliente_2 = new JLabel("New label");
		logoCliente_2.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		logoCliente_2.setIcon(new ImageIcon(FinestraCreazioneNuovoOrdine.class.getResource("/Img/esemplari.png")));
		logoCliente_2.setBounds(54, 47, 42, 41);
		infoOrdineLabel.add(logoCliente_2);
		
		JButton aggiungiEsemplareButton = new JButton("Aggiungi esemplare");
		aggiungiEsemplareButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiEsemplareButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimpicciolisciGradualmenteBottoneAdd(aggiungiEsemplareButton);
			
			}
		});
		aggiungiEsemplareButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneAdd(aggiungiEsemplareButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneAdd(aggiungiEsemplareButton);
			}
		});
		aggiungiEsemplareButton.setToolTipText("Qui puoi crere un nuovo esemplare");
		aggiungiEsemplareButton.setForeground(new Color(254, 126, 115));
		aggiungiEsemplareButton.setFont(new Font("Century", Font.PLAIN, 18));
		aggiungiEsemplareButton.setFocusPainted(false);
		aggiungiEsemplareButton.setContentAreaFilled(false);
		aggiungiEsemplareButton.setBorderPainted(false);
		aggiungiEsemplareButton.setBounds(55, 99, 240, 33);
		infoOrdineLabel.add(aggiungiEsemplareButton);
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(Color.WHITE);
		dateLabel.setFont(new Font("Century", Font.PLAIN, 20));
		dateLabel.setBounds(0, 239, 347, 25);
		infoOrdineLabel.add(dateLabel);
		
		JDateChooser DataEDataChoser = new JDateChooser();
		DataEDataChoser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DataEDataChoser.getCalendarButton().setFont(new Font("Century", Font.PLAIN, 18));
		DataEDataChoser.getCalendarButton().setBackground(Color.WHITE);
		DataEDataChoser.setToolTipText("inserisci data di esecuzione del ordine");
		DataEDataChoser.setFont(new Font("Century", Font.PLAIN, 18));
		DataEDataChoser.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		DataEDataChoser.setBackground(new Color(179, 168, 166));
		DataEDataChoser.setBounds(157, 295, 170, 33);
		infoOrdineLabel.add(DataEDataChoser);
		
		JLabel dateELabel = new JLabel("Esecuzione");
		dateELabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateELabel.setForeground(Color.WHITE);
		dateELabel.setFont(new Font("Century", Font.PLAIN, 20));
		dateELabel.setBounds(157, 267, 172, 25);
		infoOrdineLabel.add(dateELabel);
		
		JLabel lblConsegna = new JLabel("Consegna");
		lblConsegna.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsegna.setForeground(Color.WHITE);
		lblConsegna.setFont(new Font("Century", Font.PLAIN, 20));
		lblConsegna.setBounds(157, 325, 172, 25);
		infoOrdineLabel.add(lblConsegna);
		
		JDateChooser DataConsegnaDataChoser = new JDateChooser();
		DataConsegnaDataChoser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DataConsegnaDataChoser.getCalendarButton().setFont(new Font("Century", Font.PLAIN, 18));
		DataConsegnaDataChoser.getCalendarButton().setBackground(Color.WHITE);
		DataConsegnaDataChoser.setToolTipText("inserisci data di consegna del ordine");
		DataConsegnaDataChoser.setFont(new Font("Century", Font.PLAIN, 18));
		DataConsegnaDataChoser.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		DataConsegnaDataChoser.setBackground(new Color(179, 168, 166));
		DataConsegnaDataChoser.setBounds(157, 354, 170, 33);
		infoOrdineLabel.add(DataConsegnaDataChoser);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBorder(new LineBorder(new Color(179, 168, 166), 4, true));
		lblNewLabel.setIcon(new ImageIcon(FinestraCreazioneNuovoOrdine.class.getResource("/Img/calendario.png")));
		lblNewLabel.setBounds(44, 282, 98, 92);
		infoOrdineLabel.add(lblNewLabel);
		
		JPanel infoClientePanel = new JPanel();
		infoClientePanel.setBackground(new Color(119, 101, 101));
		infoClientePanel.setBounds(427, 48, 347, 397);
		contentPane.add(infoClientePanel);
		infoClientePanel.setLayout(null);
		
		JLabel clienteLabel = new JLabel("Cliente");
		clienteLabel.setForeground(new Color(255, 255, 255));
		clienteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clienteLabel.setFont(new Font("Century", Font.PLAIN, 20));
		clienteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		clienteLabel.setBounds(10, 11, 327, 25);
		infoClientePanel.add(clienteLabel);
		
		JLabel logoCliente = new JLabel("New label");
		logoCliente.setIcon(new ImageIcon(FinestraCreazioneNuovoOrdine.class.getResource("/Img/ClienteInAddOrdine.jpg")));
		logoCliente.setBounds(62, 47, 47, 41);
		infoClientePanel.add(logoCliente);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(108, 47, 200, 41);
		infoClientePanel.add(scrollPane_1);
		
		clientiBox = new JComboBox();
		scrollPane_1.setViewportView(clientiBox);
		clientiBox.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		clientiBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clientiBox.setForeground(new Color(255, 255, 255));
		clientiBox.setBackground(new Color(179, 168, 166));
		clientiBox.setToolTipText("Qui puoi selezionare il cliente");
		clientiBox.setFont(new Font("Century", Font.PLAIN, 20));
		
		JButton aggiungiClienteButton = new JButton("Aggiungi cliente");
		aggiungiClienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimpicciolisciGradualmenteBottoneAdd(aggiungiClienteButton);
				gestoreApplicazione.mostraFinestraCreazioneCliente();
			}
		});
		aggiungiClienteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneAdd(aggiungiClienteButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneAdd(aggiungiClienteButton);
			}
		});
		aggiungiClienteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiClienteButton.setToolTipText("Qui puoi crere un nuovo cliente");
		aggiungiClienteButton.setForeground(new Color(254, 126, 115));
		aggiungiClienteButton.setFont(new Font("Century", Font.PLAIN, 18));
		aggiungiClienteButton.setFocusPainted(false);
		aggiungiClienteButton.setContentAreaFilled(false);
		aggiungiClienteButton.setBorderPainted(false);
		aggiungiClienteButton.setBounds(84, 99, 198, 33);
		infoClientePanel.add(aggiungiClienteButton);
		
		JLabel indirizzoLabel = new JLabel("Indirizzo");
		indirizzoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indirizzoLabel.setForeground(Color.WHITE);
		indirizzoLabel.setFont(new Font("Century", Font.PLAIN, 20));
		indirizzoLabel.setBounds(10, 150, 327, 25);
		infoClientePanel.add(indirizzoLabel);
		
		cittaTxF = new JTextField();
		cittaTxF.setText("Città");
		cittaTxF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(cittaTxF.getText(), "Città",cittaTxF);
				vaiAvanti(cittaTxF,viaTxF,e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(cittaTxF.getText(), "Città",cittaTxF);
			}
		});
		cittaTxF.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		cittaTxF.setBackground(new Color(179, 168, 166));
		cittaTxF.setForeground(new Color(255, 255, 255));
		cittaTxF.setFont(new Font("Century", Font.PLAIN, 20));
		
		cittaTxF.setBounds(108, 187, 200, 41);
		infoClientePanel.add(cittaTxF);
		cittaTxF.setColumns(10);
		
		JLabel logoCitta = new JLabel("New label");
		logoCitta.setIcon(new ImageIcon(FinestraCreazioneNuovoOrdine.class.getResource("/Img/cittàLogo.jpg")));
		logoCitta.setBounds(67, 187, 41, 41);
		infoClientePanel.add(logoCitta);
		
		viaTxF = new JTextField();
		viaTxF.setText("Via/Viale");
		viaTxF.setForeground(Color.WHITE);
		viaTxF.setFont(new Font("Century", Font.PLAIN, 20));
		viaTxF.setColumns(10);
		viaTxF.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		viaTxF.setBackground(new Color(179, 168, 166));
		viaTxF.setBounds(108, 239, 200, 41);
		viaTxF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(viaTxF.getText(), "Via/Viale",viaTxF);
				vaiAvanti(viaTxF,numeroCivicoTxF,e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(viaTxF.getText(), "Via/Viale",viaTxF);
			}
		});
		infoClientePanel.add(viaTxF);
		
		JLabel logoCitta_1 = new JLabel("New label");
		logoCitta_1.setIcon(new ImageIcon(FinestraCreazioneNuovoOrdine.class.getResource("/Img/houseLogo.png")));
		logoCitta_1.setBounds(67, 239, 41, 41);
		infoClientePanel.add(logoCitta_1);
		
		numeroCivicoTxF = new JTextField();
		numeroCivicoTxF.setText("Numero Civico");
		numeroCivicoTxF.setForeground(Color.WHITE);
		numeroCivicoTxF.setFont(new Font("Century", Font.PLAIN, 20));
		numeroCivicoTxF.setColumns(10);
		numeroCivicoTxF.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		numeroCivicoTxF.setBackground(new Color(179, 168, 166));
		numeroCivicoTxF.setBounds(108, 291, 200, 41);
		numeroCivicoTxF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(numeroCivicoTxF.getText(), "Numero Civico",numeroCivicoTxF);
				vaiAvanti(numeroCivicoTxF,CAPTxF,e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(numeroCivicoTxF.getText(), "Numero Civico",numeroCivicoTxF);
			}
		});
		infoClientePanel.add(numeroCivicoTxF);
		
		JLabel logoCitta_2 = new JLabel("New label");
		logoCitta_2.setIcon(new ImageIcon(FinestraCreazioneNuovoOrdine.class.getResource("/Img/NumCivicoLogo.png")));
		logoCitta_2.setBounds(67, 291, 41, 41);
		infoClientePanel.add(logoCitta_2);
		
		CAPTxF = new JTextField();
		CAPTxF.setText("CAP");
		CAPTxF.setForeground(Color.WHITE);
		CAPTxF.setFont(new Font("Century", Font.PLAIN, 20));
		CAPTxF.setColumns(10);
		CAPTxF.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		CAPTxF.setBackground(new Color(179, 168, 166));
		CAPTxF.setBounds(108, 343, 200, 41);
		CAPTxF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(CAPTxF.getText(), "CAP",CAPTxF);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(CAPTxF.getText(), "CAP",CAPTxF);
			}
		});
		infoClientePanel.add(CAPTxF);
		
		JLabel logoCitta_3 = new JLabel("New label");
		logoCitta_3.setIcon(new ImageIcon(FinestraCreazioneNuovoOrdine.class.getResource("/Img/logoCAP.png")));
		logoCitta_3.setBounds(67, 343, 41, 41);
		infoClientePanel.add(logoCitta_3);
		
		JButton nuovaOrdineB = new JButton("Crea Nuovo Ordine");
		nuovaOrdineB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					verificaDate(DataEDataChoser.getDate(),DataConsegnaDataChoser.getDate());
					verificaTxtFildVuoti();
					if(confermaCreazione()== 0) {
						creaOrdine();
						
						
					}
				} catch (DateCronologicamenteSbagliateException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getNomeErrore());
				} catch (DateVuoteException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getNomeErrore());
				} catch (CampoCittàVuotoException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getNomeErrore());
				} catch (CampoViaVuotoException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getNomeErrore());
				} catch (CampoNumeroCivicoVuotoException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getNomeErrore());
				} catch (CampoCapVuotoException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getNomeErrore());
				}
				
			}
		});
		nuovaOrdineB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nuovaOrdineB.setToolTipText("premi per creare una nuovo ordine");
		nuovaOrdineB.setForeground(Color.WHITE);
		nuovaOrdineB.setFont(new Font("Century", Font.BOLD, 20));
		nuovaOrdineB.setFocusPainted(false);
		nuovaOrdineB.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		nuovaOrdineB.setBackground(new Color(254, 126, 115));
		nuovaOrdineB.setBounds(294, 446, 265, 50);
		contentPane.add(nuovaOrdineB);
		
		JButton AggiungiEsemplareBottone = new JButton("Aggiungi Esemplare A Ordine Esistente");
		AggiungiEsemplareBottone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esemplariBox.getItemCount() > 0)
					aggiungiEsemplareAdOrdneEsistente();
				else
					messaggioPopUp("Non puoi procedere ad aggiungere un esemplare, in quanto non ci sono articoli non venduti", "Attenzione");
			}
		});
		AggiungiEsemplareBottone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AggiungiEsemplareBottone.setToolTipText("Premi qui per aggiungere l'esemplare ad un ordine");
		AggiungiEsemplareBottone.setForeground(Color.WHITE);
		AggiungiEsemplareBottone.setFont(new Font("Century", Font.BOLD, 20));
		AggiungiEsemplareBottone.setFocusPainted(false);
		AggiungiEsemplareBottone.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		AggiungiEsemplareBottone.setBackground(new Color(254, 126, 115));
		AggiungiEsemplareBottone.setBounds(224, 503, 408, 50);
		contentPane.add(AggiungiEsemplareBottone);
	}
	
	
	private void creaOrdine() {
		//Preparo l'ordine
		nuovoOrd = new Ordine(clienti.get(clientiBox.getSelectedIndex()),esemplari.get(esemplariBox.getSelectedIndex()),(float)costoFild.getValue(),dataE,dataConsegna,cittaTxF.getText(),viaTxF.getText(),numeroCivicoTxF.getText(),CAPTxF.getText());		
		try {
			//Lo invio al db
			gestoreApplicazione.creaOrdine(nuovoOrd);
			messaggioPopUp("Creazioenavvenuta con successo, l'ordine ed ora disponibile", "Inserimento Riuscito");
			avviati();
		} catch (RisultatoNonRicavabileException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonPossibileCreareOrdineException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		}
	}


	private int confermaCreazione() {
		return JOptionPane.showConfirmDialog(this, "Sei sicuro di voler creare l'ordine con questi elementi?","Conferma Scelta",JOptionPane.YES_NO_CANCEL_OPTION);
		
	}


	private void verificaTxtFildVuoti() throws CampoCittàVuotoException, CampoViaVuotoException, CampoNumeroCivicoVuotoException, CampoCapVuotoException {
		boolean verifica;
		//Verifico ogni singolo campo di testo
		
		verifica = cittaTxF.getText().equals("Città") || cittaTxF.getText().isEmpty() || cittaTxF.getText().isBlank();
		if(verifica)
			throw new CampoCittàVuotoException();

		verifica = verifica || viaTxF.getText().equals("Via/Viale") || viaTxF.getText().isEmpty() || viaTxF.getText().isBlank();
		if(verifica)
			throw new CampoViaVuotoException();
		
		verifica = verifica || numeroCivicoTxF.getText().equals("Numero Civico") || numeroCivicoTxF.getText().isEmpty() || numeroCivicoTxF.getText().isBlank();
		if(verifica)
			throw new CampoNumeroCivicoVuotoException();
		
		verifica = verifica || CAPTxF.getText().equals("CAP") || CAPTxF.getText().isEmpty() || CAPTxF.getText().isBlank();
		if(verifica)
			throw new CampoCapVuotoException();
		
	}


	private void verificaDate(Date esecuzioneData, Date consegnaData) throws DateCronologicamenteSbagliateException, DateVuoteException {
		//Verifico che non siano vuote
		if(esecuzioneData == null || consegnaData == null)
			throw new DateVuoteException();
		
		//Converto le date
		dataConsegna = DateToLocalDate(consegnaData);
		dataE = DateToLocalDate(esecuzioneData);
		
		//verifico che non siano inserite cronologicamente bene
		if(dataConsegna.isBefore(dataE))
			throw new DateCronologicamenteSbagliateException();
		
	}


	private void aggiungiEsemplareAdOrdneEsistente() {
		int input = 0;
			
		ArrayList<Ordine> ordiniNonPartiti = new ArrayList<Ordine>();
		try {
			//Prendo gli ordini senza spedizione
			ordiniNonPartiti = gestoreApplicazione.dammiOrdiniNonPartitiOFalliti();
			
			//Gestisco la combobox
			JComboBox ordiniTemp = new JComboBox(ordiniNonPartiti.toArray());
			
			//Richiedo le mosse da fare all'utente
			input = JOptionPane.showConfirmDialog(this,ordiniTemp,"Seleziona Ordine",JOptionPane.OK_CANCEL_OPTION);
			if(input == 0)
				input = JOptionPane.showConfirmDialog(this,"Vuoi aggiungere l'esemplare "+ esemplari.get(esemplariBox.getSelectedIndex()).getCodiceBarre() + " all' ordine "+ ordiniNonPartiti.get(ordiniTemp.getSelectedIndex()),"Conferma Scelta",JOptionPane.OK_CANCEL_OPTION);
				if (input == 0) {
					inserisciEsemplareInOrdine(ordiniNonPartiti.get(ordiniTemp.getSelectedIndex()),esemplari.get(esemplariBox.getSelectedIndex()));
					avviati();
				}
		
		} catch (RisultatoNonRicavabileException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonCiSonoOrdiniAttesiException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		}
					
		
	}


	private void inserisciEsemplareInOrdine(Ordine ordine, Esemplare esemplare) {
		try {
			gestoreApplicazione.inserisciEsemplareInOrdine(ordine,esemplare);
			messaggioPopUp("E' stato Inserito corettamente l'esemplare nell'ordine", "Aggiunta Riuscita");
			avviati();
		} catch (NonCiSonoOrdiniAttesiException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		}
		
		
	}


	private void vaiAvanti(JTextField primoCampo, JTextField secondoCampo, KeyEvent e) {
		//Mi permette di andare al prossimo TextFild con il semplice spazio
		if(e.getKeyCode() == 10 && !primoCampo.getText().isBlank() && !primoCampo.getText().isEmpty())
			secondoCampo.requestFocusInWindow();
		
	}

	private void autoDelate(String testoDentro, String testoOrginale, JTextField txtFild) {
		//svuotare appena si scrive
		if(testoDentro.equals(testoOrginale))
			txtFild.setText("");

		
	}
	
	private void autoRitornaTesto(String testoDentro, String testoOrginale, JTextField txtFild) {
		//Riporto il testo originale se non si è scritto niente
		if(testoDentro.isEmpty() || testoDentro.isBlank())
			txtFild.setText(testoOrginale);
	}


	private void confermaRitornareIndietro() {
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 19));
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di ritornare indietro", "Ritorna a crea spedizione",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.ritornaNuovaSpedizione(this);
		
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
	
	private void rimpicciolisciGradualmenteBottoneAdd(JButton bottone) {
		//Diventa Piccolo gradualmente
		bottone.setFont(new Font("Century", Font.PLAIN, 19));
		bottone.setFont(new Font("Century", Font.PLAIN, 18));
		
	}

	private void ingradisciGradualmenteBottoneAdd(JButton bottone) {
		//Diventa grande gradualmente
		bottone.setFont(new Font("Century", Font.PLAIN, 19));
		bottone.setFont(new Font("Century", Font.PLAIN, 20));
	}
	
	
	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}
	private LocalDate DateToLocalDate(Date data) {
		return LocalDate.ofInstant(data.toInstant(), ZoneId.systemDefault());
	}

	protected void avviati() {
		
		try {
			
			prendiClienti();
			prendiEsemplari();
				
		} catch (RisultatoNonRicavabileException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonCiSonoClientiException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonCiSonoEsemplariNonVendutiException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		}
		
		
		
	}


	private void prendiEsemplari() throws RisultatoNonRicavabileException, NonCiSonoEsemplariNonVendutiException {
		//Prendo gli esemplari
		esemplari = gestoreApplicazione.dammiEsemplariNonVenduti();
		
		arrayTemp = new ArrayList<String>(esemplari.size());
		//gestisco l'estetica
		for(Esemplare esemplare : esemplari)
			arrayTemp.add("("+ esemplare.getCodiceBarre() + ") "+ esemplare.getMerceRiferimento().getNome() );
		
		//E riempio la combobox
		esemplariBox.setModel(new DefaultComboBoxModel(arrayTemp.toArray()));
		
	}


	private void prendiClienti() throws RisultatoNonRicavabileException, NonCiSonoClientiException {
		//Prendo i clienti
		clienti = gestoreApplicazione.dammiTuttiClienti();
		
		arrayTemp = new ArrayList<String>(clienti.size());
		//gestisco l'estetica
		for(Cliente cliente : clienti)
			arrayTemp.add("("+ cliente.getCodCliente() + ") "+ cliente.getNome() + " " + cliente.getCognome() );
		
		//E riempio la combobox
		clientiBox.setModel(new DefaultComboBoxModel(arrayTemp.toArray()));
		
	}
	
	
}
