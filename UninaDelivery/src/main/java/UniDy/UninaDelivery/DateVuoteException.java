package UniDy.UninaDelivery;

public class DateVuoteException extends UninaDeliveryException {
	private String messaggioErrore = "Devi inserire anche le date";
	private String tipoErrore = "Date vuote";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
