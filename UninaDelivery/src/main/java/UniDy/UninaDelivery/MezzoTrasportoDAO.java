package UniDy.UninaDelivery;

import java.util.ArrayList;

public interface MezzoTrasportoDAO {

	ArrayList<MezzoTrasporto> estraiMezziSenzaSped()
			throws RisultatoNonRicavabileException, NonCiSonoMezziTrasportoDisponibiliException;

	void registraMezzo(MezzoTrasporto mezzo) throws OperazioneUpdateNonRiuscitaException;

}