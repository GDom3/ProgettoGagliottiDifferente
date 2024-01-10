package UniDy.UninaDelivery;

import java.util.ArrayList;

public interface MagazzinoDAO {

	ArrayList<Magazzino> dammiTutteMagazzini()
			throws NonCiSonoMagazziniDisponibiliException, RisultatoNonRicavabileException;

}