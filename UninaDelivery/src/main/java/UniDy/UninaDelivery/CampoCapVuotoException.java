package UniDy.UninaDelivery;

public class CampoCapVuotoException extends UninaDeliveryException {
	private String messaggioErrore  = "Devi inserire il CAP";;
	private String tipoErrore = "CAP vuoto";
				 
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
