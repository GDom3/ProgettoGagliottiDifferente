package UniDy.UninaDelivery;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.border.LineBorder;

public class FinestraLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPasswordField passwordPF; //Contiene la password inserita dall'utente
	private JTextField usernameTF; //Contiene  l'username inserito dall'utente
	private JLabel logoUsernameImgL; // l'immagine dell'omino 
	private JPanel panelPrincipale; // panello principale eplicativo -.-
	private JLabel accessoL; //Scritta "Accesso" per fattori estetici
	private JButton loginB; //Bottone per il lOGIN
	private JLabel logoPrincipaleImgL; //L'immagine del logo
	private JButton logoPasswordImgB; //L'immagine del lucchetto dinamico, il quale rende visibile o nascosta la password
	private boolean isVisiblePassword = false; //Indicatore stato attuale della visibilità della password
	AppBrain HAL;
	/**
	 * Create the frame.
	 */
	public FinestraLogin(AppBrain appBrain) {
		HAL = appBrain;
		setResizable(false);
		setFont(new Font("Century", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraLogin.class.getResource("/Img/Icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 101, 101));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelPrincipale = new JPanel();
		panelPrincipale.setBackground(new Color(119, 101, 101));
		contentPane.add(panelPrincipale);
		panelPrincipale.setLayout(null);
		
		usernameTF = new JTextField();
		usernameTF.setBorder(null);
		usernameTF.setSelectedTextColor(new Color(255, 255, 255));
		usernameTF.setCaretColor(new Color(0, 0, 0));
		usernameTF.setToolTipText("Qui inserire il proprio username");
		usernameTF.setText("Username");
		usernameTF.setFont(new Font("Century", Font.BOLD, 30));
		usernameTF.setBackground(new Color(179, 168, 166));
		usernameTF.setBounds(274, 306, 310, 59);
		usernameTF.setColumns(10);
		usernameTF.setForeground(new Color(255,255,255));
		panelPrincipale.add(usernameTF);
		usernameTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(usernameTF.getText().equals("Username")) //Se è username si deve levare
					usernameTF.setText("");
			}
		});
		
			
		passwordPF = new JPasswordField();
		passwordPF.setText("Password");
		passwordPF.setBorder(null);
		passwordPF.setToolTipText("qui inserire la password");
		passwordPF.setFont(new Font("Century", Font.BOLD, 30));
		passwordPF.setBackground(new Color(179, 168, 166));
		passwordPF.setBounds(274, 376, 310, 59);
		passwordPF.setEchoChar('\u25CF'); //Codice pallini
		passwordPF.setForeground(new Color(255,255,255));
		panelPrincipale.add(passwordPF);
		passwordPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { // se è password si deve levare
				if(new String(passwordPF.getPassword()).equals("Password"))
					passwordPF.setText("");
			}
		});
		
		accessoL = new JLabel("Accesso");
		accessoL.setFont(new Font("Century", Font.BOLD, 30));
		accessoL.setForeground(new Color(255, 255, 255));
		accessoL.setHorizontalAlignment(SwingConstants.CENTER);
		accessoL.setBounds(307, 233, 150, 69);
		panelPrincipale.add(accessoL);
		
		logoUsernameImgL = new JLabel("");
		logoUsernameImgL.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/OminoUsername.jpg")));
		logoUsernameImgL.setBounds(205, 291, 73, 89);
		panelPrincipale.add(logoUsernameImgL);
		
		loginB = new JButton("Login");
		loginB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginB.setBackground(new Color(254, 114, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginB.setBackground(new Color(254, 126, 115));
			}
		});
		loginB.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		loginB.setFocusPainted(false);
		loginB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginB.setToolTipText("premi per accedere");
		loginB.setBackground(new Color(254, 126, 115));
		loginB.setForeground(new Color(255, 255, 255));
		loginB.setFont(new Font("Century", Font.BOLD, 30));
		loginB.setBounds(205, 446, 383, 58);
		panelPrincipale.add(loginB);
		loginB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTF.getText();
				String password = new String(passwordPF.getPassword());
				
				richiestaAccesso(username,password);
					
			}

		});
		
		
		logoPasswordImgB =new JButton("");
		logoPasswordImgB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/LucchettoChiusoConOmbra.jpg")));
		logoPasswordImgB.setBounds(205, 376, 69, 59);
		logoPasswordImgB.setBorder(null);
		logoPasswordImgB.setToolTipText("Clicca qui per modificare lavisibilità della password");
		panelPrincipale.add(logoPasswordImgB);
		logoPasswordImgB.addMouseListener(new MouseAdapter() { 
			//Funzioni per dare un effetto bottone all'lucchetto
			@Override
			public void mouseEntered(MouseEvent e) {
				levaOmbra();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mettiOmbra();
				
			}
		});
		logoPasswordImgB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPassword(); //Funzionalità classica per coprire o svelare la password
			}
		});
		
		
		logoPrincipaleImgL = new JLabel("");
		logoPrincipaleImgL.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/UniDyLogoLogin.jpg")));
		logoPrincipaleImgL.setHorizontalAlignment(SwingConstants.CENTER);
		logoPrincipaleImgL.setBounds(284, 11, 211, 234);
		panelPrincipale.add(logoPrincipaleImgL);
		
	}
	
	protected void impostaUsername(String txt) {
		this.usernameTF.setText(txt);
	}
	protected void impostaPassword(String txt) {
		this.passwordPF.setText(txt);
	}

	private void richiestaAccesso(String username, String password) {
		try{
			sonoNonVuoti(username,password);	
			
		} catch (CampoInserimentoVuotoException vuotoErrore) {
			
			messaggioPopUp(vuotoErrore.getMessaggioErrore(),vuotoErrore.getTipoErrore());
			return;
		}
		
		
		try {
			HAL.accesso(username,password);
			
		} catch (ConnessionNonRiuscitaException ErroreSQL) {
			
			messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());
			
		} catch (CreazioneStatementFallitaException ErroreSQL) {
			
			messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());
			
		} catch (RisultatoNonRicavabileException ErroreSQL) {
			
			messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());
			
		} catch (MetaDatiNonTrovatiException ErroreSQL) {
			
			messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());
			
		} catch (EstrazioneCampiFallitaException ErroreSQL) {
			
			messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());
			
		} catch (ChiusturaComunicazioneFallitaException ErroreSQL) {
			
			messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());
			
		} catch (SQLException e){
			messaggioPopUp(e.getMessage(),"Errore");
			
		}
		
	}

	private void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}
	
	private void mettiOmbra(){
		if(isVisiblePassword) // se era visivile
			logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/LucchettoApertoConOmbra.jpg"))); // metti l'ombra al lucchetto aperto
		else
			logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/LucchettoChiusoConOmbra.jpg"))); // metti l'ombra al lucchetto chiuso
	}
	
	private void levaOmbra() {
		if(isVisiblePassword) //Se era visibile
			logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/LucchettoApertoSenzaOmbra.jpg"))); //leva l'ombra all'lucchetto aperto
		else
			logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/LucchettoChiusoSenzaOmbra.jpg"))); //leva l'ombra all'lucchetto chiuso
	}
	
	private void sonoNonVuoti(String username, String password) throws CampoInserimentoVuotoException{
		//Controlli esplicativi sulla correttezza dell'input a un primo livello
		
		if(username.isBlank() || username.equals("Username")) 
			throw new CampoInserimentoVuotoException("USERNAME");
			
		if(password.isBlank() || password.equals("Password"))
			throw new CampoInserimentoVuotoException("PASSWORD");
			
		
	}
	
	private void showPassword() { // 1uesta funzione gestisce la visibilità della password
		if(isVisiblePassword) { //caso in cui la password era visibile
			logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/LucchettoChiusoSenzaOmbra.jpg"))); // Metti il lock chiuso
			passwordPF.setEchoChar('\u25CF'); //Codice pallini
			isVisiblePassword = false; // Aggiorna il rifermimento booleano
		}else{ // caso in cui la password era nascosta
			logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/LucchettoApertoSenzaOmbra.jpg")));
			passwordPF.setEchoChar((char) 0);
			isVisiblePassword = true;
		}
	}
	

}