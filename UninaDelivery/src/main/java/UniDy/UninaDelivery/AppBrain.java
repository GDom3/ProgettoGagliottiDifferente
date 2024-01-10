package UniDy.UninaDelivery;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFrame;


public class AppBrain {
	//Finestre
	private FinestraLogin loginWindow;
	private FinestraMenu menuWindow;
	private FinestraReportStatistico datiStatisticiWindow;
	private FinestraNuovaSpedizione creazioneSpedizioneWindow;
	private FinestraVisualizzaDatiFiltrabili datiOrdiniWindow;
	private FinestraCambiaStato cambiaStatoWindow;
	private FinestraCreazioneNuovoOrdine creaOrdineWindow;
	private FinestraCreazioneNuovoCliente creaClienteWindow;
	private FinestraInserimentoCorriere inserisciCorriereWindow;
	private FinestraInserimentoMezzoTrasporto inserisciMezzoWindow;
	private FinestraInserimentoEsemplare creaEsemplareWindow;
	private FinestraCreazioneNuovoMerce creaMerceWindow;
	//Database
	private ComunicaConDatabase comunicazioneSQL;
	//DAO
	private OperatoreDAO operatoreDAO;
	private SpedizioneDAO spedizioneDAO;
	private OrdineDAO ordineDAO;
	private CorriereDAO corriereDAO;
	private MezzoTrasportoDAO mezziTrasportoDAO;
	private ClienteDAO clienteDAO;
	private EsemplareDAO esemplareDAO;
	private MerceDAO merceDAO;
	private FornitoreDAO fornitoreDAO;
	private MagazzinoDAO magazzinoDAO;
	//Oggetti Utili
		
	//Nuova Sped 	
		private ArrayList<Ordine> ordiniSenzaSpedizioneOFalliti;
		private ArrayList<Corriere> corrieriDisponibili;
		private ArrayList<MezzoTrasporto> mezziDisponibili;
		private Spedizione nuovaSpedizione;
	//Login	
		private Operatore operatorePrincipale;
		private Operatore nuovoOperatore;
	
		
		private ArrayList<Spedizione> spedizioni;
	
	
	public static void main(String[] args) {
		
		AppBrain gestoreApplicazione = new AppBrain();
		
	}

	
	public AppBrain() {
		//Inizializzazione fineste
		datiStatisticiWindow = new FinestraReportStatistico(this);
		datiOrdiniWindow = new FinestraVisualizzaDatiFiltrabili(this);
		menuWindow = new FinestraMenu(this);
		creazioneSpedizioneWindow = new FinestraNuovaSpedizione(this);
		creaOrdineWindow = new FinestraCreazioneNuovoOrdine(this);
		inserisciCorriereWindow = new FinestraInserimentoCorriere(this);
		loginWindow = new FinestraLogin(this);
		loginWindow.setVisible(true);
		cambiaStatoWindow = new FinestraCambiaStato(this);
		creaClienteWindow = new FinestraCreazioneNuovoCliente(this);
		inserisciMezzoWindow = new FinestraInserimentoMezzoTrasporto(this);
		creaMerceWindow = new FinestraCreazioneNuovoMerce(this);
		creaEsemplareWindow = new FinestraInserimentoEsemplare(this);	
		
		
		// Avvia Comunicazione
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
		operatoreDAO = new OperatoreDAOPlainSQL(comunicazioneSQL);
		spedizioneDAO = new SpedizioneDAOPlainSQL(comunicazioneSQL);
		ordineDAO = new OrdineDAOPlainSQL(comunicazioneSQL);
		corriereDAO = new CorriereDAOPlainSQL(comunicazioneSQL);
		mezziTrasportoDAO = new MezzoTrasportoDAOPlainSQL(comunicazioneSQL);
		clienteDAO = new ClienteDAOPlainSQL(comunicazioneSQL);
		esemplareDAO = new EsemplareDAOPlainSQL(comunicazioneSQL);
		merceDAO = new MerceDAOPlainSQL(comunicazioneSQL);
		fornitoreDAO = new FornitoreDAOPlainSQL(comunicazioneSQL);
		magazzinoDAO = new MagazzinoDAOPlainSQL(comunicazioneSQL);
		
	}
	

