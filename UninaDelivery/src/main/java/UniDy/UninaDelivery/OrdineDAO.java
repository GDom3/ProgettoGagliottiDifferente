package UniDy.UninaDelivery;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdineDAO {

	Ordine trovaOrdine(String ordDaCercare) throws RisultatoNonRicavabileException;

	String aggiornaStatoOrdine(Ordine ordineSelezionato) throws RisultatoNonRicavabileException;

	ArrayList<Ordine> estraiOrdiniSenzaSpedOFalliti()
			throws RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException;

	ArrayList<Ordine> dammiOrdiniNonPartitiOFalliti()
			throws RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException;

	void creaOrdine(Ordine nuovoOrd) throws RisultatoNonRicavabileException, NonPossibileCreareOrdineException;

	int[] numeroMedioOrdini(int anno) throws RisultatoNonRicavabileException;

	Ordine ordineConMaggiorProdotti(int anno) throws RisultatoNonRicavabileException;

	Ordine ordineConMinorProdotti(int anno) throws RisultatoNonRicavabileException;

	Ordine dammiIformazioni(Ordine ordineModificato) throws RisultatoNonRicavabileException;

}