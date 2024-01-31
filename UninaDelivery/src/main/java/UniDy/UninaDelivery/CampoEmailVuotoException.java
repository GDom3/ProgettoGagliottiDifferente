package UniDy.UninaDelivery;

public class CampoEmailVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo E-MAIL vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