	protected void accesso(String username, String password) throws UsernameNonEsistenteException, PasswordErrataException, CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException{
		
		//Nome che apparirà nel menu
		String nomeCompletoOperatore = "Utente";
		
		//Creo il nuovo operatore
		nuovoOperatore.impostaCredenziali(username,password);
		
		//Vedo se è la prima volta o si accede con un altro account
		if(operatorePrincipale.getUsername() == null || !operatorePrincipale.equals(nuovoOperatore)){	
			//In tal caso recupero gli attributi necessari
			operatorePrincipale = operatoreDAO.provaAccesso(username, password);
		}else{
			//Altrimenti è lo stesso Operatore che ha fatto accesso prima e quindi controllo solo la password
			if(!password.equals(operatorePrincipale.getPassword()))
				throw new PasswordErrataException();
		}
		
		
		nomeCompletoOperatore = operatorePrincipale.presentati();
		
		//Do il benvenuto all'operatore
		menuWindow.impostaOperatore(nomeCompletoOperatore);
		
		// Vado nel menu
		ritornaMenu(loginWindow); 
		
		
	}

	protected void logout() {
		//Funzione che gestisce il logout
		loginWindow.impostaPassword("Password");
		loginWindow.setVisible(true);
		menuWindow.setVisible(false);
	
	}

	protected void ritornaMenu(JFrame finestra) {
		//Funzione che 'chiude' la finestra data in input e 'apre' il menu
		menuWindow.setVisible(true);
		finestra.setVisible(false);
	}

	protected void mostraFinestraVisualizza() {
		//'chiude' il menu e 'apre' il visuliazza dati finesta 
		datiOrdiniWindow.setVisible(true);
		menuWindow.setVisible(false);
		datiOrdiniWindow.richiestaVisualizzareTutti();
		
	}


