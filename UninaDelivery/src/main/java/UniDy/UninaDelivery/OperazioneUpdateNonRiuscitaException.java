package UniDy.UninaDelivery;

import java.sql.SQLException;

public class OperazioneUpdateNonRiuscitaException extends SQLException {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public OperazioneUpdateNonRiuscitaException() {
		messaggioErrore = "L'Operazione DDL o DML non è andata a buon fine";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
