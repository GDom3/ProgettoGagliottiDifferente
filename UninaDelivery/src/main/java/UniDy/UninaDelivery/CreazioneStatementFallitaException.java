package UniDy.UninaDelivery;

import java.sql.SQLException;

public class CreazioneStatementFallitaException extends SQLException {
	private String messaggioErrore;
	private String tipoErrore = "Errore";
	
	public CreazioneStatementFallitaException() {
		messaggioErrore = "Creazione Statement non riuscita";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
