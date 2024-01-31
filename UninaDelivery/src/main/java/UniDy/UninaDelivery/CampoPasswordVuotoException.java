package UniDy.UninaDelivery;

public class CampoPasswordVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo PASSWORD vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
