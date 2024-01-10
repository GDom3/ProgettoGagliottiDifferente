package UniDy.UninaDelivery;

import java.util.ArrayList;

public interface EsemplareDAO {

	ArrayList<Esemplare> dammiEsemplariNonvenduti()
			throws RisultatoNonRicavabileException, NonCiSonoEsemplariNonVendutiException;

	void inserisciEsemplareInOrdine(Ordine ordine, Esemplare esemplare) throws NonCiSonoOrdiniAttesiException;

	void creaEsemplare(Esemplare esemplareTemp) throws OperazioneUpdateNonRiuscitaException;

}