	protected int exit() {
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
	
	
	
	protected void filtraConTutto(String cliente, LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException{
		if(datiOrdiniWindow.IsDataEsecuzioneSelezionato())
			spedizioni = spedizioneDAO.ricavaSpedizioniPerUtenteEDateE(cliente, dataInizio, dataFine);
		else
			spedizioni = spedizioneDAO.ricavaSpedizioniPerUtenteEDateConsegna(cliente, dataInizio, dataFine);
		
		
		stampaInTablella();
	}
 
	
	protected void filtraSoloData(LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException  {
		if(datiOrdiniWindow.IsDataEsecuzioneSelezionato())
			spedizioni = spedizioneDAO.ricavaSpedizioniPerDateE(dataInizio, dataFine);
		else
			spedizioni = spedizioneDAO.ricavaSpedizioniPerDateConsegna(dataInizio, dataFine);
		
		stampaInTablella();
			
	}

	
	protected void filtraSoloCliente(String cliente) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {	
		spedizioni = spedizioneDAO.ricavaSpedizioniPerCliente(cliente);
		
		stampaInTablella();
		
		
	}


	private void stampaInTablella() {
		datiOrdiniWindow.svuotaTabella();
		
		for (Spedizione sped : spedizioni) 
			for(Ordine ord : sped.getOrdini()) 
				datiOrdiniWindow.aggiungiTupla(ord.getAcquirente().toString(),ord.getCodOrdine(),ord.getNumMerci(),ord.getCostoTotale(), sped.getCodSpedizione());
		
	}


	protected void modificaStatoOrdine(Object valueAt) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException {
		
		String codOrdine = valueAt.toString(); 
		
		Ordine ord = ordineDAO.trovaOrdine(codOrdine);

		
		cambiaStatoWindow.modificaStatoOrdine(ord);
		cambiaStatoWindow.setVisible(true);
		datiOrdiniWindow.setVisible(false);
	}


	protected void annullaOperazioneCambioStato() {
		datiOrdiniWindow.setVisible(true);
		cambiaStatoWindow.setVisible(false);
		
		
	}
	
	protected int annullaCambioStato() {
		
		if(!loginWindow.isVisible())
			datiOrdiniWindow.setVisible(true);
	
		
		
		
		return JFrame.DISPOSE_ON_CLOSE;
	}
	

	protected void modificaStatoSpedizione(Object valueAt) throws RisultatoNonRicavabileException, CreazioneStatementFallitaException, ConnessionNonRiuscitaException {
		
		String codSpedizione = valueAt.toString(); 
		
		Spedizione sped;
		
		sped = spedizioneDAO.trovaSpedizione(codSpedizione);


		
		cambiaStatoWindow.modificaStatoSpedizione(sped);
		cambiaStatoWindow.setVisible(true);
		datiOrdiniWindow.setVisible(false);
	}


	protected void confermaNuovoStatoOrdine(Ordine ordineSelezionato, Object elementAt) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException {
		String StatoOrdine = elementAt.toString();
		if(ordineSelezionato.getStatoOrdine().equals(StatoOrdine)) 
			datiOrdiniWindow.messaggioPopUp("Non puoi selezionare questo stato, in quanto è quello corrente","Attenzione");
			
		else {
		
			ordineSelezionato.setStatoOrdine(StatoOrdine);
			String responso = ordineDAO.aggiornaStatoOrdine(ordineSelezionato);
		
			if(responso.equals("OK")){
				String msg = "Stato ordine modificato Correttamente.\nDettaglio : L'ordine "+ordineSelezionato.getCodOrdine()+" ha come nuovo stato "+StatoOrdine;
				datiOrdiniWindow.messaggioPopUp(msg,"Operazione Riuscita");
				datiOrdiniWindow.setVisible(true);
				cambiaStatoWindow.setVisible(false);
			}else{
				String msg = "Operazione non possibile.\nDettaglio : Lo stato "+ StatoOrdine +" non è coerente con lo stato "+ responso + " della spedizione" ;
				cambiaStatoWindow.messaggioPopUp(msg, "Operazione Non Riuscita");
			}
		
		}
	}


	protected void confermaNuovoStatoSpedizione(Spedizione spedizioneSelezionata, Object elementAt) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException {
		String StatoSpedizione = elementAt.toString();
		if(spedizioneSelezionata.getStatoSpedizione().equals(StatoSpedizione)) 
			datiOrdiniWindow.messaggioPopUp("Non puoi selezionare questo stato, in quanto è quello corrente","Attenzione");
			
		else {
		
			spedizioneSelezionata.setStatoSpedizione(StatoSpedizione);
			String responso = spedizioneDAO.aggiornaStatoSpedizione(spedizioneSelezionata);
		
			if(responso.equals("OK")){
				String msg = "Stato Spedizione modificato Correttamente.\nDettaglio : spedizione "+spedizioneSelezionata.getCodSpedizione()+" ha come nuovo stato "+StatoSpedizione;
				datiOrdiniWindow.messaggioPopUp(msg,"Operazione Riuscita");
				datiOrdiniWindow.setVisible(true);
				cambiaStatoWindow.setVisible(false);
			}else{
				String msg = "Operazione non possibile.\nDettaglio : Lo stato "+ StatoSpedizione +" non è coerente con lo stato "+ responso + " della spedizione" ;
				cambiaStatoWindow.messaggioPopUp(msg, "Operazione Non Riuscita");
			}
		
		}
		
		
		
		
	}


	protected void mostraFinestraNuovaSpedizione() {
		creazioneSpedizioneWindow.setVisible(true);
		creazioneSpedizioneWindow.avviati();
		menuWindow.setVisible(false);
			
		
	}


	protected void estraiOrdiniSenzaSpedOFalliti() throws RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException {
		ordiniSenzaSpedizioneOFalliti =  ordineDAO.estraiOrdiniSenzaSpedOFalliti();
	}


	protected void estraiCorrieriSenzaSped() throws RisultatoNonRicavabileException, NonCiSonoCorrieriDisponibiliException {
		corrieriDisponibili =  corriereDAO.estraiCorrieriSenzaSped();
	}


	protected void estraiMezziSenzaSped() throws RisultatoNonRicavabileException, NonCiSonoMezziTrasportoDisponibiliException {
		mezziDisponibili =  mezziTrasportoDAO.estraiMezziSenzaSped();
	}


	protected ArrayList<Spedizione> dammiSpedizioniNonPartite() throws NonCiSonoSpedizioniNonPartite, RisultatoNonRicavabileException {
		return spedizioneDAO.dammiSpedizioniNonPartite();
	}


	protected void inserisciOrdineInSpedizione(Spedizione spedizione, Ordine ordine) throws OperazioneUpdateNonRiuscitaException {
			spedizioneDAO.inserisciOrdineInSpedizione(spedizione,ordine);
		
	}


	protected void mostraFinestraReport() {
		datiStatisticiWindow.setVisible(true);
		menuWindow.setVisible(false);
	}


	protected void mostramiSchermataCreaOrdine() {
		creaOrdineWindow.setVisible(true);
		creazioneSpedizioneWindow.setVisible(false);
		creaOrdineWindow.avviati();
		
		
	}


	protected void ritornaNuovaSpedizione(JFrame finestra) {
		creazioneSpedizioneWindow.setVisible(true);
		creazioneSpedizioneWindow.avviati();
		finestra.setVisible(false);
		
	}


	protected ArrayList<Cliente> dammiTuttiClienti() throws RisultatoNonRicavabileException, NonCiSonoClientiException {
		return clienteDAO.dammiTuttiClienti();
	}


	protected ArrayList<Esemplare> dammiEsemplariNonVenduti() throws RisultatoNonRicavabileException, NonCiSonoEsemplariNonVendutiException {
		return esemplareDAO.dammiEsemplariNonvenduti();
	}


	protected ArrayList<Ordine> dammiOrdiniNonPartitiOFalliti() throws RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException {
		return ordineDAO.dammiOrdiniNonPartitiOFalliti();
	}


	protected void inserisciEsemplareInOrdine(Ordine ordine, Esemplare esemplare) throws NonCiSonoOrdiniAttesiException {
		esemplareDAO.inserisciEsemplareInOrdine(ordine,esemplare);
		
	}


	protected void creaOrdine(Ordine nuovoOrd) throws RisultatoNonRicavabileException, NonPossibileCreareOrdineException {
		ordineDAO.creaOrdine(nuovoOrd);
		
	}


	protected void mostraFinestraCreazioneCliente() {
		creaClienteWindow.setVisible(true);
		creaOrdineWindow.setVisible(false);
		
	}


	protected void ritornaNuovoOrdine(JFrame finestra) {
		creaOrdineWindow.avviati();
		creaOrdineWindow.setVisible(true);
		creaOrdineWindow.avviati();
		finestra.setVisible(false);
		
	}

	protected void assumiCorriere(Corriere corriere) throws OperazioneUpdateNonRiuscitaException {
		corriereDAO.assumiCorriere(corriere);
		
	}


	protected ArrayList<Corriere> estraiTuttiCorrieri() throws RisultatoNonRicavabileException, NonCiSonoCorrieriException {
		return corriereDAO.estraiTuttiCorrieri();
	}
	
	protected void mostramiSchermataInserimentoCorriere() {
		inserisciCorriereWindow.avviati();
		inserisciCorriereWindow.setVisible(true);
		creazioneSpedizioneWindow.setVisible(false);
	}


	protected void registraCliente(Cliente clienteTemp) throws NonPossibileCreareClienteException{
		clienteDAO.registraCliente(clienteTemp);
		
	}
	
	protected void registraMezzo(MezzoTrasporto mezzo)  throws OperazioneUpdateNonRiuscitaException{
		mezziTrasportoDAO.registraMezzo(mezzo);
	}

	protected void mostramiSchermataInserimentoMezzo() {
		inserisciMezzoWindow.setVisible(true);
		creazioneSpedizioneWindow.setVisible(false);
	}


	protected void mostraFinestraCreazioneMerce() {
		creaMerceWindow.setVisible(true);
		creaMerceWindow.avviati();
		creaEsemplareWindow.setVisible(false);
	}
	
	protected void mostraFinestraCreazioneEsemplare() {
		creaEsemplareWindow.setVisible(true);
		creaEsemplareWindow.avviati();
		creaOrdineWindow.setVisible(false);
	}


	protected void creaNuovaMerce(Merce merceTemp) throws NonPossibileCreareMerceException {
		merceDAO.creaNuovaMerce(merceTemp);
	}
	
	protected ArrayList<Fornitore> dammiTuttiFornitori() throws RisultatoNonRicavabileException, NonCiSonoFornitoriException {
		return fornitoreDAO.dammiTuttiFornitori();
		
	}
	
	protected ArrayList<Merce> dammiTutteMerci() throws RisultatoNonRicavabileException, NonCiSonoMerciDisponibiliException {
		return merceDAO.estraiMerce();
	}


	protected void ritornaEsemplare(FinestraCreazioneNuovoMerce finestraCreazioneNuovoMerce)  {
		creaEsemplareWindow.setVisible(true);
		creaEsemplareWindow.avviati();
		creaMerceWindow.setVisible(false);
	}


	protected ArrayList<Magazzino> dammiTutteMagazzini() throws RisultatoNonRicavabileException, NonCiSonoMagazziniDisponibiliException {
		return magazzinoDAO.dammiTutteMagazzini();
	}


	protected void creaEsemplare(Esemplare esemplareTemp) throws OperazioneUpdateNonRiuscitaException {
		esemplareDAO.creaEsemplare(esemplareTemp);
		
	}


	public int[] numeroMedioOrdini(int anno) throws RisultatoNonRicavabileException {
		return ordineDAO.numeroMedioOrdini(anno);
	}
	
	
	protected String ordineConMaggiorProdotti (int anno) throws RisultatoNonRicavabileException {
		return ordineDAO.ordineConMaggiorProdotti(anno).getCodOrdine();

	}
	
	protected String ordineConMinorProdotti (int anno) throws RisultatoNonRicavabileException {
		return ordineDAO.ordineConMinorProdotti(anno).getCodOrdine();

	}


	protected Object[] DammiFormatoComboBoxOrdiniSenzaSpedOFalliti() {
		return ordiniSenzaSpedizioneOFalliti.toArray();
	}


	protected Object[] DammiFormatoComboBoxCorrieriDisponibili() {
		return corrieriDisponibili.toArray();
	}


	protected Object[] DammiFormatoComboBoxMezziDisponibili() {
		return mezziDisponibili.toArray();
	}


	protected void creaSpedizioneNuova(int ordineIndex, int mezzoIndex, int corrieriIndex, int km) throws OperazioneUpdateNonRiuscitaException, RisultatoNonRicavabileException, NonPossibileCreareSpedizioneException {
		nuovaSpedizione = new Spedizione(ordiniSenzaSpedizioneOFalliti.get(ordineIndex),mezziDisponibili.get(mezzoIndex),corrieriDisponibili.get(corrieriIndex));

		nuovaSpedizione.setKM(km);
		spedizioneDAO.creaNuovaSpedizione(nuovaSpedizione);
	}
}
	



