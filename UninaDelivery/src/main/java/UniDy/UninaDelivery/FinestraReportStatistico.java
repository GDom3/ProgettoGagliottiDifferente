package UniDy.UninaDelivery;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import org.jfree.chart.*;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FinestraReportStatistico extends JFrame {

	private static final long serialVersionUID = 1L;
	//Amministratore
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JButton menuB ;
	private JSpinner annoFild;
	private JPanel panel;
	private JPanel risultatiScrittiPanel;
	//Oggetti utili
	private String ordineMaggiore;
	private String ordineMinore;
	private ChartPanel pannelloGrafico;
	

	public FinestraReportStatistico(AppBrain appBrain) {
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Si è verificato un errore: " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
		gestoreApplicazione = appBrain;
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraReportStatistico.class.getResource("/Img/Icon.png")));
		setTitle("UninaDelivery");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(179, 168, 166));
		panel.setBounds(81, 96, 508, 335);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		annoFild = new JSpinner();
		annoFild.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		annoFild.setModel(new SpinnerNumberModel(Integer.valueOf(2023), Integer.valueOf(0), null, Integer.valueOf(1)));
		annoFild.setToolTipText("Inserisci stipendio contratto");
		annoFild.setForeground(Color.WHITE);
		annoFild.setFont(new Font("Century", Font.PLAIN, 20));
		annoFild.setBorder(new LineBorder(new Color(119, 101, 101), 2, true));
		annoFild.setBackground(new Color(179, 168, 166));
		annoFild.setBounds(10, 183, 155, 41);
		generaPanel.add(annoFild);
		
		JLabel lblAnno = new JLabel("Anno");
		lblAnno.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnno.setForeground(Color.WHITE);
		lblAnno.setFont(new Font("Century", Font.PLAIN, 20));
		lblAnno.setBounds(10, 147, 155, 25);
		generaPanel.add(lblAnno);
		
		risultatiScrittiPanel = new JPanel();
		risultatiScrittiPanel.setBackground(new Color(179, 168, 166));
		risultatiScrittiPanel.setBounds(81, 442, 508, 87);
		contentPane.add(risultatiScrittiPanel);
		risultatiScrittiPanel.setLayout(null);
		
		JLabel lblOrdineConMinor = new JLabel("Ordine con Minore Numero di Prodotti :");
		lblOrdineConMinor.setForeground(Color.WHITE);
		lblOrdineConMinor.setFont(new Font("Century", Font.PLAIN, 19));
		lblOrdineConMinor.setBounds(10, 47, 391, 25);
		risultatiScrittiPanel.add(lblOrdineConMinor);
		
		JLabel lblOrdineConMaggior = new JLabel("Ordine con Maggior Numero di Prodotti :");
		lblOrdineConMaggior.setForeground(Color.WHITE);
		lblOrdineConMaggior.setFont(new Font("Century", Font.PLAIN, 19));
		lblOrdineConMaggior.setBounds(10, 11, 391, 25);
		risultatiScrittiPanel.add(lblOrdineConMaggior);
		
		JLabel minOrd = new JLabel("");
		minOrd.setHorizontalAlignment(SwingConstants.CENTER);
		minOrd.setForeground(Color.WHITE);
		minOrd.setFont(new Font("Century", Font.PLAIN, 19));
		minOrd.setBounds(399, 47, 99, 25);
		risultatiScrittiPanel.add(minOrd);
		
		JLabel magOrd = new JLabel("");
		magOrd.setHorizontalAlignment(SwingConstants.CENTER);
		magOrd.setForeground(Color.WHITE);
		magOrd.setFont(new Font("Century", Font.PLAIN, 19));
		magOrd.setBounds(399, 11, 99, 25);
		risultatiScrittiPanel.add(magOrd);
		
		JButton generaB = new JButton("Genera Report");
		generaB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
			
					chiediRisposteReport();
					generaGrafico();
					
					//Scrivo i valori risultanti
					magOrd.setText(ordineMaggiore);
					minOrd.setText(ordineMinore);
					
					
				} catch (UninaDeliveryException Errore) {
					messaggioPopUp(Errore.getMessaggioErrore(),Errore.getTipoErrore());
				} catch (UninaDeliverySQLException ErroreSQL) {
					messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());
				}
				
		
			}
		});
		generaB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				generaB.setBackground(new Color(254, 114, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				generaB.setBackground(new Color(254, 126, 115));
			}
		});
		generaB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		generaB.setToolTipText("premi per generare il report");
		generaB.setForeground(Color.WHITE);
		generaB.setFont(new Font("Century", Font.BOLD, 20));
		generaB.setFocusPainted(false);
		generaB.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		generaB.setBackground(new Color(254, 126, 115));
		generaB.setBounds(10, 313, 155, 37);
		generaPanel.add(generaB);
		
		JButton btnScaricaReport = new JButton("Scarica Report");
		btnScaricaReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						
				
					ScaricaGrafico();
						
					
				} catch (RisultatoNonRicavabileException e1) {
					messaggioPopUp(e1.getMessaggioErrore(),e1.getTipoErrore());
				} catch (IOException e1) {
					messaggioPopUp("Creazione file non riuscita", "Errore");
				} catch (Exception e1) {
					messaggioPopUp(e1.getMessage(), "Errore");
				}
				
			}

		});
		btnScaricaReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnScaricaReport.setBackground(new Color(254, 114, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnScaricaReport.setBackground(new Color(254, 126, 115));
			}
		});
		btnScaricaReport.setToolTipText("premi per scaricare il report");
		btnScaricaReport.setForeground(Color.WHITE);
		btnScaricaReport.setFont(new Font("Century", Font.BOLD, 20));
		btnScaricaReport.setFocusPainted(false);
		btnScaricaReport.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		btnScaricaReport.setBackground(new Color(254, 126, 115));
		btnScaricaReport.setBounds(10, 361, 155, 37);
		generaPanel.add(btnScaricaReport);
	
		
		panel.setVisible(false);
		risultatiScrittiPanel.setVisible(false);
		
		
	}	
	
	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}
	
	private void chiediRisposteReport() throws NonPossibileRicavareStatisticheException  {
		
		int anno = (int) annoFild.getValue();
		
		try {
			//Mi prendo l'ordine con maggior numero di prodotti
			ordineMaggiore = gestoreApplicazione.ordineConMaggiorProdotti(anno);
		
			//e poi ,i prendo l'ordine con minor numero di prodotti
			ordineMinore = gestoreApplicazione.ordineConMinorProdotti(anno);
		} catch (RisultatoNonRicavabileException e) {
		
			panel.setVisible(false);
			risultatiScrittiPanel.setVisible(false);
			throw new NonPossibileRicavareStatisticheException();
		}
		
		
	}

	private void generaGrafico() throws RisultatoNonRicavabileException {
		//Faccio il grafico
		pannelloGrafico = gestoreApplicazione.creazioneGrafico((int)annoFild.getValue());

		//rendo visibile i risultati
		panel.setVisible(true);
		risultatiScrittiPanel.setVisible(true);
		panel.removeAll();
		panel.add(pannelloGrafico,BorderLayout.CENTER);
		panel.validate();
		
		
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
	
	
	private void ScaricaGrafico() throws Exception {
		if(panel.isVisible()) {
			gestoreApplicazione.scaricaGrafico(pannelloGrafico);
			messaggioPopUp("Immagine report scaricata correttamente","Salva immagine report");
		}else {
			throw new Exception("Non puoi scaricare il grafico se prima non lo generi");
		}
	}
}
