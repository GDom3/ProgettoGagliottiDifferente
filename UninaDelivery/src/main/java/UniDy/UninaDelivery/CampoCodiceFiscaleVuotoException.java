package UniDy.UninaDelivery;

public class CampoCodiceFiscaleVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo CODICE FISCALE vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
