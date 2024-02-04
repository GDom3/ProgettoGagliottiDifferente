package UniDy.UninaDelivery;

public class CampoCapVuotoException extends UninaDeliveryException {
	private String messaggioErrore  = "Devi inserire il CAP, poichè il campo è vuoto";;
	private String tipoErrore = "Attenzione";
				 
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
