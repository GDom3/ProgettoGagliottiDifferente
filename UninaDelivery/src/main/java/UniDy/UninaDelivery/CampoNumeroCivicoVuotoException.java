package UniDy.UninaDelivery;

public class CampoNumeroCivicoVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Devi inserire il numero civico, poichè il campo è vuoto";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
