package UniDy.UninaDelivery;

import java.sql.SQLException;

public class CreazioneStatementFallitaException extends UninaDeliverySQLException {
	private String messaggioErrore = "Creazione Statement non riuscita";;
	private String tipoErrore = "Errore";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
