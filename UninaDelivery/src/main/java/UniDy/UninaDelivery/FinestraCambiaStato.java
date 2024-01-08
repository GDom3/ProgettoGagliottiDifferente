package UniDy.UninaDelivery;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private Spedizione spedizioneSelezionata;
	private Ordine ordineSelezionato;

	public FinestraCambiaStato(AppBrain appBrain) {
		setAlwaysOnTop(true);
		setResizable(false);

		setTitle("UninaDelivery");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraCambiaStato.class.getResource("/Img/Icon.png")));
		gestoreApplicazione = appBrain;

		setDefaultCloseOperation(appBrain.annullaCambioStato());
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
		confermaBottone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String[] parole = titoloLabel.getText().split(" ");
					
					if(parole[0].equals("Spedizione"))
						gestoreApplicazione.confermaNuovoStatoSpedizione(spedizioneSelezionata,modelloStatiSpedizioni.getElementAt(statiBox.getSelectedIndex()));
					else
						gestoreApplicazione.confermaNuovoStatoOrdine(ordineSelezionato,modelloStatiOrdini.getElementAt(statiBox.getSelectedIndex()));
					
				} catch (CreazioneStatementFallitaException e1) {
					messaggioPopUp(e1.getMessaggioErrore(), e1.getTipoErrore());
				} catch (ConnessionNonRiuscitaException e1) {
					messaggioPopUp(e1.getMessaggioErrore(), e1.getTipoErrore());
				} catch (RisultatoNonRicavabileException e1) {
					messaggioPopUp(e1.getMessaggioErrore(), e1.getTipoErrore());
				}
				
			}
		});
		confermaBottone.setToolTipText("Qui Puoi confermare la tua scelta");
		confermaBottone.setText("Conferma");
		confermaBottone.setHorizontalAlignment(SwingConstants.CENTER);
		confermaBottone.setForeground(Color.WHITE);
		confermaBottone.setFont(new Font("Century", Font.BOLD, 20));
		confermaBottone.setFocusPainted(false);
		confermaBottone.setBorder(new LineBorder(new Color(52, 43, 42), 2, true));
		confermaBottone.setBackground(new Color(179, 168, 166));
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
	}
	
	protected void modificaStatoOrdine(Ordine ord) {

		// Imposto l'ordine nella combobox
		ordineSelezionato = ord;
		statiBox.setModel(modelloStatiOrdini);
		//Bado alla grafica
		titoloLabel.setText("Ordine : " + ordineSelezionato.getCodOrdine());
		statoAttualeLabel.setText("Stato Attuale : "+ ord.getStatoOrdine());
		
		

	}

	protected void modificaStatoSpedizione(Spedizione sped) {

		// Imposto la spedizione nella combobox
		spedizioneSelezionata = sped;
		statiBox.setModel(modelloStatiSpedizioni);
		//Bado alla grafica
		titoloLabel.setText("Spedizione : " + spedizioneSelezionata.getCodSpedizione());
		statoAttualeLabel.setText("Stato Attuale : "+ sped.getStatoSpedizione());

	}
	
	protected void messaggioPopUp(String testo, String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.WARNING_MESSAGE);
	}

}
