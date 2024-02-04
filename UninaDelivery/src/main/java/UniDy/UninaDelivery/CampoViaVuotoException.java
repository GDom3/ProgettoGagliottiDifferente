package UniDy.UninaDelivery;

public class CampoViaVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Devi inserire la via/viale, poichè il campo è vuoto";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
