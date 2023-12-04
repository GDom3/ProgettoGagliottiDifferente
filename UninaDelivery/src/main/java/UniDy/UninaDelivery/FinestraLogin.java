package UniDy.UninaDelivery;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.PasswordAuthentication;

public class FinestraLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPasswordField passwordIn;
	private JTextField usernameIn;
	private JLabel userImg;
	private JPanel panelPrincipale;
	private JLabel accessoTxt;
	private JButton tastoLogin;
	private JLabel errorMsg;
	private JLabel logoImg;
	private JButton lockImg;
	private boolean isVisiblePassword = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraLogin frame = new FinestraLogin();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FinestraLogin() {
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
		
		usernameIn = new JTextField();
		usernameIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(usernameIn.getText().equals("Username"))
					usernameIn.setText("");
			}
		});
		
		
		infoUsernameIn();
		
		passwordIn = new JPasswordField();
		passwordIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(passwordIn.getText().equals("Password"))
					passwordIn.setText("");
			}
		});
		infoPasswordIn();
		
		accessoTxt = new JLabel("Accesso");
		infoAccessoTxt();
		
		userImg = new JLabel("");
		infoUserImg();
		
		tastoLogin = new JButton("Login");
		infoTastoLogin();
		tastoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accesso();
			}
		});
		
		
		lockImg =new JButton("");
		lockImg.addMouseListener(new MouseAdapter() { 
			//Funzioni per dare un effetto bottone all'lucchetto
			@Override
			public void mouseEntered(MouseEvent e) {
				if(isVisiblePassword == false)
					lockImg.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/passL.jpg")));
				else
					lockImg.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/OpenLok.jpg")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(isVisiblePassword == true) 
					lockImg.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/OpenLocket.jpg")));
				else
					lockImg.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/CloseLocket.jpg"))); 
				
			}
		});
		
		
		infoLockImg();
		lockImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPassword(); //Funzionalità classica per coprire o svelare la password
			}
		});
		
		
		
		logoImg = new JLabel("New label");
		infoLogoImg();
		
		
		errorMsg = new JLabel(""); // Errore : Username non esistente oppure Errore : Password errata
		infoErrorMsg();
		
	}
	
	private void accesso() {
		
		errorMsg.setText("Errore : Username non esistente");
		//errorMsg.setText("Errore : Password errata");
		
		
	}
	
	private void showPassword() { // 1uesta funzione gestisce la visibilità della password
		if(isVisiblePassword == false) { // caso in cui la password era nascosta
			lockImg.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/OpenLok.jpg")));
			passwordIn.setEchoChar((char) 0);
			isVisiblePassword = true;
		}else{ //caso in cui la password era visibile
			lockImg.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/CloseLocket.jpg"))); // Metti il lock chiuso
			passwordIn.setEchoChar('\u25CF'); //Codice pallini
			isVisiblePassword = false; // Aggiorna il rifermimento booleano
		}
	}
	
	
	private void infoUsernameIn() {
		
		
		usernameIn.setBorder(null);
		usernameIn.setSelectedTextColor(new Color(255, 255, 255));
		usernameIn.setCaretColor(new Color(0, 0, 0));
		usernameIn.setToolTipText("Qui inserire il proprio username");
		usernameIn.setText("Username");
		usernameIn.setFont(new Font("Century", Font.BOLD, 30));
		usernameIn.setBackground(new Color(179, 168, 166));
		usernameIn.setBounds(274, 306, 310, 59);
		usernameIn.setColumns(10);
		usernameIn.setForeground(new Color(255,255,255));
		panelPrincipale.add(usernameIn);
	
	}
	
	private void infoAccessoTxt() {
		accessoTxt.setFont(new Font("Century", Font.BOLD, 30));
		accessoTxt.setForeground(new Color(255, 255, 255));
		accessoTxt.setHorizontalAlignment(SwingConstants.CENTER);
		accessoTxt.setBounds(307, 233, 150, 69);
		panelPrincipale.add(accessoTxt);
	}
	
	private void infoPasswordIn() {
		passwordIn.setText("Password");
		passwordIn.setBorder(null);
		passwordIn.setToolTipText("qui inserire la password");
		passwordIn.setFont(new Font("Century", Font.BOLD, 30));
		passwordIn.setBackground(new Color(179, 168, 166));
		passwordIn.setBounds(274, 376, 310, 59);
		passwordIn.setEchoChar('\u25CF'); //Codice pallini
		passwordIn.setForeground(new Color(255,255,255));
		panelPrincipale.add(passwordIn);
	}
	
	private void infoUserImg() {
		userImg.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/USErLogo_.jpg")));
		userImg.setBounds(205, 291, 73, 89);
		panelPrincipale.add(userImg);
	}
	
	private void infoTastoLogin() {
		tastoLogin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tastoLogin.setToolTipText("premi per accedere");
		tastoLogin.setBackground(new Color(254, 126, 115));
		tastoLogin.setForeground(new Color(255, 255, 255));
		tastoLogin.setFont(new Font("Century", Font.BOLD, 30));
		tastoLogin.setBounds(205, 446, 383, 58);
		panelPrincipale.add(tastoLogin);
		
	}
	private void infoLogoImg() {
		logoImg.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/logoLogiN.jpg")));
		logoImg.setHorizontalAlignment(SwingConstants.CENTER);
		logoImg.setBounds(284, 11, 211, 234);
		panelPrincipale.add(logoImg);
	}
	private void infoErrorMsg() {
		errorMsg.setForeground(Color.RED);
		errorMsg.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 30));
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setBounds(0, 514, 774, 37);
		panelPrincipale.add(errorMsg);
	}
	private void infoLockImg() {
		lockImg.setIcon(new ImageIcon(FinestraLogin.class.getResource("/Img/CloseLocket.jpg")));
		lockImg.setBounds(205, 376, 69, 59);
		lockImg.setBorder(null);
		lockImg.setToolTipText("Clicca qui per modificare lavisibilità della password");
		panelPrincipale.add(lockImg);
	}
}


