package UniDy.UninaDelivery;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Cursor;


public class FinestraMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	//Amministratore
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JLabel operatoreL;
	private JButton logOutB;
	
	public FinestraMenu(AppBrain appBrain) {
		gestoreApplicazione = appBrain;
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraMenu.class.getResource("/Img/Icon.png")));
		setTitle("UninaDelivery");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JPanel contentPane;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel parteSxPanel;
		parteSxPanel = new JPanel();
		parteSxPanel.setBackground(new Color(239, 235, 229));
		parteSxPanel.setBounds(0, 0, 385, 561);
		contentPane.add(parteSxPanel);
		parteSxPanel.setLayout(null);
		
		JLabel logoPrincipaleImgSxL;
		logoPrincipaleImgSxL = new JLabel("");
		logoPrincipaleImgSxL.setToolTipText("UninaDelivery - Veloci come un falco");
		logoPrincipaleImgSxL.setHorizontalAlignment(SwingConstants.CENTER);
		logoPrincipaleImgSxL.setIcon(new ImageIcon(FinestraMenu.class.getResource("/Img/SxMenu.jpg")));
		logoPrincipaleImgSxL.setBounds(66, 165, 232, 210);
		parteSxPanel.add(logoPrincipaleImgSxL);
		
		JLabel benvenutoL;
		benvenutoL = new JLabel("Benvenuto/a");
		benvenutoL.setForeground(new Color(0, 0, 0));
		benvenutoL.setFont(new Font("Century", Font.PLAIN, 30));
		benvenutoL.setHorizontalAlignment(SwingConstants.CENTER);
		benvenutoL.setBounds(10, 11, 365, 69);
		parteSxPanel.add(benvenutoL);
		
		operatoreL = new JLabel("");
		operatoreL.setVerticalAlignment(SwingConstants.TOP);
		operatoreL.setHorizontalAlignment(SwingConstants.CENTER);
		operatoreL.setFont(new Font("Century", Font.PLAIN, 27));
		operatoreL.setBounds(10, 75, 365, 125);
		parteSxPanel.add(operatoreL);
		
		JPanel parteDxPanel;
		parteDxPanel = new JPanel();
		parteDxPanel.setToolTipText("");
		parteDxPanel.setBackground(new Color(119, 101, 101));
		parteDxPanel.setBounds(383, 0, 401, 561);
		contentPane.add(parteDxPanel);
		parteDxPanel.setLayout(null);
		
		JButton visualizzaB;
		visualizzaB = new JButton();
		visualizzaB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		visualizzaB.setBorder(new LineBorder(new Color(52, 43, 42), 2, true));
		visualizzaB.setFocusPainted(false);
		visualizzaB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				scurisciBottoneMenu(visualizzaB);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				schiarisciBottoneMenu(visualizzaB);	
			}
		});
		visualizzaB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appBrain.mostraFinestraVisualizza();
			}
		});
		visualizzaB.setHorizontalAlignment(SwingConstants.CENTER);
		visualizzaB.setBounds(48, 156, 306, 51);
		visualizzaB.setToolTipText("Qui andrai alla sezione che permette di visualizzare, filtrare e modificare gli stati degli ordini");
		visualizzaB.setText("Visualizza");
		visualizzaB.setForeground(Color.WHITE);
		visualizzaB.setFont(new Font("Century", Font.BOLD, 30));
		visualizzaB.setBackground(new Color(179, 168, 166));
		parteDxPanel.add(visualizzaB);
		
		JButton nuovaSpedizioneB;
		nuovaSpedizioneB = new JButton();
		nuovaSpedizioneB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appBrain.mostraFinestraNuovaSpedizione();
			}
		});
		nuovaSpedizioneB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nuovaSpedizioneB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				schiarisciBottoneMenu(nuovaSpedizioneB);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				scurisciBottoneMenu(nuovaSpedizioneB);
			}
		});
		nuovaSpedizioneB.setBorder(new LineBorder(new Color(52, 43, 42), 2, true));
		nuovaSpedizioneB.setFocusPainted(false);
		nuovaSpedizioneB.setToolTipText("Qui poi andare a creare una nuova spedizione con i relativi ordini");
		nuovaSpedizioneB.setText("Gestisci Spedizione");
		nuovaSpedizioneB.setHorizontalAlignment(SwingConstants.CENTER);
		nuovaSpedizioneB.setForeground(Color.WHITE);
		nuovaSpedizioneB.setFont(new Font("Century", Font.BOLD, 30));
		nuovaSpedizioneB.setBackground(new Color(179, 168, 166));
		nuovaSpedizioneB.setBounds(48, 258, 306, 51);
		parteDxPanel.add(nuovaSpedizioneB);
		
		JButton reportB;
		reportB = new JButton();
		reportB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reportB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				scurisciBottoneMenu(reportB);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				schiarisciBottoneMenu(reportB);
			}
		});
		reportB.setBorder(new LineBorder(new Color(52, 43, 42), 2, true));
		reportB.setFocusPainted(false);
		reportB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestoreApplicazione.mostraFinestraReport();
			}
		});
		reportB.setToolTipText("Qui visualizzi un report statistico");
		reportB.setText("Report");
		reportB.setHorizontalAlignment(SwingConstants.CENTER);
		reportB.setForeground(Color.WHITE);
		reportB.setFont(new Font("Century", Font.BOLD, 30));
		reportB.setBackground(new Color(179, 168, 166));
		reportB.setBounds(48, 360, 306, 51);
		parteDxPanel.add(reportB);
		
		logOutB = new JButton("Logout");
		logOutB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOutB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confermaLogOut();
				
			}
		});
		logOutB.setFocusPainted(false);
		logOutB.setContentAreaFilled(false);
		logOutB.setBorderPainted(false);
		logOutB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradimentoLogOut();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rinpicciolisciLogOut();
			}
		});
		logOutB.setBackground(new Color(119, 101, 101));
		logOutB.setForeground(new Color(255, 255, 255));
		logOutB.setBorder(null);
		logOutB.setHorizontalTextPosition(SwingConstants.CENTER);
		logOutB.setFont(new Font("Century", Font.PLAIN, 18));
		logOutB.setBounds(294, 11, 97, 31);
		parteDxPanel.add(logOutB);
	}
	
	private void schiarisciBottoneMenu(JButton bottone) {
		bottone.setBackground(new Color(179, 168, 166));
		
	}

	private void scurisciBottoneMenu(JButton bottone) {
		bottone.setBackground(new Color(141, 129, 123));
	}

	private void rinpicciolisciLogOut() {
		//Diventa Piccolo gradualmente
		logOutB.setFont(new Font("Century", Font.PLAIN, 19));
		logOutB.setFont(new Font("Century", Font.PLAIN, 18));
		
	}

	private void ingradimentoLogOut() {
		//Diventa grande gradualmente
		logOutB.setFont(new Font("Century", Font.PLAIN, 19));
		logOutB.setFont(new Font("Century", Font.PLAIN, 20));
		
	}

	private void confermaLogOut() {
		logOutB.setFont(new Font("Century", Font.PLAIN, 19));
		logOutB.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di fare il logout?", "Logout",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.logout();
		
	}

	protected void impostaOperatore(String operatoreL) {
		this.operatoreL.setText(operatoreL);
		this.operatoreL.setToolTipText("Benvenuto/a "+ operatoreL);
		
	}
}
