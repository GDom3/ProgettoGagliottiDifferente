package UniDy.UninaDelivery;

import java.sql.SQLException;

public class NonCiSonoSpedizioniNonPartiteException extends SQLException {
	private String messaggioErrore= "Impossibile procedere all'aggiunta, in quanto tutte le spedizioni sono partite o concluse";
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoSpedizioniNonPartiteException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
