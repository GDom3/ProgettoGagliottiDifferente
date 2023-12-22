package UniDy.UninaDelivery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;


public class AppBrain {
	
	private FinestraLogin loginWindow;
	private FinestraMenu menuWindow;
	private FinestraReportStatistico datiStatisticiWindow;
	private FinestraVisualizzaDatiFiltrabili datiOrdiniWindow;

	ComunicaConDatabase comunicazioneSQL;
	private Operatore operatorePrincipale;
	private OperatoreDAO operatoreDAO;
	private Operatore nuovoOperatore;
	private SpedizioneDAO spedizioneDAO;
	private ArrayList<Spedizione> spedizioni;
	public static void main(String[] args) {
		
		AppBrain gestoreApplicazione = new AppBrain();
		
	}

	
	public AppBrain() {
		//Inizializzazione fineste
		datiStatisticiWindow = new FinestraReportStatistico(this);
		datiOrdiniWindow = new FinestraVisualizzaDatiFiltrabili(this);
		menuWindow = new FinestraMenu(this);
		loginWindow = new FinestraLogin(this);
		
		//mostraFinestraVisualizza();
		//datiOrdiniWindow.setVisible(true); 
		//menuWindow.setVisible(true); 
		loginWindow.setVisible(true);
		
		
		try {
			comunicazioneSQL = new ComunicaConDatabase();
		} catch (ConnessionNonRiuscitaException e) {
			loginWindow.messaggioPopUp("Servizio database non garantito : " + e.getMessaggioErrore(), e.getTipoErrore());
		} catch (CreazioneStatementFallitaException e) {
			loginWindow.messaggioPopUp("Servizio database non garantito : " + e.getMessaggioErrore(), e.getTipoErrore());
		}
		
		
		//Inizializzaziione DTO
			operatorePrincipale = new Operatore(null,null);
			nuovoOperatore = new Operatore(null,null);
				
		//Inizializzaziione DAO
			operatoreDAO = new OperatoreDAOPlainSQL(comunicazioneSQL,this);
			spedizioneDAO = new SpedizioneDAOPlainSQL(comunicazioneSQL, this);
			
	}
	

	protected void accesso(String username, String password) throws UsernameNonEsistenteException, PasswordErrataException, CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException{
		
		//Nome che apparirà nel menu
		String nomeCompletoOperatore = "Utente";
		
		//Creo il nuovo operatore
		nuovoOperatore.impostaCredenziali(username,password);
		
		//Vedo se è la prima volta o si accede con un altro account
		if(operatorePrincipale.getUsername() == null || !operatorePrincipale.equals(nuovoOperatore)){	
			//In tal caso recupero gli attributi necessari
			operatoreDAO.provaAccesso(username, password);
		}else{
			//Altrimenti è lo stesso Operatore che ha fatto accesso prima e quindi controllo solo la password
			if(!password.equals(operatorePrincipale.getPassword()))
				throw new PasswordErrataException();
		}
		
		//Imposto L'operatore validato 
		operatorePrincipale.setUsername(username);
		operatorePrincipale.setPassword(password);
		operatorePrincipale.setNome(nuovoOperatore.getNome());
		operatorePrincipale.setCognome(nuovoOperatore.getCognome());
		nomeCompletoOperatore = operatorePrincipale.presentati();
		
		//Do il benvenuto all'operatore
		menuWindow.impostaOperatore(nomeCompletoOperatore);
		
		// Vado nel menu
		ritornaMenu(loginWindow); 
		
		
	}
	
	protected void restrituisciNomeOperatore(String string) {
		nuovoOperatore.setNome(string);		
	}

	protected void restrituisciCognomeOperatore(String string) {
		nuovoOperatore.setCognome(string);	
		
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


	public int exit() {
		BufferedWriter buffer;
		
		try {
			comunicazioneSQL.chiudiComunicazioneDatabase();
		} catch (ChiusturaComunicazioneFallitaException e) {
            try {
				buffer = new BufferedWriter (new FileWriter(new File("src/main/java/File/LogErroriChiusuraConnessione.txt")));
				buffer.append(LocalDate.now().toString() + " Errore Chiusura");
				buffer.close();
			} catch (IOException e1) {
				//Errore di scrittura superfluo
			}
            
		}catch (NullPointerException e) {
			//Appena avviato darà errore sicuro
		}
		
		return JFrame.EXIT_ON_CLOSE;
	}
	
	
	protected void filtra() throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException  {
		
		spedizioni = spedizioneDAO.ricavaSpedizioni("");
		stampaInTablella();
				
			
	}
	
	
	
	protected void filtra(String cliente, LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException{
		if(datiOrdiniWindow.IsDataEsecuzioneSelezionato())
			spedizioni = spedizioneDAO.ricavaSpedizioniPerUtenteEDateE(cliente, dataInizio, dataFine);
		else
			spedizioni = spedizioneDAO.ricavaSpedizioniPerUtenteEDateConsegna(cliente, dataInizio, dataFine);
		
		
		stampaInTablella();
	}
 
	
	protected void filtra(LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException  {
		if(datiOrdiniWindow.IsDataEsecuzioneSelezionato())
			spedizioni = spedizioneDAO.ricavaSpedizioniPerDateE(dataInizio, dataFine);
		else
			spedizioni = spedizioneDAO.ricavaSpedizioniPerDateConsegna(dataInizio, dataFine);
		
		stampaInTablella();
			
	}

	
	protected void filtra(String cliente) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {	
		spedizioni = spedizioneDAO.ricavaSpedizioniPerCliente(cliente);
		
		stampaInTablella();
		
		
	}


	private void stampaInTablella() {
		datiOrdiniWindow.svuotaTabella();
		
		for (Spedizione sped : spedizioni) 
			for(Ordine ord : sped.getOrdini()) 
				datiOrdiniWindow.aggiungiTupla(ord.getAcquirente().toString(),ord.getCodOrdine(),ord.getNumMerci(),ord.getCostoTotale(), sped.getCodSpedizione());
		
	}



	


}


