package UniDy.UninaDelivery;

public class PasswordErrataException extends UninaDeliveryException {
	private String messaggioErrore= "Password inserita è errata";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
