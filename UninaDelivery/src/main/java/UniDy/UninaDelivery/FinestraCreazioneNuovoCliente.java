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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;

public class FinestraCreazioneNuovoCliente extends JFrame {

	private static final long serialVersionUID = 1L; 
	//Aministratore
	AppBrain gestoreApplicazione;
	//Grafica Globale
	private JButton indietroBottone;
	private JTextField nomeTextField;
	private JTextField cognomeTextFild;
	private JTextField CodiceFiscaleTextField;
	
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
		titoloLabel.setBounds(12, -1, 693, 37);
		homePanel.add(titoloLabel);
		
		JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setBackground(new Color(119, 101, 101));
		anagraficaPanel.setBounds(69, 37, 358, 251);
		contentPane.add(anagraficaPanel);
		anagraficaPanel.setLayout(null);
		
		JLabel logoCitta_1_1 = new JLabel("New label");
		logoCitta_1_1.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/BDate.png")));
		logoCitta_1_1.setBorder(new LineBorder(new Color(179, 168, 166), 1, true));
		logoCitta_1_1.setBounds(80, 197, 43, 41);
		anagraficaPanel.add(logoCitta_1_1);
		
		JLabel anagraficaTitoloLabel = new JLabel("Dati Anagrafici");
		anagraficaTitoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		anagraficaTitoloLabel.setForeground(new Color(255, 255, 255));
		anagraficaTitoloLabel.setFont(new Font("Century", Font.PLAIN, 20));
		anagraficaTitoloLabel.setBounds(10, 11, 338, 25);
		anagraficaPanel.add(anagraficaTitoloLabel);
		
		JLabel logoCitta = new JLabel("New label");
		logoCitta.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		logoCitta.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/IDCard.png")));
		logoCitta.setBounds(31, 47, 89, 87);
		anagraficaPanel.add(logoCitta);
		
		nomeTextField = new JTextField();
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
		nomeTextField.setBounds(120, 47, 200, 41);
		anagraficaPanel.add(nomeTextField);
		
		cognomeTextFild = new JTextField();
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
		cognomeTextFild.setBounds(120, 93, 200, 41);
		anagraficaPanel.add(cognomeTextFild);
		
		JLabel logoCitta_1 = new JLabel("New label");
		logoCitta_1.setBorder(new LineBorder(new Color(179, 168, 166), 3, true));
		logoCitta_1.setIcon(new ImageIcon(FinestraCreazioneNuovoCliente.class.getResource("/Img/CodFisc.png")));
		logoCitta_1.setBounds(57, 145, 46, 41);
		anagraficaPanel.add(logoCitta_1);
		
		CodiceFiscaleTextField = new JTextField();
		CodiceFiscaleTextField.setText("Codice Fiscale");
		CodiceFiscaleTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				autoDelate(CodiceFiscaleTextField.getText(), "Codice Fiscale", CodiceFiscaleTextField);
				vaiAvanti(CodiceFiscaleTextField, CodiceFiscaleTextField, e);
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
		CodiceFiscaleTextField.setBounds(103, 145, 200, 41);
		anagraficaPanel.add(CodiceFiscaleTextField);
		
		JDateChooser DataEDataChoser = new JDateChooser();
		DataEDataChoser.getCalendarButton().setFont(new Font("Century", Font.PLAIN, 18));
		DataEDataChoser.getCalendarButton().setBackground(Color.WHITE);
		DataEDataChoser.setToolTipText("inserisci data di esecuzione del ordine");
		DataEDataChoser.setFont(new Font("Century", Font.PLAIN, 18));
		DataEDataChoser.setBorder(new LineBorder(new Color(179, 168, 166), 2, true));
		DataEDataChoser.setBackground(new Color(179, 168, 166));
		DataEDataChoser.setBounds(120, 197, 170, 41);
		anagraficaPanel.add(DataEDataChoser);
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
