package UniDy.UninaDelivery;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Cursor;

public class FinestraCreazioneNuovoMerce extends JFrame {

	private static final long serialVersionUID = 1L;
	//Amministratore
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JButton indietroBottone;
	private JTextField nomeField;
	private JSpinner txtPeso;
	private JTextField txtMarca;
	private JSpinner annoProduioneUscita;
	//Gestione ComboBox
	private JComboBox fornitoriBox;
	
	public FinestraCreazioneNuovoMerce(AppBrain appBrain) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraCreazioneNuovoMerce.class.getResource("/Img/Icon.png")));
		gestoreApplicazione = appBrain;
		setTitle("UninaDelivery");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 600);
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
		titoloSXL.setIcon(new ImageIcon(FinestraCreazioneNuovoMerce.class.getResource("/Img/SxTitoloImg.jpg")));
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloSXL.setBounds(10, 76, 45, 474);
		intestazionePanel.add(titoloSXL);
		
		JLabel logoSXImgL = new JLabel("New Label");
		logoSXImgL.setIcon(new ImageIcon(FinestraCreazioneNuovoMerce.class.getResource("/Img/LogoHSX.png")));
		logoSXImgL.setBounds(0, 0, 71, 65);
		intestazionePanel.add(logoSXImgL);
		
		JPanel homePanel = new JPanel();
		homePanel.setLayout(null);
		homePanel.setBackground(new Color(119, 101, 101));
		homePanel.setBounds(68, 0, 724, 37);
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
				try {
					confermaRitornareIndietro();
					
				} catch (UninaDeliveryException Errore) {
					messaggioPopUp(Errore.getMessaggioErrore(),Errore.getTipoErrore());
				} catch (UninaDeliverySQLException ErroreSQL) {
					messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());
				}
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
		indietroBottone.setBounds(637, 11, 77, 22);
		homePanel.add(indietroBottone);
		
		JLabel titoloLabel = new JLabel("Creazione Nuova Merce");
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLabel.setForeground(Color.WHITE);
		titoloLabel.setFont(new Font("Century", Font.BOLD, 30));
		titoloLabel.setBounds(12, -1, 693, 37);
		homePanel.add(titoloLabel);
		
		JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setLayout(null);
		anagraficaPanel.setBackground(new Color(119, 101, 101));
		anagraficaPanel.setBounds(81, 48, 701, 335);
		contentPane.add(anagraficaPanel);
		
		JLabel specificheTitoloLabel = new JLabel("Macro Specifiche Prodotto");
		specificheTitoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		specificheTitoloLabel.setForeground(Color.WHITE);
		specificheTitoloLabel.setFont(new Font("Century", Font.PLAIN, 20));
		specificheTitoloLabel.setBounds(10, 47, 341, 25);
		anagraficaPanel.add(specificheTitoloLabel);
		
		nomeField = new JTextField();
		nomeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(nomeField.getText(), "Nome", nomeField);
				vaiAvanti(nomeField, txtMarca, e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(nomeField.getText(), "Nome", nomeField);
				
			}
		});
		nomeField.setToolTipText("inserisci nome");
		nomeField.setText("Nome");
		nomeField.setForeground(Color.WHITE);
		nomeField.setFont(new Font("Century", Font.PLAIN, 20));
		nomeField.setColumns(10);
		nomeField.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		nomeField.setBackground(new Color(179, 168, 166));
		nomeField.setBounds(104, 80, 200, 41);
		anagraficaPanel.add(nomeField);
		
		txtPeso = new JSpinner();
		txtPeso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtPeso.setModel(new SpinnerNumberModel(Float.valueOf(1), Float.valueOf(0), null, Float.valueOf(1)));
		txtPeso.setToolTipText("inserisci peso");
		txtPeso.setForeground(Color.WHITE);
		txtPeso.setFont(new Font("Century", Font.PLAIN, 20));
		txtPeso.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		txtPeso.setBackground(new Color(179, 168, 166));
		txtPeso.setBounds(104, 224, 200, 41);
		anagraficaPanel.add(txtPeso);
		
		txtMarca = new JTextField();
		txtMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(txtMarca.getText(), "Marca", txtMarca);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(nomeField.getText(), "Nome", nomeField);
			}
		});
		txtMarca.setToolTipText("inserisci marca");
		txtMarca.setText("Marca");
		txtMarca.setForeground(Color.WHITE);
		txtMarca.setFont(new Font("Century", Font.PLAIN, 20));
		txtMarca.setColumns(10);
		txtMarca.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		txtMarca.setBackground(new Color(179, 168, 166));
		txtMarca.setBounds(104, 136, 200, 41);
		anagraficaPanel.add(txtMarca);
		
		JLabel logoCitta_1 = new JLabel("New label");
		logoCitta_1.setBackground(new Color(179, 168, 166));
		logoCitta_1.setIcon(new ImageIcon(FinestraCreazioneNuovoMerce.class.getResource("/Img/badge.png")));
		logoCitta_1.setOpaque(true);
		logoCitta_1.setBorder(new LineBorder(new Color(179, 168, 166), 3, true));
		logoCitta_1.setBounds(56, 136, 49, 41);
		anagraficaPanel.add(logoCitta_1);
		
		JLabel immagineNomeLabel = new JLabel("");
		immagineNomeLabel.setIcon(new ImageIcon(FinestraCreazioneNuovoMerce.class.getResource("/Img/nameridimensionata.png")));
		immagineNomeLabel.setOpaque(true);
		immagineNomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineNomeLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineNomeLabel.setBackground(new Color(179, 168, 166));
		immagineNomeLabel.setBounds(56, 80, 49, 41);
		anagraficaPanel.add(immagineNomeLabel);
		
		JLabel immagineCognomeLabel = new JLabel("");
		immagineCognomeLabel.setIcon(new ImageIcon(FinestraCreazioneNuovoMerce.class.getResource("/Img/kilogram.png")));
		immagineCognomeLabel.setOpaque(true);
		immagineCognomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCognomeLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineCognomeLabel.setBackground(new Color(179, 168, 166));
		immagineCognomeLabel.setBounds(56, 224, 49, 41);
		anagraficaPanel.add(immagineCognomeLabel);
		
		JLabel immagineCognomeLabel_1 = new JLabel("");
		immagineCognomeLabel_1.setIcon(new ImageIcon(FinestraCreazioneNuovoMerce.class.getResource("/Img/BDate.png")));
		immagineCognomeLabel_1.setOpaque(true);
		immagineCognomeLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCognomeLabel_1.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineCognomeLabel_1.setBackground(new Color(179, 168, 166));
		immagineCognomeLabel_1.setBounds(402, 83, 49, 41);
		anagraficaPanel.add(immagineCognomeLabel_1);
		
		annoProduioneUscita = new JSpinner();
		annoProduioneUscita.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		annoProduioneUscita.setModel(new SpinnerNumberModel(Integer.valueOf(2000), null, null, Integer.valueOf(1)));
		annoProduioneUscita.setToolTipText("Inserisci stipendio contratto");
		annoProduioneUscita.setForeground(Color.WHITE);
		annoProduioneUscita.setFont(new Font("Century", Font.PLAIN, 20));
		annoProduioneUscita.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		annoProduioneUscita.setBackground(new Color(179, 168, 166));
		annoProduioneUscita.setBounds(450, 83, 200, 41);
		anagraficaPanel.add(annoProduioneUscita);
		
		JLabel lblAnnoDiProduzione = new JLabel("Anno Di Produzione");
		lblAnnoDiProduzione.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnoDiProduzione.setForeground(Color.WHITE);
		lblAnnoDiProduzione.setFont(new Font("Century", Font.PLAIN, 20));
		lblAnnoDiProduzione.setBounds(350, 47, 341, 25);
		anagraficaPanel.add(lblAnnoDiProduzione);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeso.setForeground(Color.WHITE);
		lblPeso.setFont(new Font("Century", Font.PLAIN, 20));
		lblPeso.setBounds(10, 187, 331, 25);
		anagraficaPanel.add(lblPeso);
		
		JLabel lblFornitore = new JLabel("Fornitore");
		lblFornitore.setHorizontalAlignment(SwingConstants.CENTER);
		lblFornitore.setForeground(Color.WHITE);
		lblFornitore.setFont(new Font("Century", Font.PLAIN, 20));
		lblFornitore.setBounds(350, 136, 341, 25);
		anagraficaPanel.add(lblFornitore);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(452, 173, 198, 39);
		anagraficaPanel.add(scrollPane);
		
		fornitoriBox = new JComboBox();
		fornitoriBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(fornitoriBox);
		fornitoriBox.setToolTipText("Qui puoi selezionare il fornitore");
		fornitoriBox.setForeground(Color.WHITE);
		fornitoriBox.setFont(new Font("Century", Font.PLAIN, 20));
		fornitoriBox.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		fornitoriBox.setBackground(new Color(179, 168, 166));
		
		JLabel immagineCodiceFiscaleLabel_1 = new JLabel("");
		immagineCodiceFiscaleLabel_1.setIcon(new ImageIcon(FinestraCreazioneNuovoMerce.class.getResource("/Img/supervisore.png")));
		immagineCodiceFiscaleLabel_1.setOpaque(true);
		immagineCodiceFiscaleLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCodiceFiscaleLabel_1.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineCodiceFiscaleLabel_1.setBackground(new Color(179, 168, 166));
		immagineCodiceFiscaleLabel_1.setBounds(402, 172, 49, 41);
		anagraficaPanel.add(immagineCodiceFiscaleLabel_1);
		
		JButton btnCreaNuovaMerce = new JButton("Crea Nuova Merce");
		btnCreaNuovaMerce.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreaNuovaMerce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controlloInput();
					gestoreApplicazione.creaNuovaMerce(nomeField.getText(),(float)txtPeso.getValue(),txtMarca.getText(),(int)annoProduioneUscita.getValue(),fornitoriBox.getSelectedIndex());
					messaggioPopUp("Merce inserita correttamente nel DataBase", "Inserimento riuscito");
				} catch (UninaDeliveryException Errore) {
					messaggioPopUp(Errore.getMessaggioErrore(),Errore.getTipoErrore());
				}
				
			}
		});
		btnCreaNuovaMerce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreaNuovaMerce.setBackground(new Color(254, 114, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCreaNuovaMerce.setBackground(new Color(254, 126, 115));
			}
		});
		btnCreaNuovaMerce.setToolTipText("premi per creare una nuova merce");
		btnCreaNuovaMerce.setForeground(Color.WHITE);
		btnCreaNuovaMerce.setFont(new Font("Century", Font.BOLD, 20));
		btnCreaNuovaMerce.setFocusPainted(false);
		btnCreaNuovaMerce.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		btnCreaNuovaMerce.setBackground(new Color(254, 126, 115));
		btnCreaNuovaMerce.setBounds(299, 421, 265, 50);
		contentPane.add(btnCreaNuovaMerce);
	}
	

	private void controlloInput() throws CampoNomeVuotoException, CampoPesoVuotoException, CampoMarcaVuotoException {
		if(nomeField.getText().equals("Nome"))
			throw new CampoNomeVuotoException();
		else if ( (float) txtPeso.getValue() ==  0)
			throw new CampoPesoVuotoException();
		else if (txtMarca.getText().equals("Marca"))
			throw new CampoMarcaVuotoException();	
		
	}
	
	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}
	
	protected void avviati() {
		
		try {
			fornitoriBox.setModel(new DefaultComboBoxModel(gestoreApplicazione.dammiFormatoComboBoxFornitori()));
		} catch (UninaDeliveryException Errore) {
			messaggioPopUp(Errore.getMessaggioErrore(),Errore.getTipoErrore());
		} catch (UninaDeliverySQLException ErroreSQL) {
			messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());
		}
	
	}	

	private void confermaRitornareIndietro() throws RisultatoNonRicavabileException, NonCiSonoMerciDisponibiliException {
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 19));
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di ritornare indietro", "Ritorna a crea spedizione",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.ritornaEsemplare(this);
		
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
