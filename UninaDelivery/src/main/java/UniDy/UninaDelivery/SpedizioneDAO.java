package UniDy.UninaDelivery;

import java.time.LocalDate;
import java.util.ArrayList;

public interface SpedizioneDAO {

	ArrayList<Spedizione> ricavaSpedizioni(String addonsSQL)
			throws RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException;

	ArrayList<Spedizione> ricavaSpedizioniPerCliente(String cliente)
			throws RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException;

	ArrayList<Spedizione> ricavaSpedizioniPerDateE(LocalDate dataInizio, LocalDate dataFine)
			throws RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException;

	ArrayList<Spedizione> ricavaSpedizioniPerDateConsegna(LocalDate dataInizio, LocalDate dataFine)
			throws RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException;

	ArrayList<Spedizione> ricavaSpedizioniPerUtenteEDateE(String cliente, LocalDate dataInizio, LocalDate dataFine)
			throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException,
			DatiTrovatiDopoIlFiltraggioVuotiException;

	ArrayList<Spedizione> ricavaSpedizioniPerUtenteEDateConsegna(String cliente, LocalDate dataInizio,
			LocalDate dataFine) throws RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException;

	Spedizione trovaSpedizione(String codSpedizione) throws RisultatoNonRicavabileException;

	String aggiornaStatoSpedizione(Spedizione spedizioneSelezionata) throws RisultatoNonRicavabileException;

	void creaNuovaSpedizione(Spedizione nuovaSpedizione) throws OperazioneUpdateNonRiuscitaException,
			RisultatoNonRicavabileException, NonPossibileCreareSpedizioneException;

	ArrayList<Spedizione> dammiSpedizioniNonPartite()
			throws NonCiSonoSpedizioniNonPartiteException, RisultatoNonRicavabileException;

	void inserisciOrdineInSpedizione(Spedizione spedizione, Ordine ordine) throws OperazioneUpdateNonRiuscitaException;

}