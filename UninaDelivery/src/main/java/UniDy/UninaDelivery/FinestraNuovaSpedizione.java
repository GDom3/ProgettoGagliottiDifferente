package UniDy.UninaDelivery;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FinestraNuovaSpedizione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AppBrain gestoreApplicazione;
	private JPanel intestazionePanel;
	private JLabel titoloSXL;
	private JLabel logoSXImgL;
	private JPanel homePanel;
	private JButton menuB;
	private JButton aggiungiOrdineButton;
	private JLabel mezzoLabel;
	private JLabel corriereLabal;
	private JLabel ordineLabel;
	private JComboBox mezzoBox;
	private JComboBox corriereBox;
	private JComboBox ordineBox;
	private JButton aggiungiCorriereButton;
	private JButton aggiungiMezzoButton;
	private ArrayList<Ordine> ordini;

	public FinestraNuovaSpedizione(AppBrain appBrain) {
		setFont(new Font("Century", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraNuovaSpedizione.class.getResource("/Img/Icon.png")));
		setResizable(false);
		setTitle("UninaDelivery");
		gestoreApplicazione = appBrain;
		setDefaultCloseOperation(gestoreApplicazione.exit());
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 101, 101));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		intestazionePanel = new JPanel();
		intestazionePanel.setLayout(null);
		intestazionePanel.setBackground(new Color(239, 235, 229));
		intestazionePanel.setBounds(0, 0, 71, 561);
		contentPane.add(intestazionePanel);
		
		
		titoloSXL = new JLabel("");
		titoloSXL.setIcon(new ImageIcon(FinestraNuovaSpedizione.class.getResource("/Img/SxTitoloImg.jpg")));
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		titoloSXL.setBounds(10, 76, 45, 474);
		intestazionePanel.add(titoloSXL);
		
		logoSXImgL = new JLabel("New Label");
		logoSXImgL.setIcon(new ImageIcon(FinestraNuovaSpedizione.class.getResource("/Img/LogoHSX.png")));
		logoSXImgL.setBounds(0, 0, 71, 65);
		titoloSXL.setHorizontalAlignment(SwingConstants.LEFT);
		intestazionePanel.add(logoSXImgL);
		
		homePanel = new JPanel();
		homePanel.setLayout(null);
		homePanel.setBackground(new Color(119, 101, 101));
		homePanel.setBounds(69, 0, 715, 37);
		contentPane.add(homePanel);
		
		menuB = new JButton("Home");
		menuB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		menuB.setHorizontalTextPosition(SwingConstants.CENTER);
		menuB.setForeground(Color.WHITE);
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		menuB.setFocusPainted(false);
		menuB.setContentAreaFilled(false);
		menuB.setBorderPainted(false);
		menuB.setBorder(null);
		menuB.setBackground(new Color(119, 101, 101));
		menuB.setBounds(326, 11, 60, 22);
		homePanel.add(menuB);
		
		ordineBox = new JComboBox();
		ordineBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ordineBox.setToolTipText("Qui puoi selezionare l'ordine");
		ordineBox.setFont(new Font("Century", Font.PLAIN, 20));
		ordineBox.setBounds(114, 136, 200, 41);
		contentPane.add(ordineBox);
		
		corriereBox = new JComboBox();
		corriereBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		corriereBox.setToolTipText("Qui puoi selezionare il corriere");
		corriereBox.setFont(new Font("Century", Font.PLAIN, 20));
		corriereBox.setBounds(324, 136, 200, 41);
		contentPane.add(corriereBox);
		
		mezzoBox = new JComboBox();
		mezzoBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mezzoBox.setToolTipText("Qui puoi selezionare il mezzo di trasporto");
		mezzoBox.setFont(new Font("Century", Font.PLAIN, 20));
		mezzoBox.setBounds(534, 136, 200, 41);
		contentPane.add(mezzoBox);

		ordineLabel = new JLabel("Ordine");
		ordineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ordineLabel.setForeground(new Color(255, 255, 255));
		ordineLabel.setFont(new Font("Century", Font.PLAIN, 30));
		ordineLabel.setBounds(114, 88, 200, 37);
		contentPane.add(ordineLabel);
	
		corriereLabal = new JLabel("Corriere");
		corriereLabal.setHorizontalAlignment(SwingConstants.CENTER);
		corriereLabal.setForeground(Color.WHITE);
		corriereLabal.setFont(new Font("Century", Font.PLAIN, 30));
		corriereLabal.setBounds(324, 88, 200, 37);
		contentPane.add(corriereLabal);

		
		mezzoLabel = new JLabel("Mezzo");
		mezzoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mezzoLabel.setForeground(Color.WHITE);
		mezzoLabel.setFont(new Font("Century", Font.PLAIN, 30));
		mezzoLabel.setBounds(534, 88, 200, 37);
		contentPane.add(mezzoLabel);
		
		aggiungiOrdineButton = new JButton("Aggiungi ordine");
		aggiungiOrdineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiOrdineButton);
			}
		});
		aggiungiOrdineButton.setFocusPainted(false);
		aggiungiOrdineButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiOrdineButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneAnnulla(aggiungiOrdineButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiOrdineButton);
			}
		});
		aggiungiOrdineButton.setForeground(new Color(254, 126, 115));
		aggiungiOrdineButton.setContentAreaFilled(false);
		aggiungiOrdineButton.setBorderPainted(false);
		aggiungiOrdineButton.setFont(new Font("Century", Font.PLAIN, 18));
		aggiungiOrdineButton.setToolTipText("Qui puoi crere un nuovo ordine");
		aggiungiOrdineButton.setBounds(114, 188, 200, 33);
		contentPane.add(aggiungiOrdineButton);
		
		aggiungiCorriereButton = new JButton("Aggiungi corriere");
		aggiungiCorriereButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiCorriereButton.setToolTipText("Qui puoi assumere un nuovo corriere");
		aggiungiCorriereButton.setForeground(new Color(254, 126, 115));
		aggiungiCorriereButton.setFont(new Font("Century", Font.PLAIN, 18));
		aggiungiCorriereButton.setFocusPainted(false);
		aggiungiCorriereButton.setContentAreaFilled(false);
		aggiungiCorriereButton.setBorderPainted(false);
		aggiungiCorriereButton.setBounds(324, 188, 200, 33);
		aggiungiCorriereButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiCorriereButton);
			}
		});
		aggiungiCorriereButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneAnnulla(aggiungiCorriereButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiCorriereButton);
			}
		});
		contentPane.add(aggiungiCorriereButton);
		
		aggiungiMezzoButton = new JButton("Aggiungi mezzo");
		aggiungiMezzoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiMezzoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiMezzoButton);
			}
		});
		aggiungiMezzoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingradisciGradualmenteBottoneAnnulla(aggiungiMezzoButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rimpicciolisciGradualmenteBottoneAnnulla(aggiungiMezzoButton);
			}
		});
		aggiungiMezzoButton.setToolTipText("Qui puoi acquisire un nuovo mezzo");
		aggiungiMezzoButton.setForeground(new Color(254, 126, 115));
		aggiungiMezzoButton.setFont(new Font("Century", Font.PLAIN, 18));
		aggiungiMezzoButton.setFocusPainted(false);
		aggiungiMezzoButton.setContentAreaFilled(false);
		aggiungiMezzoButton.setBorderPainted(false);
		aggiungiMezzoButton.setBounds(534, 188, 200, 33);
		contentPane.add(aggiungiMezzoButton);
		

	}
	
	private void confermaRitornareMenu() {
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		int output = JOptionPane.showConfirmDialog(this, "Confermi di ritornare al menu?", "Ritorna al menu",0 ,JOptionPane.YES_NO_OPTION);
		if(output == 0)
			gestoreApplicazione.ritornaMenu(this);
		
	}
	
	
	private void rimpicciolisciGradualmenteBottoneMenu() {
		//Diventa Piccolo gradualmente
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 18));
		
	}
	
	private void rimpicciolisciGradualmenteBottoneAnnulla(JButton bottone) {
		//Diventa Piccolo gradualmente
		bottone.setFont(new Font("Century", Font.PLAIN, 19));
		bottone.setFont(new Font("Century", Font.PLAIN, 18));
		
	}

	private void ingradisciGradualmenteBottoneAnnulla(JButton bottone) {
		//Diventa grande gradualmente
		bottone.setFont(new Font("Century", Font.PLAIN, 19));
		bottone.setFont(new Font("Century", Font.PLAIN, 20));
	}

	private void ingradisciGradualmenteBottoneMenu() {
		//Diventa grande gradualmente
		menuB.setFont(new Font("Century", Font.PLAIN, 19));
		menuB.setFont(new Font("Century", Font.PLAIN, 20));
	}

	protected void avviati() {
		setVisible(true);
		try {
			ordini = gestoreApplicazione.estraiOrdiniSenzaSpedOFalliti();
		} catch (CreazioneStatementFallitaException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (ConnessionNonRiuscitaException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (RisultatoNonRicavabileException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		} catch (NonCiSonoOrdiniAttesiException e) {
			messaggioPopUp(e.getMessaggioErrore(),e.getTipoErrore());
		}
		
		riempiOrdini();
		
	}
	
	private void riempiOrdini() {
	
		ArrayList<String> codici = new ArrayList<String>(ordini.size());
	
		for (Ordine ordine : ordini) {
			codici.add(ordine.getCodOrdine() + "(" + ordine.getStatoOrdine() + ")");
		}
		
		
		DefaultComboBoxModel modelloOrdini = new DefaultComboBoxModel(codici.toArray());
		ordineBox.setModel(modelloOrdini);
		
	}

	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}
}
