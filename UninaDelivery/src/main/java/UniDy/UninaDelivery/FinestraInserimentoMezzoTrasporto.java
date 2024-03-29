package UniDy.UninaDelivery;


import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FinestraInserimentoMezzoTrasporto extends JFrame {

	private static final long serialVersionUID = 1L;
	//Amministratorre
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JPanel contentPane;
	private JButton indietroBottone ; 
	private JTextField targaTxf;
	private JTextField modelloTxf;
	private JTextField marcaTxf;
	private JTextField patenteTxf;
	private JSpinner costoFild;
	
	public FinestraInserimentoMezzoTrasporto(AppBrain appBrain) {

		setForeground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("UninaDelivery");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraInserimentoMezzoTrasporto.class.getResource("/Img/Icon.png")));
		gestoreApplicazione = appBrain;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel titoloLabel = new JLabel("Registrazione nuovo mezzo ");
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLabel.setForeground(Color.WHITE);
		titoloLabel.setFont(new Font("Century", Font.BOLD, 30));
		titoloLabel.setBounds(12, -1, 693, 37);
		homePanel.add(titoloLabel);
		
		JPanel parteSpecificheVeicoloPanel = new JPanel();
		parteSpecificheVeicoloPanel.setLayout(null);
		parteSpecificheVeicoloPanel.setBackground(new Color(119, 101, 101));
		parteSpecificheVeicoloPanel.setBounds(81, 48, 347, 357);
		contentPane.add(parteSpecificheVeicoloPanel);
		
		
		JLabel specificheVeicoloLabel = new JLabel("Specifiche veicolo");
		specificheVeicoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		specificheVeicoloLabel.setForeground(Color.WHITE);
		specificheVeicoloLabel.setFont(new Font("Century", Font.PLAIN, 20));
		specificheVeicoloLabel.setBounds(10, 11, 327, 25);
		parteSpecificheVeicoloPanel.add(specificheVeicoloLabel);
		
		JPanel parteAmministrativaPanel = new JPanel();
		parteAmministrativaPanel.setLayout(null);
		parteAmministrativaPanel.setBackground(new Color(119, 101, 101));
		parteAmministrativaPanel.setBounds(427, 48, 347, 357);
		contentPane.add(parteAmministrativaPanel);
		
		JLabel parteAmministrativaLabel = new JLabel("Parte amministrativa");
		parteAmministrativaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parteAmministrativaLabel.setForeground(Color.WHITE);
		parteAmministrativaLabel.setFont(new Font("Century", Font.PLAIN, 20));
		parteAmministrativaLabel.setBounds(10, 11, 327, 25);
		parteAmministrativaPanel.add(parteAmministrativaLabel);
		
		JLabel immagineCapienzaLabel = new JLabel("");
		immagineCapienzaLabel.setIcon(new ImageIcon(FinestraInserimentoMezzoTrasporto.class.getResource("/Img/maxsize.png")));
		immagineCapienzaLabel.setOpaque(true);
		immagineCapienzaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCapienzaLabel.setBackground(new Color(179, 168, 166));
		immagineCapienzaLabel.setBounds(42, 169, 77, 41);
		parteAmministrativaPanel.add(immagineCapienzaLabel);
		
		JLabel immagineCostoAssicurazioneLabel = new JLabel("");
		immagineCostoAssicurazioneLabel.setIcon(new ImageIcon(FinestraInserimentoMezzoTrasporto.class.getResource("/Img/dollarridimensionato.png")));
		immagineCostoAssicurazioneLabel.setOpaque(true);
		immagineCostoAssicurazioneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCostoAssicurazioneLabel.setBackground(new Color(179, 168, 166));
		immagineCostoAssicurazioneLabel.setBounds(42, 257, 77, 41);
		parteAmministrativaPanel.add(immagineCostoAssicurazioneLabel);
		
		costoFild = new JSpinner();
		costoFild.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		costoFild.setToolTipText("Inserisci costo assicurazione");
		costoFild.setModel(new SpinnerNumberModel(Float.valueOf(1), Float.valueOf(1), null, Float.valueOf(1)));
		costoFild.setForeground(Color.WHITE);
		costoFild.setFont(new Font("Century", Font.PLAIN, 20));
		costoFild.setBackground(new Color(179, 168, 166));
		costoFild.setBounds(119, 257, 200, 41);
		parteAmministrativaPanel.add(costoFild);
		
		JLabel lblCostoAssicurazione = new JLabel("Costo assicurazione");
		lblCostoAssicurazione.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostoAssicurazione.setForeground(Color.WHITE);
		lblCostoAssicurazione.setFont(new Font("Century", Font.PLAIN, 20));
		lblCostoAssicurazione.setBounds(10, 221, 337, 25);
		parteAmministrativaPanel.add(lblCostoAssicurazione);
		
		JSpinner capienzaFild = new JSpinner();
		capienzaFild.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		capienzaFild.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		capienzaFild.setToolTipText("Inserisci capienza del mezzo");
		capienzaFild.setForeground(Color.WHITE);
		capienzaFild.setFont(new Font("Century", Font.PLAIN, 20));
		capienzaFild.setBackground(new Color(179, 168, 166));
		capienzaFild.setBounds(119, 169, 200, 41);
		parteAmministrativaPanel.add(capienzaFild);
		
		JLabel lblCapienza = new JLabel("Capienza");
		lblCapienza.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapienza.setForeground(Color.WHITE);
		lblCapienza.setFont(new Font("Century", Font.PLAIN, 20));
		lblCapienza.setBounds(10, 133, 327, 25);
		parteAmministrativaPanel.add(lblCapienza);
		
		patenteTxf = new JTextField();
		patenteTxf.setBounds(118, 81, 200, 41);
		parteAmministrativaPanel.add(patenteTxf);
		patenteTxf.setToolTipText("Inserisci le patenti necessarie (separate da  ';')");
		patenteTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(patenteTxf.getText(),"Patenti",patenteTxf);
			}
			
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(patenteTxf.getText(),"Patenti",patenteTxf);
			}
		});
		patenteTxf.setText("Patenti");
		patenteTxf.setForeground(Color.WHITE);
		patenteTxf.setFont(new Font("Century", Font.PLAIN, 20));
		patenteTxf.setColumns(10);
		patenteTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		patenteTxf.setBackground(new Color(179, 168, 166));
		
		JLabel immaginePatenteLabel = new JLabel("");
		immaginePatenteLabel.setBounds(41, 81, 77, 41);
		parteAmministrativaPanel.add(immaginePatenteLabel);
		immaginePatenteLabel.setIcon(new ImageIcon(FinestraInserimentoMezzoTrasporto.class.getResource("/Img/patentridimensionato.png")));
		immaginePatenteLabel.setOpaque(true);
		immaginePatenteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immaginePatenteLabel.setBackground(new Color(179, 168, 166));
		
		
		JLabel immagineTargaLabel = new JLabel("");
		parteSpecificheVeicoloPanel.add(immagineTargaLabel);
		immagineTargaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineTargaLabel.setIcon(new ImageIcon(FinestraInserimentoMezzoTrasporto.class.getResource("/Img/targaridimensionata.png")));
		immagineTargaLabel.setBounds(35, 81, 77, 41);
		immagineTargaLabel.setBackground(new Color(179, 168, 166));
		immagineTargaLabel.setOpaque(true);
		
		targaTxf = new JTextField();
		targaTxf.setToolTipText("Inserisci la targa");
		targaTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(targaTxf.getText() , "Targa" ,targaTxf );
				vaiAvanti(targaTxf,marcaTxf,e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(targaTxf.getText(),"Targa",targaTxf);
			}
		});
		targaTxf.setText("Targa");
		targaTxf.setForeground(Color.WHITE);
		targaTxf.setFont(new Font("Century", Font.PLAIN, 20));
		targaTxf.setColumns(10);
		targaTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		targaTxf.setBackground(new Color(179, 168, 166));
		targaTxf.setBounds(112, 81, 200, 41);
		parteSpecificheVeicoloPanel.add(targaTxf);
		
		JLabel immagineModelloLabel = new JLabel("");
		immagineModelloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineModelloLabel.setIcon(new ImageIcon(FinestraInserimentoMezzoTrasporto.class.getResource("/Img/mezzoridimensionato.png")));
		immagineModelloLabel.setOpaque(true);
		immagineModelloLabel.setBackground(new Color(179, 168, 166));
		immagineModelloLabel.setBounds(35, 257, 77, 41);
		parteSpecificheVeicoloPanel.add(immagineModelloLabel);
		
		modelloTxf = new JTextField();
		modelloTxf.setToolTipText("Inserisci il modello");
		modelloTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(modelloTxf.getText(),"Modello",modelloTxf);
				vaiAvanti(modelloTxf,patenteTxf,e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(modelloTxf.getText(),"Modello",modelloTxf);
			}
		});
		modelloTxf.setText("Modello");
		modelloTxf.setForeground(Color.WHITE);
		modelloTxf.setFont(new Font("Century", Font.PLAIN, 20));
		modelloTxf.setColumns(10);
		modelloTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		modelloTxf.setBackground(new Color(179, 168, 166));
		modelloTxf.setBounds(112, 257, 200, 41);
		parteSpecificheVeicoloPanel.add(modelloTxf);
		
		JLabel immagineMarcaLabel = new JLabel("");
		immagineMarcaLabel.setIcon(new ImageIcon(FinestraInserimentoMezzoTrasporto.class.getResource("/Img/brandridimensionato.png")));
		immagineMarcaLabel.setOpaque(true);
		immagineMarcaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineMarcaLabel.setBackground(new Color(179, 168, 166));
		immagineMarcaLabel.setBounds(35, 169, 77, 41);
		parteSpecificheVeicoloPanel.add(immagineMarcaLabel);
		
		marcaTxf = new JTextField();
		marcaTxf.setToolTipText("Inserisci la marca");
		marcaTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(marcaTxf.getText(),"Marca",marcaTxf);
				vaiAvanti(marcaTxf,modelloTxf,e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(marcaTxf.getText(),"Marca",marcaTxf);
			}
		});
		marcaTxf.setText("Marca");
		marcaTxf.setForeground(Color.WHITE);
		marcaTxf.setFont(new Font("Century", Font.PLAIN, 20));
		marcaTxf.setColumns(10);
		marcaTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		marcaTxf.setBackground(new Color(179, 168, 166));
		marcaTxf.setBounds(112, 169, 200, 41);
		parteSpecificheVeicoloPanel.add(marcaTxf);
		
		JButton nuovoMezzoBtn = new JButton("Registra Nuovo Mezzo");
		nuovoMezzoBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nuovoMezzoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controlloInput();
					gestoreApplicazione.registraMezzo(targaTxf.getText() , marcaTxf.getText() , modelloTxf.getText() , (int) capienzaFild.getValue() , patenteTxf.getText() , (float) costoFild.getValue());
					messaggioPopUp("Hai registrato un nuovo mezzo", "Registrazione mezzo di trasporto");
				} catch (UninaDeliveryException Errore) {
					messaggioPopUp(Errore.getMessaggioErrore(),Errore.getTipoErrore());
				} catch (UninaDeliverySQLException ErroreSQL) {
					messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());	
				}
			}
		});
		nuovoMezzoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				nuovoMezzoBtn.setBackground(new Color(254, 114, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nuovoMezzoBtn.setBackground(new Color(254, 126, 115));
			}
		});
		nuovoMezzoBtn.setToolTipText("premi per registrare un nuovo mezzo");
		nuovoMezzoBtn.setForeground(Color.WHITE);
		nuovoMezzoBtn.setFont(new Font("Century", Font.BOLD, 20));
		nuovoMezzoBtn.setFocusPainted(false);
		nuovoMezzoBtn.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		nuovoMezzoBtn.setBackground(new Color(254, 126, 115));
		nuovoMezzoBtn.setBounds(296, 438, 265, 50);
		contentPane.add(nuovoMezzoBtn);
		
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Si è verificato un errore: " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
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

	private void resetCampi() {
		targaTxf.setText("Targa");
		modelloTxf.setText("Modello");
		marcaTxf.setText("Marca");
		patenteTxf.setText("Patenti");
	}
	
	private void confermaRitornareIndietro() {
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 19));
		indietroBottone.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di ritornare indietro?", "Ritorna a crea spedizione",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.ritornaNuovaSpedizione(this);
		
	}
	
	//Procedura che ci permetterà di mostrare con un messaggio PopUp  
	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
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
	
	private void autoDelate(String testoDentro, String testoOrginale, JTextField txtFild) {
		//svuotare appena si scrive
		if(testoDentro.equals(testoOrginale))
			txtFild.setText("");
	}
	
	private void controlloInput() throws CampoTargaVuotoException, CampoModelloVuotoException, CampoMarcaVuotoException, CampoPatentiVuotoException {
		
		if (targaTxf.getText().isBlank() || targaTxf.getText().equals("Targa")) 
	        throw new CampoTargaVuotoException();

	    if (marcaTxf.getText().isBlank() || marcaTxf.getText().equals("Marca"))
	        throw new CampoMarcaVuotoException();  

	    if (modelloTxf.getText().isBlank() || modelloTxf.getText().equals("Modello"))
	        throw new CampoModelloVuotoException();

	    if (patenteTxf.getText().isBlank() || patenteTxf.getText().equals("Patenti"))
	        throw new CampoPatentiVuotoException();
		
	}
	
}
