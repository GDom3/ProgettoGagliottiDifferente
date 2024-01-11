package UniDy.UninaDelivery;

import java.sql.SQLException;

public class CreazioneStatementFallitaException extends SQLException {
	private String messaggioErrore = "Creazione Statement non riuscita";;
	private String tipoErrore = "Errore";
	
	public CreazioneStatementFallitaException() {

		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
