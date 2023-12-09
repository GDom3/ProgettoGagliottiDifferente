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

	JPanel parteSxPanel;
	JPanel parteDxPanel;
	JLabel logoPrincipaleImgSxL;
	JLabel operatoreL;
	JLabel benvenutoL;
	JButton logOutB;


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton visualizzaB;
	private JButton nuovaSpedizioneB;
	private JButton reportB;
	private AppBrain Hal;

	/**
	 * Create the frame.
	 */
	public FinestraMenu(AppBrain appBrain) {
		Hal = appBrain;
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraMenu.class.getResource("/Img/Icon.png")));
		setTitle("UninaDelivery");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		parteSxPanel = new JPanel();
		parteSxPanel.setBackground(new Color(239, 235, 229));
		parteSxPanel.setBounds(0, 0, 385, 561);
		contentPane.add(parteSxPanel);
		parteSxPanel.setLayout(null);
		
		logoPrincipaleImgSxL = new JLabel("");
		logoPrincipaleImgSxL.setHorizontalAlignment(SwingConstants.CENTER);
		logoPrincipaleImgSxL.setIcon(new ImageIcon(FinestraMenu.class.getResource("/Img/SxMenu.jpg")));
		logoPrincipaleImgSxL.setBounds(66, 165, 232, 210);
		parteSxPanel.add(logoPrincipaleImgSxL);
		
		benvenutoL = new JLabel("Benvenuto/a");
		benvenutoL.setForeground(new Color(0, 0, 0));
		benvenutoL.setFont(new Font("Century", Font.BOLD, 30));
		benvenutoL.setHorizontalAlignment(SwingConstants.CENTER);
		benvenutoL.setBounds(10, 11, 365, 69);
		parteSxPanel.add(benvenutoL);
		
		operatoreL = new JLabel("");
		operatoreL.setHorizontalAlignment(SwingConstants.CENTER);
		operatoreL.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 30));
		operatoreL.setBounds(10, 69, 365, 55);
		parteSxPanel.add(operatoreL);
		
		parteDxPanel = new JPanel();
		parteDxPanel.setBackground(new Color(119, 101, 101));
		parteDxPanel.setBounds(383, 0, 401, 561);
		contentPane.add(parteDxPanel);
		parteDxPanel.setLayout(null);
		
		visualizzaB = new JButton();
		visualizzaB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		visualizzaB.setBorder(new LineBorder(new Color(52, 43, 42), 2, true));
		visualizzaB.setFocusPainted(false);
		visualizzaB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				visualizzaB.setBackground(new Color(141, 129, 123));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				visualizzaB.setBackground(new Color(179, 168, 166));
			}
		});
		visualizzaB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appBrain.mostraFinestraVisualizza();
			}
		});
		visualizzaB.setHorizontalAlignment(SwingConstants.CENTER);
		visualizzaB.setBounds(48, 156, 306, 51);
		visualizzaB.setToolTipText("Qui andrai alla sezione che permette di visualizzare gli ordini");
		visualizzaB.setText("Visualizza");
		visualizzaB.setForeground(Color.WHITE);
		visualizzaB.setFont(new Font("Century", Font.BOLD, 30));
		visualizzaB.setBackground(new Color(179, 168, 166));
		parteDxPanel.add(visualizzaB);
		
		nuovaSpedizioneB = new JButton();
		nuovaSpedizioneB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nuovaSpedizioneB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				nuovaSpedizioneB.setBackground(new Color(179, 168, 166));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				nuovaSpedizioneB.setBackground(new Color(141, 129, 123));
			}
		});
		nuovaSpedizioneB.setBorder(new LineBorder(new Color(52, 43, 42), 2, true));
		nuovaSpedizioneB.setFocusPainted(false);
		nuovaSpedizioneB.setToolTipText("Qui poi andare a creare una nuova spedizione con i relativi ordini");
		nuovaSpedizioneB.setText("Nuova Spedizione");
		nuovaSpedizioneB.setHorizontalAlignment(SwingConstants.CENTER);
		nuovaSpedizioneB.setForeground(Color.WHITE);
		nuovaSpedizioneB.setFont(new Font("Century", Font.BOLD, 30));
		nuovaSpedizioneB.setBackground(new Color(179, 168, 166));
		nuovaSpedizioneB.setBounds(48, 255, 306, 51);
		parteDxPanel.add(nuovaSpedizioneB);
		
		reportB = new JButton();
		reportB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reportB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reportB.setBackground(new Color(141, 129, 123));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reportB.setBackground(new Color(179, 168, 166));
			}
		});
		reportB.setBorder(new LineBorder(new Color(52, 43, 42), 2, true));
		reportB.setFocusPainted(false);
		reportB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				//Diventa grande gradualmente
				logOutB.setFont(new Font("Century", Font.PLAIN, 19));
				logOutB.setFont(new Font("Century", Font.PLAIN, 20));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//Diventa Piccolo gradualmente
				logOutB.setFont(new Font("Century", Font.PLAIN, 19));
				logOutB.setFont(new Font("Century", Font.PLAIN, 18));
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
	
	private void confermaLogOut() {
		logOutB.setFont(new Font("Century", Font.PLAIN, 19));
		logOutB.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di fare il logout?", "Logout",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			Hal.logout();
		
	}

	protected void impostaOperatore(String operatoreL) {
		this.operatoreL.setText(operatoreL);
	}
}
