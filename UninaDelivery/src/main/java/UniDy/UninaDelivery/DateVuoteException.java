package UniDy.UninaDelivery;

public class DateVuoteException extends UninaDeliveryException {
	private String messaggioErrore = "Devi inserire anche le date, il campo è vuoto";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
