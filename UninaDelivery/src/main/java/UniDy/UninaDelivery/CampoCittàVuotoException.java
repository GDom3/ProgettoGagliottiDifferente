package UniDy.UninaDelivery;

public class CampoCittàVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Devi inserire la città";
	private String tipoErrore = "Città vuota";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
