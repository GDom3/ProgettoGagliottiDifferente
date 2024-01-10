package UniDy.UninaDelivery;

import java.util.ArrayList;

public interface FornitoreDAO {

	ArrayList<Fornitore> dammiTuttiFornitori() throws RisultatoNonRicavabileException, NonCiSonoFornitoriException;

}