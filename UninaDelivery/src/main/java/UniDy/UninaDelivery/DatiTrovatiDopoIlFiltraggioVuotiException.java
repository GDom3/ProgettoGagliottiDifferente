package UniDy.UninaDelivery;

public class DatiTrovatiDopoIlFiltraggioVuotiException extends UninaDeliveryException {
	private String messaggioErrore  = "Il Risultato del filtraggio ha dato un esito vuoto, ovvero non esistono spedizioni con queste caratteristiche";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
