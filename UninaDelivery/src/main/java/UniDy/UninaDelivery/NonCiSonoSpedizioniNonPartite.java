package UniDy.UninaDelivery;

import java.sql.SQLException;

public class NonCiSonoSpedizioniNonPartite extends SQLException {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoSpedizioniNonPartite() {
		messaggioErrore = "Impossibile procedere all'aggiunta, in quanto tutte le spedizioni sono partite o concluse";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
