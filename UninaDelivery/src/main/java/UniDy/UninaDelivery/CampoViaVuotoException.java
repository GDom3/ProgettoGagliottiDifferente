package UniDy.UninaDelivery;

public class CampoViaVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Devi inserire la via/viale";
	private String tipoErrore = "Via/viale vuota";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
