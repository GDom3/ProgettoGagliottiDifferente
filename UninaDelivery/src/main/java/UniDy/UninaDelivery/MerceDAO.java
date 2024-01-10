package UniDy.UninaDelivery;

import java.util.ArrayList;

public interface MerceDAO {

	void creaNuovaMerce(Merce nuovaMerce) throws NonPossibileCreareMerceException;

	ArrayList<Merce> estraiMerce() throws RisultatoNonRicavabileException, NonCiSonoMerciDisponibiliException;

}