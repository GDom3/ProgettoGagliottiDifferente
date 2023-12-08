package UniDy.UninaDelivery;

import java.sql.SQLException;

public class MetaDatiNonTrovatiException extends SQLException {
	private String messaggioErrore;
	private String tipoErrore = "Errore";
	
	public MetaDatiNonTrovatiException() {
		messaggioErrore = "Metadati non trovati, ovvero non è stato possibile ricavare come sia composto il risultato della query";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}

