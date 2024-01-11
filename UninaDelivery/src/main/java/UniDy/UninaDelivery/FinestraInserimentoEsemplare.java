package UniDy.UninaDelivery;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

public class FinestraInserimentoEsemplare extends JFrame {

	private static final long serialVersionUID = 1L;
	//Amministratore
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JButton indietroBottone;
	private JTextField codiceABarreTxf;
	private JTextField coloreTxf;
	private JComboBox merceBox;
	private JComboBox magazzinoBox;
	private JDateChooser dataFineGaranziaDataChoser;
	//oggetti utili
	private LocalDate garanzia;
	
	public FinestraInserimentoEsemplare(AppBrain appBrain ) {
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
		titoloSXL.setIcon(new ImageIcon(FinestraInserimentoEsemplare.class.getResource("/Img/SxTitoloImg.jpg")));
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloSXL.setBounds(10, 76, 45, 474);
		intestazionePanel.add(titoloSXL);
		
		JLabel logoSXImgL = new JLabel("New Label");
		logoSXImgL.setIcon(new ImageIcon(FinestraInserimentoEsemplare.class.getResource("/Img/LogoHSX.png")));
		logoSXImgL.setBounds(0, 0, 71, 65);
		intestazionePanel.add(logoSXImgL);
		
		JLabel lblCreazioneNuovoEsemplare = new JLabel("Creazione nuovo esemplare");
		lblCreazioneNuovoEsemplare.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreazioneNuovoEsemplare.setForeground(Color.WHITE);
		lblCreazioneNuovoEsemplare.setFont(new Font("Century", Font.BOLD, 30));
		lblCreazioneNuovoEsemplare.setBounds(81, 0, 693, 37);
		contentPane.add(lblCreazioneNuovoEsemplare);
		
		 indietroBottone = new JButton("Indietro");
		 indietroBottone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 indietroBottone.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyPressed(KeyEvent e) {
		 		
		 	}
		 });
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
		indietroBottone.setBounds(697, 12, 77, 22);
		contentPane.add(indietroBottone);
		
		JPanel parteSpecificheVeicoloPanel = new JPanel();
		parteSpecificheVeicoloPanel.setLayout(null);
		parteSpecificheVeicoloPanel.setBackground(new Color(119, 101, 101));
		parteSpecificheVeicoloPanel.setBounds(81, 48, 347, 397);
		contentPane.add(parteSpecificheVeicoloPanel);
		
		JLabel SpecificaProdottolbl = new JLabel("Specifica prodotto");
		SpecificaProdottolbl.setHorizontalAlignment(SwingConstants.CENTER);
		SpecificaProdottolbl.setForeground(Color.WHITE);
		SpecificaProdottolbl.setFont(new Font("Century", Font.PLAIN, 20));
		SpecificaProdottolbl.setBounds(10, 11, 327, 25);
		parteSpecificheVeicoloPanel.add(SpecificaProdottolbl);
		
		JLabel immagineCodiceABarreLabel = new JLabel("");
		immagineCodiceABarreLabel.setIcon(new ImageIcon(FinestraInserimentoEsemplare.class.getResource("/Img/barcode-product.png")));
		immagineCodiceABarreLabel.setOpaque(true);
		immagineCodiceABarreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCodiceABarreLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineCodiceABarreLabel.setBackground(new Color(179, 168, 166));
		immagineCodiceABarreLabel.setBounds(46, 47, 49, 41);
		parteSpecificheVeicoloPanel.add(immagineCodiceABarreLabel);
		
		codiceABarreTxf = new JTextField();
		codiceABarreTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(codiceABarreTxf.getText(),"Codice a barre",codiceABarreTxf);
				vaiAvanti(codiceABarreTxf,coloreTxf,e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(codiceABarreTxf.getText(),"Codice a barre",codiceABarreTxf);
			}
		});
		
		codiceABarreTxf.setToolTipText("Inserisci codice a barre");
		codiceABarreTxf.setText("Codice a barre");
		codiceABarreTxf.setForeground(Color.WHITE);
		codiceABarreTxf.setFont(new Font("Century", Font.PLAIN, 20));
		codiceABarreTxf.setColumns(10);
		codiceABarreTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		codiceABarreTxf.setBackground(new Color(179, 168, 166));
		codiceABarreTxf.setBounds(95, 47, 200, 41);
		parteSpecificheVeicoloPanel.add(codiceABarreTxf);
		
		JLabel immagineCostoLabel = new JLabel("");
		immagineCostoLabel.setIcon(new ImageIcon(FinestraInserimentoEsemplare.class.getResource("/Img/dollarridimensionato.png")));
		immagineCostoLabel.setOpaque(true);
		immagineCostoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCostoLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineCostoLabel.setBackground(new Color(179, 168, 166));
		immagineCostoLabel.setBounds(46, 222, 49, 45);
		parteSpecificheVeicoloPanel.add(immagineCostoLabel);
		
		JLabel immagineColoreLabel = new JLabel("");
		immagineColoreLabel.setIcon(new ImageIcon(FinestraInserimentoEsemplare.class.getResource("/Img/color-palette.png")));
		immagineColoreLabel.setOpaque(true);
		immagineColoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineColoreLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineColoreLabel.setBackground(new Color(179, 168, 166));
		immagineColoreLabel.setBounds(46, 99, 49, 45);
		parteSpecificheVeicoloPanel.add(immagineColoreLabel);
		
		coloreTxf = new JTextField();
		coloreTxf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(coloreTxf.getText(),"Colore",coloreTxf);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				autoRitornaTesto(coloreTxf.getText(),"Colore",coloreTxf);
			}
		});
		coloreTxf.setToolTipText("Inserisci colore");
		coloreTxf.setText("Colore");
		coloreTxf.setForeground(Color.WHITE);
		coloreTxf.setFont(new Font("Century", Font.PLAIN, 20));
		coloreTxf.setColumns(10);
		coloreTxf.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(255, 255, 255)));
		coloreTxf.setBackground(new Color(179, 168, 166));
		coloreTxf.setBounds(95, 99, 200, 45);
		parteSpecificheVeicoloPanel.add(coloreTxf);
		
		JSpinner costoFild = new JSpinner();
		costoFild.setModel(new SpinnerNumberModel(Float.valueOf(1), Float.valueOf(1), null, Float.valueOf(1)));
		costoFild.setToolTipText("Inserisci stipendio contratto");
		costoFild.setForeground(Color.WHITE);
		costoFild.setFont(new Font("Century", Font.PLAIN, 20));
		costoFild.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		costoFild.setBackground(new Color(179, 168, 166));
		costoFild.setBounds(95, 222, 200, 45);
		parteSpecificheVeicoloPanel.add(costoFild);
		
		JLabel immagineCostoLabel_1 = new JLabel("");
		immagineCostoLabel_1.setIcon(new ImageIcon(FinestraInserimentoEsemplare.class.getResource("/Img/product-description.png")));
		immagineCostoLabel_1.setOpaque(true);
		immagineCostoLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		immagineCostoLabel_1.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineCostoLabel_1.setBackground(new Color(179, 168, 166));
		immagineCostoLabel_1.setBounds(46, 323, 49, 45);
		parteSpecificheVeicoloPanel.add(immagineCostoLabel_1);
		
		JLabel lblNewLabel = new JLabel("Descrizione");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Century", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 285, 327, 25);
		parteSpecificheVeicoloPanel.add(lblNewLabel);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrezzo.setForeground(Color.WHITE);
		lblPrezzo.setFont(new Font("Century", Font.PLAIN, 20));
		lblPrezzo.setBounds(10, 186, 327, 25);
		parteSpecificheVeicoloPanel.add(lblPrezzo);
		
		JPanel panel = new JPanel();
		panel.setBounds(95, 323, 200, 45);
		parteSpecificheVeicoloPanel.add(panel);
		panel.setLayout(null);
	
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 200, 45);
		panel.add(scrollPane);
		
		
		JTextArea descrizioneText = new JTextArea();
		scrollPane.setViewportView(descrizioneText);
		
		JPanel parteAmministrativaPanel = new JPanel();
		parteAmministrativaPanel.setLayout(null);
		parteAmministrativaPanel.setBackground(new Color(119, 101, 101));
		parteAmministrativaPanel.setBounds(438, 48, 347, 397);
		contentPane.add(parteAmministrativaPanel);
		
		JLabel parteAmministrativaLabel = new JLabel("Parte amministrativa");
		parteAmministrativaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parteAmministrativaLabel.setForeground(Color.WHITE);
		parteAmministrativaLabel.setFont(new Font("Century", Font.PLAIN, 20));
		parteAmministrativaLabel.setBounds(10, 11, 327, 25);
		parteAmministrativaPanel.add(parteAmministrativaLabel);
		
		JLabel immagineDataFineGaranziaLabel = new JLabel("");
		immagineDataFineGaranziaLabel.setIcon(new ImageIcon(FinestraInserimentoEsemplare.class.getResource("/Img/garanzia.png")));
		immagineDataFineGaranziaLabel.setOpaque(true);
		immagineDataFineGaranziaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineDataFineGaranziaLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineDataFineGaranziaLabel.setBackground(new Color(179, 168, 166));
		immagineDataFineGaranziaLabel.setBounds(46, 84, 49, 41);
		parteAmministrativaPanel.add(immagineDataFineGaranziaLabel);
		
		dataFineGaranziaDataChoser = new JDateChooser();
		dataFineGaranziaDataChoser.getCalendarButton().setFont(new Font("Century", Font.PLAIN, 18));
		dataFineGaranziaDataChoser.getCalendarButton().setBackground(Color.WHITE);
		dataFineGaranziaDataChoser.setToolTipText("Inserisci data di nascita");
		dataFineGaranziaDataChoser.setFont(new Font("Century", Font.PLAIN, 18));
		dataFineGaranziaDataChoser.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		dataFineGaranziaDataChoser.setBackground(new Color(179, 168, 166));
		dataFineGaranziaDataChoser.setBounds(94, 84, 200, 41);
		parteAmministrativaPanel.add(dataFineGaranziaDataChoser);
		
		JLabel lblDataFineGaranzia = new JLabel("Data fine garanzia");
		lblDataFineGaranzia.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataFineGaranzia.setForeground(Color.WHITE);
		lblDataFineGaranzia.setFont(new Font("Century", Font.PLAIN, 20));
		lblDataFineGaranzia.setBounds(10, 47, 327, 25);
		parteAmministrativaPanel.add(lblDataFineGaranzia);
		
		JLabel immagineMerceLabel = new JLabel("");
		immagineMerceLabel.setIcon(new ImageIcon(FinestraInserimentoEsemplare.class.getResource("/Img/esemplari.png")));
		immagineMerceLabel.setOpaque(true);
		immagineMerceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		immagineMerceLabel.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineMerceLabel.setBackground(new Color(179, 168, 166));
		immagineMerceLabel.setBounds(46, 194, 49, 41);
		parteAmministrativaPanel.add(immagineMerceLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(96, 194, 198, 41);
		parteAmministrativaPanel.add(scrollPane_1);
		
		merceBox = new JComboBox();
		scrollPane_1.setViewportView(merceBox);
		merceBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		merceBox.setToolTipText("Qui puoi selezionare la merce");
		merceBox.setForeground(Color.WHITE);
		merceBox.setFont(new Font("Century", Font.PLAIN, 20));
		merceBox.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		merceBox.setBackground(new Color(179, 168, 166));
		
		JLabel lblSelezionaMerce = new JLabel("Seleziona merce");
		lblSelezionaMerce.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelezionaMerce.setForeground(Color.WHITE);
		lblSelezionaMerce.setFont(new Font("Century", Font.PLAIN, 20));
		lblSelezionaMerce.setBounds(10, 158, 327, 25);
		parteAmministrativaPanel.add(lblSelezionaMerce);
		
		JLabel immagineMerceLabel_1 = new JLabel("");
		immagineMerceLabel_1.setIcon(new ImageIcon(FinestraInserimentoEsemplare.class.getResource("/Img/warehouse.png")));
		immagineMerceLabel_1.setOpaque(true);
		immagineMerceLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		immagineMerceLabel_1.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		immagineMerceLabel_1.setBackground(new Color(179, 168, 166));
		immagineMerceLabel_1.setBounds(46, 329, 49, 41);
		parteAmministrativaPanel.add(immagineMerceLabel_1);
		
		magazzinoBox = new JComboBox();
		magazzinoBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		magazzinoBox.setToolTipText("Qui puoi selezionare il magazzino");
		magazzinoBox.setForeground(Color.WHITE);
		magazzinoBox.setFont(new Font("Century", Font.PLAIN, 20));
		magazzinoBox.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		magazzinoBox.setBackground(new Color(179, 168, 166));
		magazzinoBox.setBounds(96, 329, 198, 41);
		parteAmministrativaPanel.add(magazzinoBox);
		
		JLabel lblSelezionaMagazzinoIn = new JLabel("Seleziona magazzino in cui sita");
		lblSelezionaMagazzinoIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelezionaMagazzinoIn.setForeground(Color.WHITE);
		lblSelezionaMagazzinoIn.setFont(new Font("Century", Font.PLAIN, 20));
		lblSelezionaMagazzinoIn.setBounds(10, 293, 327, 25);
		parteAmministrativaPanel.add(lblSelezionaMagazzinoIn);
		
		JButton btnAggiungiMerce = new JButton("Aggiungi Merce");
		btnAggiungiMerce.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAggiungiMerce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimpicciolisciGradualmenteBottoneAdd(btnAggiungiMerce);
				gestoreApplicazione.mostraFinestraCreazioneMerce();
			}
		});
		btnAggiungiMerce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneAdd(btnAggiungiMerce);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneAdd(btnAggiungiMerce);
			}
		});
		btnAggiungiMerce.setToolTipText("Qui puoi crere una nuova merce");
		btnAggiungiMerce.setForeground(new Color(254, 126, 115));
		btnAggiungiMerce.setFont(new Font("Century", Font.PLAIN, 18));
		btnAggiungiMerce.setFocusPainted(false);
		btnAggiungiMerce.setContentAreaFilled(false);
		btnAggiungiMerce.setBorderPainted(false);
		btnAggiungiMerce.setBounds(76, 246, 198, 33);
		parteAmministrativaPanel.add(btnAggiungiMerce);
		
		JButton btnRegistraNuovaMerce = new JButton("Registra nuovo esemplare");
		btnRegistraNuovaMerce.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					sonoNonVuoti();
					
					gestoreApplicazione.creaEsemplare(codiceABarreTxf.getText(), coloreTxf.getText(),(float)costoFild.getValue(),garanzia,descrizioneText.getText(),merceBox.getSelectedIndex(),magazzinoBox.getSelectedIndex());
					messaggioPopUp("Esemplare registrato correttamente", "Registrazione esemplare");
				
				}catch (CampoCodiceABarreVuotoException vuotoErrore) {
					messaggioPopUp(vuotoErrore.getMessaggioErrore(),vuotoErrore.getTipoErrore());
				} catch (CampoColoreVuotoException vuotoErrore) {
					messaggioPopUp(vuotoErrore.getMessaggioErrore(),vuotoErrore.getTipoErrore());
				} catch (DateVuoteException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getNomeErrore());
				} catch (OperazioneUpdateNonRiuscitaException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getTipoErrore());
				}
			}

		});
		btnRegistraNuovaMerce.setToolTipText("premi per registrare una nuova merce");
		btnRegistraNuovaMerce.setForeground(Color.WHITE);
		btnRegistraNuovaMerce.setFont(new Font("Century", Font.BOLD, 20));
		btnRegistraNuovaMerce.setFocusPainted(false);
		btnRegistraNuovaMerce.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		btnRegistraNuovaMerce.setBackground(new Color(254, 126, 115));
		btnRegistraNuovaMerce.setBounds(301, 472, 265, 50);
		contentPane.add(btnRegistraNuovaMerce);
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
		int output = JOptionPane.showConfirmDialog(this, "Confermi di ritornare indietro", "Ritorna a crea spedizione",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.ritornaNuovoOrdine(this);
		
	}
	
	
	protected void resetCampi() {
		codiceABarreTxf.setText("Codice a barre");
		coloreTxf.setText("Colore");
	}
	
	private void autoDelate(String testoDentro, String testoOrginale, JTextField txtFild) {
		//svuotare appena si scrive
		if(testoDentro.equals(testoOrginale))
			txtFild.setText("");

		
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
	
	//Procedura che ci permetterà di mostrare con un messaggio PopUp  
	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}
	
	
	private void sonoNonVuoti() throws CampoCodiceABarreVuotoException , CampoColoreVuotoException, DateVuoteException {
		//Controlli  sulla correttezza dell'input
		
		if(codiceABarreTxf.getText().isBlank() || codiceABarreTxf.getText().equals("Codice a barre")) 
			throw new CampoCodiceABarreVuotoException();
		
		else if(coloreTxf.getText().isBlank() || coloreTxf.getText().equals("Colore"))
			throw new CampoColoreVuotoException();	

		else if(dataFineGaranziaDataChoser.getDate() == null)
			throw new DateVuoteException();
		else
			garanzia = DateToLocalDate(dataFineGaranziaDataChoser.getDate());
		
	}

	private LocalDate DateToLocalDate(Date data) {
		return LocalDate.ofInstant(data.toInstant(), ZoneId.systemDefault());
	}
	
	protected void avviati()  {
		try {
			//Setto le combobox
			merceBox.setModel(new DefaultComboBoxModel(gestoreApplicazione.dammiFormatoComboBoxMerce()));
			magazzinoBox.setModel(new DefaultComboBoxModel(gestoreApplicazione.dammiFormatoComboBoxMagazzini()));
		
		} catch (RisultatoNonRicavabileException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonCiSonoMerciDisponibiliException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonCiSonoMagazziniDisponibiliException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		}
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

}
	

