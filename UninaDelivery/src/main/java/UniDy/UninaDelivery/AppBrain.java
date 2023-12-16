package UniDy.UninaDelivery;

import java.sql.*;
import java.time.LocalDate;

import javax.swing.JFrame;


public class AppBrain {
	
	private FinestraLogin loginWindow;
	private FinestraMenu menuWindow;
	private FinestraReportStatistico datiStatisticiWindow;
	private FinestraVisualizzaDatiFiltrabili datiOrdiniWindow;
	//private ResultSet risultato;
	private GestoreFiltraggioOrdini gestroreFiltri;
	//private ComunicaConDatabase comunicazioneSQL = new ComunicaConDatabase();
	
	private Operatore operatore;
	
	public static void main(String[] args) {
		
		AppBrain gestoreApplicazione = new AppBrain();
		
	}

	public AppBrain() {
			
		loginWindow = new FinestraLogin(this);
		menuWindow = new FinestraMenu(this);
		datiStatisticiWindow = new FinestraReportStatistico(this);
		datiOrdiniWindow = new FinestraVisualizzaDatiFiltrabili(this);
		gestroreFiltri = new GestoreFiltraggioOrdini(this);
		
		operatore = new Operatore();
		
		//mostraFinestraVisualizza();
		
		//datiOrdiniWindow.setVisible(true); 
		//menuWindow.setVisible(true); 
		loginWindow.setVisible(true);
		
	}


	protected void accesso(String username, String password) throws  SQLException{
		
		String nomeOperatore = "Utente";
		
		operatore.provaAccesso(username, password);
		
		nomeOperatore = operatore.getNomeOperatore();
		
		//Do il benvenuto al nuovo operatore
		menuWindow.impostaOperatore(nomeOperatore);
		// Vado nel menu
		ritornaMenu(loginWindow); 
		
		
	}
	
	protected void logout() {
		//Funzione che gestisce il logout
		loginWindow.impostaPassword("Password");
		menuWindow.setVisible(false);
		loginWindow.setVisible(true);
		
	}

	protected void ritornaMenu(JFrame finestra) {
		//Funzione che 'chiude' la finestra data in input e 'apre' il menu
		finestra.setVisible(false);
		menuWindow.setVisible(true);
	}

	protected void mostraFinestraVisualizza() {
		//'chiude' il menu e 'apre' il visuliazza dati finesta 
		menuWindow.setVisible(false);
		datiOrdiniWindow.setVisible(true);
		datiOrdiniWindow.richiestaVisualizzareTutti();
		
	}
	
	protected void filtra() throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, EstrazioneCampiFallitaException {
		gestroreFiltri.visualizzaOrdini("");
		
	}
	
	protected void filtra(String cliente, LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, EstrazioneCampiFallitaException  {
		gestroreFiltri.filtraPerUtenteData(cliente, dataInizio, dataFine);
	}

	protected void filtra(LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, EstrazioneCampiFallitaException {
		gestroreFiltri.filtraPerDate(dataInizio, dataFine);
		
	}

	protected void filtra(String cliente) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, EstrazioneCampiFallitaException {
		gestroreFiltri.filtraPerCliente(cliente);
	}

	protected FinestraVisualizzaDatiFiltrabili getDatiOrdiniWindow() {
		return datiOrdiniWindow;
	}


}


