package UniDy.UninaDelivery;

public class CampoPatentiVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo PATENTI vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
