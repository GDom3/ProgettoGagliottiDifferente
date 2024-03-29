package UniDy.UninaDelivery;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import org.apache.commons.mail.EmailException;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class FinestraCambiaStato extends JFrame {

	private static final long serialVersionUID = 1L;
	//Amministratore
	private AppBrain gestoreApplicazione;
	//Grafica Globale
	private JLabel statoAttualeLabel;
	private JLabel titoloLabel;
	//Gestione ComboBox
	private JComboBox statiBox;
	private String[] statiSpedizioniPossibili = { "Conclusa", "Partita", "Presa In Carico", "Ritardo" };
	private String[] statiOrdiniPossibili = { "Consegnato", "In Consegna", "Spedito", "Presa In Carico", "Ritardo","Tentativo Fallito" };
	private DefaultComboBoxModel modelloStatiOrdini = new DefaultComboBoxModel(statiOrdiniPossibili);
	private DefaultComboBoxModel modelloStatiSpedizioni = new DefaultComboBoxModel(statiSpedizioniPossibili);
	//Oggetti Reali
	private String spedizioneSelezionata;
	private String ordineSelezionato;
	private String spedizioneStato;
	private String ordineStato;

	public FinestraCambiaStato(AppBrain appBrain) {
		setAlwaysOnTop(true);
		setResizable(false);

		setTitle("UninaDelivery");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraCambiaStato.class.getResource("/Img/Icon.png")));
		gestoreApplicazione = appBrain;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel principalePannello;
		principalePannello = new JPanel();
		principalePannello.setBackground(new Color(119, 101, 101));
		principalePannello.setBounds(0, 0, 434, 261);
		contentPane.add(principalePannello);
		principalePannello.setLayout(null);

		JButton annullaBottone;
		annullaBottone = new JButton();
		annullaBottone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				annullaBottone.setBackground(new Color(141, 129, 123));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				annullaBottone.setBackground(new Color(179, 168, 166));
			}
		});
		annullaBottone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		annullaBottone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestoreApplicazione.annullaOperazioneCambioStato();
			}
		});
		annullaBottone.setToolTipText("Qui Puoi annullare tutto");
		annullaBottone.setText("Annulla");
		annullaBottone.setHorizontalAlignment(SwingConstants.CENTER);
		annullaBottone.setForeground(Color.WHITE);
		annullaBottone.setFont(new Font("Century", Font.BOLD, 20));
		annullaBottone.setFocusPainted(false);
		annullaBottone.setBorder(new LineBorder(new Color(52, 43, 42), 2, true));
		annullaBottone.setBackground(new Color(179, 168, 166));
		annullaBottone.setBounds(226, 184, 108, 41);
		principalePannello.add(annullaBottone);

		JButton confermaBottone;
		confermaBottone = new JButton();
		confermaBottone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				confermaBottone.setBackground(new Color(254, 114, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				confermaBottone.setBackground(new Color(254, 126, 115));
			}
		});
		confermaBottone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		confermaBottone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String[] parole = titoloLabel.getText().split(" ");
					
					if(parole[0].equals("Spedizione"))
						gestoreApplicazione.confermaNuovoStatoSpedizione(spedizioneSelezionata,spedizioneStato,modelloStatiSpedizioni.getElementAt(statiBox.getSelectedIndex()));
					else {
						gestoreApplicazione.confermaNuovoStatoOrdine(ordineSelezionato,ordineStato,modelloStatiOrdini.getElementAt(statiBox.getSelectedIndex()));
						
					}
				} catch (UninaDeliverySQLException ErroreSQL) {
					messaggioPopUp(ErroreSQL.getMessaggioErrore(),ErroreSQL.getTipoErrore());
				} catch (EmailException e1) {
					if(e1.getMessage().contains("domain"))
						messaggioPopUp("Dominio non adatto","Attenzione");
					else
						messaggioPopUp(e1.getMessage(),"Errore");
				} catch (IOException e1) {
					messaggioPopUp("Credenziali email non accessibili, è possibile comunque continuare senza il servizio email","Attenzione");
				}
				
			}
		});
		confermaBottone.setToolTipText("Qui Puoi confermare la tua scelta");
		confermaBottone.setText("Conferma");
		confermaBottone.setHorizontalAlignment(SwingConstants.CENTER);
		confermaBottone.setForeground(Color.WHITE);
		confermaBottone.setFont(new Font("Century", Font.BOLD, 20));
		confermaBottone.setFocusPainted(false);
		confermaBottone.setBorder(new LineBorder(new Color(158, 91, 76), 2, true));
		confermaBottone.setBackground(new Color(254, 126, 115));
		confermaBottone.setBounds(108, 184, 108, 41);
		principalePannello.add(confermaBottone);

		statiBox = new JComboBox();
		statiBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		statiBox.setBackground(new Color(179, 168, 166));
		statiBox.setForeground(new Color(255, 255, 255));
		statiBox.setFont(new Font("Century", Font.PLAIN, 20));
		statiBox.setToolTipText("Qui puoi modificare lo stato");
		statiBox.setBounds(108, 114, 226, 41);
		principalePannello.add(statiBox);

		JLabel descrizioneLabel;
		descrizioneLabel = new JLabel("Scegli lo stato in cui aggiornare");
		descrizioneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descrizioneLabel.setForeground(new Color(255, 255, 255));
		descrizioneLabel.setFont(new Font("Century", Font.PLAIN, 20));
		descrizioneLabel.setBounds(10, 46, 414, 35);
		principalePannello.add(descrizioneLabel);
		
		titoloLabel = new JLabel();
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLabel.setForeground(Color.WHITE);
		titoloLabel.setFont(new Font("Century", Font.BOLD, 20));
		titoloLabel.setBounds(10, 11, 414, 35);
		principalePannello.add(titoloLabel);
		
		statoAttualeLabel = new JLabel("Stato attuale : ");
		statoAttualeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statoAttualeLabel.setForeground(Color.WHITE);
		statoAttualeLabel.setFont(new Font("Century", Font.PLAIN, 18));
		statoAttualeLabel.setBounds(10, 79, 414, 25);
		principalePannello.add(statoAttualeLabel);
		
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Si è verificato un errore: " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void modificaStatoOrdine(String ord, String statoOrd) {

		// Imposto l'ordine nella combobox
		ordineSelezionato = ord;
		ordineStato = statoOrd;
		statiBox.setModel(modelloStatiOrdini);
		//Bado alla grafica
		titoloLabel.setText("Ordine : " + ordineSelezionato);
		statoAttualeLabel.setText("Stato Attuale : "+ statoOrd);
		
		

	}

	protected void modificaStatoSpedizione(String sped, String StatoSped) {

		// Imposto la spedizione nella combobox
		spedizioneSelezionata = sped;
		spedizioneStato = StatoSped;
		statiBox.setModel(modelloStatiSpedizioni);
		//Bado alla grafica
		titoloLabel.setText("Spedizione : " + spedizioneSelezionata);
		statoAttualeLabel.setText("Stato Attuale : "+ StatoSped);

	}
	
	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}

}
