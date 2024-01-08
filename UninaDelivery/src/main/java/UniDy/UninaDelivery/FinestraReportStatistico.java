package UniDy.UninaDelivery;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;

public class FinestraReportStatistico extends JFrame {

	private static final long serialVersionUID = 1L;
	//Amministratore
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JButton menuB ;
	

	public FinestraReportStatistico(AppBrain appBrain) {
		gestoreApplicazione = appBrain;
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraReportStatistico.class.getResource("/Img/Icon.png")));
		setTitle("UninaDelivery");
		setResizable(false);
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
		titoloSXL.setIcon(new ImageIcon(FinestraReportStatistico.class.getResource("/Img/SxTitoloImg.jpg")));
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloSXL.setBounds(10, 76, 45, 474);
		intestazionePanel.add(titoloSXL);
		
		JLabel logoSXImgL = new JLabel("New Label");
		logoSXImgL.setIcon(new ImageIcon(FinestraReportStatistico.class.getResource("/Img/LogoHSX.png")));
		logoSXImgL.setBounds(0, 0, 71, 65);
		intestazionePanel.add(logoSXImgL);
		
		JPanel homePanel = new JPanel();
		homePanel.setLayout(null);
		homePanel.setBackground(new Color(119, 101, 101));
		homePanel.setBounds(69, 0, 715, 37);
		contentPane.add(homePanel);
		
		menuB = new JButton("Home");
		menuB.setToolTipText("premi per ritornare al menu");
		menuB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuB.setHorizontalTextPosition(SwingConstants.CENTER);
		menuB.setForeground(Color.WHITE);
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		menuB.setFocusPainted(false);
		menuB.setContentAreaFilled(false);
		menuB.setBorderPainted(false);
		menuB.setBorder(null);
		menuB.setBackground(new Color(119, 101, 101));
		menuB.setBounds(326, 11, 60, 22);
		menuB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confermaRitornareMenu();
				
			}
		});
		menuB.setBounds(326, 11, 60, 22);
		menuB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneMenu();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneMenu();
				
			}
		});
		homePanel.add(menuB);
		
		JLabel titoloLabel = new JLabel("Report Statistico");
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLabel.setFont(new Font("Century", Font.BOLD, 30));
		titoloLabel.setForeground(new Color(255, 255, 255));
		titoloLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		titoloLabel.setBounds(79, 48, 695, 37);
		contentPane.add(titoloLabel);
		
		JPanel generaPanel = new JPanel();
		generaPanel.setLayout(null);
		generaPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		generaPanel.setBackground(new Color(179, 168, 166));
		generaPanel.setBounds(599, 96, 175, 433);
		contentPane.add(generaPanel);
		
		JLabel titoloFiltriL = new JLabel("Filtri");
		titoloFiltriL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloFiltriL.setForeground(Color.WHITE);
		titoloFiltriL.setFont(new Font("Century", Font.BOLD, 30));
		titoloFiltriL.setBounds(10, 11, 136, 37);
		generaPanel.add(titoloFiltriL);
		
		JLabel imgFiltriL = new JLabel("");
		imgFiltriL.setIcon(new ImageIcon(FinestraReportStatistico.class.getResource("/Img/FiltraGrande.jpg")));
		imgFiltriL.setRequestFocusEnabled(false);
		imgFiltriL.setHorizontalTextPosition(SwingConstants.RIGHT);
		imgFiltriL.setBackground(new Color(179, 168, 166));
		imgFiltriL.setBounds(132, 11, 33, 37);
		generaPanel.add(imgFiltriL);
		
		JButton generaB = new JButton("Genera Report");
		generaB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		generaB.setToolTipText("premi per generare il report");
		generaB.setForeground(Color.WHITE);
		generaB.setFont(new Font("Century", Font.BOLD, 20));
		generaB.setFocusPainted(false);
		generaB.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		generaB.setBackground(new Color(254, 126, 115));
		generaB.setBounds(10, 385, 155, 37);
		generaPanel.add(generaB);
	}
	
	
	private void confermaRitornareMenu() {
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di ritornare al menu?", "Ritorna al menu",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.ritornaMenu(this);
		
	}
	
	private void ingradisciGradualmenteBottoneMenu() {
		//Diventa grande gradualmente
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 20));
	}
	
	
	private void rimpicciolisciGradualmenteBottoneMenu() {
		//Diventa Piccolo gradualmente
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		
	}
	
}
