package UniDy.UninaDelivery;

public class NonCiSonoOrdiniAttesiException extends UninaDeliverySQLException {
	private String messaggioErrore= "Non ci sono Ordini attesi, ovvero nessun ordine non è stato consegnato o spedito già";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
