package UniDy.UninaDelivery;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;

public class FinestraCreazioneNuovoCliente extends JFrame {

	private static final long serialVersionUID = 1L; 
	//Aministratore
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JButton indietroBottone;
	private JTextField nomeTextField;
	private JTextField cognomeTextFild;
	private JTextField CodiceFiscaleTextField;
	private JTextField txtEmail;
	private JTextField txtNumeroCellulare;
	private JDateChooser DataNascitaDataChoser;
	//Gestione radio
	private ButtonGroup gruppoRadioContatto;
	private JRadioButton telefonoRadio;
	private JRadioButton smsRadio;
	private JRadioButton emailRadio;
	//oggetti reali
	private LocalDate dataDiNascita;
	
	public FinestraCreazioneNuovoCliente(AppBrain appBrain) {
		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setFont(new Font("Century", Font.PLAIN, 20));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraCreazioneNuovoCliente.class.getResource("/Img/Icon.png")));
		setTitle("UninaDelivery");
		gestoreApplicazione = appBrain;
		setDefaultCloseOperation(gestoreApplicazione.exit());
		setBounds(100, 100, 800, 600);
		JPanel contentPane;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel intestazionePanel = new JPanel();
		intestazionePanel.setLayout(null);
		intestazionePanel.setBackground(new Color(239, 235, 229));
		intestazionePanel.setBounds(0, 0, 71, 561);
		contentPane.add(intestazionePanel);
		
		JLabel titoloSXL = new JLabel("");
		titoloSXL.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/SxTitoloImg.jpg")));
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloSXL.setBounds(10, 76, 45, 474);
		intestazionePanel.add(titoloSXL);
		
		JLabel logoSXImgL = new JLabel("New Label");
		logoSXImgL.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/LogoHSX.png")));
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
		
		JLabel titoloLabel = new JLabel("Creazione Nuovo Cliente");
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLabel.setForeground(Color.WHITE);
		titoloLabel.setFont(new Font("Century", Font.BOLD, 30));
		titoloLabel.setBounds(0, -1, 715, 37);
		homePanel.add(titoloLabel);
		
		JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setBackground(new Color(119, 101, 101));
		anagraficaPanel.setBounds(69, 37, 358, 262);
		contentPane.add(anagraficaPanel);
		anagraficaPanel.setLayout(null);
		
		JLabel logoCitta_1_1 = new JLabel("New label");
		logoCitta_1_1.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/BDate.png")));
		logoCitta_1_1.setBorder(new LineBorder(new Color(179, 168, 166), 1, true));
		logoCitta_1_1.setBounds(78, 203, 43, 41);
		anagraficaPanel.add(logoCitta_1_1);
		
		JLabel anagraficaTitoloLabel = new JLabel("Dati Anagrafici");
		anagraficaTitoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		anagraficaTitoloLabel.setForeground(new Color(255, 255, 255));
		anagraficaTitoloLabel.setFont(new Font("Century", Font.PLAIN, 20));
		anagraficaTitoloLabel.setBounds(10, 11, 338, 25);
		anagraficaPanel.add(anagraficaTitoloLabel);
		
		nomeTextField = new JTextField();
		nomeTextField.setToolTipText("inserisci nome");
		nomeTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(nomeTextField.getText(), "Nome", nomeTextField);
				vaiAvanti(nomeTextField, cognomeTextFild, e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(nomeTextField.getText(), "Nome", nomeTextField);
			}
		});
		nomeTextField.setText("Nome");
		nomeTextField.setForeground(Color.WHITE);
		nomeTextField.setFont(new Font("Century", Font.PLAIN, 20));
		nomeTextField.setColumns(10);
		nomeTextField.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		nomeTextField.setBackground(new Color(179, 168, 166));
		nomeTextField.setBounds(106, 47, 200, 41);
		anagraficaPanel.add(nomeTextField);
		
		cognomeTextFild = new JTextField();
		cognomeTextFild.setToolTipText("inserisci  cognome");
		cognomeTextFild.setText("Cognome");
		cognomeTextFild.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(cognomeTextFild.getText(), "Cognome", cognomeTextFild);
				vaiAvanti(cognomeTextFild, CodiceFiscaleTextField, e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(cognomeTextFild.getText(), "Cognome", cognomeTextFild);
			}
		});
		cognomeTextFild.setForeground(Color.WHITE);
		cognomeTextFild.setFont(new Font("Century", Font.PLAIN, 20));
		cognomeTextFild.setColumns(10);
		cognomeTextFild.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		cognomeTextFild.setBackground(new Color(179, 168, 166));
		cognomeTextFild.setBounds(106, 99, 200, 41);
		anagraficaPanel.add(cognomeTextFild);
		
		CodiceFiscaleTextField = new JTextField();
		CodiceFiscaleTextField.setToolTipText("inserisci codice fiscale");
		CodiceFiscaleTextField.setText("Codice Fiscale");
		CodiceFiscaleTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(CodiceFiscaleTextField.getText(), "Codice Fiscale", CodiceFiscaleTextField);
				vaiAvanti(CodiceFiscaleTextField, txtEmail, e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(CodiceFiscaleTextField.getText(), "Codice Fiscale", CodiceFiscaleTextField);
			}
		});
		CodiceFiscaleTextField.setForeground(Color.WHITE);
		CodiceFiscaleTextField.setFont(new Font("Century", Font.PLAIN, 20));
		CodiceFiscaleTextField.setColumns(10);
		CodiceFiscaleTextField.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		CodiceFiscaleTextField.setBackground(new Color(179, 168, 166));
		CodiceFiscaleTextField.setBounds(106, 151, 200, 41);
		anagraficaPanel.add(CodiceFiscaleTextField);
		
		JLabel logoCitta_1 = new JLabel("New label");
		logoCitta_1.setBackground(new Color(179, 168, 166));
		logoCitta_1.setOpaque(true);
		logoCitta_1.setBorder(new LineBorder(new Color(179, 168, 166), 3, true));
		logoCitta_1.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/CodFisc.png")));
		logoCitta_1.setBounds(58, 151, 49, 41);
		anagraficaPanel.add(logoCitta_1);
		
		DataNascitaDataChoser = new JDateChooser();
		DataNascitaDataChoser.getCalendarButton().setFont(new Font("Century", Font.PLAIN, 18));
		DataNascitaDataChoser.getCalendarButton().setBackground(Color.WHITE);
		DataNascitaDataChoser.setToolTipText("inserisci data di esecuzione del ordine");
		DataNascitaDataChoser.setFont(new Font("Century", Font.PLAIN, 18));
		DataNascitaDataChoser.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		DataNascitaDataChoser.setBackground(new Color(179, 168, 166));
		DataNascitaDataChoser.setBounds(118, 203, 170, 41);
		anagraficaPanel.add(DataNascitaDataChoser);
		
		JLabel immagineNomeLabel = new JLabel("");
		immagineNomeLabel.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/nameridimensionata.png")));
		immagineNomeLabel.setOpaque(true);
		immagineNomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineNomeLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineNomeLabel.setBackground(new Color(179, 168, 166));
		immagineNomeLabel.setBounds(58, 47, 49, 41);
		anagraficaPanel.add(immagineNomeLabel);
		
		JLabel immagineCognomeLabel = new JLabel("");
		immagineCognomeLabel.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/cognomeridimensionata.png")));
		immagineCognomeLabel.setOpaque(true);
		immagineCognomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCognomeLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineCognomeLabel.setBackground(new Color(179, 168, 166));
		immagineCognomeLabel.setBounds(58, 99, 49, 41);
		anagraficaPanel.add(immagineCognomeLabel);
		
		JPanel anagraficaPanel_1 = new JPanel();
		anagraficaPanel_1.setLayout(null);
		anagraficaPanel_1.setBackground(new Color(119, 101, 101));
		anagraficaPanel_1.setBounds(426, 37, 358, 262);
		contentPane.add(anagraficaPanel_1);
		
		JLabel lblContatti = new JLabel("Contatti");
		lblContatti.setHorizontalAlignment(SwingConstants.CENTER);
		lblContatti.setForeground(Color.WHITE);
		lblContatti.setFont(new Font("Century", Font.PLAIN, 20));
		lblContatti.setBounds(10, 11, 338, 25);
		anagraficaPanel_1.add(lblContatti);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("inserisci email");
		txtEmail.setText("E-Mail");
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(txtEmail.getText(), "E-Mail", txtEmail);
				vaiAvanti(txtEmail, txtNumeroCellulare, e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(txtEmail.getText(), "E-Mail", txtEmail);
			}
		});
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setFont(new Font("Century", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		txtEmail.setBackground(new Color(179, 168, 166));
		txtEmail.setBounds(100, 47, 200, 41);
		anagraficaPanel_1.add(txtEmail);
		
		txtNumeroCellulare = new JTextField();
		txtNumeroCellulare.setToolTipText("inserisci numero telefonico");
		txtNumeroCellulare.setText("Numero Cellulare");
		txtNumeroCellulare.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(txtNumeroCellulare.getText(), "Numero Cellulare", txtNumeroCellulare);
	
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(txtNumeroCellulare.getText(), "Numero Cellulare", txtNumeroCellulare);
			}
		});
		txtNumeroCellulare.setForeground(Color.WHITE);
		txtNumeroCellulare.setFont(new Font("Century", Font.PLAIN, 20));
		txtNumeroCellulare.setColumns(10);
		txtNumeroCellulare.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		txtNumeroCellulare.setBackground(new Color(179, 168, 166));
		txtNumeroCellulare.setBounds(100, 99, 200, 41);
		anagraficaPanel_1.add(txtNumeroCellulare);
		
		JLabel immagineEmailLabel = new JLabel("");
		immagineEmailLabel.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/emailridimensionata.png")));
		immagineEmailLabel.setOpaque(true);
		immagineEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineEmailLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineEmailLabel.setBackground(new Color(179, 168, 166));
		immagineEmailLabel.setBounds(52, 47, 49, 41);
		anagraficaPanel_1.add(immagineEmailLabel);
		
		JLabel immagineNumerotelefonoLabel = new JLabel("");
		immagineNumerotelefonoLabel.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/telefonoridimensionato.png")));
		immagineNumerotelefonoLabel.setOpaque(true);
		immagineNumerotelefonoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineNumerotelefonoLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineNumerotelefonoLabel.setBackground(new Color(179, 168, 166));
		immagineNumerotelefonoLabel.setBounds(52, 99, 49, 41);
		anagraficaPanel_1.add(immagineNumerotelefonoLabel);
		
		gruppoRadioContatto = new ButtonGroup();
		
		emailRadio = new JRadioButton("E-Mail");
		emailRadio.setToolTipText("seleziona questo se vuoi filtrare per date di cosegna");
		emailRadio.setForeground(Color.WHITE);
		emailRadio.setFont(new Font("Century", Font.PLAIN, 16));
		emailRadio.setFocusPainted(false);
		emailRadio.setContentAreaFilled(false);
		emailRadio.setBounds(132, 179, 92, 24);
		anagraficaPanel_1.add(emailRadio);
		
		telefonoRadio = new JRadioButton("Telefono");
		telefonoRadio.setToolTipText("seleziona questo se vuoi filtrare per date di cosegna");
		telefonoRadio.setForeground(Color.WHITE);
		telefonoRadio.setFont(new Font("Century", Font.PLAIN, 16));
		telefonoRadio.setFocusPainted(false);
		telefonoRadio.setContentAreaFilled(false);
		telefonoRadio.setBounds(132, 206, 92, 24);
		anagraficaPanel_1.add(telefonoRadio);
		
		smsRadio = new JRadioButton("SMS");
		smsRadio.setToolTipText("seleziona questo se vuoi filtrare per date di cosegna");
		smsRadio.setForeground(Color.WHITE);
		smsRadio.setFont(new Font("Century", Font.PLAIN, 16));
		smsRadio.setFocusPainted(false);
		smsRadio.setContentAreaFilled(false);
		smsRadio.setBounds(132, 233, 92, 24);
		anagraficaPanel_1.add(smsRadio);
		
		gruppoRadioContatto.add(telefonoRadio);
		gruppoRadioContatto.add(emailRadio);
		gruppoRadioContatto.add(smsRadio);
		emailRadio.setSelected(true);
		
		JLabel lblPreferenzaContatto = new JLabel("Preferenza Contatto");
		lblPreferenzaContatto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreferenzaContatto.setForeground(Color.WHITE);
		lblPreferenzaContatto.setFont(new Font("Century", Font.PLAIN, 20));
		lblPreferenzaContatto.setBounds(10, 145, 338, 25);
		anagraficaPanel_1.add(lblPreferenzaContatto);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(119, 101, 101));
		panel.setBounds(69, 293, 715, 268);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton nuovoClienteBtn = new JButton("Registra Nuovo Cliente");
		nuovoClienteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Controllo l'esattezza e corttezza del'input
					controllaInput();
					
					//preparo i dati di input
					String RadioScelta = sceltaRadio();
				
					//Creo il nuovo cliente
					gestoreApplicazione.registraCliente(CodiceFiscaleTextField.getText(),nomeTextField.getText(),cognomeTextFild.getText(),dataDiNascita,txtEmail.getText(),txtNumeroCellulare.getText(),RadioScelta);
					gestoreApplicazione.mandaMailIscrizione(CodiceFiscaleTextField.getText(),nomeTextField.getText(),cognomeTextFild.getText(),dataDiNascita,txtEmail.getText(),txtNumeroCellulare.getText(),RadioScelta);
					messaggioPopUp("Registrazione nuovo cliente avvenuta con successo", "Registrazione Completata");
					
					
				} catch (CampoNomeVuotoException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getTipoErrore());
				} catch (CampoCognomeVuotoException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getTipoErrore());
				} catch (CampoCodiceFiscaleVuotoException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getTipoErrore());
				} catch (CampoEmailVuotoException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getTipoErrore());
				} catch (CampoNumeroCellulareVuotoException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getTipoErrore());
				} catch (DateVuoteException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getNomeErrore());
				} catch (NonPossibileCreareClienteException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getTipoErrore());
				}
			}
		});
		nuovoClienteBtn.setToolTipText("premi per creare un nuovo cliente");
		nuovoClienteBtn.setForeground(Color.WHITE);
		nuovoClienteBtn.setFont(new Font("Century", Font.BOLD, 20));
		nuovoClienteBtn.setFocusPainted(false);
		nuovoClienteBtn.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		nuovoClienteBtn.setBackground(new Color(254, 126, 115));
		nuovoClienteBtn.setBounds(228, 45, 265, 50);
		panel.add(nuovoClienteBtn);
	}
	
	private String sceltaRadio() {
		String scelta = "Email";
		if(telefonoRadio.isSelected())
			scelta = "Telefono";
		else if(smsRadio.isSelected())
			scelta = "SMS";

		return scelta;
	}

	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}
	
	private void controllaInput() throws CampoNomeVuotoException, CampoCognomeVuotoException, CampoCodiceFiscaleVuotoException, CampoEmailVuotoException, CampoNumeroCellulareVuotoException, DateVuoteException {
		if(nomeTextField.getText().equals("Nome"))
			throw new CampoNomeVuotoException();
		else if(cognomeTextFild.getText().equals("Cognome"))
			throw new CampoCognomeVuotoException();
		else if(CodiceFiscaleTextField.getText().equals("Codice Fiscale"))
			throw new CampoCodiceFiscaleVuotoException();	
		else if(txtEmail.getText().equals("E-Mail"))
			throw new CampoEmailVuotoException();	
		else if(txtNumeroCellulare.getText().equals("Numero Cellulare"))
			throw new CampoNumeroCellulareVuotoException();	
		else if(DataNascitaDataChoser.getDate() == null)
			throw new DateVuoteException();
		else
			dataDiNascita = DateToLocalDate(DataNascitaDataChoser.getDate());
			
		
		
	}

	private LocalDate DateToLocalDate(Date data) {
		return LocalDate.ofInstant(data.toInstant(), ZoneId.systemDefault());
	}

	private void confermaRitornareIndietro() {
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 19));
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di ritornare indietro", "Ritorna a crea spedizione",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.ritornaNuovoOrdine(this);
		
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

	
	
	private void rimpicciolisciGradualmenteBottoneIndietro() {
		//Diventa Piccolo gradualmente
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 19));
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 18));
		
	}
	
	private void vaiAvanti(JTextField primoCampo, JTextField secondoCampo, KeyEvent e) {
		//Mi permette di andare al prossimo TextFild con il semplice spazio
		if(e.getKeyCode() == 10 && !primoCampo.getText().isBlank() && !primoCampo.getText().isEmpty())
			secondoCampo.requestFocusInWindow();
		
	}
	
	private void ingradisciGradualmenteBottoneIndietro() {
		//Diventa grande gradualmente
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 19));
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 20));
	}
}
