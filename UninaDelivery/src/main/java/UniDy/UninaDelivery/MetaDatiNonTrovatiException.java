package UniDy.UninaDelivery;

import java.sql.SQLException;

public class MetaDatiNonTrovatiException extends SQLException {
	private String messaggioErrore = "Metadati non trovati, ovvero non è stato possibile ricavare come sia composto il risultato della query";
	private String tipoErrore = "Errore";
	
	public MetaDatiNonTrovatiException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}

