package UniDy.UninaDelivery;

import java.sql.SQLException;

public class NonCiSonoOrdiniAttesiException extends SQLException {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoOrdiniAttesiException() {
		messaggioErrore = "Non ci sono Ordini attesi, ovvero nessun ordine non è stato consegnato o spedito già";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
