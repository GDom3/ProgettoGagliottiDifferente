package UniDy.UninaDelivery;

import java.util.ArrayList;

public interface CorriereDAO {

	ArrayList<Corriere> estraiCorrieriSenzaSped()
			throws RisultatoNonRicavabileException, NonCiSonoCorrieriDisponibiliException;

	ArrayList<Corriere> estraiTuttiCorrieri() throws RisultatoNonRicavabileException, NonCiSonoCorrieriException;

	void assumiCorriere(Corriere corriere) throws OperazioneUpdateNonRiuscitaException;

}