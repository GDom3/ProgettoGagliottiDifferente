package UniDy.UninaDelivery;

import java.sql.SQLException;

public class NonCiSonoOrdiniAttesiException extends SQLException {
	private String messaggioErrore= "Non ci sono Ordini attesi, ovvero nessun ordine non è stato consegnato o spedito già";
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoOrdiniAttesiException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
