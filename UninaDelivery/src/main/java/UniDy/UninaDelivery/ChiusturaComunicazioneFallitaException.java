package UniDy.UninaDelivery;

import java.sql.SQLException;

public class ChiusturaComunicazioneFallitaException extends SQLException {
	private String messaggioErrore = "La fase di chiustura della comunicazione con il db ha avuto un insuccesso";
	private String tipoErrore = "Errore";
	
	public ChiusturaComunicazioneFallitaException() {
	
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
