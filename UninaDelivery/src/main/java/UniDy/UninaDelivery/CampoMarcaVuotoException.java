package UniDy.UninaDelivery;

public class CampoMarcaVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo MARCA vuoto, inserire un valore per poter proseguire";;
	private String tipoErrore = "Attenzione";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
