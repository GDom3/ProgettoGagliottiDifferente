package UniDy.UninaDelivery;

public class CampoCittàVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Devi inserire la città, poichè il campo è vuoto";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
