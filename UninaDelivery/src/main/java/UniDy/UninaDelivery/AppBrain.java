package UniDy.UninaDelivery;

import java.awt.Color;
import java.awt.Paint;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFrame;

import org.apache.commons.mail.EmailException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


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
	private ArrayList<Spedizione> spedizioni;	
	//Nuova Sped 	
		private ArrayList<Ordine> ordiniSenzaSpedizioneOFalliti;
		private ArrayList<Corriere> corrieriDisponibili;
		private ArrayList<MezzoTrasporto> mezziDisponibili;
		private Spedizione nuovaSpedizione;
		private ArrayList<Spedizione> spedizioniNonPartite;
	//Login	
		private Operatore operatorePrincipale;
		private Operatore nuovoOperatore;
	//Nuovo Corriere
		private ArrayList<Corriere> supervisori;
	//Nuovo Ord
		private ArrayList<Esemplare> esemplariNonVenduti;
		private ArrayList<Cliente> clientela;
		private ArrayList<Ordine> ordiniNonPartiti;
	//Nuovo Esemplare
		private ArrayList<Magazzino> magazzini;
		private ArrayList<Merce> merci;
	//nuova Merce
		private ArrayList<Fornitore> fornitori;
		
	//mail
		private UninaDeliveryMailSender mailSender;
	
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
		mailSender = new UninaDeliveryMailSender();		
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
		String nomeCompletoOperatore;
		
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
				datiOrdiniWindow.aggiungiTupla(ord.getAcquirente().getCodiceFiscale(),ord.getCodOrdine(),ord.getNumMerci(),ord.getCostoTotale(), sped.getCodSpedizione());
		
	}


	protected void modificaStatoOrdine(Object valueAt) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException {
		
		String codOrdine = valueAt.toString(); 
		
		Ordine ord = ordineDAO.trovaOrdine(codOrdine);

		
		cambiaStatoWindow.modificaStatoOrdine(ord.getCodOrdine(),ord.getStatoOrdine());
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


		
		cambiaStatoWindow.modificaStatoSpedizione(sped.getCodSpedizione(),sped.getStatoSpedizione());
		cambiaStatoWindow.setVisible(true);
		datiOrdiniWindow.setVisible(false);
	}


	
	protected void confermaNuovoStatoOrdine(String ordineSelezionato, Object statoOriginale, Object stato) throws RisultatoNonRicavabileException, EmailException {
		String StatoOrdine = stato.toString();
		if(statoOriginale.equals(StatoOrdine)) 
			datiOrdiniWindow.messaggioPopUp("Non puoi selezionare questo stato, in quanto è quello corrente","Attenzione");
			
		else {
		
			Ordine ordineAggiornato = new Ordine(ordineSelezionato, StatoOrdine);
			String responso = ordineDAO.aggiornaStatoOrdine(ordineAggiornato);
		
			if(responso.equals("OK")){
				String msg = "Stato ordine modificato Correttamente.\nDettaglio : L'ordine "+ordineSelezionato+" ha come nuovo stato "+StatoOrdine;
				datiOrdiniWindow.messaggioPopUp(msg,"Operazione Riuscita");
				informaEmailOrdineStatoModificato(ordineSelezionato,StatoOrdine);
				datiOrdiniWindow.setVisible(true);
				cambiaStatoWindow.setVisible(false);
			}else{
				String msg = "Operazione non possibile.\nDettaglio : Lo stato "+ StatoOrdine +" non è coerente con lo stato "+ responso + " della spedizione" ;
				cambiaStatoWindow.messaggioPopUp(msg, "Operazione Non Riuscita");
			}
		
		}
	}
	
	
	public void confermaNuovoStatoSpedizione(String spedizioneSelezionata, String spedizioneStato, Object elementAt) throws RisultatoNonRicavabileException {
		String StatoSpedizione = elementAt.toString();
		if(spedizioneStato.equals(StatoSpedizione)) 
			datiOrdiniWindow.messaggioPopUp("Non puoi selezionare questo stato, in quanto è quello corrente","Attenzione");
			
		else {
			
			Spedizione spedizioneAggiornata = new Spedizione(spedizioneSelezionata,null);
			spedizioneAggiornata.setStatoSpedizione(StatoSpedizione);
			String responso = spedizioneDAO.aggiornaStatoSpedizione(spedizioneAggiornata);
		
			if(responso.equals("OK")){
				String msg = "Stato Spedizione modificato Correttamente.\nDettaglio : spedizione "+spedizioneSelezionata+" ha come nuovo stato "+StatoSpedizione;
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


	


	protected void inserisciOrdineInSpedizione(int spedizione, int ordine) throws OperazioneUpdateNonRiuscitaException {
			spedizioneDAO.inserisciOrdineInSpedizione(spedizioniNonPartite.get(spedizione),ordiniSenzaSpedizioneOFalliti.get(ordine));
		
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


	protected void dammiTuttiClienti() throws RisultatoNonRicavabileException, NonCiSonoClientiException {
		clientela =  clienteDAO.dammiTuttiClienti();
	}


	protected void estraiEsemplariNonVenduti() throws RisultatoNonRicavabileException, NonCiSonoEsemplariNonVendutiException {
		esemplariNonVenduti = esemplareDAO.dammiEsemplariNonvenduti();
	}


	protected void dammiOrdiniNonPartitiOFalliti() throws RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException {
		ordiniNonPartiti =  ordineDAO.dammiOrdiniNonPartitiOFalliti();
	}


	protected void inserisciEsemplareInOrdine(int ordineIndex, int esemplareIndex) throws NonCiSonoOrdiniAttesiException {
		esemplareDAO.inserisciEsemplareInOrdine(ordiniNonPartiti.get(ordineIndex),esemplariNonVenduti.get(esemplareIndex));
		
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

	protected void assumiCorriere(String CF, String nome, String cognome, LocalDate dataNascita, String patenti, String email, String numero, int contratto, int anni, int index) throws OperazioneUpdateNonRiuscitaException {
		
		Corriere corriere;
		
		if(index < 0)
			corriere = new Corriere(CF,nome,cognome,dataNascita,patenti,email,numero,contratto,anni,"null",true);
		else
			corriere = new Corriere(CF,nome,cognome,dataNascita,patenti,email,numero,contratto,anni,supervisori.get(index).getCodiceFiscale(),true);
		
		corriereDAO.assumiCorriere(corriere);
		
	}


	protected void estraiTuttiCorrieri() throws RisultatoNonRicavabileException, NonCiSonoCorrieriException {
		supervisori = corriereDAO.estraiTuttiCorrieri();
	}
	
	protected void mostramiSchermataInserimentoCorriere() {
		inserisciCorriereWindow.avviati();
		inserisciCorriereWindow.setVisible(true);
		creazioneSpedizioneWindow.setVisible(false);
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


	
	
	protected void dammiTuttiFornitori() throws RisultatoNonRicavabileException, NonCiSonoFornitoriException {
		fornitori =  fornitoreDAO.dammiTuttiFornitori();
		
	}
	
	protected void dammiTutteMerci() throws RisultatoNonRicavabileException, NonCiSonoMerciDisponibiliException {
		merci = merceDAO.estraiMerce();
	}


	protected void ritornaEsemplare(FinestraCreazioneNuovoMerce finestraCreazioneNuovoMerce)  {
		creaEsemplareWindow.setVisible(true);
		creaEsemplareWindow.avviati();
		creaMerceWindow.setVisible(false);
	}


	protected void dammiTutteMagazzini() throws RisultatoNonRicavabileException, NonCiSonoMagazziniDisponibiliException {
		magazzini =  magazzinoDAO.dammiTutteMagazzini();
	}


	protected int[] numeroMedioOrdini(int anno) throws RisultatoNonRicavabileException {
		return ordineDAO.numeroMedioOrdini(anno);
	}
	
	
	protected String ordineConMaggiorProdotti (int anno) throws RisultatoNonRicavabileException {
		 Ordine ord = ordineDAO.ordineConMaggiorProdotti(anno);
		 
		 return "n"+ord.getCodOrdine() + " con " + ord.getNumMerci();
	}
	
	protected String ordineConMinorProdotti (int anno) throws RisultatoNonRicavabileException {
		Ordine ord = ordineDAO.ordineConMinorProdotti(anno);
		 
		return "n"+ord.getCodOrdine() + " con " + ord.getNumMerci();

	}


	protected Object[] dammiFormatoComboBoxOrdiniSenzaSpedOFalliti() throws RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException {
		estraiOrdiniSenzaSpedOFalliti();
		return ordiniSenzaSpedizioneOFalliti.toArray();
	}


	protected Object[] dammiFormatoComboBoxCorrieriDisponibili() throws RisultatoNonRicavabileException, NonCiSonoCorrieriDisponibiliException {
		estraiCorrieriSenzaSped();
		return corrieriDisponibili.toArray();
	}

	protected Object[] dammiFormatoComboBoxMezziDisponibili() throws RisultatoNonRicavabileException, NonCiSonoMezziTrasportoDisponibiliException {
		estraiMezziSenzaSped();
		return mezziDisponibili.toArray();
	}
	
	protected Object[] dammiFormatoComboBoxSpedizioniNonPartite() throws NonCiSonoSpedizioniNonPartiteException, RisultatoNonRicavabileException{
		//Ricavo le spedizioni disponibili
		trovaSpedizioniNonPartite();
		
		return spedizioniNonPartite.toArray();
	}
	
	protected Object[] dammiFormatoComboBoxEsemplariNonVenduti() throws RisultatoNonRicavabileException, NonCiSonoEsemplariNonVendutiException {
		estraiEsemplariNonVenduti();
		return esemplariNonVenduti.toArray();
	}
	
	protected Object[] dammiFormatoComboBoxClientela() throws RisultatoNonRicavabileException, NonCiSonoClientiException {
		dammiTuttiClienti();
		return clientela.toArray();
	}
	
	protected Object[] dammiFormatoComboBoxOrdiniNonPartiti() throws RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException {
		dammiOrdiniNonPartitiOFalliti();
		return ordiniNonPartiti.toArray();
	}
	
	protected Object[] dammiFormatoComboBoxMagazzini() throws RisultatoNonRicavabileException, NonCiSonoMagazziniDisponibiliException {
		dammiTutteMagazzini();
		return magazzini.toArray();
	}
	
	protected Object[] dammiFormatoComboBoxMerce() throws RisultatoNonRicavabileException, NonCiSonoMerciDisponibiliException {
		dammiTutteMerci();
		return merci.toArray();
	}
	
	protected Object[] dammiFormatoComboBoxFornitori() throws RisultatoNonRicavabileException, NonCiSonoFornitoriException {
		dammiTuttiFornitori();
		return fornitori.toArray();
	}

	protected void creaSpedizioneNuova(int ordineIndex, int mezzoIndex, int corrieriIndex, int km) throws OperazioneUpdateNonRiuscitaException, RisultatoNonRicavabileException, NonPossibileCreareSpedizioneException {
		nuovaSpedizione = new Spedizione(ordiniSenzaSpedizioneOFalliti.get(ordineIndex),mezziDisponibili.get(mezzoIndex),corrieriDisponibili.get(corrieriIndex));

		nuovaSpedizione.setKM(km);
		spedizioneDAO.creaNuovaSpedizione(nuovaSpedizione);
	}
	
	protected String dammiCodiceOrdineDeiDisponibili(int i) {
		
		return ordiniSenzaSpedizioneOFalliti.get(i).getCodOrdine();
		
	}
	
	protected String dammiCodiceSpedizioneDaiNonPartiti(int i) {
		
		return spedizioniNonPartite.get(i).getCodSpedizione();
		
	}
	
	protected void trovaSpedizioniNonPartite() throws NonCiSonoSpedizioniNonPartiteException, RisultatoNonRicavabileException {
			spedizioniNonPartite =  spedizioneDAO.dammiSpedizioniNonPartite();
	}


	protected void registraMezzo(String Targa, String Marca, String Modello, int capienza, String patente, float costo) throws OperazioneUpdateNonRiuscitaException {
		
		MezzoTrasporto mezzo = new MezzoTrasporto(Targa, Marca, Modello, capienza, patente, costo);
		mezziTrasportoDAO.registraMezzo(mezzo);
	}


	protected Object[] dammiFormatoComboBoxSupervisori() throws RisultatoNonRicavabileException, NonCiSonoCorrieriException {
		estraiTuttiCorrieri();
		ArrayList<String> formato = new ArrayList<String>();
		
		formato.add("E' un Supervisore");
		for(Corriere corriere : supervisori)
			formato.add(corriere.toString());
		
		return formato.toArray();
	}
	
	protected void creaOrdine(int indexClienti, int indexEsemplari, float costo, LocalDate dataE, LocalDate dataConsegna,
			String città, String via, String numCiv, String cap) throws RisultatoNonRicavabileException, NonPossibileCreareOrdineException {
		
		Ordine nuovoOrd = new Ordine(clientela.get(indexClienti),esemplariNonVenduti.get(indexEsemplari),costo,dataE,dataConsegna,città,via,numCiv,cap);
		
		ordineDAO.creaOrdine(nuovoOrd);
	}


	protected String dammiCodiceBarreDaEsemplariNonVenduti(int selectedIndex) {
		return esemplariNonVenduti.get(selectedIndex).getCodiceBarre();
	}
	
	protected String dammiCodiceOrdineDaNonPartiti(int selectedIndex) {
		return ordiniNonPartiti.get(selectedIndex).getCodOrdine();
	}
	

	protected void registraCliente(String cf, String nome, String cognome, LocalDate dataDiNascita, String email,
			String numCell, String radioScelta) throws NonPossibileCreareClienteException {
		
		Cliente clienteTemp = new Cliente(cf,nome,cognome,dataDiNascita,email,numCell,radioScelta);
		
		
		clienteDAO.registraCliente(clienteTemp);
		
	}
	
	protected void creaEsemplare(String cod, String colore, float costo, LocalDate garanzia, String descrizione,
			int indexMerce, int indexMagazzini) throws OperazioneUpdateNonRiuscitaException {
		
		Esemplare esemplareTemp = new Esemplare(cod, colore, costo, garanzia, descrizione, merci.get(indexMerce), magazzini.get(indexMagazzini));
		
		esemplareDAO.creaEsemplare(esemplareTemp);
	}
	
	protected void creaNuovaMerce(String nome, float peso, String marca, int anno, int indexFornitore) throws NonPossibileCreareMerceException {
		
		Merce merceTemp = new Merce(nome,peso,marca,anno,fornitori.get(indexFornitore));
		merceDAO.creaNuovaMerce(merceTemp);
		
		
	}
	
	protected ChartPanel creazioneGrafico(int anno) throws RisultatoNonRicavabileException{
		
		//Vettore in cui per ogni mese c'è il valore di ordini venduti
		int valori[] = numeroMedioOrdini(anno);
		//Semplicemente i mesi dell'anno
		String mesi[] = {"Gen","Feb","Mar","Apr","Mag","Giu","Lug","Ago","Set","Ott","Nov","Dic"};
		
		//alloco un'istanza della classe che ci permettera di riempire il grafico
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		//Mi ricavo i valori risultanti
		for(int i = 0; i < 12 ; i++)
			dataset.setValue(valori[i], "", mesi[i]);
		
		
		//Creo il grafico
		JFreeChart grafico =  ChartFactory.createBarChart3D("Report Statistico","Mesi", "Numero Ordini", dataset, PlotOrientation.VERTICAL,false,false,false);
		grafico.setTitle("Numero Medio Ordini");
		Paint colore = new Color(179, 168, 166);
		grafico.setBackgroundPaint(colore);
				
		//Gestisco lo stile del diagramma
		CategoryPlot catpot = grafico.getCategoryPlot();
		catpot.setOutlinePaint(Color.white);
		catpot.setRangeGridlinePaint(Color.white);
		catpot.setDomainGridlinePaint(Color.white);
		catpot.setOutlinePaint(Color.white);
				
		//Creo il panello contenente il grafico e lo aggiungo al panello della finestra
		ChartPanel pannelloGrafico = new ChartPanel(grafico);
		
		
		return pannelloGrafico;
		
		
	}

	
	protected void mandaMailIscrizione(String cf, String nome, String cognome, LocalDate dataDiNascita, String email,String numCell, String radioScelta) throws EmailException {
		Cliente iscritto = new Cliente (cf,nome,cognome,dataDiNascita,email,numCell,radioScelta); 
		mailSender.mandaMailaCliente(iscritto);
	}

	protected void mandaMailAssunzione(String codiceFiscale, String nome, String cognome, LocalDate dataDiNascita, String patenti,String mail, String cellulare, int contratto, int contributi, int coordinatore) throws EmailException {
		Corriere corriereAssunto;
		if(coordinatore == -1)
			corriereAssunto = new Corriere(codiceFiscale,nome,cognome,dataDiNascita,patenti,mail,cellulare,contratto,contributi,null,true);
		else
			corriereAssunto = new Corriere(codiceFiscale,nome,cognome,dataDiNascita,patenti,mail,cellulare,contratto,contributi,supervisori.get(coordinatore).getCodiceFiscale(),true);
		mailSender.mandaMailAssunzioneCorriere(corriereAssunto);
	}


	public void informaEmailOrdineStatoModificato(String ordineSelezionato, String ordineStato) throws RisultatoNonRicavabileException, EmailException {
		Ordine ordineModificato = new Ordine(ordineSelezionato, ordineStato);
		
		ordineModificato = ordineDAO.dammiIformazioni(ordineModificato);
		
		mailSender.informaStatoOrdineCambiato(ordineModificato);
		
	}


	
	


}
	



