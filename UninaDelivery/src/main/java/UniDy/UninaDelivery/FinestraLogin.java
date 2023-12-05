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
	
	/**
	 * Create the frame.
	 */
	public FinestraLogin(Hal gestoreApplicazione) {
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
		logoUsernameImgL.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/USErLogo_.jpg")));
		logoUsernameImgL.setBounds(205, 291, 73, 89);
		panelPrincipale.add(logoUsernameImgL);
		
		loginB = new JButton("Login");
		loginB.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
				
				//Controlli esplicativi sulla correttezza dell'input a un primo livello
				if(username.isEmpty()) 
					messaggioPopUp("Errore : Campo Username Vuoto", "Attenzione");
				else if (username.equals("Username"))
					messaggioPopUp("Errore : Campo Username non Valido", "Attenzione");
				else if(password.isEmpty())
					messaggioPopUp("Errore : Campo Password Vuoto", "Attenzione");
				else if (password.equals("Password"))
					messaggioPopUp("Errore : Campo Password non Valido", "Attenzione");
				else
					gestoreApplicazione.accesso(username,password);
			}
		});
		
		
		logoPasswordImgB =new JButton("");
		logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/CloseLocket.jpg")));
		logoPasswordImgB.setBounds(205, 376, 69, 59);
		logoPasswordImgB.setBorder(null);
		logoPasswordImgB.setToolTipText("Clicca qui per modificare lavisibilità della password");
		panelPrincipale.add(logoPasswordImgB);
		logoPasswordImgB.addMouseListener(new MouseAdapter() { 
			//Funzioni per dare un effetto bottone all'lucchetto
			@Override
			public void mouseEntered(MouseEvent e) {
				if(isVisiblePassword) //Se era visibile
					logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/OpenLok.jpg"))); //leva l'ombra all'lucchetto aperto
				else
					logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/passL.jpg"))); //leva l'ombra all'lucchetto chiuso
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(isVisiblePassword) // se era visivile
					logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/OpenLocket.jpg"))); // metti l'ombra al lucchetto aperto
				else
					logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/CloseLocket.jpg"))); // metti l'ombra al lucchetto chiuso
				
			}
		});
		logoPasswordImgB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPassword(); //Funzionalità classica per coprire o svelare la password
			}
		});
		
		
		
		logoPrincipaleImgL = new JLabel("New label");
		logoPrincipaleImgL.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/logoLogin.jpg")));
		logoPrincipaleImgL.setHorizontalAlignment(SwingConstants.CENTER);
		logoPrincipaleImgL.setBounds(284, 11, 211, 234);
		panelPrincipale.add(logoPrincipaleImgL);
		

		
	}
	
	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}
	
	
	
	private void showPassword() { // 1uesta funzione gestisce la visibilità della password
		if(isVisiblePassword == false) { // caso in cui la password era nascosta
			logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/OpenLok.jpg")));
			passwordPF.setEchoChar((char) 0);
			isVisiblePassword = true;
		}else{ //caso in cui la password era visibile
			logoPasswordImgB.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/passL.jpg"))); // Metti il lock chiuso
			passwordPF.setEchoChar('\u25CF'); //Codice pallini
			isVisiblePassword = false; // Aggiorna il rifermimento booleano
		}
	}
	

}