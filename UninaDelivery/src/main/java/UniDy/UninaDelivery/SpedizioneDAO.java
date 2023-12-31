package UniDy.UninaDelivery;

import java.time.LocalDate;
import java.util.ArrayList;

public interface SpedizioneDAO {

	ArrayList<Spedizione> ricavaSpedizioni(String addonsSQL) 
			throws CreazioneStatementFallitaException,
			ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException;

	ArrayList<Spedizione> ricavaSpedizioniPerCliente(String cliente) 
			throws CreazioneStatementFallitaException,
			ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException;

	ArrayList<Spedizione> ricavaSpedizioniPerDateE(LocalDate dataInizio, LocalDate dataFine)
			throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException,
			DatiTrovatiDopoIlFiltraggioVuotiException;

	ArrayList<Spedizione> ricavaSpedizioniPerDateConsegna(LocalDate dataInizio, LocalDate dataFine)
			throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException,
			DatiTrovatiDopoIlFiltraggioVuotiException;

	ArrayList<Spedizione> ricavaSpedizioniPerUtenteEDateE(String cliente, LocalDate dataInizio, LocalDate dataFine)
			throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException,
			DatiTrovatiDopoIlFiltraggioVuotiException;

	ArrayList<Spedizione> ricavaSpedizioniPerUtenteEDateConsegna(String cliente, LocalDate dataInizio, LocalDate dataFine) 
			throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException,
			RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException;

	Spedizione trovaSpedizione(String codSpedizione) 
			throws RisultatoNonRicavabileException, CreazioneStatementFallitaException, ConnessionNonRiuscitaException;

	String aggiornaStatoSpedizione(Spedizione spedizioneSelezionata) 
			throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException;

	void creaNuovaSpedizione(Spedizione nuovaSpedizione) throws OperazioneUpdateNonRiuscitaException, RisultatoNonRicavabileException, NonPossibileCreareSpedizioneException;

	ArrayList<Spedizione> dammiSpedizioniNonPartite() throws NonCiSonoSpedizioniNonPartite, RisultatoNonRicavabileException;

	void inserisciOrdineInSpedizione(Spedizione spedizione, Ordine ordine) throws OperazioneUpdateNonRiuscitaException;
	
}